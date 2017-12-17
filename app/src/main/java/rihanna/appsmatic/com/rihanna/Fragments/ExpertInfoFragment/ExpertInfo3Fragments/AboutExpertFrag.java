package rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Experinces.GetExperinces;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Dilaogs.FireDialog;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo;
import rihanna.appsmatic.com.rihanna.R;


public class AboutExpertFrag extends Fragment {

    TextView address,serviceType,cert,expyears,aboutExpert;
    private TextView viewCertificates;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_expert, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        address=(TextView)view.findViewById(R.id.aboutex_frag_address_tv);
        cert=(TextView)view.findViewById(R.id.aboutex_frag_cert_tv);
        serviceType=(TextView)view.findViewById(R.id.aboutex_frag_servicetype_tv);
        expyears=(TextView)view.findViewById(R.id.aboutex_frag_expyears_tv);
        aboutExpert=(TextView)view.findViewById(R.id.aboutex_frag_expinfo_tv);
        viewCertificates=(TextView)view.findViewById(R.id.show_cert_btn);
        viewCertificates.setVisibility(View.INVISIBLE);

        address.setText(ExpertInfo.expertAddress);
        if(ExpertInfo.expertcert.equals("0")){
            cert.setText(getResources().getString(R.string.nocert));
            viewCertificates.setVisibility(View.INVISIBLE);
        }else {
            cert.setText(ExpertInfo.expertcert+" "+getResources().getString(R.string.cert));
            viewCertificates.setVisibility(View.VISIBLE);
        }

        if(ExpertInfo.expertserviceIndoor){
            serviceType.setText(getResources().getString(R.string.outdoortv));
        }else {
            serviceType.setText(getResources().getString(R.string.outdoorserv));
        }


        //get about expert and exp years
        Generator.createService(RihannaAPI.class).getExperinces(ExpertInfo.expertId).enqueue(new Callback<GetExperinces>() {
            @Override
            public void onResponse(Call<GetExperinces> call, Response<GetExperinces> response) {
                if(response.isSuccessful()){
                   if(response.body().getExperiences()!=null) {
                       if(!response.body().getExperiences().isEmpty()) {
                           aboutExpert.setText(response.body().getExperiences().get(0).getAboutExpert());
                           expyears.setText(response.body().getExperiences().get(0).getYearsOfExperience() + " " + getResources().getString(R.string.yesars));
                       }else {
                           aboutExpert.setText(getResources().getString(R.string.notset));
                           expyears.setText(getResources().getString(R.string.notset));
                       }
                   }else {
                       Toast.makeText(getContext(),"Null from expert expertise about expert tab",Toast.LENGTH_SHORT).show();
                   }
                }else {
                    try {
                        Toast.makeText(getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetExperinces> call, Throwable t) {
                Toast.makeText(getContext(),"Connection error from expert expertise about expert tab "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });



        viewCertificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                viewCertificates.clearAnimation();
                viewCertificates.setAnimation(anim);

                FireDialog.certificatesDialog(getContext(),ExpertInfo.expertId,viewCertificates,getResources().getString(R.string.certification));
            }
        });
    }
}
