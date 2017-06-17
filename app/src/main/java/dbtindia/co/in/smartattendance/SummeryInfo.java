package dbtindia.co.in.smartattendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import dbtindia.co.in.smartattendance.UI.PieChartView;
import de.hdodenhof.circleimageview.CircleImageView;

public class SummeryInfo extends AppCompatActivity implements View.OnClickListener {

    protected CircleImageView studPagePicview;
    protected TextView studpgThesisTitle;
    protected PieChartView pieChart;
    protected LinearLayout main;
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.summery_info);
        initView();
        Bundle b = getIntent().getExtras();
        if (!b.isEmpty()) {
            String id = b.getString("SuID");
            Log.i(TAG, "data get: = " + id);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.stud_page_picview) {

        } else if (view.getId() == R.id.studpg_thesis_title) {

        } else if (view.getId() == R.id.pie_chart) {

        } else if (view.getId() == R.id.main) {

        }
    }

    private void initView() {
        studPagePicview = (CircleImageView) findViewById(R.id.stud_page_picview);
        studPagePicview.setOnClickListener(SummeryInfo.this);
        studpgThesisTitle = (TextView) findViewById(R.id.studpg_thesis_title);
        studpgThesisTitle.setOnClickListener(SummeryInfo.this);
        pieChart = (PieChartView) findViewById(R.id.pie_chart);
        pieChart.setOnClickListener(SummeryInfo.this);
        main = (LinearLayout) findViewById(R.id.main);
        main.setOnClickListener(SummeryInfo.this);
    }
}
