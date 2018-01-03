package rihanna.appsmatic.com.rihanna.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Countries.ResCountry;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.BillingAddress;
import rihanna.appsmatic.com.rihanna.API.Models.District.Districts;
import rihanna.appsmatic.com.rihanna.API.Models.Registration.PostNewCustomer;
import rihanna.appsmatic.com.rihanna.API.Models.Registration.RCustomer;
import rihanna.appsmatic.com.rihanna.API.Models.States.ResStates;
import rihanna.appsmatic.com.rihanna.API.Models.verifications.VerificationCode;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.R;

public class SignUpScreen extends AppCompatActivity {

    private ImageView home;
    private TextView signUpBtn;
    private EditText emailInput,passwordInput,fNameInput,lNameInput,phoneInput,repass,verificationCodeInput,address1;
    private TextView verifyMobile;
    private BetterSpinner filterStates;
    private BetterSpinner filterdistructs;
    private static List<String> countriesIds= new ArrayList<>();
    private static List<String> countriesNames= new ArrayList<>();
    private static List<String>statesIds= new ArrayList<>();
    private static List<String>statesNames= new ArrayList<>();
    private static List<String> districtsIds=new ArrayList<>();
    private static List<String> districtsNames=new ArrayList<>();
    private static final String SAUDI_ID="69";
    private static final String KUWAIT_ID="69";
    private static String countryKey,stateKey,districtkey,statusid,countryid;
    private LinearLayout socialmedisButtons,socialtitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        signUpBtn=(TextView)findViewById(R.id.resume_reg_btn);
        home=(ImageView)findViewById(R.id.home_btn_sign_up);
        emailInput=(EditText)findViewById(R.id.signup_email_input);
        passwordInput=(EditText)findViewById(R.id.signup_password_input);
        fNameInput=(EditText)findViewById(R.id.fname_input_reg);
        lNameInput=(EditText)findViewById(R.id.lname_input_reg);
        phoneInput=(EditText)findViewById(R.id.phone_input_reg);
        repass=(EditText)findViewById(R.id.signup_re_password);
        address1=(EditText)findViewById(R.id.address_input_reg);


        //hide social media login
        socialmedisButtons=(LinearLayout)findViewById(R.id.sm);
        socialtitle=(LinearLayout)findViewById(R.id.linearLayout2);
        socialmedisButtons.setVisibility(View.INVISIBLE);
        socialtitle.setVisibility(View.INVISIBLE);


        filterStates =(BetterSpinner)findViewById(R.id.signup_states_spinner2);
        filterdistructs=(BetterSpinner)findViewById(R.id.signup_districts_spinner);
        filterStates.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item));
        filterdistructs.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item));
        filterStates.setHint(getResources().getString(R.string.selectstate));
        filterStates.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHint(getResources().getString(R.string.selectdistrect));





                            //get states by Country id
                            Generator.createService(RihannaAPI.class).getStates(SAUDI_ID).enqueue(new Callback<ResStates>() {

                                @Override
                                public void onResponse(Call<ResStates> call, Response<ResStates> response) {
                                    if (response.isSuccessful()) {

                                        //fill names and ids to spinner list from response
                                        for (int i = 0; i < response.body().getStates().size(); i++) {
                                            statesNames.add(response.body().getStates().get(i).getName());
                                            statesIds.add(response.body().getStates().get(i).getId());
                                        }

                                        //add names to spinner list
                                        final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(SignUpScreen.this, android.R.layout.simple_spinner_dropdown_item, statesNames);
                                        statesadabter.notifyDataSetChanged();
                                        filterStates.setAdapter(statesadabter);
                                        filterStates.setHint(getResources().getString(R.string.selectstate));
                                        filterStates.setHintTextColor(Color.GRAY);
                                        //states list selection item action start home activity and send state id
                                        filterStates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                stateKey = statesNames.get(position);
                                                statusid = statesIds.get(position);

                                                //Get districts
                                                Generator.createService(RihannaAPI.class).getDestrics("Saudi Arabia", stateKey).enqueue(new Callback<Districts>() {
                                                    @Override
                                                    public void onResponse(Call<Districts> call, final Response<Districts> response) {

                                                        if (response.isSuccessful()) {


                                                            //fill names and ids to spinner list from response
                                                            for (int i = 0; i < response.body().getDistricts().size(); i++) {
                                                                districtsNames.add(response.body().getDistricts().get(i).getName());
                                                                districtsIds.add(response.body().getDistricts().get(i).getId());
                                                            }


                                                            //add names to spinner list
                                                            final ArrayAdapter<String> districtadapter = new ArrayAdapter<>(SignUpScreen.this, android.R.layout.simple_spinner_dropdown_item, districtsNames);
                                                            districtadapter.notifyDataSetChanged();
                                                            filterdistructs.setAdapter(districtadapter);
                                                            filterdistructs.setHint(getResources().getString(R.string.selectdistrect));
                                                            filterdistructs.setHintTextColor(Color.GRAY);
                                                            filterdistructs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                @Override
                                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                                    if (!districtsNames.isEmpty()) {
                                                                        districtsNames.clear();
                                                                        districtsIds.clear();
                                                                    }

                                                                    districtkey = response.body().getDistricts().get(position).getName();

                                                                    Log.e("gooood", "Country Id : " + countryid + "status id : " + statusid + " district name : " + districtkey);

                                                                }
                                                            });

                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "response from filter districts not success", Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Districts> call, Throwable t) {
                                                        Toast.makeText(getApplicationContext(), "response from filter districts failed" + " " + t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                });


                                            }
                                        });

                                    } else {
                                        Toast.makeText(getApplication(), "Response not sucsess from states ", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResStates> call, Throwable t) {
                                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(SignUpScreen.this);
                                    dialogBuilder
                                            .withTitle(getResources().getString(R.string.connectionerror))
                                            .withDialogColor(R.color.colorPrimary)
                                            .withTitleColor("#FFFFFF")
                                            .withDuration(700)                                          //def
                                            .withEffect(Effectstype.RotateBottom)
                                            .withMessage(t.getMessage() + " : From states ")
                                            .show();
                                }
                            });














        //SignUp button Action
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pattern p = Pattern.compile("^(.+)@(.+)$");
                Matcher m = p.matcher(emailInput.getText().toString());
                Pattern pPhone = Pattern.compile("(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
                        "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
                        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$");
                Matcher mPhone = pPhone.matcher(phoneInput.getText().toString());

                //Inputs validations
                if (emailInput.getText().toString().length() == 0) {
                    emailInput.setError(getResources().getString(R.string.loginvalemail));

                } else if (passwordInput.getText().toString().length() == 0) {
                    passwordInput.setError(getResources().getString(R.string.loginvalpassword));

                } else if (repass.getText().toString().length() == 0) {
                    repass.setError(getResources().getString(R.string.loginvalpassword));

                } else if (fNameInput.getText().toString().length() == 0) {
                    fNameInput.setError(getResources().getString(R.string.fnameerrorr));

                } else if (lNameInput.getText().toString().length() == 0) {
                    lNameInput.setError(getResources().getString(R.string.lnameerrorr));

                } else if (phoneInput.getText().toString().length() == 0) {
                    phoneInput.setError(getResources().getString(R.string.phoneerror));

                } else if (!mPhone.matches()) {
                    phoneInput.setError(getResources().getString(R.string.phonevalid));

                } else if (!passwordInput.getText().toString().equals(repass.getText().toString())) {
                    passwordInput.setError(getResources().getString(R.string.passnotmatch));
                    repass.setError(getResources().getString(R.string.passnotmatch));

                } else if (!m.matches()) {
                    emailInput.setError(getResources().getString(R.string.notvalidemail));

                } else if (passwordInput.getText().length() < 6) {
                    passwordInput.setError(getResources().getString(R.string.passwordleanght));

                } else if (address1.getText().length() == 0) {

                    address1.setError(getResources().getString(R.string.addreesserorr));
                }
                else if (filterStates.getText().length() == 0) {

                    filterStates.setError(getResources().getString(R.string.selectstate));
                } else if (filterdistructs.getText().length() == 0) {
                    filterdistructs.setError(getResources().getString(R.string.selectdistrect));
                } else {

                    //Send Registration request >>
                    //create data
                    PostNewCustomer postNewCustomer = new PostNewCustomer();
                    RCustomer customer = new RCustomer();


                    //fill billing address
                    BillingAddress billingAddress = new BillingAddress();
                    billingAddress.setCountryId(Integer.parseInt(SAUDI_ID));
                    billingAddress.setEmail(emailInput.getText().toString() + "");
                    billingAddress.setFirstName(fNameInput.getText().toString() + "");
                    billingAddress.setLastName(lNameInput.getText().toString() + "");
                    billingAddress.setStateProvinceId(Integer.parseInt(statusid));
                    billingAddress.setCity(districtkey);
                    billingAddress.setPhoneNumber(phoneInput.getText().toString() + "");
                    billingAddress.setAddress1(address1.getText() + "");
                    billingAddress.setZipPostalCode("00");

                    List<Integer> rollIds = new ArrayList<Integer>();
                    rollIds.add(3);
                    customer.setRoleIds(rollIds);
                    customer.setEmail(emailInput.getText().toString() + "");
                    customer.setPassword(passwordInput.getText().toString() + "");
                    customer.setFirstName(fNameInput.getText().toString() + "");
                    customer.setLastName(lNameInput.getText().toString() + "");
                    customer.setPhone(phoneInput.getText().toString() + "");
                    customer.setBillingAddress(billingAddress);
                    //postNewCustomer.setCustomer(customer);


                    Gson gson = new Gson();
                    String regDatajson = gson.toJson(customer);
                    Log.e("Json : ", regDatajson);


                    //Activate phone Number
                    Generator.createService(RihannaAPI.class).verifyMoblieNum(phoneInput.getText().toString()).enqueue(new Callback<VerificationCode>() {
                        @Override
                        public void onResponse(Call<VerificationCode> call, Response<VerificationCode> response) {

                            if (response.isSuccessful()) {
                                if (response.body().getErrorMessage().equals("") || response.body().getErrorMessage() == null) {

                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.codewillsend) + " ", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), response.body().getErrorMessage() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                Toast.makeText(getApplicationContext(), "Not success from mobile verification", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<VerificationCode> call, Throwable t) {

                            Toast.makeText(getApplicationContext(), "Failure from mobile verification" + t.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });




                    //Start Activation Screen and send data
                    startActivity(new Intent(SignUpScreen.this, ActivateAccountScreen.class).putExtra("regData", regDatajson));


                    countriesNames.clear();
                    countriesIds.clear();
                    statesNames.clear();
                    countriesIds.clear();
                    districtsNames.clear();
                    districtsIds.clear();




                }


            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        countriesNames.clear();
        countriesIds.clear();
        statesNames.clear();
        countriesIds.clear();
        districtsNames.clear();
        districtsIds.clear();
    }
}
