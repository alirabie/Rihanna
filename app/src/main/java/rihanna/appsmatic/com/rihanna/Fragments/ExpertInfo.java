package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import rihanna.appsmatic.com.rihanna.Adabtors.CustomFragmentPagerAdapter;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfo3Fragments.AboutExpertFrag;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfo3Fragments.LatestOffersFrag;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfo3Fragments.ServicesFrag;
import rihanna.appsmatic.com.rihanna.R;


public class ExpertInfo extends Fragment {


    LatestOffersFrag latestOffersFrag;
    AboutExpertFrag aboutExpertFrag;
    ServicesFrag servicesFrag;
    ViewPager p;
    CustomFragmentPagerAdapter adapter;
    PagerSlidingTabStrip tabsStrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_expert_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        servicesFrag=new ServicesFrag();
        aboutExpertFrag=new AboutExpertFrag();
        latestOffersFrag=new LatestOffersFrag();
        //Tab Layout With 3 fragments
        p=(ViewPager)view.findViewById(R.id.expert_info_viewpager_master);
        tabsStrip = (PagerSlidingTabStrip)view.findViewById(R.id.expert_tabs_master);

        adapter = new CustomFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(latestOffersFrag,getResources().getString(R.string.tablatestoffers));
        adapter.addFragment(servicesFrag, getResources().getString(R.string.tabexpertservices));
        adapter.addFragment(aboutExpertFrag, getResources().getString(R.string.tababoutexpert));
        p.setAdapter(adapter);
        tabsStrip.setViewPager(p);
        adapter.notifyDataSetChanged();

    }



    @Override
    public void onDetach() {
        super.onDetach();
        getFragmentManager().beginTransaction().remove(this).commit();
        getFragmentManager().popBackStack();
    }
}

