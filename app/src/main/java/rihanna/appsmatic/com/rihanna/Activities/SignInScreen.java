package rihanna.appsmatic.com.rihanna.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.RegResponse;
import rihanna.appsmatic.com.rihanna.API.Models.verifications.VerificationCode;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

public class SignInScreen extends AppCompatActivity {

    private EditText user,pass;
    private TextView forgetPassBtn,createNewAccount,login;
    private CheckBox remeberMe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Setup items
        forgetPassBtn=(TextView)findViewById(R.id.forgetpassbtn);
        createNewAccount=(TextView)findViewById(R.id.create_account_btn);
        login=(TextView)findViewById(R.id.login_btn);
        user=(EditText)findViewById(R.id.email_input_login);
        pass=(EditText)findViewById(R.id.password_input_login);
        remeberMe=(CheckBox)findViewById(R.id.remeberlogincheck);



        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            forgetPassBtn.setBackgroundResource(R.drawable.ripple);
            createNewAccount.setBackgroundResource(R.drawable.ripple);
        }



        //login btn action
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                login.clearAnimation();
                login.setAnimation(anim);

                if (user.getText().toString().length() == 0) {
                    user.setError(getResources().getString(R.string.loginvalemail));

                } else if (pass.getText().toString().length() == 0) {
                    pass.setError(getResources().getString(R.string.loginvalpassword));

                } else {


                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(SignInScreen.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.pleasewait));
                    mProgressDialog.show();

                    HashMap loginData = new HashMap();
                    loginData.put("email", user.getText().toString() + "");
                    loginData.put("password", pass.getText().toString() + "");


                    if(remeberMe.isChecked()){
                        SaveSharedPreference.setUserName(SignInScreen.this,loginData.get("email").toString()+"",loginData.get("password").toString()+"");
                    }else {
                        SaveSharedPreference.setUserName(SignInScreen.this,"","");
                    }




                    //request login from server
                    Generator.createService(RihannaAPI.class).login(loginData).enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                            if (response.isSuccessful()) {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                if (response.body().getCustomers() != null) {
                                    SaveSharedPreference.setCustomerId(SignInScreen.this,response.body().getCustomers().get(0).getId());
                                    SaveSharedPreference.setCustomerInfo(SignInScreen.this,response.body());
                                    startActivity(new Intent(SignInScreen.this, Home.class));
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.loginsucsess), Toast.LENGTH_LONG).show();
                                    SignInScreen.this.finish();
                                    Log.e("Done : ", response.body().getCustomers().get(0).getId() + "");


                                } else if (response.body().getErrors().getAccount() != null) {
                                    //Show Error
                                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(SignInScreen.this);
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

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                Toast.makeText(SignInScreen.this, "not success from sign in", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {

                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            Toast.makeText(SignInScreen.this, t.getMessage() + " from sign in", Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }
        });





        //forget password button
        forgetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                forgetPassBtn.clearAnimation();
                forgetPassBtn.setAnimation(anim);
                //Loading Dialog
                Pattern p = Pattern.compile("^(.+)@(.+)$");
                Matcher m = p.matcher(user.getText().toString());
                if (user.getText().length() == 0 || !m.matches()) {
                    user.setError(getResources().getString(R.string.notvalidemail));
                } else {

                    final ProgressDialog mProgressDialog = new ProgressDialog(SignInScreen.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.pleasewait));
                    mProgressDialog.show();
                    Generator.createService(RihannaAPI.class).retrivePassword(user.getText().toString()).enqueue(new Callback<VerificationCode>() {
                        @Override
                        public void onResponse(Call<VerificationCode> call, Response<VerificationCode> response) {

                            if (response.isSuccessful()) {
                                if (response.body().getErrorMessage().equals("") || response.body().getErrorMessage() == null) {
                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.newpasswillsend ) + " ", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), response.body().getErrorMessage() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Not success from retrieve password", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<VerificationCode> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failure from retrieve password" + t.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });



        //new account button
        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                createNewAccount.clearAnimation();
                createNewAccount.setAnimation(anim);
                startActivity(new Intent(SignInScreen.this, SignUpScreen.class));
            }
        });






    }
}
