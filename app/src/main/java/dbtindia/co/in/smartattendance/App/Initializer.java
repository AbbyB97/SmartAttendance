package dbtindia.co.in.smartattendance.App;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

import dbtindia.co.in.smartattendance.DataModels.Professor;
import dbtindia.co.in.smartattendance.DataModels.Student;
import dbtindia.co.in.smartattendance.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Abhi on 4/1/2017.
 */

public class Initializer extends Application {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        ParseObject.registerSubclass(Student.class);
        ParseObject.registerSubclass(Professor.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.ApplicationId))
                .clientKey(getResources().getString(R.string.AndroidClientKey))
                .server(getResources().getString(R.string.ServerAddress))
                .build());
//webpro server app
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.saveInBackground();
        Log.i(TAG, "Application---->>>>: Application Initialized");

    }
}
