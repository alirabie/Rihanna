package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import java.util.List;

import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.Service;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Dilaogs.FireDialog;
import rihanna.appsmatic.com.rihanna.OffLineOrder.OffOrderItem;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/11/2017.
 */
public class ExpertDiscountedServicesAdb extends RecyclerView.Adapter<ExpertDiscountedServicesAdb.Vh002> {

    private Context context;
    private List<Service> expertServices;
    private Fragment fragment;


    public ExpertDiscountedServicesAdb(Context context, List<Service> expertServices, Fragment fragment) {
        this.context = context;
        this.expertServices = expertServices;
        this.fragment = fragment;
    }

    @Override
    public Vh002 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh002(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_expert_discounted_service,parent,false));
    }

    @Override
    public void onBindViewHolder(final Vh002 holder, final int position) {
        holder.setIsRecyclable(false);

        animate(holder);
            holder.serviceName.setText(expertServices.get(position).getServiceName() + "");
            holder.price.setText(expertServices.get(position).getPrice() + "");
            holder.discount.setText(expertServices.get(position).getPrice()-expertServices.get(position).getDiscountAmount()+"");
            holder.unSubscribeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                    holder.unSubscribeBtn.clearAnimation();
                    holder.unSubscribeBtn.setAnimation(anim);

                }

            });



        holder.unSubscribeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    if (Home.SetTimeForAllServices) {
                        OffOrderItem offOrderItem = new OffOrderItem();
                        offOrderItem.setId(expertServices.get(position).getServiceId() + "");
                        offOrderItem.setName(expertServices.get(position).getServiceName());
                        offOrderItem.setFromTime(Home.offOrderItem.getFromTime());
                        offOrderItem.setToTime(Home.offOrderItem.getToTime());
                        offOrderItem.setDate(Home.offOrderItem.getDate());
                        offOrderItem.setPrice(expertServices.get(position).getPrice() - expertServices.get(position).getDiscountAmount());
                        Home.orderItems.add(offOrderItem);

                    } else {
                        FireDialog.pickService(context, holder.unSubscribeBtn, expertServices.get(position).getExpertId() + "", expertServices.get(position).getServiceId() + "",
                                expertServices.get(position).getServiceName(),
                                expertServices.get(position).getPrice() - expertServices.get(position).getDiscountAmount());
                    }
                } else {
                    for (int i = 0; i < Home.orderItems.size(); i++) {
                        if (expertServices.get(position).getServiceId().toString().equals(Home.orderItems.get(i).getId().toString())) {
                            Home.orderItems.remove(i);
                            if (Home.orderItems.isEmpty()) {
                                Home.SetTimeForAllServices = false;
                            }
                            break;
                        }
                    }
                }


            }
        });


        holder.description.setText(expertServices.get(position).getDescription());
        holder.expandableLayout.initLayout();
        holder.expandableLayout.setInRecyclerView(true);
        holder.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandableLayout.toggle();
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

        TextView serviceName,price,discount;
        CheckBox unSubscribeBtn;
        ExpandableLinearLayout expandableLayout ;
        FrameLayout toggle;
        TextView description;

        public Vh002(View itemView) {
            super(itemView);
            serviceName=(TextView)itemView.findViewById(R.id.item_layout_service_name);
            price=(TextView)itemView.findViewById(R.id.item_layout_service_price);
            discount=(TextView)itemView.findViewById(R.id.discounted_price);
            unSubscribeBtn=(CheckBox)itemView.findViewById(R.id.item_layout_service_subscribe_btn);
            expandableLayout=(ExpandableLinearLayout)itemView.findViewById(R.id.expandable);
            description=(TextView)itemView.findViewById(R.id.discription_tv);
            toggle=(FrameLayout)itemView.findViewById(R.id.togle);
        }
    }
}
