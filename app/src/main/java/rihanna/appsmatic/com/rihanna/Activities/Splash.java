package rihanna.appsmatic.com.rihanna.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import rihanna.appsmatic.com.rihanna.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        Window window = this.getWindow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }



        //Splash Duration
        Thread timer = new Thread() {
            public void run() {
                try {

                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                        Intent i = new Intent(Splash.this, Home.class);
                        startActivity(i);
                        Splash.this.finish();
                    }
            }
        };
        timer.start();

    }
}
