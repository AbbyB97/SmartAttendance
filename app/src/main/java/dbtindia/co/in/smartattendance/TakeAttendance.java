package dbtindia.co.in.smartattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import dbtindia.co.in.smartattendance.Adapters.TakeAttendanceAdapter;
import dbtindia.co.in.smartattendance.DataModels.Student;
import dbtindia.co.in.smartattendance.UI.ProgressWheel;

public class TakeAttendance extends AppCompatActivity {
    protected RecyclerView recyclerView;
    ParseQuery<Student> pqr;
    List<Student> mlist;
    TakeAttendanceAdapter stda;
    private String TAG = getClass().getSimpleName();
    private ProgressWheel pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.take_attendance);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        pwd = (ProgressWheel) findViewById(R.id.progressWheel);
        pwd.spin();
        mlist = new ArrayList<Student>();

        stda = new TakeAttendanceAdapter(mlist, this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(stda);
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
                        stda.notifyDataSetChanged();
                        pwd.stopSpinning();
                        Log.i(TAG, "done: Data Loaded  " + i.getStudFName() + "  " + i.getStudEmail());
                    }
                } else {
                    Toast.makeText(TakeAttendance.this, "Data Not Present", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
