package rihanna.appsmatic.com.rihanna.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.District.Districts;
import rihanna.appsmatic.com.rihanna.API.Models.OutdoorLocations.ResAddress;
import rihanna.appsmatic.com.rihanna.API.Models.States.ResStates;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.GPS.GPSTracker;
import rihanna.appsmatic.com.rihanna.OffLineOrder.offAddress;
import rihanna.appsmatic.com.rihanna.R;

public class CustomerLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double lat,lang;
    private Marker marker;
    private GPSTracker gpsTracker;
    private TextView next;
    private BetterSpinner avalibalelocations;
    private static List<String> statesIds;
    private static List<String>statesNames;
    private static List<String> districtsIds;
    private static List<String> districtsNames;
    private static List<String> locations;
    private EditText addr,phone;
    static String districtId="";
    static String stateId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_location);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        addr=(EditText)findViewById(R.id.customer_location_street_input);
        phone=(EditText)findViewById(R.id.customer_location_phone_input);
        next=(TextView)findViewById(R.id.date_time_next_btn);
        avalibalelocations=(BetterSpinner)findViewById(R.id.avalibale_expert_disrictes_sp);
        avalibalelocations.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_list_custome));


        lat=0.0;
        lang=0.0;
        gpsTracker = new GPSTracker(getApplicationContext());


        //get expert locations add expert id
        Generator.createService(RihannaAPI.class).getOutdoorAddress(getIntent().getStringExtra("expId")).enqueue(new Callback<List<ResAddress>>() {
            @Override
            public void onResponse(Call<List<ResAddress>> call, Response<List<ResAddress>> response) {
                if(response.isSuccessful()){
                    statesIds=new ArrayList<String>();
                    districtsIds=new ArrayList<String>();
                    locations=new ArrayList<String>();

                    if(response.body()!=null){

                        for(int i=0;i<response.body().size();i++){
                           statesIds.add(response.body().get(i).getStateId()+"");
                           districtsIds.add(response.body().get(i).getDistrictId()+"");
                           locations.add(response.body().get(i).getState() + " / " + response.body().get(i).getDistrict());
                        }


                        avalibalelocations.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_list_custome,locations));
                        avalibalelocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                stateId=statesIds.get(position);
                                districtId=districtsIds.get(position);

                                Toast.makeText(getApplicationContext(),stateId+" "+districtId,Toast.LENGTH_SHORT).show();

                            }
                        });




                    }else {
                        Toast.makeText(getApplicationContext(),"Null from Outdoor address API ",Toast.LENGTH_SHORT).show();
                    }

                }else {

                    try {
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResAddress>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Connection Error from Outdoor address API "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });






















        //Check GPS status
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CustomerLocation.this);
            builder.setMessage(R.string.gpsoff)
                    .setCancelable(false)
                    .setPositiveButton(R.string.turnon, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Home.turnLocationOn(CustomerLocation.this);
                        }
                    }).setIcon(android.R.drawable.alert_light_frame);
            AlertDialog alert = builder.create();
            alert.show();
        }





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(CustomerLocation.this, R.anim.alpha);
                next.clearAnimation();
                next.setAnimation(anim);
                Pattern pPhone = Pattern.compile("(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
                        "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
                        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$");
                Matcher mPhone = pPhone.matcher(phone.getText().toString());
                if(addr.getText().toString().isEmpty()){
                    addr.setError(getResources().getString(R.string.addreesserorr));
                }else if(phone.getText().toString().isEmpty()){
                    phone.setError(getResources().getString(R.string.phoneerror));
                }else if(!mPhone.matches()){
                    phone.setError(getResources().getString(R.string.phonevalid));
                }else if (avalibalelocations.getText().toString().isEmpty()) {
                    avalibalelocations.setError("!");
                }else {

                    //set address to offline order
                    offAddress offAddress=new offAddress();
                    offAddress.setCountryId("69");
                    offAddress.setStateId(stateId);
                    offAddress.setDistrictId(districtId);
                    offAddress.setAddr(addr.getText().toString());
                    offAddress.setLat(lat);
                    offAddress.setLng(lang);
                    Home.offOrderModel.setOffAddress(offAddress);
                    startActivity(new Intent(CustomerLocation.this, OrderScreen.class));
                    CustomerLocation.this.finish();

                }



            }
        });






        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lat=gpsTracker.getLatitude();
        lang=gpsTracker.getLongitude();

        LatLng currentLocation=new LatLng(lat,lang);
        marker=mMap.addMarker(new MarkerOptions().position(currentLocation).title(getResources().getString(R.string.locationdet)));
        float zoomLevel = (float) 16.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, zoomLevel));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (marker != null) {
                    marker.remove();
                }
                marker = mMap.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(latLng.latitude, latLng.longitude))
                        .draggable(true).visible(true).title(getResources().getString(R.string.locationdet)));
                lat = latLng.latitude;
                lang = latLng.longitude;
                Toast.makeText(getApplicationContext(), lat + " " + lang + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
