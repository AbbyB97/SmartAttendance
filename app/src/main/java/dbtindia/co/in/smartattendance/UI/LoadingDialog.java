package dbtindia.co.in.smartattendance.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Abhi on 4/1/2017.
 */

public class LoadingDialog {
    Context ctx;
    ProgressDialog progressDialog;

    public LoadingDialog(Context ctx) {
        this.ctx = ctx;
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void showProgressDialog(String title, String Message, Context ctxv) throws NullPointerException {

        progressDialog = new ProgressDialog(ctxv);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.setTitle(title);
        progressDialog.setMessage(Message);
        progressDialog.show();
    }

    public void stopProgressDilaog() {
        progressDialog.cancel();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
