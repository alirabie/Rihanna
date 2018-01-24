package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import rihanna.appsmatic.com.rihanna.API.Models.Categories.ResCategory;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Fragments.Services;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 1/18/2018.
 */
public class CategoryAdb extends RecyclerView.Adapter<CategoryAdb.CateVh> {

    private Context context;
    private ResCategory categories;

    public CategoryAdb(Context context, ResCategory categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public CateVh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CateVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_categories,parent,false));
    }

    @Override
    public void onBindViewHolder(final CateVh holder, final int position) {

        animate(holder);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "arabic.ttf");
        if(SaveSharedPreference.getImgLoadingSatatus(context)) {

            if (categories.getCategories().get(position).getImage() != null) {

                Glide.with(context)
                        .load(categories.getCategories().get(position).getImage().getSrc().toString())
                        .asBitmap().override(1080, 600)
                        .placeholder(R.drawable.bgcategories)
                        .fitCenter()
                        .into(holder.categoryImage);


            } else {

                Glide.with(context)
                        .load(R.drawable.bgcategories)
                        .fitCenter()
                        .into(holder.categoryImage);

            }
        }else {
            Glide.with(context)
                    .load(R.drawable.bgcategories)
                    .fitCenter()
                    .into(holder.categoryImage);
        }

        holder.categoryName.setTypeface(face);
        holder.categoryName.setText(categories.getCategories().get(position).getName());



        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.categoryImage.clearAnimation();
                holder.categoryImage.setAnimation(anim);
                Services services = new Services();
                Bundle bundle = new Bundle();
                bundle.putString("sourceflag","filter");
                bundle.putString("category",categories.getCategories().get(position).getName());
                Home.selectedCategory=categories.getCategories().get(position).getName();
                bundle.putString("state","");
                bundle.putString("district","");
                bundle.putString("state","");
                bundle.putString("email","");
                bundle.putString("rate","");
                bundle.putString("country","");
                services.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, services);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
            }
        });




    }

    @Override
    public int getItemCount() {
        return categories.getCategories().size();
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
    public static class CateVh extends RecyclerView.ViewHolder{
        private ImageView categoryImage;
        private TextView categoryName;
        public CateVh(View itemView) {
            super(itemView);
            categoryImage=(ImageView)itemView.findViewById(R.id.category_image);
            categoryName=(TextView)itemView.findViewById(R.id.category_name);
        }
    }
}
