package rihanna.appsmatic.com.rihanna.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.github.badoualy.datepicker.DatePickerTimeline;
import com.github.badoualy.datepicker.MonthView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

import rihanna.appsmatic.com.rihanna.GPS.GPSTracker;
import rihanna.appsmatic.com.rihanna.R;

public class DateTimeScreen extends AppCompatActivity {


    TabLayout timetabs;
    TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datetime);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        next=(TextView)findViewById(R.id.date_time_next_btn);

        //fill time tabs
        timetabs=(TabLayout)findViewById(R.id.time_tabs);
        for(int i=0;i<4;i++) {
            timetabs.addTab(timetabs.newTab().setText("12:30AM"));
        }






        //time selection
        timetabs.setSelectedTabIndicatorColor(Color.BLACK);
        timetabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               Toast.makeText(getApplicationContext(),tab.getText().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });














        //Date Packer
        DatePickerTimeline timeline = (DatePickerTimeline) findViewById(R.id.timeline);
        timeline.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
            @Override
            public CharSequence getLabel(Calendar calendar, int index) {
                return Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) % 2000);
            }
        });

        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                Toast.makeText(getApplicationContext(),day+" / "+month+" / "+year,Toast.LENGTH_LONG).show();
            }
        });

        timeline.setFirstVisibleDate(2016, Calendar.JULY, 19);
        timeline.setLastVisibleDate(2020, Calendar.JULY, 19);
        //timeline.setFollowScroll(false);






        //next
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(DateTimeScreen.this, R.anim.alpha);
                next.clearAnimation();
                next.setAnimation(anim);
                startActivity(new Intent(DateTimeScreen.this,CustomerLocation.class));
            }
        });

    }







}
