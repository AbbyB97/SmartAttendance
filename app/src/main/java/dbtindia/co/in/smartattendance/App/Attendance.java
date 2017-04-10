package dbtindia.co.in.smartattendance.App;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dbtindia.co.in.smartattendance.DataModels.Student;

/**
 * Created by Abhi on 4/2/2017.
 */

public class Attendance {
    private static final String TAG = "Attendance";


    public Attendance() {
    }

    public boolean markAttendance(final String selectedUser, final String attendstat) {
        boolean markedstat = false;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        final String month_name = month_date.format(cal.getTime());

        ParseQuery<Student> m = ParseQuery.getQuery(Student.class);
        m.getInBackground(selectedUser, new GetCallback<Student>() {
            @Override
            public void done(Student mt, ParseException e) {
                if (mt != null) {
                    if (!mt.has(month_name)) {
//                        if data has current month ,save todays attendance
                        Log.i(TAG, "done: Object Not Has Month");
                        try {
                            JSONObject jom = new JSONObject();
                            JSONObject jomo = new JSONObject();
                            jom.put(month_name + 1, attendstat);
                            jomo.put(month_name, jom);
                            mt.put("Stud_Attendance", jomo);
                            mt.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Log.i(TAG, "done: New Object Added");
                                }
                            });
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    } else {
//                        if data doesn,t have the running month then create new json object and ad to main object
                        Log.i(TAG, "done: Object Has Month");

                    }
                    Log.i(TAG, "done: Object Attendace Recieved");
                } else {
                    Log.i(TAG, "done: Object Attendace Not Recieved");
                    e.printStackTrace();
                }
            }
        });
        return markedstat;
    }
}
