package rihanna.appsmatic.com.rihanna.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
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
import rihanna.appsmatic.com.rihanna.Fragments.AboutApp;
import rihanna.appsmatic.com.rihanna.Fragments.Filter;
import rihanna.appsmatic.com.rihanna.Fragments.ListOfOrders;
import rihanna.appsmatic.com.rihanna.Fragments.OrderInfo;
import rihanna.appsmatic.com.rihanna.Fragments.Profile;
import rihanna.appsmatic.com.rihanna.Fragments.Sale;
import rihanna.appsmatic.com.rihanna.Fragments.Services;
import rihanna.appsmatic.com.rihanna.Fragments.Settings;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BetterSpinner cities;
    private BetterSpinner categories;
    private static List<String> citesNames;
    private static List<String>citesIds;

    private static List<String>categoriesNames;
    private static List<String>categoriesIds;
    private LinearLayout sideMenuButtons;
    public static Typeface face;
    private ImageView homeSide,profileSide,latestOffersSide,ordersListSide,settingsSide,abutAppSide,exitLoginSide;
    DrawerLayout drawer;
    public static TextView tittle;
    public static LinearLayout topButtons ,spainnersBox;
    private boolean doubleBackToExitPressedOnce = false;

    private ImageView hairBtn,makeUpBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         face= Typeface.createFromAsset(getAssets(), "arabic.ttf");
         drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
         tittle=(TextView)findViewById(R.id.filtertitle);
         tittle.setTypeface(face);
         tittle.setVisibility(View.INVISIBLE);
         topButtons=(LinearLayout)findViewById(R.id.top_buttons_box);
         spainnersBox=(LinearLayout)findViewById(R.id.spinners_box);
        hairBtn=(ImageView)findViewById(R.id.hair_btn);
        makeUpBtn=(ImageView)findViewById(R.id.mack_up_btn);




        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        //Setup two header spinners


        //Setup states spinner
        cities =(BetterSpinner)findViewById(R.id.citydown);
        cities.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        cities.setHint(getResources().getString(R.string.city));
        cities.setTypeface(face);
        cities.setHintTextColor(Color.WHITE);
        citesNames=new ArrayList<>();
        citesIds=new ArrayList<>();
        Generator.createService(RihannaAPI.class).getStates("69").enqueue(new Callback<ResStates>() {

            @Override
            public void onResponse(Call<ResStates> call, Response<ResStates> response) {
                if (response.isSuccessful()) {

                    //fill names and ids to spinner list from response
                    for (int i = 0; i < response.body().getStates().size(); i++) {
                        citesNames.add(response.body().getStates().get(i).getName());
                        citesIds.add(response.body().getStates().get(i).getId());
                    }

                    cities.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome, citesNames));
                    cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Services services = new Services();
                            Bundle bundle = new Bundle();
                            bundle.putString("sourceflag","cites");
                            bundle.putString("state", citesNames.get(position));
                            services.setArguments(bundle);
                            android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragmentcontener, services);
                            fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                            fragmentTransaction.commit();
                            Toast.makeText(getApplicationContext(), citesNames.get(position), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(getApplication(), "Response not sucsess from states ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResStates> call, Throwable t) {
                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(Home.this);
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


        //setup categories spinner
        categories = (BetterSpinner) findViewById(R.id.countrydown);
        categories.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        categories.setHint(getResources().getString(R.string.categoriy));
        categories.setTypeface(face);
        categories.setHintTextColor(Color.WHITE);
        categoriesNames=new ArrayList<>();
        categoriesIds=new ArrayList<>();
        Generator.createService(RihannaAPI.class).getCategories().enqueue(new Callback<ResCategory>() {
            @Override
            public void onResponse(Call<ResCategory> call, Response<ResCategory> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCategories() != null) {
                        for (int i = 0; i < response.body().getCategories().size(); i++) {
                            categoriesNames.add(response.body().getCategories().get(i).getName());
                            categoriesIds.add(response.body().getCategories().get(i).getId());
                        }

                        categories.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome, categoriesNames));
                        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Services services = new Services();
                                Bundle bundle = new Bundle();
                                bundle.putString("sourceflag","categories");
                                bundle.putString("category", categoriesNames.get(position));
                                services.setArguments(bundle);
                                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.fragmentcontener, services);
                                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                                fragmentTransaction.commit();
                                Toast.makeText(getApplicationContext(), categoriesNames.get(position), Toast.LENGTH_SHORT).show();

                            }
                        });

                    } else {
                        Toast.makeText(Home.this, "Null from categories Api", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        Toast.makeText(Home.this, "Response Not Success from categories Api" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResCategory> call, Throwable t) {
                Toast.makeText(Home.this, "Connection Error from categories Api" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        hairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                hairBtn.clearAnimation();
                hairBtn.setAnimation(anim);

                Services services = new Services();
                Bundle bundle = new Bundle();
                bundle.putString("sourceflag","categories");
                bundle.putString("category", "Hair");
                services.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, services);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
            }
        });


        makeUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                makeUpBtn.clearAnimation();
                makeUpBtn.setAnimation(anim);
                Services services = new Services();
                Bundle bundle = new Bundle();
                bundle.putString("sourceflag","categories");
                bundle.putString("category", "Makeup");
                services.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, services);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
            }
        });


        //Setup Side Menu Items
        homeSide=(ImageView)findViewById(R.id.home_side_button);
        profileSide=(ImageView)findViewById(R.id.profile_side_button);
        latestOffersSide=(ImageView)findViewById(R.id.offers_side_button);
        ordersListSide=(ImageView)findViewById(R.id.orders_side_button);
        settingsSide=(ImageView)findViewById(R.id.settings_side_buttons);
        abutAppSide=(ImageView)findViewById(R.id.aboutapp_side_buttons);
        exitLoginSide=(ImageView)findViewById(R.id.logout_side_button);











        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            homeSide.setBackgroundResource(R.drawable.ripple);
            profileSide.setBackgroundResource(R.drawable.ripple);
            latestOffersSide.setBackgroundResource(R.drawable.ripple);
            ordersListSide.setBackgroundResource(R.drawable.ripple);
            settingsSide.setBackgroundResource(R.drawable.ripple);
            abutAppSide.setBackgroundResource(R.drawable.ripple);
            exitLoginSide.setBackgroundResource(R.drawable.ripple);
        }


        //check language
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            homeSide.setImageResource(R.drawable.home);
            profileSide.setImageResource(R.drawable.profile);
            latestOffersSide.setImageResource(R.drawable.sale);
            ordersListSide.setImageResource(R.drawable.orders);
            settingsSide.setImageResource(R.drawable.settings);
            abutAppSide.setImageResource(R.drawable.about);
            if(SaveSharedPreference.getCustomerInfo(Home.this)==null){
                exitLoginSide.setImageResource(R.drawable.signin);
            }else {
                exitLoginSide.setImageResource(R.drawable.logout);
            }
        }else{
            homeSide.setImageResource(R.drawable.home_en);
            profileSide.setImageResource(R.drawable.profile_en);
            latestOffersSide.setImageResource(R.drawable.sale_en);
            ordersListSide.setImageResource(R.drawable.orders_en);
            settingsSide.setImageResource(R.drawable.settings_en);
            abutAppSide.setImageResource(R.drawable.aboutus_en);
            if(SaveSharedPreference.getCustomerInfo(Home.this)==null){
                exitLoginSide.setImageResource(R.drawable.signin_en);
            }else {
                exitLoginSide.setImageResource(R.drawable.signout_en);
            }
        }














        //Action Side menu buttons :

        //START FIRST SCREEN
        Services services=new Services();
        android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontener, services);
        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
        fragmentTransaction.commit();

        //Home button
        homeSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                homeSide.clearAnimation();
                homeSide.setAnimation(anim);
                Services services=new Services();
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, services);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                tittle.setVisibility(View.INVISIBLE);
                topButtons.setVisibility(View.VISIBLE);
                spainnersBox.setVisibility(View.VISIBLE);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        //Profile button
        profileSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                profileSide.clearAnimation();
                profileSide.setAnimation(anim);
                Profile profile=new Profile();
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, profile);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                tittle.setVisibility(View.VISIBLE);
                tittle.setText(getResources().getString(R.string.profile));
                Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                tittle.clearAnimation();
                tittle.setAnimation(anim3);
                topButtons.setVisibility(View.INVISIBLE);
                spainnersBox.setVisibility(View.INVISIBLE);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        //Offers button
        latestOffersSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                latestOffersSide.clearAnimation();
                latestOffersSide.setAnimation(anim);

                Sale sale=new Sale();
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener,sale);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                tittle.setVisibility(View.VISIBLE);
                tittle.setText(getResources().getString(R.string.salestitle));
                Animation anim5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                tittle.clearAnimation();
                tittle.setAnimation(anim5);
                topButtons.setVisibility(View.INVISIBLE);
                spainnersBox.setVisibility(View.INVISIBLE);
                drawer.closeDrawer(GravityCompat.START);

            }
        });



        //Orders List
        ordersListSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                ordersListSide.clearAnimation();
                ordersListSide.setAnimation(anim);

                ListOfOrders listOfOrders=new ListOfOrders();
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, listOfOrders);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                tittle.setVisibility(View.VISIBLE);
                tittle.setText(getResources().getString(R.string.listorderstitle));
                Animation anim5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                tittle.clearAnimation();
                tittle.setAnimation(anim5);
                topButtons.setVisibility(View.INVISIBLE);
                spainnersBox.setVisibility(View.INVISIBLE);
                drawer.closeDrawer(GravityCompat.START);


            }
        });

        //settings button
        settingsSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                settingsSide.clearAnimation();
                settingsSide.setAnimation(anim);
                Settings settings=new Settings();
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, settings);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                tittle.setVisibility(View.VISIBLE);
                tittle.setText(getResources().getString(R.string.settings));
                Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                tittle.clearAnimation();
                tittle.setAnimation(anim2);
                topButtons.setVisibility(View.INVISIBLE);
                spainnersBox.setVisibility(View.INVISIBLE);
                drawer.closeDrawer(GravityCompat.START);
            }
        });


        //About App button
        abutAppSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                abutAppSide.clearAnimation();
                abutAppSide.setAnimation(anim);
                AboutApp aboutApp=new AboutApp();
                android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, aboutApp);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                tittle.setVisibility(View.VISIBLE);
                tittle.setText(getResources().getString(R.string.aboutapp));
                Animation anim5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                tittle.clearAnimation();
                tittle.setAnimation(anim5);
                topButtons.setVisibility(View.INVISIBLE);
                spainnersBox.setVisibility(View.INVISIBLE);
                drawer.closeDrawer(GravityCompat.START);
            }
        });



        //Exit/login button
        exitLoginSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(Home.this, R.anim.alpha);
                exitLoginSide.clearAnimation();
                exitLoginSide.setAnimation(anim);

                if(SaveSharedPreference.getCustomerInfo(Home.this)==null){

                    //Login
                    startActivity(new Intent(Home.this,SignInScreen.class));
                    drawer.closeDrawer(GravityCompat.START);

                }else {

                    //Logout and clear all data
                    SaveSharedPreference.setCustomerId(Home.this,"");
                    SaveSharedPreference.setCustomerInfo(Home.this, null);
                    SaveSharedPreference.setUserName(Home.this, "", "");
                    startActivity(new Intent(Home.this, Splash.class));
                    drawer.closeDrawer(GravityCompat.START);
                    Home.this.finish();

                }


            }
        });
























        //Side menu

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.sidem_icon);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                    //Animate side icons
                    sideMenuButtons=(LinearLayout)findViewById(R.id.side_buttons_container);
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                    sideMenuButtons.clearAnimation();
                    sideMenuButtons.setAnimation(anim);
                }
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.doubleBackToExitPressedOnce = false;
    }

    //back press
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            //START FIRST SCREEN
            Services services = new Services();
            android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, services);
            fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            fragmentTransaction.commit();
            categories.setText("");
            cities.setText("");
            topButtons.setVisibility(View.VISIBLE);
            spainnersBox.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Press Again", Toast.LENGTH_SHORT).show();
            if (doubleBackToExitPressedOnce) {
                final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(Home.this);
                dialogBuilder
                        .withTitle(getResources().getString(R.string.app_name))
                        .withDialogColor(R.color.colorPrimary)
                        .withTitleColor("#FFFFFF")
                        .withIcon(getResources().getDrawable(R.drawable.logo))
                        .withDuration(700)                                          //def
                        .withEffect(Effectstype.RotateBottom)
                        .withMessage("are You Sure ? ")
                        .withButton1Text(getResources().getString(R.string.yes))
                        .withButton2Text(getResources().getString(R.string.no))
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogBuilder.dismiss();
                                Home.this.finish();
                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogBuilder.dismiss();
                            }
                        })
                        .show();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            // Toast.makeText(this, R.string.pressagain, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }



    //Option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Filter filter = new Filter();
            // Bundle bundle = new Bundle();
            // filter.setArguments(bundle);
            android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, filter);
            fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            fragmentTransaction.commit();
            tittle.setText(getResources().getString(R.string.filtertitle));
            tittle.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
            tittle.clearAnimation();
            tittle.setAnimation(anim);
            topButtons.setVisibility(View.INVISIBLE);
            spainnersBox.setVisibility(View.INVISIBLE);

            return true;
        }else if (id==R.id.action_shopping_cart){
            OrderInfo orderInfo =new OrderInfo();
            android.support.v4.app.FragmentManager fragmentManager = (Home.this).getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, orderInfo);
            fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            fragmentTransaction.commit();
            tittle.setText(getResources().getString(R.string.orderinfo));
            tittle.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
            tittle.clearAnimation();
            tittle.setAnimation(anim);
            topButtons.setVisibility(View.INVISIBLE);
            spainnersBox.setVisibility(View.INVISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }













    //Turn GPS ON Method
    public static void turnLocationOn(final Context ctx){

        isGooglePlayServicesAvailable((Activity) ctx);

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(ctx)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("TurnLocationOn", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("TurnLocationOn", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult((Activity) ctx, 0x1);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("TurnLocationOn", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("TurnLocationOn", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });


    }



    //Done Dialog method
    public static void fireDoneDialog(Context context,String exName,View button){
        //Initialize Done Dialog
        final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(context);
        dialogBuildercard
                .withDuration(700)//def
                .withDialogColor(Color.WHITE)
                .withEffect(Effectstype.Newspager)
                .withTitleColor(context.getResources().getColor(R.color.colorPrimary))
                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                .setCustomView(R.layout.thanks_dialog_layout, button.getContext())
                .withButton1Text(context.getResources().getString(R.string.dissmis))
                .withDialogColor(R.color.colorPrimary)
                .withTitle(context.getResources().getString(R.string.app_name))
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuildercard.dismiss();
                    }
                })
                .show();

        //Setup Items
        ImageView thanksMsg=(ImageView)dialogBuildercard.findViewById(R.id.thanks_dialog_smilemessage);
        ImageView advBanner=(ImageView)dialogBuildercard.findViewById(R.id.thanks_dialog_addv_img);
        TextView expertNameTv=(TextView)dialogBuildercard.findViewById(R.id.thanks_dialog_expert_name_tv);

        //check language
        if(SaveSharedPreference.getLangId(context).equals("ar")){
            thanksMsg.setImageResource(R.drawable.thanks);
        }else{
            thanksMsg.setImageResource(R.drawable.thanks_en);
        }


        expertNameTv.setText(" "+exName);
        dialogBuildercard.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK;
            }





        });













    }


    //Check Google Service
    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }
}
