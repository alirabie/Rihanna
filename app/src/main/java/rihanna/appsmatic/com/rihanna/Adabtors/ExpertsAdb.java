package rihanna.appsmatic.com.rihanna.Adabtors;

import android.app.Activity;
import android.content.Context;
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
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rihanna.appsmatic.com.rihanna.API.Models.Experts.ExpertsResponse;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo;
import rihanna.appsmatic.com.rihanna.Fragments.Services;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/16/2017.
 */
public class ExpertsAdb extends RecyclerView.Adapter<ExpertsAdb.Vholder> {

    Context context;
    ExpertsResponse expertsResponse;

    public ExpertsAdb(Context context, ExpertsResponse expertsResponse) {
        this.context = context;
        this.expertsResponse = expertsResponse;
    }

    @Override
    public Vholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_service,parent,false));
    }

    @Override
    public void onBindViewHolder(final Vholder holder, final int position) {

      //  animate(holder);
        holder.indoorImge.setVisibility(View.INVISIBLE);
        holder.certificateImge.setVisibility(View.INVISIBLE);


        if(expertsResponse.getVendors().get(position).getCertifications()==0){
            holder.certificateImge.setVisibility(View.INVISIBLE);
        }else {
            holder.certificateImge.setVisibility(View.VISIBLE);
        }

        if(expertsResponse.getVendors().get(position).getIndoor()){
            holder.indoorImge.setVisibility(View.VISIBLE);
        }else {
            holder.indoorImge.setVisibility(View.INVISIBLE);
        }

        holder.name.setText(expertsResponse.getVendors().get(position).getName()+"");
        holder.address.setText(expertsResponse.getVendors().get(position).getAddress() + "");

        if(SaveSharedPreference.getImgLoadingSatatus(context)) {

            if(expertsResponse.getVendors().get(position).getProfileImage()!=null) {
                Picasso.with(context).load(expertsResponse.getVendors().get(position).getProfileImage())
                        .placeholder(R.drawable.loadinggif).fit()
                        .into(holder.expertImg);
            }else {
                Picasso.with(context).load("http://mansour.msol5.com/wp-content/uploads/2016/09/banner_1.jpg")
                        .placeholder(R.drawable.loadinggif).fit()
                        .into(holder.expertImg);
            }
        }

        //set Expert Class

        if(expertsResponse.getVendors().get(position).getCustomerRoleName()!=null) {
            if (expertsResponse.getVendors().get(position).getCustomerRoleName().toString().equals("Expert A")) {
                holder.classImge.setImageResource(R.drawable.a_icon);
            } else if (expertsResponse.getVendors().get(position).getCustomerRoleName().toString().equals("Expert B")) {
                holder.classImge.setImageResource(R.drawable.b_icon);
            }
        }







        holder.ratingBar.setRating(expertsResponse.getVendors().get(position).getRating());
        holder.expertProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.expertProfileBtn.clearAnimation();
                holder.expertProfileBtn.setAnimation(anim);

                //reset offline order data
                Home.offOrderModel.reset();
                Home.customerCount=1;
                Home.orderItems.clear();


                ExpertInfo expertInfo=new ExpertInfo();
                Bundle bundle=new Bundle();
                bundle.putString("expertId",expertsResponse.getVendors().get(position).getId());
                bundle.putString("name",expertsResponse.getVendors().get(position).getName()+"");
                bundle.putString("expaddrss", expertsResponse.getVendors().get(position).getAddress());
                bundle.putString("expcert", expertsResponse.getVendors().get(position).getCertifications() + "");
                bundle.putBoolean("expisindoorserv", expertsResponse.getVendors().get(position).getIndoor());
                bundle.putInt("rate", expertsResponse.getVendors().get(position).getRating());
                if(expertsResponse.getVendors().get(position).getCustomerRoleName()!=null) {
                    bundle.putString("expertclass", expertsResponse.getVendors().get(position).getCustomerRoleName());
                }

                expertInfo.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, expertInfo);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
            }
        });





    }

    @Override
    public int getItemCount() {
        return expertsResponse.getVendors().size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    public static class Vholder extends RecyclerView.ViewHolder{
        private TextView name,address;
        private ImageView expertImg,certificateImge,indoorImge,classImge;
        private RelativeLayout expertProfileBtn;
        private RatingBar ratingBar;
        public Vholder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.expert_name_tv);
            address=(TextView)itemView.findViewById(R.id.expert_address_tv);
            expertImg=(ImageView)itemView.findViewById(R.id.expert_img);
            certificateImge=(ImageView)itemView.findViewById(R.id.cert_icon);
            classImge=(ImageView)itemView.findViewById(R.id.a_icon);
            indoorImge=(ImageView)itemView.findViewById(R.id.arrw_icon);
            ratingBar=(RatingBar)itemView.findViewById(R.id.ratingBar);
            expertProfileBtn=(RelativeLayout)itemView.findViewById(R.id.expert_btn);

        }
    }
}
