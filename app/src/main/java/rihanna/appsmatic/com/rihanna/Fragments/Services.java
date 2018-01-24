package rihanna.appsmatic.com.rihanna.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Experts.ExpertsResponse;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Activities.CustomerLocation;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Adabtors.ExpertsAdb;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo;
import rihanna.appsmatic.com.rihanna.R;

public class Services extends Fragment {

    private RecyclerView expertsList;
    private TextView emptyFlag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_services, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        expertsList=(RecyclerView)view.findViewById(R.id.experts_list);
        emptyFlag=(TextView)view.findViewById(R.id.empty_list_flag);
        emptyFlag.setVisibility(View.INVISIBLE);



        //Get All Experts
    if(getArguments()==null){
        //Loading Dialog
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.pleasewait));
        mProgressDialog.show();
        Generator.createService(RihannaAPI.class).getAllExperts().enqueue(new Callback<ExpertsResponse>() {
            @Override
            public void onResponse(Call<ExpertsResponse> call, Response<ExpertsResponse> response) {
                if(response.isSuccessful()){
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if(response.body().getVendors()!=null){
                        if(response.body().getVendors().isEmpty()){
                            emptyFlag.setVisibility(View.VISIBLE);
                        }else {
                            emptyFlag.setVisibility(View.INVISIBLE);
                            expertsList.setAdapter(new ExpertsAdb(getContext(), response.body()));
                            expertsList.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                    }else {
                        Toast.makeText(getContext(),"Null from Experts API",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    try {
                        Toast.makeText(getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ExpertsResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getContext(),"Connection error from Experts API "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }else {

        if(getArguments().get("sourceflag").toString().equals("categories")) {
            //Loading Dialog
            final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(getResources().getString(R.string.pleasewait));
            mProgressDialog.show();
            Generator.createService(RihannaAPI.class).getExpertsByFilterComp(getArguments().getString("category") + "","", "", "","","","").enqueue(new Callback<ExpertsResponse>() {
                @Override
                public void onResponse(Call<ExpertsResponse> call, Response<ExpertsResponse> response) {
                    if (response.isSuccessful()) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        if (response.body().getVendors() != null) {
                            if (response.body().getVendors().isEmpty()) {
                                emptyFlag.setVisibility(View.VISIBLE);
                            } else {
                                emptyFlag.setVisibility(View.INVISIBLE);
                                expertsList.setAdapter(new ExpertsAdb(getContext(), response.body()));
                                expertsList.setLayoutManager(new LinearLayoutManager(getContext()));
                            }

                        } else {
                            Toast.makeText(getContext(), "Null from Experts API", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<ExpertsResponse> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Toast.makeText(getContext(), "Connection error from Experts API " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else if(getArguments().get("sourceflag").toString().equals("cites")){
            //Loading Dialog
            final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(getResources().getString(R.string.pleasewait));
            mProgressDialog.show();
            Generator.createService(RihannaAPI.class).getExpertsByFilterComp("", "",getArguments().getString("state") + "","","","","saudi arabia").enqueue(new Callback<ExpertsResponse>() {
                @Override
                public void onResponse(Call<ExpertsResponse> call, Response<ExpertsResponse> response) {
                    if (response.isSuccessful()) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        if (response.body().getVendors() != null) {
                            if (response.body().getVendors().isEmpty()) {
                                emptyFlag.setVisibility(View.VISIBLE);
                            } else {
                                emptyFlag.setVisibility(View.INVISIBLE);
                                expertsList.setAdapter(new ExpertsAdb(getContext(), response.body()));
                                expertsList.setLayoutManager(new LinearLayoutManager(getContext()));
                            }

                        } else {
                            Toast.makeText(getContext(), "Null from Experts API", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<ExpertsResponse> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Toast.makeText(getContext(), "Connection error from Experts API " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else if(getArguments().get("sourceflag").toString().equals("filter")){
            //Loading Dialog
            final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(getResources().getString(R.string.pleasewait));
            mProgressDialog.show();
            Generator.createService(RihannaAPI.class).getExpertsByFilterComp(
                    getArguments().getString("category") + "",
                    getArguments().getString("email") + "",
                    getArguments().getString("state") + "",
                    getArguments().getString("rate")+"",
                    getArguments().getString("district")+"",
                    getArguments().getString("keyword")+"",
                    getArguments().getString("country")+"")
            .enqueue(new Callback<ExpertsResponse>() {
                @Override
                public void onResponse(Call<ExpertsResponse> call, Response<ExpertsResponse> response) {
                    if (response.isSuccessful()) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        if (response.body().getVendors() != null) {
                            if (response.body().getVendors().isEmpty()) {
                                emptyFlag.setVisibility(View.VISIBLE);
                            } else {
                                emptyFlag.setVisibility(View.INVISIBLE);
                                expertsList.setAdapter(new ExpertsAdb(getContext(), response.body()));
                                expertsList.setLayoutManager(new LinearLayoutManager(getContext()));
                            }

                        } else {
                            Toast.makeText(getContext(), "Null from Experts API", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<ExpertsResponse> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Toast.makeText(getContext(), "Connection error from Experts API " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });





        }





    }
















    }
}
