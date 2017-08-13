package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.R;


public class Filter extends Fragment {

    private EditText keyword,byNearst,byCountry;
    private BetterSpinner expertRateSpinner,categoriesSpainner,servicesSpinners;
    private TextView filterBtn;


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
        byNearst=(EditText)view.findViewById(R.id.byneer_input);
        byCountry=(EditText)view.findViewById(R.id.by_cityordistrict);
        filterBtn=(TextView)view.findViewById(R.id.filter_btn);
        filterBtn.setTypeface(Home.face);


        expertRateSpinner=(BetterSpinner)view.findViewById(R.id.by_expert_rate_spinner);
        categoriesSpainner=(BetterSpinner)view.findViewById(R.id.by_categories_spinner);
        servicesSpinners=(BetterSpinner)view.findViewById(R.id.by_services_ofeers_spinner);

        expertRateSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome));
        expertRateSpinner.setHint(getResources().getString(R.string.expertrate));

        categoriesSpainner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome));
        categoriesSpainner.setHint(getResources().getString(R.string.bycategory));

        servicesSpinners.setAdapter(new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome));
        servicesSpinners.setHint(getResources().getString(R.string.byserviceoroffer));




        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                filterBtn.clearAnimation();
                filterBtn.setAnimation(anim);
                Services services = new Services();
                // Bundle bundle = new Bundle();
                // filter.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, services);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                Home.tittle.setVisibility(View.INVISIBLE);
                Home.topButtons.setVisibility(View.VISIBLE);
                Home.spainnersBox.setVisibility(View.VISIBLE);
            }
        });


    }
}
