package rihanna.appsmatic.com.rihanna.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Categories.ResCategory;
import rihanna.appsmatic.com.rihanna.API.Models.District.Districts;
import rihanna.appsmatic.com.rihanna.API.Models.States.ResStates;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.R;


public class Filter extends Fragment {

    private EditText keyword,email;
    private BetterSpinner expertRateSpinner,categoriesSpainner,citySp,districtSp;
    private TextView filterBtn;
    private static String stateKey="";
    private static String stateId;
    private static String districtKey="";
    private static String categoryKey="";
    private static String rateVal="0";

    private static List<String> statesIds;
    private static List<String>statesNames;
    private static List<String> districtsIds;
    private static List<String> districtsNames;
    private static List<String>categoriesNames;
    private static List<String>categoriesIds;
    private static List<String> rates;
    private static boolean countriesDone=false;
    private static boolean CategoriesDone=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //SetUp Items
        keyword=(EditText)view.findViewById(R.id.searchkey_input);
        filterBtn=(TextView)view.findViewById(R.id.filter_btn);
        filterBtn.setTypeface(Home.face);
        keyword=(EditText)view.findViewById(R.id.searchkey_input);
        email=(EditText)view.findViewById(R.id.byemail_input);
        expertRateSpinner=(BetterSpinner)view.findViewById(R.id.by_expert_rate_spinner);
        categoriesSpainner=(BetterSpinner)view.findViewById(R.id.by_categories_spinner);
        citySp=(BetterSpinner)view.findViewById(R.id.by_city_spinner);
        districtSp=(BetterSpinner)view.findViewById(R.id.by_district_spinner);
        expertRateSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome));
        categoriesSpainner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome));
        citySp.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome));
        districtSp.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome));



        //get States by id
        Generator.createService(RihannaAPI.class).getStates(Home.SAUDI_ID).enqueue(new Callback<ResStates>() {
            @Override
            public void onResponse(Call<ResStates> call, Response<ResStates> response) {
                if (response.isSuccessful()) {
                    filterBtn.setEnabled(true);
                    statesNames=new ArrayList<String>();
                    statesIds=new ArrayList<String>();
                    //fill names and ids to spinner list from response
                    for (int i = 0; i < response.body().getStates().size(); i++) {
                        statesNames.add(response.body().getStates().get(i).getName());
                        statesIds.add(response.body().getStates().get(i).getId());
                    }

                    countriesDone=true;
                    //add names to spinner list
                    final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, statesNames);
                    statesadabter.notifyDataSetChanged();
                    citySp.setAdapter(statesadabter);
                    citySp.setHint(getResources().getString(R.string.selectstate));
                    citySp.setHintTextColor(Color.GRAY);
                    //states list selection item action start home activity and send state id
                    citySp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                            stateKey = statesNames.get(position);
                            stateId = statesIds.get(position);

                            Toast.makeText(getContext(),stateKey,Toast.LENGTH_SHORT).show();
                            //Get districts
                            Generator.createService(RihannaAPI.class).getDestrics("Saudi Arabia", stateKey).enqueue(new Callback<Districts>() {
                                @Override
                                public void onResponse(Call<Districts> call, final Response<Districts> response) {

                                    if (response.isSuccessful()) {
                                        districtsNames=new ArrayList<String>();
                                        districtsIds=new ArrayList<String>();

                                        //fill names and ids to spinner list from response
                                        for (int i = 0; i < response.body().getDistricts().size(); i++) {
                                            districtsNames.add(response.body().getDistricts().get(i).getName());
                                            districtsIds.add(response.body().getDistricts().get(i).getId());
                                        }

                                        //add names to spinner list
                                        final ArrayAdapter<String> districtadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, districtsNames);
                                        districtadapter.notifyDataSetChanged();
                                        districtSp.setAdapter(districtadapter);
                                        districtSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                districtKey = response.body().getDistricts().get(position).getName();

                                                Toast.makeText(getContext(),districtKey,Toast.LENGTH_SHORT).show();

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



        //Get Categories
        Generator.createService(RihannaAPI.class).getCategories().enqueue(new Callback<ResCategory>() {
            @Override
            public void onResponse(Call<ResCategory> call, Response<ResCategory> response) {
                if (response.isSuccessful()) {

                    categoriesNames=new ArrayList<String>();
                    categoriesIds=new ArrayList<String>();
                    if (response.body().getCategories() != null) {
                        for (int i = 0; i < response.body().getCategories().size(); i++) {
                            categoriesNames.add(response.body().getCategories().get(i).getName());
                            categoriesIds.add(response.body().getCategories().get(i).getId());
                        }

                        CategoriesDone=true;
                        categoriesSpainner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome, categoriesNames));
                        categoriesSpainner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                categoryKey=categoriesNames.get(position);
                                Toast.makeText(getContext(),categoryKey, Toast.LENGTH_SHORT).show();

                            }
                        });

                    } else {
                        Toast.makeText(getContext(), "Null from categories Api", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        Toast.makeText(getContext(), "Response Not Success from categories Api" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResCategory> call, Throwable t) {
                Toast.makeText(getContext(), "Connection Error from categories Api" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        


        //Rate Spinner
        rates =new ArrayList<>();
        for(int i=0;i<=5;i++){
            rates.add(i + "");
        }

        expertRateSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome, rates));
        expertRateSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rateVal=rates.get(position);
                Log.e("rate",rateVal);
            }
        });


        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                filterBtn.clearAnimation();
                filterBtn.setAnimation(anim);

                //Check if all Spinners Complete loading
                if(!countriesDone||!CategoriesDone) {
                    return;
                }
                    Services services = new Services();
                    Bundle bundle = new Bundle();
                    bundle.putString("sourceflag", "filter");
                    bundle.putString("category", categoryKey + "");
                    if(!stateKey.equals("")){
                        bundle.putString("country",Home.country);
                    }else {
                        bundle.putString("country","");
                    }
                    bundle.putString("state", stateKey + "");
                    bundle.putString("email", email.getText().toString() + "");
                    bundle.putString("rate", rateVal + "");
                    bundle.putString("district", districtKey + "");
                    bundle.putString("keyword", keyword.getText().toString()+"");
                    services.setArguments(bundle);
                    android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontener, services);
                    fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                    fragmentTransaction.commit();
                    districtKey = "";
                    stateKey = "";
                    categoryKey = "";
                    districtKey="";
                    rateVal="0";
                    countriesDone=false;
                    CategoriesDone=false;
                    Home.tittle.setVisibility(View.INVISIBLE);
                    Home.topButtons.setVisibility(View.VISIBLE);
                    Home.spainnersBox.setVisibility(View.VISIBLE);

            }
        });


    }
}
