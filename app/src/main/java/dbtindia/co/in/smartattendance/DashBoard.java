package dbtindia.co.in.smartattendance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import dbtindia.co.in.smartattendance.App.Preferences;
import dbtindia.co.in.smartattendance.DataModels.Professor;
import dbtindia.co.in.smartattendance.DataModels.Student;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    private String TAG = getClass().getSimpleName();
    protected CircleImageView tImagec;
    protected TextView titleMain, titleSmall, blankTv, studAttendTv, attendPanTv, studThesTv;
    protected Toolbar dashTb;
    protected Preferences p;
    protected CardView studAttendC, attendPanC, studThesC;
    protected LinearLayout contentTask;
    protected RelativeLayout dashboard;
    String utype, uemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.dash_board);
        //add email verfied cross check logic from sharedpreference.
        initView();

        ParseUser cu = ParseUser.getCurrentUser();
        p = new Preferences(this);
        uemail = p.getUserType();
        if (uemail != null) {
            try {
                tImagec.setImageBitmap(setCurrentUserProfile(utype, uemail));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.getInBackground("HZgpsN1ojr", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                    JSONObject jomain = new JSONObject();
                    JSONObject Jan = new JSONObject();
                    try {
                        Jan.put("jan 1", "P");
                        Jan.put("jan 2", "P");
                        Jan.put("jan 3", "A");
                        Jan.put("jan 4", "P");
                        Jan.put("jan 5", "A");
                        Jan.put("jan 6", "P");
                        jomain.put("January", Jan);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
//                    ParseObject p= ParseObject.create("Student");
                    object.put("Stud_Attendance", jomain);
//                    p.saveInBackground();
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.i(TAG, "done: Json Nested is Stored Successfully");
                        }
                    });
                } else {
                    // something went wrong
                }
            }
        });*/
    }

    private Bitmap setCurrentUserProfile(String utype, String uemail) throws ParseException {
        Bitmap btmo = null;
        byte[] btm;
        switch (utype) {
            case "Professor":
                btm = new Professor().getProfPic().getData();
                btmo = BitmapFactory.decodeByteArray(btm, 0, btm.length);
                break;
            case "Student":
                btm = new Student().getStudProfile().getData();
                btmo = BitmapFactory.decodeByteArray(btm, 0, btm.length);
                break;
        }
        return btmo;
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.t_imagec) {
            //profile view
        } else if (view.getId() == R.id.title_main) {
            //profile view
        } else if (view.getId() == R.id.title_small) {
            //profile view
        } else if (view.getId() == R.id.stud_attend_c) {
            //goto Attendance page
            startActivity(new Intent(DashBoard.this, TakeAttendance.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (view.getId() == R.id.attend_pan_c) {
            //attendance detail page
            startActivity(new Intent(DashBoard.this, DetailAttendance.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (view.getId() == R.id.stud_thes_c) {
            //student thesis detail page
            startActivity(new Intent(DashBoard.this, StudentSummery.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
    }

    private void initView() {
        tImagec = (CircleImageView) findViewById(R.id.t_imagec);
        tImagec.setOnClickListener(DashBoard.this);
        titleMain = (TextView) findViewById(R.id.title_main);
        titleMain.setOnClickListener(DashBoard.this);
        titleSmall = (TextView) findViewById(R.id.title_small);
        titleSmall.setOnClickListener(DashBoard.this);
        dashTb = (Toolbar) findViewById(R.id.dash_tb);
        blankTv = (TextView) findViewById(R.id.blank_tv);
        studAttendTv = (TextView) findViewById(R.id.stud_attend_tv);
        studAttendC = (CardView) findViewById(R.id.stud_attend_c);
        studAttendC.setOnClickListener(DashBoard.this);
        attendPanTv = (TextView) findViewById(R.id.attend_pan_tv);
        attendPanC = (CardView) findViewById(R.id.attend_pan_c);
        attendPanC.setOnClickListener(DashBoard.this);
        studThesTv = (TextView) findViewById(R.id.stud_thes_tv);
        studThesC = (CardView) findViewById(R.id.stud_thes_c);
        studThesC.setOnClickListener(DashBoard.this);
        contentTask = (LinearLayout) findViewById(R.id.content_task);
        dashboard = (RelativeLayout) findViewById(R.id.dashboard);
    }

    private void setUserImage() {
        ImageView imgUser = (ImageView) findViewById(R.id.t_imagec);
        ParseUser currentUser = ParseUser.getCurrentUser();
        try {
            ParseFile img = currentUser.getParseFile("userImage");
            Bitmap bmp = BitmapFactory.decodeStream(img.getDataStream());
            imgUser.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
