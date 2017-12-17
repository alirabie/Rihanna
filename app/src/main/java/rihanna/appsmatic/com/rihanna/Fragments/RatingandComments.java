package rihanna.appsmatic.com.rihanna.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo;
import rihanna.appsmatic.com.rihanna.R;


public class RatingandComments extends Fragment {


    ImageView backBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ratingand_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*
        backBtn=(ImageView)view.findViewById(R.id.expert_details_ratingfrag_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(),R.anim.alpha);
                backBtn.clearAnimation();
                backBtn.setAnimation(anim);

                ExpertInfo expertInfo=new ExpertInfo();
                Bundle bundle=new Bundle();
                bundle.putString("expertId",getArguments().getString("expertId"));
                expertInfo.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, expertInfo);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                Home.tittle.setVisibility(View.INVISIBLE);
                Home.tittle.setText(getResources().getString(R.string.filtertitle));
                Home.topButtons.setVisibility(View.INVISIBLE);
                Home.spainnersBox.setVisibility(View.INVISIBLE);
            }
        });

*/


    }

}
