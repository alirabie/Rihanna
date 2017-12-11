package rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;

import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Adabtors.CustomFragmentPagerAdapter;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments.AboutExpertFrag;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments.LatestOffersFrag;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments.ServicesFrag;
import rihanna.appsmatic.com.rihanna.Fragments.RatingandComments;
import rihanna.appsmatic.com.rihanna.R;


public class ExpertInfo extends Fragment {


    LatestOffersFrag latestOffersFrag;
    AboutExpertFrag aboutExpertFrag;
    ServicesFrag servicesFrag;
    ViewPager p;
    CustomFragmentPagerAdapter adapter;
    PagerSlidingTabStrip tabsStrip;
    public static String expertId ="";
    private ImageView goToRatesComments;

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

        goToRatesComments =(ImageView)view.findViewById(R.id.expert_details_goto_customercomments);

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



        goToRatesComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                 goToRatesComments.clearAnimation();
                 goToRatesComments.setAnimation(anim);


               // Home.fireDoneDialog(getContext(),"خبيرة التجميل جويل",goToRatesComments);


                RatingandComments ratingandComments=new RatingandComments();

                //send id

                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, ratingandComments);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                Home.tittle.setVisibility(View.INVISIBLE);
                Home.tittle.setText(getResources().getString(R.string.filtertitle));
                Home.topButtons.setVisibility(View.INVISIBLE);
                Home.spainnersBox.setVisibility(View.INVISIBLE);

            }
        });







    }



    @Override
    public void onDetach() {
        super.onDetach();
        getFragmentManager().beginTransaction().remove(this).commit();
        getFragmentManager().popBackStack();
    }
}

