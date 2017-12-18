package rihanna.appsmatic.com.rihanna.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.Customer;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.RegResponse;
import rihanna.appsmatic.com.rihanna.API.Models.Registration.PostNewCustomer;
import rihanna.appsmatic.com.rihanna.API.Models.Registration.RCustomer;
import rihanna.appsmatic.com.rihanna.API.Models.verifications.VerificationCode;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.R;

public class ActivateAccountScreen extends AppCompatActivity {
    private TextView verifyMobile;
    private EditText codeInput;
    private PostNewCustomer postNewCustomer;
    private RCustomer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activate_account_screen);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        verifyMobile=(TextView)findViewById(R.id.active_btn);
        codeInput=(EditText)findViewById(R.id.code_input_active);


        Type type = new TypeToken<RCustomer>() {}.getType();
        Gson gson = new Gson();
        customer= gson.fromJson(getIntent().getStringExtra("regData"), type);



        //Verify phone number
        verifyMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(ActivateAccountScreen.this, R.anim.alpha);
                verifyMobile.clearAnimation();
                verifyMobile.setAnimation(anim);
                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(ActivateAccountScreen.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.pleasewait));
                    mProgressDialog.show();
                if(codeInput.getText().toString().length()==0){
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    codeInput.setError(getResources().getString(R.string.insertvercode));
                }else {


                   customer.setVerificationcode(codeInput.getText().toString()+"");
                    postNewCustomer=new PostNewCustomer();
                    postNewCustomer.setCustomer(customer);

                    Generator.createService(RihannaAPI.class).regesterNewCustomer(postNewCustomer).enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {

                            if (response.isSuccessful()) {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                if (response.body().getCustomers() != null) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.regsuccsess), Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ActivateAccountScreen.this, SignInScreen.class));
                                    ActivateAccountScreen.this.finish();
                                } else if (response.body().getErrors() != null) {
                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.faild) + " " + response.body().getErrors().getAccount() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "Not success from SignUp", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.faild) + " " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });












                }







                }



        });


    }
}
