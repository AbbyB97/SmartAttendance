package dbtindia.co.in.smartattendance.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.List;

import dbtindia.co.in.smartattendance.DataModels.Student;
import dbtindia.co.in.smartattendance.R;
import dbtindia.co.in.smartattendance.SummeryInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Abhi on 4/1/2017.
 */

public class SummeryAdapter extends RecyclerView.Adapter<SummeryAdapter.ThesisViwHolder> {
    List<Student> studlist;
    Context ctx;
    Student selectedUser;
        
    private String TAG = getClass().getSimpleName();

    public SummeryAdapter(List<Student> studlist, Context ctx) {
        this.studlist = studlist;
        this.ctx = ctx;
    }

    @Override
    public ThesisViwHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_thesis_detail, parent, false);
        return new ThesisViwHolder(view);
    }

    @Override
    public void onBindViewHolder(final ThesisViwHolder h, int position) {
        selectedUser = studlist.get(position);
        if (selectedUser != null && selectedUser.getStudProfile().isDataAvailable()) {
//            ParseFile p= new ParseFile()
            h.studtv.setText(selectedUser.getStudFName() + " " + selectedUser.getStudLName());
            selectedUser.getStudProfile().getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        // data has the bytes for the resume
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        h.tcv.setImageBitmap(bitmap);
                    } else {
                        // something went wrong
                    }
                }
            });
            Log.i(TAG, "onBindViewHolder: data retrived " + selectedUser.getStudFName() + " " + selectedUser.getStudLName());
        }
        Log.i(TAG, "onBindViewHolder: Data Summery Activity");
    }

    @Override
    public int getItemCount() {
        return studlist.size();
    }

    public class ThesisViwHolder extends RecyclerView.ViewHolder {
        CircleImageView tcv;
        TextView studtv, thesis;

        public ThesisViwHolder(View itemView) {
            super(itemView);
            studtv = (TextView) itemView.findViewById(R.id.thesis_stud_name);
            thesis = (TextView) itemView.findViewById(R.id.thesis_stud_thesis);
            tcv = (CircleImageView) itemView.findViewById(R.id.thesi_prof_cv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//
                }
            });

        }
    }
}
