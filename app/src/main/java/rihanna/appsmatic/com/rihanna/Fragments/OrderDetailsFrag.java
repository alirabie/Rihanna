package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Order;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Adabtors.OrderItemsAdb;
import rihanna.appsmatic.com.rihanna.R;
import rihanna.appsmatic.com.rihanna.Utils;

public class OrderDetailsFrag extends Fragment {

    rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.Order order;

    private TextView orderNumTv,customerName,customerPhone,customerAddress,CustomerstateTv,finished,serviceTypeFlag,paymentType,totalPrice;
    private LinearLayout outDoorFlag,showOnmapBtn;
    private FrameLayout orderConfirmedFlag;
    private RecyclerView orderitemsList;
    private LinearLayout customerInfoPannel;
    private ImageView callCust,sms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       order=Utils.getGsonParser().fromJson(getArguments().get("order").toString(), rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.Order.class);

        orderNumTv=(TextView)view.findViewById(R.id.order_info_num_and_category);
        customerName=(TextView)view.findViewById(R.id.order_info_cust_name);
        customerPhone=(TextView)view.findViewById(R.id.order_info_cust_phone);
        customerAddress=(TextView)view.findViewById(R.id.order_info_cust_address);
        finished=(TextView)view.findViewById(R.id.finishorder_btn);
        serviceTypeFlag=(TextView)view.findViewById(R.id.servicetypeflag);
        CustomerstateTv=(TextView)view.findViewById(R.id.state_tv);
        paymentType=(TextView)view.findViewById(R.id.paymentstatus);
        totalPrice=(TextView)view.findViewById(R.id.totalprice);
        orderConfirmedFlag=(FrameLayout)view.findViewById(R.id.orderconfirmed_flag);
        CustomerstateTv=(TextView)view.findViewById(R.id.state_tv);
        orderConfirmedFlag.setVisibility(View.INVISIBLE);


        orderNumTv.setText(getContext().getResources().getString(R.string.ordernum) + order.getId() + " " + order.getOrderStatus());
        customerName.setText(order.getBillingAddress().getFirstName() + " " + order.getBillingAddress().getLastName());
        customerAddress.setText(order.getBillingAddress().getAddress1());
        customerPhone.setText(order.getBillingAddress().getPhoneNumber());
        CustomerstateTv.setText(order.getBillingAddress().getCity()+" "+order.getBillingAddress().getProvince()+"");
        totalPrice.setText(order.getOrderTotal()+" "+getResources().getString(R.string.sr));
        paymentType.setText(getContext().getResources().getString(R.string.paymentstatus) + " : " + order.getPaymentStatus());

        if (order.getServiceType()!=null) {
            if (order.getServiceType().toString().equals("indoor")) {
               serviceTypeFlag.setText(getContext().getResources().getString(R.string.indoor));
            } else {
                serviceTypeFlag.setText(getContext().getResources().getString(R.string.outdoor));
            }
        }else {
            serviceTypeFlag.setText(getContext().getResources().getString(R.string.notset));
        }


        if(order.getOrderStatus().equals("Pending")){
            finished.setVisibility(View.INVISIBLE);
        }



        if(order.getOrderItems()!=null) {
            orderitemsList = (RecyclerView) view.findViewById(R.id.order_info_order_list);
            orderitemsList.setAdapter(new OrderItemsAdb(order.getOrderItems(), getContext()));
            orderitemsList.setLayoutManager(new LinearLayoutManager(getContext()));
        }




    }



    @Override
    public void onResume() {
        super.onResume();

        if (getView() == null) {
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    ListOfOrders listOrders = new ListOfOrders();
                    android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontener,listOrders);
                    fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                    fragmentTransaction.commit();
                    //set title
                    Home.tittle.setText(getResources().getString(R.string.listorderstitle));
                    return true;
                }
                return false;
            }
        });

    }



    }