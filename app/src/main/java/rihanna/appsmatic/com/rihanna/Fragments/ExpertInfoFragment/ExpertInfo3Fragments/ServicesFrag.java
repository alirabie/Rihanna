package rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.ResExpertServices;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.Service;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Adabtors.ExpertServicesAdb;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo;
import rihanna.appsmatic.com.rihanna.R;

public class ServicesFrag extends Fragment {

    private TextView emptyFlag;
    private RecyclerView servicesList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expert_services, container, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyFlag=(TextView)view.findViewById(R.id.empty_services_flag_frag);
        emptyFlag.setVisibility(View.INVISIBLE);

        //Setup Expert Services List
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getContext().getResources().getString(R.string.loading));
        mProgressDialog.show();


        //Add here expert Id from expert list adaptor               here !
        Generator.createService(RihannaAPI.class).getExpertServices(ExpertInfo.expertId).enqueue(new Callback<ResExpertServices>() {
            @Override
            public void onResponse(Call<ResExpertServices> call, Response<ResExpertServices> response) {
                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body().getServices() != null) {
                        if (response.body().getServices().isEmpty()) {
                            emptyFlag.setVisibility(View.VISIBLE);
                        } else {
                            //Collect none discounted services
                            List<Service>services=new ArrayList<Service>();
                            for(int i=0;i<response.body().getServices().size();i++){
                                if(response.body().getServices().get(i).getDiscountAmount()==0.0||response.body().getServices().get(i).getDiscountPercentage()==0.0){
                                    services.add(response.body().getServices().get(i));
                                }
                            }

                            if(services.isEmpty()){
                                emptyFlag.setVisibility(View.VISIBLE);
                            }else {
                                emptyFlag.setVisibility(View.INVISIBLE);
                                servicesList = (RecyclerView) view.findViewById(R.id.sevecices_frag_list);
                                servicesList.setAdapter(new ExpertServicesAdb(getContext(),services, ServicesFrag.this));
                                servicesList.setLayoutManager(new LinearLayoutManager(getContext()));
                            }


                        }
                    } else {
                        Toast.makeText(getContext(), "Null From get Expert Services", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    try {
                        Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResExpertServices> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getContext(), "Connection Error From get Expert Services " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
