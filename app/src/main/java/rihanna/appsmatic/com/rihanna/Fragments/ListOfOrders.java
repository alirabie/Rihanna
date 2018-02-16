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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.Order;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.ResOrderCreation;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Adabtors.CustomerOrdersAdb;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;


public class ListOfOrders extends Fragment {


    TextView noreqFlag,noacceptedFlag;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_of_orders, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noreqFlag=(TextView)view.findViewById(R.id.noreqflag);
        noacceptedFlag=(TextView)view.findViewById(R.id.noacceptedflag);
        noacceptedFlag.setVisibility(View.INVISIBLE);
        noreqFlag.setVisibility(View.INVISIBLE);

        //Loading Dialog
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.pleasewait));
        mProgressDialog.show();
        Generator.createService(RihannaAPI.class).getCustomerOrdersById(SaveSharedPreference.getCustomerId(getContext())).enqueue(new Callback<ResOrderCreation>() {
            @Override
            public void onResponse(Call<ResOrderCreation> call, Response<ResOrderCreation> response) {
                if(response.isSuccessful()){
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if(response.body()!=null){
                        List<Order>acceptedOrders=new ArrayList<Order>();
                        List<Order>pindingOrders=new ArrayList<Order>();




                        //Split Orders
                        for (Order order :response.body().getOrders()){
                            if(order.getOrderStatus().equals("Pending")){
                                pindingOrders.add(order);
                            }else {
                                acceptedOrders.add(order);
                            }
                        }


                        //fill pending orders
                        if(pindingOrders.isEmpty()){
                            noreqFlag.setVisibility(View.VISIBLE);
                        }else {
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mLayoutManager.setReverseLayout(true);
                            mLayoutManager.setStackFromEnd(true);
                            noreqFlag.setVisibility(View.INVISIBLE);
                            RecyclerView acceptedOrdersList=(RecyclerView)view.findViewById(R.id.requsted_list);
                            acceptedOrdersList.setAdapter(new CustomerOrdersAdb(getContext(),pindingOrders));
                            acceptedOrdersList.setLayoutManager(mLayoutManager);
                        }



                        //fill accepted orders
                        if(acceptedOrders.isEmpty()){
                            noacceptedFlag.setVisibility(View.VISIBLE);
                        }else {
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mLayoutManager.setReverseLayout(true);
                            mLayoutManager.setStackFromEnd(true);
                            noacceptedFlag.setVisibility(View.INVISIBLE);
                            RecyclerView acceptedOrdersList=(RecyclerView)view.findViewById(R.id.acepted_requsted_list);
                            acceptedOrdersList.setAdapter(new CustomerOrdersAdb(getContext(),acceptedOrders));
                            acceptedOrdersList.setLayoutManager(mLayoutManager);
                        }



                    }else {
                        Toast.makeText(getContext(),"Null from customer orders API",Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<ResOrderCreation> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getContext(),"Connection Error from customer orders API" +t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


























    }
}
