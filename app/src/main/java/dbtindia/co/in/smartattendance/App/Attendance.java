package dbtindia.co.in.smartattendance.App;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        Date date = new Date();
        SimpleDateFormat f1 = new SimpleDateFormat("MMMM");
        final String month_name = f1.format(date);

        SimpleDateFormat f2 = new SimpleDateFormat("dd/yyyy");
        final String strDate = f2.format(date);

        ParseQuery<Student> m = ParseQuery.getQuery(Student.class);
        m.getInBackground(selectedUser, new GetCallback<Student>() {
            @Override
            public void done(final Student mt, ParseException e) {
                if (mt != null) {
                    JSONObject main = mt.getJSONObject("Stud_Attendance");
                    JSONObject month = new JSONObject();

                    if (main != null && main.has(month_name)) {
                        try {
                            month = main.getJSONObject(month_name);
                            month.put(strDate, attendstat);
                            main.put(month_name, month);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        mt.put("Stud_Attendance", main);
                        mt.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                Log.i(TAG, "done: New Entry Added");
                            }
                        });

                        //add attendance entry to month obj of main attendance obj.

                        Log.i(TAG, "Month Check : ");
                    } else {
                        //add new month obj;
                        JSONObject newMonth = new JSONObject();
                        try {
                            newMonth.put(strDate, attendstat);
                            main.put(month_name, newMonth);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                        mt.put("Stud_Attendance", main);
                        mt.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                Log.i(TAG, "done: New Entry Added");
                            }
                        });

                        Log.i(TAG, "Month Check !: ");

                    }

                    Log.i(TAG, "DATACHECK: data is present");
                } else {

                }
            }
        });
        return markedstat;
    }
}
