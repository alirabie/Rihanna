package rihanna.appsmatic.com.rihanna.Activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.List;

import rihanna.appsmatic.com.rihanna.Fragments.Filter;
import rihanna.appsmatic.com.rihanna.Fragments.Services;
import rihanna.appsmatic.com.rihanna.R;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BetterSpinner locations;
    private BetterSpinner countries;
    private static List<String> vendorsNames;
    private static List<String> vendorsIds;
    private static List<String>products;
    private static List<String> pids;
    private LinearLayout sideMenuButtons;
    public static Typeface face;
    private ImageView homeSide,profileSide,latestOffersSide,ordersListSide,settingsSide,abutAppSide,exitLoginSide;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         drawer= (DrawerLayout) findViewById(R.id.drawer_layout);


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        face= Typeface.createFromAsset(getAssets(), "farsi_three_light.ttf");

        //Setup two header spinners
        countries = (BetterSpinner) findViewById(R.id.countrydown);
        countries.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        countries.setHint("المدينة");
        countries.setHintTextColor(Color.WHITE);

        locations =(BetterSpinner)findViewById(R.id.citydown);
        locations.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        locations.setHint("المنطقة");
        locations.setHintTextColor(Color.WHITE);




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

        //Action Side menu buttons :

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
                fragmentTransaction.setCustomAnimations(R.anim.fadein,R.anim.fadeout);
                fragmentTransaction.commit();
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

                //do

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

                //do

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

                //do

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


                drawer.closeDrawer(GravityCompat.START);

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



    //back press
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
}
