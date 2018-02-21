package rihanna.appsmatic.com.rihanna.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.RegResponse;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.NotificationsService;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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




        launchTestService();

        //Check location permissions for Marshmallow
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        }



        //Splash Duration
        Thread timer = new Thread() {
            public void run() {
                try {

                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if(SaveSharedPreference.getUserName(Splash.this).equals("")&&SaveSharedPreference.getUserPassword(Splash.this).equals("")){
                        Intent i = new Intent(Splash.this, SignInScreen.class);
                        startActivity(i);
                        Splash.this.finish();
                    }else {

                        HashMap loginData = new HashMap();
                        loginData.put("email", SaveSharedPreference.getUserName(Splash.this));
                        loginData.put("password",SaveSharedPreference.getUserPassword(Splash.this));
                        //request login from server
                        Generator.createService(RihannaAPI.class).login(loginData).enqueue(new Callback<RegResponse>() {
                            @Override
                            public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getCustomers() != null) {
                                        SaveSharedPreference.setCustomerId(Splash.this,response.body().getCustomers().get(0).getId());
                                        SaveSharedPreference.setCustomerInfo(Splash.this,response.body());
                                        startActivity(new Intent(Splash.this, SliderSplash.class));
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.loginsucsess), Toast.LENGTH_LONG).show();
                                        Splash.this.finish();
                                        Log.e("Done : ", response.body().getCustomers().get(0).getId() + "");


                                    } else if (response.body().getErrors().getAccount() != null) {

                                        //Logout and clear all data
                                        SaveSharedPreference.setCustomerId(Splash.this,"");
                                        SaveSharedPreference.setCustomerInfo(Splash.this, null);
                                        SaveSharedPreference.setUserName(Splash.this, "", "");
                                        startActivity(new Intent(Splash.this, Splash.class));
                                        Splash.this.finish();


                                        //Show Error
                                        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(Splash.this);
                                        dialogBuilder
                                                .withTitle(getResources().getString(R.string.app_name))
                                                .withDialogColor(R.color.colorPrimary)
                                                .withTitleColor("#FFFFFF")
                                                .withDuration(700)                                          //def
                                                .withEffect(Effectstype.RotateBottom)
                                                .withMessage(response.body().getErrors().getAccount() + "")
                                                .show();
                                    }


                                } else {


                                    Toast.makeText(Splash.this, "not success from sign in", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<RegResponse> call, Throwable t) {

                                //Initialize Done Dialog
                                final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(Splash.this);
                                dialogBuildercard
                                        .withDuration(700)//def
                                        .withEffect(Effectstype.Fall)
                                        .withIcon(getResources().getDrawable(R.drawable.logo))
                                        .withDialogColor(Color.BLACK)
                                        .withTitleColor(Color.WHITE)
                                        .withMessage(getResources().getString(R.string.connectionerr))
                                        .withTitle(getResources().getString(R.string.connectionerror))
                                        .isCancelableOnTouchOutside(false)
                                        .withButton1Text(getResources().getString(R.string.dissmis))
                                        .setButton1Click(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                dialogBuildercard.dismiss();
                                                Splash.this.finish();
                                            }
                                        }).show();
                            }
                        });


                    }
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

    public void launchTestService() {
        // Construct our Intent specifying the Service
        Intent i = new Intent(this, NotificationsService.class);
        // Add extras to the bundle
        i.putExtra("foo", "bar");
        // Start the service
        startService(i);
    }



}
