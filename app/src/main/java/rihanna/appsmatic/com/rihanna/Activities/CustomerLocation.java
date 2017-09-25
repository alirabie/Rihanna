package rihanna.appsmatic.com.rihanna.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import rihanna.appsmatic.com.rihanna.GPS.GPSTracker;
import rihanna.appsmatic.com.rihanna.R;

public class CustomerLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double lat,lang;
    private Marker marker;
    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_location);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }



        lat=0.0;
        lang=0.0;
        gpsTracker = new GPSTracker(getApplicationContext());



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