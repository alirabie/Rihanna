package rihanna.appsmatic.com.rihanna.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Categories.ResCategory;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Adabtors.CategoryAdb;
import rihanna.appsmatic.com.rihanna.R;


public class Categories extends Fragment {

    private RecyclerView categoriesList;
    private TextView emptyFlag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyFlag=(TextView)view.findViewById(R.id.emptyf);
        emptyFlag.setVisibility(View.INVISIBLE);

        //Get Categories
        //Loading Dialog
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.pleasewait));
        mProgressDialog.show();
        Generator.createService(RihannaAPI.class).getCategories().enqueue(new Callback<ResCategory>() {
            @Override
            public void onResponse(Call<ResCategory> call, Response<ResCategory> response) {
                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body().getCategories() != null) {
                        if(response.body().getCategories().isEmpty()){
                            emptyFlag.setVisibility(View.VISIBLE);
                        }else {
                            emptyFlag.setVisibility(View.INVISIBLE);
                            categoriesList=(RecyclerView)view.findViewById(R.id.categories_list);
                            categoriesList.setAdapter(new CategoryAdb(getContext(),response.body()));
                            categoriesList.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                    } else {

                        Toast.makeText(getContext(), "Null from categories Api", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    try {
                        Toast.makeText(getContext(), "Response Not Success from categories Api" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResCategory> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getContext(), "Connection Error from categories Api" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });










    }
}
