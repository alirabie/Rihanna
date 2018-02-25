package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.os.Bundle;
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

import java.util.List;

import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.Order;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Fragments.ListOfOrders;
import rihanna.appsmatic.com.rihanna.Fragments.OrderDetailsFrag;
import rihanna.appsmatic.com.rihanna.R;
import rihanna.appsmatic.com.rihanna.Utils;

/**
 * Created by Eng Ali on 1/1/2018.
 */
public class CustomerOrdersAdb extends RecyclerView.Adapter<CustomerOrdersAdb.OrdersVh> {

    Context context;
    List<Order>orders;


    public CustomerOrdersAdb(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public OrdersVh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrdersVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_requstes_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final OrdersVh holder, final int position) {

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
                break;
            case "Processing":
                holder.statusIdcator.setImageResource(R.drawable.acceptedstatus);
                holder.orderStatus.setText(context.getResources().getString(R.string.orderstatus)+" : "+context.getResources().getString(R.string.processingorders));
                break;
            case "Complete":
                holder.statusIdcator.setImageResource(R.drawable.acceptedstatus);
                holder.orderStatus.setText(context.getResources().getString(R.string.orderstatus)+" : "+context.getResources().getString(R.string.completedorders));
                break;
            case "Cancelled":
                holder.statusIdcator.setImageResource(android.R.drawable.ic_delete);
                holder.orderStatus.setText(context.getResources().getString(R.string.orderstatus)+" : "+context.getResources().getString(R.string.canceldorders));
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

        TextView orderNum,serviceType,orderStatus,paymentSataus,totalPrice;
        ImageView statusIdcator;
        LinearLayout goOrderDetailsBtn;


        public OrdersVh(View itemView) {
            super(itemView);

            orderNum=(TextView)itemView.findViewById(R.id.ordernum);
            serviceType=(TextView)itemView.findViewById(R.id.servicetype);
            orderStatus=(TextView)itemView.findViewById(R.id.orderstatus);
            paymentSataus=(TextView)itemView.findViewById(R.id.paymentstatus);
            totalPrice=(TextView)itemView.findViewById(R.id.req_list_serv_price);

            statusIdcator=(ImageView)itemView.findViewById(R.id.req_list_serv_status);
            goOrderDetailsBtn=(LinearLayout)itemView.findViewById(R.id.goorderdetails);





        }
    }
}
