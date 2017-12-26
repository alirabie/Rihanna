package rihanna.appsmatic.com.rihanna.Adabtors;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Activities.OrderScreen;
import rihanna.appsmatic.com.rihanna.OffLineOrder.OffOrderItem;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/26/2017.
 */
public class OfflineOrderItemsAdb extends RecyclerView.Adapter<OfflineOrderItemsAdb.OffVh> {

    List<OffOrderItem>offOrderItems;
    Context context;

    public OfflineOrderItemsAdb(List<OffOrderItem> offOrderItems, Context context) {
        this.offOrderItems = offOrderItems;
        this.context = context;
    }

    @Override
    public OffVh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OffVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_order_item_sammry,parent,false));
    }

    @Override
    public void onBindViewHolder(final OffVh holder, final int position) {

        holder.name.setText(offOrderItems.get(position).getName().toString());
        holder.date.setText(offOrderItems.get(position).getDate());
        holder.fro.setText(offOrderItems.get(position).getFromTime());
        holder.to.setText(offOrderItems.get(position).getToTime());
        holder.price.setText(offOrderItems.get(position).getPrice()+"");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.delete.clearAnimation();
                holder.delete.setAnimation(anim);
                Home.orderItems.remove(position);
                notifyDataSetChanged();
                OrderScreen.totlPrice.setText(getSum() * Home.customerCount + "");

                if(offOrderItems.size()==0){
                    Home.customerCount=1;
                    ((Activity)context).finish();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return offOrderItems.size();
    }

    public float getSum(){
        float x=0;
        for(int i=0;i<offOrderItems.size();i++){
            x=x+offOrderItems.get(i).getPrice();
        }
        return x;
    }


    public static class OffVh extends RecyclerView.ViewHolder{

        private TextView name,date,fro,to,price;
        private ImageView delete;
        public OffVh(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.service_name_tv);
            date=(TextView)itemView.findViewById(R.id.date_tv);
            fro=(TextView)itemView.findViewById(R.id.from_tv);
            to=(TextView)itemView.findViewById(R.id.to_tv);
            price=(TextView)itemView.findViewById(R.id.price_tv);
            delete=(ImageView)itemView.findViewById(R.id.delete_btn);
        }

    }
}
