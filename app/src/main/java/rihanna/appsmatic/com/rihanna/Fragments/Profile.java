package rihanna.appsmatic.com.rihanna.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Countries.ResCountry;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.BillingAddress;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.RegResponse;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.UpdateCustomer;
import rihanna.appsmatic.com.rihanna.API.Models.District.Districts;
import rihanna.appsmatic.com.rihanna.API.Models.Registration.RCustomer;
import rihanna.appsmatic.com.rihanna.API.Models.States.ResStates;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

public class Profile extends Fragment {




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
    private TextView signUpBtn;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fNameInput=(EditText)view.findViewById(R.id.fname_input_profile);
        lNameInput=(EditText)view.findViewById(R.id.lname_input_profile);
        emailInput=(EditText)view.findViewById(R.id.email_input_profile);
        signUpBtn=(TextView)view.findViewById(R.id.save_profile_btn);
        passwordInput=(EditText)view.findViewById(R.id.password_input_profile);
        phoneInput=(EditText)view.findViewById(R.id.phone_input_profile);
        repass=(EditText)view.findViewById(R.id.re_password_input_profile);
        address1=(EditText)view.findViewById(R.id.profile_address_input);
        //verificationCodeInput=(EditText)view.findViewById(R.id.verificationcodeinput);
       // verifyMobile=(TextView)view.findViewById(R.id.phone_ver_link_btn);
//        verificationCodeInput.setVisibility(View.INVISIBLE);





        filterStates =(BetterSpinner)view.findViewById(R.id.profile_states_spinner);
        filterdistructs=(BetterSpinner)view.findViewById(R.id.profile_districts_spinner);
        filterStates.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item));
        filterdistructs.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item));
        filterStates.setHint(getResources().getString(R.string.selectstate));
        filterStates.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHint(getResources().getString(R.string.selectdistrect));



        //fill inputs from saved data
        emailInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getEmail() + "");
        fNameInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getFirstName()+"");
        lNameInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getLastName() + "");
       // filterCountries.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getCountry().toString()+"");
        filterStates.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getProvince().toString()+"");
        filterdistructs.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getCity().toString()+"");
        address1.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getAddress1().toString() + "");
        phoneInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getPhoneNumber().toString() + "");



                            //get states by id
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
                                        final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, statesNames);
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
                                                Generator.createService(RihannaAPI.class).getDestrics(Home.country, stateKey).enqueue(new Callback<Districts>() {
                                                    @Override
                                                    public void onResponse(Call<Districts> call, final Response<Districts> response) {

                                                        if (response.isSuccessful()) {


                                                            //fill names and ids to spinner list from response
                                                            for (int i = 0; i < response.body().getDistricts().size(); i++) {
                                                                districtsNames.add(response.body().getDistricts().get(i).getName());
                                                                districtsIds.add(response.body().getDistricts().get(i).getId());
                                                            }


                                                            //add names to spinner list
                                                            final ArrayAdapter<String> districtadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, districtsNames);
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
                                                            Toast.makeText(getContext(), "response from filter districts not success", Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Districts> call, Throwable t) {
                                                        Toast.makeText(getContext(), "response from filter districts failed" + " " + t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                });


                                            }
                                        });

                                    } else {
                                        Toast.makeText(getContext(), "Response not sucsess from states ", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResStates> call, Throwable t) {
                                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());
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





        //Save button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern p = Pattern.compile("^(.+)@(.+)$");
                Matcher m = p.matcher(emailInput.getText().toString());
                Pattern pPhone = Pattern.compile("^(009665|9665|\\+9665|05|5)([0-9]{8})$");
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
                } else if (filterStates.getText().length() == 0) {

                    filterStates.setError(getResources().getString(R.string.selectstate));
                } else if (filterdistructs.getText().length() == 0) {
                    filterdistructs.setError(getResources().getString(R.string.selectdistrect));
                } else {


                    //Update request >>

                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getContext().getResources().getString(R.string.pleasewait));
                    mProgressDialog.show();

                    //create data

                    RCustomer customer = new RCustomer();
                    //fill billing address
                    BillingAddress billingAddress = new BillingAddress();
                    billingAddress.setCountryId(Integer.parseInt(SAUDI_ID));
                    billingAddress.setEmail(emailInput.getText().toString() + "");
                    billingAddress.setFirstName(fNameInput.getText().toString() + "");
                    billingAddress.setLastName(lNameInput.getText().toString() + "");
                    billingAddress.setStateProvinceId(Integer.parseInt(statusid));
                    billingAddress.setCity(districtkey);
                    billingAddress.setPhoneNumber("966" + phoneInput.getText().toString().substring(phoneInput.getText().toString().indexOf("5")) + "");
                    billingAddress.setAddress1(address1.getText() + "");
                    billingAddress.setZipPostalCode("00");
                    List<Integer> rollIds = new ArrayList<Integer>();
                    rollIds.add(3);
                    customer.setRoleIds(rollIds);
                    customer.setId(SaveSharedPreference.getCustomerId(getContext()));
                    customer.setEmail(emailInput.getText().toString() + "");
                    customer.setPassword(passwordInput.getText().toString() + "");
                    customer.setFirstName(fNameInput.getText().toString() + "");
                    customer.setLastName(lNameInput.getText().toString() + "");
                    customer.setPhone("966"+phoneInput.getText().toString().substring(phoneInput.getText().toString().indexOf("5"))+"");
                    customer.setVerificationcode("0000");
                    customer.setBillingAddress(billingAddress);

                    UpdateCustomer updateCustomer = new UpdateCustomer();
                    updateCustomer.setCustomer(customer);

                    Gson gson = new Gson();
                    String json = gson.toJson(updateCustomer);

                    Log.e("new Customer Data :", json);

                    Generator.createService(RihannaAPI.class).updateCustomer(updateCustomer, SaveSharedPreference.getCustomerId(getContext())).enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {

                            if (response.isSuccessful()) {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                if (response.body().getCustomers() != null) {
                                    Toast.makeText(getContext(), getResources().getString(R.string.update), Toast.LENGTH_LONG).show();
                                    //Save new Data locally
                                    SaveSharedPreference.setCustomerInfo(getContext(), response.body());
                                    countriesNames.clear();
                                    countriesIds.clear();
                                    statesNames.clear();
                                    countriesIds.clear();
                                    districtsNames.clear();
                                    districtsIds.clear();

                                    //Change Fragment
                                    Services services=new Services();
                                    android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragmentcontener, services);
                                    fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                                    fragmentTransaction.commit();
                                    Home.tittle.setVisibility(View.INVISIBLE);
                                    Home.topButtons.setVisibility(View.VISIBLE);
                                    Home.spainnersBox.setVisibility(View.VISIBLE);

                                } else if (response.body().getErrors() != null) {
                                    Toast.makeText(getContext(), getResources().getString(R.string.faild) + " " + response.body().getErrors().getAccount() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                try {
                                    Toast.makeText(getContext(),response.errorBody().string(), Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            Toast.makeText(getContext(), getResources().getString(R.string.faild) + " update customer " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }

                countriesNames.clear();
                countriesIds.clear();
                statesNames.clear();
                countriesIds.clear();
                districtsNames.clear();
                districtsIds.clear();


            }
        });

                }


    @Override
    public void onResume() {
        super.onResume();
        countriesNames.clear();
        countriesIds.clear();
        statesNames.clear();
        countriesIds.clear();
        districtsNames.clear();
        districtsIds.clear();

    }
}
