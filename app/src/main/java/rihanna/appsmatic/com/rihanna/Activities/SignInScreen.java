package rihanna.appsmatic.com.rihanna.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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

public class SignInScreen extends AppCompatActivity  {

    private EditText user, pass;
    private TextView forgetPassBtn, createNewAccount, login;
    private CheckBox remeberMe;
    private LoginButton fbLoginButton;
    private TwitterLoginButton twitterLoginButton;
    private SignInButton signInButton;
    private CallbackManager callbackManager;
    private final String CONSUMER_KEY = "Cx1OokZ9Zf7CICMcjU6rfdUSh";
    private final String CONSUMER_SECRET = "ZzCgAxMuQf1XoOlnIngs5VL4o4WtGve6hvyw3tPpBKEcppk6i9";
    private GoogleApiClient mGoogleApiClient;
    private AccessTokenTracker accessTokenTracker;
    private ImageView fblogin,twitterlogin,gmailLogin;




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


        //Google sign in API Configurations
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Twitter sign in API Configurations
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.INFO))
                .twitterAuthConfig(new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);


        //Facebook sign in API Configurations
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };


        //printKeyHash();


        //Setup items
        forgetPassBtn = (TextView) findViewById(R.id.forgetpassbtn);
        createNewAccount = (TextView) findViewById(R.id.create_account_btn);
        login = (TextView) findViewById(R.id.login_btn);
        user = (EditText) findViewById(R.id.email_input_login);
        pass = (EditText) findViewById(R.id.password_input_login);
        remeberMe = (CheckBox) findViewById(R.id.remeberlogincheck);
        fbLoginButton = (LoginButton) findViewById(R.id.facebook_login_btn);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_btn);
        signInButton = (SignInButton) findViewById(R.id.gmail_login_btn);

       //Mask of buttons actions for social media login
        fblogin=(ImageView)findViewById(R.id.fbloginbutton);
        twitterlogin=(ImageView)findViewById(R.id.twitterloginbutton);
        gmailLogin=(ImageView)findViewById(R.id.gmailloginbutton);


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


                    if (remeberMe.isChecked()) {
                        SaveSharedPreference.setUserName(SignInScreen.this, loginData.get("email").toString() + "", loginData.get("password").toString() + "");
                    } else {
                        SaveSharedPreference.setUserName(SignInScreen.this, "", "");
                    }


                    //request login from server
                    Generator.createService(RihannaAPI.class).login(loginData).enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                            if (response.isSuccessful()) {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                if (response.body().getCustomers() != null) {
                                    SaveSharedPreference.setCustomerId(SignInScreen.this, response.body().getCustomers().get(0).getId());
                                    SaveSharedPreference.setCustomerInfo(SignInScreen.this, response.body());
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
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.newpasswillsend) + " ", Toast.LENGTH_LONG).show();

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


        //Facebook Login Button
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(SignInScreen.this, "Success" + "Facebook Login  :  " + loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(SignInScreen.this, "Facebook Login Canceled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(SignInScreen.this, "Facebook Login Error :  " + e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERRRRRR", e.getMessage());
            }
        });
        //Facebook mask button
        fblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                fblogin.clearAnimation();
                fblogin.setAnimation(anim);
                fbLoginButton.performClick();
            }
        });







        //Twitter Login Button
        twitterLoginButton.setCallback(new com.twitter.sdk.android.core.Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(SignInScreen.this, "Success" + "Twitter Login  :  " + result.data.getUserId() + " " + result.data.getUserName(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(SignInScreen.this, "Twitter Login Error : " + exception.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        //Twitter Mask Button
        twitterlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                twitterlogin.clearAnimation();
                twitterlogin.setAnimation(anim);
                twitterLoginButton.performClick();
            }
        });


        //Gmail login button
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        //Gmail Mask Button
        gmailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                gmailLogin.clearAnimation();
                gmailLogin.setAnimation(anim);
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, 9001);
            }
        });

    }








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Get Facebook login results
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //Get twitter login results
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);



        //Get Gmail login results
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();

                Toast.makeText(SignInScreen.this, "Success" + "Gmail Login  :  " + acct.getId().toString()+" "+acct.getDisplayName(), Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(SignInScreen.this, "Error Gmail Login ", Toast.LENGTH_LONG).show();
            }
        }
    }







    //Get KeyHash
    public void printKeyHash() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("rihanna.appsmatic.com.rihanna", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash:", e.toString());
        }
    }


}