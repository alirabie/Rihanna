package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RatingBar;
import android.widget.TextView;

import rihanna.appsmatic.com.rihanna.API.Models.Reviews.GetReviews.GetReviews;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/23/2017.
 */
public class CommentsAdb extends RecyclerView.Adapter<CommentsAdb.CommentsVh> {

    Context context;
    GetReviews getReviews;

    public CommentsAdb(Context context, GetReviews getReviews) {
        this.context = context;
        this.getReviews = getReviews;
    }

    @Override
    public CommentsVh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_comments_list,parent,false));
    }

    @Override
    public void onBindViewHolder(CommentsVh holder, int position) {
        animate(holder);
        holder.name.setText("Customer");
        holder.comment.setText(getReviews.getRatings().get(position).getReviewText());
        holder.ratingBar.setRating(getReviews.getRatings().get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return getReviews.getRatings().size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
    public static class CommentsVh extends RecyclerView.ViewHolder{

        TextView name,comment;
        RatingBar ratingBar;

        public CommentsVh(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.user_comment_name);
            comment=(TextView)itemView.findViewById(R.id.user_comment_comment);
            ratingBar=(RatingBar)itemView.findViewById(R.id.ratingBar2);

        }
    }
}
