package dbtindia.co.in.smartattendance.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.List;

import dbtindia.co.in.smartattendance.App.Attendance;
import dbtindia.co.in.smartattendance.DataModels.Student;
import dbtindia.co.in.smartattendance.R;

/**
 * Created by Abhi on 4/1/2017.
 */

public class TakeAttendanceAdapter extends RecyclerView.Adapter<TakeAttendanceAdapter.StudViewHolder> {
    String TAG = getClass().getSimpleName();
    Context ct;

    List<Student> studArray;

    public TakeAttendanceAdapter(List<Student> studArray, Context context) {
        this.studArray = studArray;
        this.ct = context;
    }

    @Override
    public StudViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_take_attendance, parent, false);
        return new StudViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StudViewHolder hld, int position) {
        final Student s = studArray.get(position);
        if (s != null) {
            hld.tv.setText(s.getStudFName() + " " + s.getStudLName());
//            if (s.getParseFile("Stud_Prof_Pic").isDataAvailable()) {
                s.getStudProfile().getDataInBackground(new GetDataCallback() {
                    public void done(byte[] data, ParseException e) {
                        if (e == null) {
                            // data has the bytes for the resume
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            hld.iv.setImageBitmap(bitmap);
                        } else {
                            e.printStackTrace();
                            // something went wrong
                        }
                    }
                });
            /*} else {
                hld.iv.setImageResource(R.drawable.dbt_rounded_logo);
            }*/
        }
        Log.i(TAG, "onBindViewHolder: Data Bound  " + s.getStudFName() + "  " + s.getStudEmail());

        hld.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ct, "TextViw from viewholder is clicked " + hld.tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        hld.markgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Absent_rb:
                        //add mark attendance function for clicked student view block and pass absent as parameter
                        hld.a.setClickable(false);
                        hld.p.setClickable(false);
                        hld.ab.markAttendance(s.getObjectId(), "A");
                        Toast.makeText(ct, "Marked Absent", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Present_rb:
                        //add mark attendance function for clicked student view block and pass absent as parameter
                        hld.a.setClickable(false);
                        hld.p.setClickable(false);
                        hld.ab.markAttendance(s.getObjectId(), "P");
                        Toast.makeText(ct, "Marked Present", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        hld.markgrp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                hld.a.setClickable(true);
                hld.p.setClickable(true);
                Toast.makeText(ct, "Edit Attendance", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return studArray.size();
    }

    public class StudViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ImageView iv;
        public RadioButton p, a;
        Attendance ab;
        public RadioGroup markgrp;

        public StudViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.c_name);
            iv = (ImageView) itemView.findViewById(R.id.c_iv);
            p = (RadioButton) itemView.findViewById(R.id.Present_rb);
            a = (RadioButton) itemView.findViewById(R.id.Absent_rb);
            markgrp = (RadioGroup) itemView.findViewById(R.id.Attendance_rb_grp);
            ab = new Attendance();

        }

    }
}
