package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.ResExpertServices;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/11/2017.
 */
public class ExpertServicesAdb extends RecyclerView.Adapter<ExpertServicesAdb.Vh002> {

    private Context context;
    private ResExpertServices expertServices;
    private Fragment fragment;


    public ExpertServicesAdb(Context context, ResExpertServices expertServices, Fragment fragment) {
        this.context = context;
        this.expertServices = expertServices;
        this.fragment = fragment;
    }

    @Override
    public Vh002 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh002(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_expert_service,parent,false));
    }

    @Override
    public void onBindViewHolder(final Vh002 holder, final int position) {

        animate(holder);
        holder.serviceName.setText(expertServices.getServices().get(position).getServiceName()+"");
        holder.price.setText(expertServices.getServices().get(position).getPrice()+"");
        holder.unSubscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.unSubscribeBtn.clearAnimation();
                holder.unSubscribeBtn.setAnimation(anim);

                //Add service to order list or shopping cart
                
            }
        });





    }

    @Override
    public int getItemCount() {
        return expertServices.getServices().size();
    }


    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
    public static class Vh002 extends RecyclerView.ViewHolder{

        TextView serviceName,price,unSubscribeBtn;

        public Vh002(View itemView) {
            super(itemView);
            serviceName=(TextView)itemView.findViewById(R.id.item_layout_service_name);
            price=(TextView)itemView.findViewById(R.id.item_layout_service_price);
            unSubscribeBtn=(TextView)itemView.findViewById(R.id.item_layout_service_subscribe_btn);
        }
    }
}
