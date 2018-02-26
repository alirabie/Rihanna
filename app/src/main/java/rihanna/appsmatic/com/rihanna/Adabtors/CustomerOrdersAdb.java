package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.CancleOrders.ChangingResponse;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.Order;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Activities.CustomerLocation;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Activities.OrderScreen;
import rihanna.appsmatic.com.rihanna.Fragments.ListOfOrders;
import rihanna.appsmatic.com.rihanna.Fragments.OrderDetailsFrag;
import rihanna.appsmatic.com.rihanna.OffLineOrder.offAddress;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;
import rihanna.appsmatic.com.rihanna.Utils;

import static java.security.AccessController.getContext;

/**
 * Created by Eng Ali on 1/1/2018.
 */
public class CustomerOrdersAdb extends RecyclerView.Adapter<CustomerOrdersAdb.OrdersVh> {

    Context context;
    List<Order>orders;
    private final int DECLINE=40;
    private Fragment fragment;

    public CustomerOrdersAdb(Context context, List<Order> orders, Fragment fragment) {
        this.context = context;
        this.orders = orders;
        this.fragment = fragment;
    }

    @Override
    public OrdersVh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrdersVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_requstes_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final OrdersVh holder, final int position) {

        holder.cancle.setVisibility(View.INVISIBLE);
        //animate(holder);
        holder.orderNum.setText(context.getResources().getString(R.string.ordernum)+orders.get(position).getId());
        if (orders.get(position).getServiceType()!=null) {
            if (orders.get(position).getServiceType().toString().equals("indoor")) {
                holder.serviceType.setText(context.getResources().getString(R.string.indoor));
            } else {
                holder.serviceType.setText(context.getResources().getString(R.string.outdoor));
            }
        }else {
            holder.serviceType.setText(context.getResources().getString(R.string.notset));
        }


        holder.totalPrice.setText(orders.get(position).getOrderTotal() + "");

        //status Control Logic
        switch (orders.get(position).getOrderStatus()) {
            case "Pending":
                holder.statusIdcator.setImageResource(R.drawable.waitingstatus);
                holder.orderStatus.setText(context.getResources().getString(R.string.orderstatus)+" : "+context.getResources().getString(R.string.pendingorders));
                holder.cancle.setVisibility(View.VISIBLE);
                break;
            case "Processing":
                holder.statusIdcator.setImageResource(R.drawable.acceptedstatus);
                holder.orderStatus.setText(context.getResources().getString(R.string.orderstatus)+" : "+context.getResources().getString(R.string.processingorders));
                holder.cancle.setVisibility(View.INVISIBLE);
                break;
            case "Complete":
                holder.statusIdcator.setImageResource(R.drawable.acceptedstatus);
                holder.orderStatus.setText(context.getResources().getString(R.string.orderstatus)+" : "+context.getResources().getString(R.string.completedorders));
                holder.cancle.setVisibility(View.INVISIBLE);
                break;
            case "Cancelled":
                holder.statusIdcator.setImageResource(android.R.drawable.ic_delete);
                holder.orderStatus.setText(context.getResources().getString(R.string.orderstatus)+" : "+context.getResources().getString(R.string.canceldorders));
                holder.cancle.setVisibility(View.INVISIBLE);
                break;
        }


        switch (orders.get(position).getPaymentStatus()){
            case "Pending":
                holder.paymentSataus.setText(context.getResources().getString(R.string.paymentstatus) + " : " + context.getResources().getString(R.string.pendingorders));
                break;
            case "Processing":
                holder.paymentSataus.setText(context.getResources().getString(R.string.paymentstatus) + " : " + context.getResources().getString(R.string.processingorders));
                break;
            case "Complete":
                holder.paymentSataus.setText(context.getResources().getString(R.string.paymentstatus) + " : " + context.getResources().getString(R.string.completedorders));
                break;
            case "Cancelled":
                holder.paymentSataus.setText(context.getResources().getString(R.string.paymentstatus) + " : " + context.getResources().getString(R.string.canceldorders));
                break;

        }

        holder.goOrderDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.goOrderDetailsBtn.clearAnimation();
                holder.goOrderDetailsBtn.setAnimation(anim);
                Home.setupCartBadge(0);
                OrderDetailsFrag orderDetailsFrag = new OrderDetailsFrag();
                Bundle bundle=new Bundle();
                bundle.putString("order", Utils.getGsonParser().toJson(orders.get(position)));
                orderDetailsFrag.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener,orderDetailsFrag);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
                //set title
                Home.tittle.setText(context.getResources().getString(R.string.orderdetails));
            }
        });




     holder.cancle.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
             holder.cancle.clearAnimation();
             holder.cancle.setAnimation(anim);

             //Initialize Dialog
             final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(context);
             dialogBuildercard
                     .withDuration(700)//def
                     .withEffect(Effectstype.Fall)
                     .withDialogColor(context.getResources().getColor(R.color.colorPrimary))
                     .withTitleColor(Color.BLACK)
                     .withMessage(context.getResources().getString(R.string.cancledialog))
                     .withTitle(context.getResources().getString(R.string.app_name))
                     .isCancelableOnTouchOutside(false)
                     .withButton1Text(context.getResources().getString(R.string.yes))
                     .withButton2Text(context.getResources().getString(R.string.no))
                     .setButton1Click(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Generator.createService(RihannaAPI.class).changeOrdrStatus(orders.get(position).getId(), DECLINE + "").enqueue(new Callback<ChangingResponse>() {
                                 @Override
                                 public void onResponse(Call<ChangingResponse> call, Response<ChangingResponse> response) {
                                     if (response.isSuccessful()) {
                                         if (response.body() != null) {
                                             if (response.body().getMessage().toString().equals("Status Updated")) {
                                                 Toast.makeText(context,context.getResources().getString(R.string.cancle), Toast.LENGTH_SHORT).show();
                                                 //Refresh Fragment
                                                 android.support.v4.app.FragmentManager fragmentManager2 = ((FragmentActivity) context).getSupportFragmentManager();
                                                 fragmentManager2.beginTransaction().detach(fragment).attach(fragment).commit();

                                             } else {
                                                 Toast.makeText(context, "Not Updated from change order status", Toast.LENGTH_SHORT).show();
                                             }
                                         } else {
                                             Toast.makeText(context, "null from change order status", Toast.LENGTH_SHORT).show();
                                         }
                                     } else {
                                         try {
                                             Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                         } catch (IOException e) {
                                             e.printStackTrace();
                                         }
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<ChangingResponse> call, Throwable t) {
                                     Toast.makeText(context, "connection error from change order status " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                             });
                             dialogBuildercard.dismiss();
                         }
                     })
                     .setButton2Click(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                             dialogBuildercard.dismiss();
                         }
                     }).show();

         }
     });



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    public static class OrdersVh extends RecyclerView.ViewHolder{

        TextView orderNum,serviceType,orderStatus,paymentSataus,totalPrice,cancle;
        ImageView statusIdcator;
        LinearLayout goOrderDetailsBtn;


        public OrdersVh(View itemView) {
            super(itemView);

            orderNum=(TextView)itemView.findViewById(R.id.ordernum);
            serviceType=(TextView)itemView.findViewById(R.id.servicetype);
            orderStatus=(TextView)itemView.findViewById(R.id.orderstatus);
            paymentSataus=(TextView)itemView.findViewById(R.id.paymentstatus);
            totalPrice=(TextView)itemView.findViewById(R.id.req_list_serv_price);

            cancle=(TextView)itemView.findViewById(R.id.req_list_serv_cancle);
            statusIdcator=(ImageView)itemView.findViewById(R.id.req_list_serv_status);
            goOrderDetailsBtn=(LinearLayout)itemView.findViewById(R.id.goorderdetails);





        }
    }
}
