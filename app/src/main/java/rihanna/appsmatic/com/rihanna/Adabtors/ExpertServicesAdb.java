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

import java.util.List;

import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.ResExpertServices;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.Service;
import rihanna.appsmatic.com.rihanna.Dilaogs.FireDialog;
import rihanna.appsmatic.com.rihanna.Fragments.Services;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/11/2017.
 */
public class ExpertServicesAdb extends RecyclerView.Adapter<ExpertServicesAdb.Vh002> {

    private Context context;
    private List<Service> expertServices;
    private Fragment fragment;


    public ExpertServicesAdb(Context context, List<Service> expertServices, Fragment fragment) {
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
            holder.serviceName.setText(expertServices.get(position).getServiceName() + "");
            holder.price.setText(expertServices.get(position).getPrice() + "");
            holder.unSubscribeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                    holder.unSubscribeBtn.clearAnimation();
                    holder.unSubscribeBtn.setAnimation(anim);
                    FireDialog.pickService(context, holder.unSubscribeBtn, expertServices.get(position).getExpertId() + "", expertServices.get(position).getServiceId() + "",
                            expertServices.get(position).getServiceName(),
                            expertServices.get(position).getPrice());
                }

            });
        }


    @Override
    public int getItemCount() {
        return expertServices.size();
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
