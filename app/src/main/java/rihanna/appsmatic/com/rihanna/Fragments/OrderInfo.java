package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rihanna.appsmatic.com.rihanna.Activities.DateTimeScreen;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;


public class OrderInfo extends Fragment {


   private TextView bookBtn;
   private ImageView header;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bookBtn=(TextView)view.findViewById(R.id.shopping_cart_orderinfo_booknow_btn);
        //header=(ImageView)view.findViewById(R.id.shopping_cart_orderinfo_heder_txt);


        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), DateTimeScreen.class));
            }
        });





    }
}
