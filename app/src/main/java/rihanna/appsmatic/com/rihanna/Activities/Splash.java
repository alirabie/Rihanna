package rihanna.appsmatic.com.rihanna.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Locale;

import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        setLang(R.layout.activity_splach);
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

                        Intent i = new Intent(Splash.this, SliderSplash.class);
                        startActivity(i);
                        Splash.this.finish();
                    }
            }
        };
        timer.start();

    }

    // Change language method
    public  void setLang(int layout){
        String languageToLoad = SaveSharedPreference.getLangId(this);
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(layout);
    }
}
