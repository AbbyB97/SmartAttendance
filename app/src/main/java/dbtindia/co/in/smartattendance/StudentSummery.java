package dbtindia.co.in.smartattendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import dbtindia.co.in.smartattendance.Adapters.SummeryAdapter;
import dbtindia.co.in.smartattendance.DataModels.Student;
import dbtindia.co.in.smartattendance.UI.ProgressWheel;

public class StudentSummery extends AppCompatActivity {
    ParseQuery<Student> pqr;
    List<Student> mlist;
    SummeryAdapter detailStudlist;
    private String TAG = getClass().getSimpleName();
    private ProgressWheel pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_summery);

        RecyclerView stud_detail_recycle = (RecyclerView) findViewById(R.id.thesis_list_recycler);
        pwd=(ProgressWheel)findViewById(R.id.progressWheel);
        pwd.spin();
        mlist = new ArrayList<Student>();

        detailStudlist = new SummeryAdapter(mlist, this);

        stud_detail_recycle.setLayoutManager(new LinearLayoutManager(this));
        stud_detail_recycle.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        stud_detail_recycle.setAdapter(detailStudlist);
        prepareDataList();
    }

    private void prepareDataList() {
        pqr = new ParseQuery<Student>(Student.class);
        pqr.findInBackground(new FindCallback<Student>() {
            @Override
            public void done(List<Student> stdl, ParseException e) {
                if (e == null) {
                    for (Student i : stdl) {
                        mlist.add(i);
                        detailStudlist.notifyDataSetChanged();
                        pwd.stopSpinning();
                        Log.i(TAG, "done: Data Loaded  " + i.getStudFName() + "  " + i.getStudEmail());
                    }
                } else {
                    Toast.makeText(StudentSummery.this, "Data Not Present", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
