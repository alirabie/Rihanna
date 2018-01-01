package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.OrderItem;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 1/1/2018.
 */
public class OrderItemsAdb extends RecyclerView.Adapter<OrderItemsAdb.OrderitemVh> {

    List<OrderItem>orderItems;
    Context context;

    public OrderItemsAdb(List<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;
    }

    @Override
    public OrderitemVh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderitemVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(OrderitemVh holder, int position) {

        holder.name.setText(orderItems.get(position).getProduct().getName());
        holder.price.setText(orderItems.get(position).getProduct().getPrice()+" "+context.getResources().getString(R.string.sr));

        if(orderItems.get(position).getServiceDate()==null){
            holder.dateTv.setText(context.getResources().getString(R.string.notset));
        }else {
            //Date setup
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            final SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

            Date date = null;
            try {
                date = sourceFormat.parse(orderItems.get(position).getServiceDate()+"");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String datet = DateFormat.format(date);

            holder.dateTv.setText(datet);
        }


        if(orderItems.get(position).getServiceTimeFrom()==null){
            holder.timefromTv.setText(context.getResources().getString(R.string.notset));
        }else {
            holder.timefromTv.setText(orderItems.get(position).getServiceTimeFrom().toString());
        }


        if(orderItems.get(position).getServiceTimeTo()==null){
            holder.timeToTv.setText(context.getResources().getString(R.string.notset));
        }else {
            holder.timeToTv.setText(orderItems.get(position).getServiceTimeTo().toString());
        }


    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public static class OrderitemVh extends RecyclerView.ViewHolder{

        private TextView name,price,dateTv,timefromTv,timeToTv;

        public OrderitemVh(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.order_items_name);
            price=(TextView)itemView.findViewById(R.id.order_items_price);
            dateTv=(TextView)itemView.findViewById(R.id.order_info_date);
            timefromTv=(TextView)itemView.findViewById(R.id.order_info_time_from);
            timeToTv=(TextView)itemView.findViewById(R.id.order_info_time_to);

        }
    }
}
