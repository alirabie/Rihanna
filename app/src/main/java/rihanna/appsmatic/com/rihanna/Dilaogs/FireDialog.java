package rihanna.appsmatic.com.rihanna.Dilaogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import junit.framework.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Certificates.CertificatesList;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertTimes.SchdulesResponse;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.PostReview;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Rating;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Response.ResReview;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.GetReviews.GetReviews;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Adabtors.CertificatesAdb;
import rihanna.appsmatic.com.rihanna.Adabtors.CommentsAdb;
import rihanna.appsmatic.com.rihanna.R;
import rihanna.appsmatic.com.rihanna.SQLiteDB.DB;
import rihanna.appsmatic.com.rihanna.SQLiteDB.DB_Models.ExpertTime;

/**
 * Created by Eng Ali on 12/17/2017.
 */
public class FireDialog {

    public static List<String>timesFrom;
    public static List<String>timesTo;
    public static List<String>fromToTv;
    public static List<ExpertTime>expertTimes;
    public static String fromKey;
    public static String toKey;
    public static String dateKey;

    //Comments Dialog
    public static void CommentsDialog(final Context context,View view, String exId,int rate,String name){


        final TextView send,title,custreviewNum,emptyFlag;
        RatingBar ratingBar;
        ImageView back;
        final RecyclerView commentsList;


        //Initialize Done Dialog
        final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(context);
        dialogBuildercard
                .withDuration(700)//def
                .withEffect(Effectstype.Slit)
                .withDialogColor(Color.BLACK)
                .withTitleColor(Color.WHITE)
                .withTitle(name)
                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                .setCustomView(R.layout.fragment_ratingand_comments, view.getContext())
                .show();

       //Setup Items

        custreviewNum=(TextView)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_userscomments);
        emptyFlag=(TextView)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_empty_flag);
        ratingBar=(RatingBar)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_users_rating);
        commentsList=(RecyclerView)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_comments_list);
        emptyFlag.setVisibility(View.VISIBLE);
        ratingBar.setRating(rate);
        //Loading Dialog
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(context.getResources().getString(R.string.pleasewait));
        mProgressDialog.show();
        Generator.createService(RihannaAPI.class).getReviews(exId).enqueue(new Callback<GetReviews>() {
            @Override
            public void onResponse(Call<GetReviews> call, Response<GetReviews> response) {
                if(response.isSuccessful()){
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if(response.body().getRatings()!=null){
                        custreviewNum.setText(context.getResources().getString(R.string.rviewsnum)+" "+response.body().getRatings().size());
                        if(response.body().getRatings().isEmpty()){
                            emptyFlag.setVisibility(View.VISIBLE);
                        }else {
                            emptyFlag.setVisibility(View.INVISIBLE);
                            commentsList.setAdapter(new CommentsAdb(context, response.body()));
                            commentsList.setLayoutManager(new LinearLayoutManager(context));
                        }
                    }else {
                        Toast.makeText(context,"Null from get reviews API",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    try {
                        Toast.makeText(context,response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetReviews> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(context,"Connection error from get reviews API "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });







    }


    //Get Expert Certificates
    public static void certificatesDialog(final Context context,String expId,View view,String name){

        final RecyclerView certList;
        final ProgressBar progressBar;

        //Initialize Done Dialog
        final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(context);
        dialogBuildercard
                .withDuration(700)//def
                .withEffect(Effectstype.Slit)
                .withDialogColor(Color.WHITE)
                .withTitleColor(context.getResources().getColor(R.color.colorPrimary))
                .withTitle(name)
                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                .setCustomView(R.layout.certificates_dialog, view.getContext())
                .show();

        //setup items
        certList=(RecyclerView)dialogBuildercard.findViewById(R.id.certificates_list);
        progressBar=(ProgressBar)dialogBuildercard.findViewById(R.id.progressbar);




        //Get Certificates
        Generator.createService(RihannaAPI.class).getExpertCertificates(expId).enqueue(new Callback<CertificatesList>() {
            @Override
            public void onResponse(Call<CertificatesList> call, Response<CertificatesList> response) {

                if (response.isSuccessful()) {
                    if(progressBar.isShown())
                        progressBar.setVisibility(View.GONE);
                    if (response.body().getCertificates() != null) {
                        certList.setAdapter(new CertificatesAdb(context,response.body()));
                        certList.setLayoutManager(new LinearLayoutManager(context));
                    } else {
                        Toast.makeText(context, "Null from get certificates", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if(progressBar.isShown())
                        progressBar.setVisibility(View.GONE);
                    try {
                        Toast.makeText(context, "Not success from get certificates " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<CertificatesList> call, Throwable t) {
                if(progressBar.isShown())
                    progressBar.setVisibility(View.GONE);

                Toast.makeText(context, "Connection error from get certificates " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });










    }


    //Review Expert Dialog
    public static void experrReviewDailog(final Context context,View view, final String expertId, final String customerId,String name){

        final TextView send;
        final EditText comment;
        final RatingBar ratingBar;


        //Initialize Done Dialog
        final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(context);
        dialogBuildercard
                .withDuration(700)//def
                .withEffect(Effectstype.Slit)
                .withDialogColor(Color.BLACK)
                .withTitleColor(Color.WHITE)
                .withTitle(name)
                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                .setCustomView(R.layout.expert_review_dilaog, view.getContext())
                .show();

        send=(TextView)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_send_btn);
        comment=(EditText)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_comment_input);
        ratingBar=(RatingBar)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_rateoursevices);
        ratingBar.setRating(0);

        ratingBar.setMax(5);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                send.clearAnimation();
                send.setAnimation(anim);

                PostReview postReview = new PostReview();
                Rating rating = new Rating();
                rating.setCustomerId(Integer.parseInt(customerId));
                rating.setExpertId(Integer.parseInt(expertId));
                rating.setRating(Math.round(ratingBar.getRating()));
                rating.setReviewText(comment.getText().toString());

                postReview.setRating(rating);
                Generator.createService(RihannaAPI.class).AddReview(postReview).enqueue(new Callback<ResReview>() {
                    @Override
                    public void onResponse(Call<ResReview> call, Response<ResReview> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getRatings() != null) {
                                dialogBuildercard.dismiss();
                            } else {
                                Toast.makeText(context, "Null from rating API ", Toast.LENGTH_SHORT).show();
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
                    public void onFailure(Call<ResReview> call, Throwable t) {
                        Toast.makeText(context, "Connection Error from rating API " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
















    }


    public static void pickService (final Context context,View view,String expertId, final String serviceId,String serviceName){

        MaterialCalendarView calendarView;
        final BetterSpinner avalibalTimesSp;
        final TextView save;

        //Initialize Done Dialog
        final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(context);
        dialogBuildercard
                .withDuration(700)//def
                .withEffect(Effectstype.Fall)
                .withDialogColor(Color.WHITE)
                .withTitleColor(Color.BLACK)
                .withTitle(serviceName)
                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                .setCustomView(R.layout.pick_service_dilaog, view.getContext())
                .show();

        calendarView=(MaterialCalendarView)dialogBuildercard.findViewById(R.id.calendarView);
        save=(TextView)dialogBuildercard.findViewById(R.id.save_order_item_btn);
        calendarView.setDateSelected(CalendarDay.today(), true);
        avalibalTimesSp=(BetterSpinner)dialogBuildercard.findViewById(R.id.expert_times_sp);
        avalibalTimesSp.setAdapter(new ArrayAdapter<>(context, R.layout.drop_down_list_custome));



        //get data from server
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(context.getResources().getString(R.string.loading));
        mProgressDialog.show();
        Generator.createService(RihannaAPI.class).getExpertSchadules(expertId).enqueue(new Callback<SchdulesResponse>() {
            @Override
            public void onResponse(Call<SchdulesResponse> call, Response<SchdulesResponse> response) {
                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body().getDeliveryschedules() != null) {

                        expertTimes = new ArrayList<ExpertTime>();
                        for (int i = 0; i < response.body().getDeliveryschedules().size(); i++) {
                            ExpertTime expertTime = new ExpertTime();
                            expertTime.setDay(response.body().getDeliveryschedules().get(i).getDay() + 1);
                            expertTime.setFrom(response.body().getDeliveryschedules().get(i).getTimefrom());
                            expertTime.setTo(response.body().getDeliveryschedules().get(i).getTimeto());
                            expertTimes.add(expertTime);
                        }

                        Log.e("ggg", expertTimes.size() + "");

                    } else {
                        Toast.makeText(context, "Null from times API", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


            }

            @Override
            public void onFailure(Call<SchdulesResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(context, "Connection Error from times API " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(date.getYear(), date.getMonth(), date.getDay());

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(date.getYear(), date.getMonth(), date.getDay());
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
                dateKey = format.format(calendar.getTime());

                timesFrom = new ArrayList<String>();
                timesTo = new ArrayList<String>();
                fromToTv = new ArrayList<String>();

                if (filterExpertTimes(calendar.get(Calendar.DAY_OF_WEEK), expertTimes).isEmpty()) {
                    avalibalTimesSp.setHint(context.getResources().getString(R.string.didntwork));
                    avalibalTimesSp.setText("");
                }else {
                    avalibalTimesSp.setHint(context.getResources().getString(R.string.selectfromtimes));
                    avalibalTimesSp.setText("");
                }

                for (int i = 0; i < filterExpertTimes(calendar.get(Calendar.DAY_OF_WEEK), expertTimes).size(); i++) {
                    //Date setup
                    SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat DesiredFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
                    Date dateFrom = null;
                    try {
                        dateFrom = sourceFormat.parse(filterExpertTimes(calendar.get(Calendar.DAY_OF_WEEK), expertTimes).get(i).getFrom());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date dateTo = null;
                    try {
                        dateTo = sourceFormat.parse(filterExpertTimes(calendar.get(Calendar.DAY_OF_WEEK), expertTimes).get(i).getTo());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String formattedDateFrom = DesiredFormat.format(dateFrom.getTime());
                    String fromatedDateTo = DesiredFormat.format(dateTo.getTime());

                    timesFrom.add(formattedDateFrom);
                    timesTo.add(fromatedDateTo);
                    fromToTv.add(formattedDateFrom + " - " + fromatedDateTo);

                }

                avalibalTimesSp.setAdapter(new ArrayAdapter<>(context, R.layout.drop_down_list_custome, fromToTv));
                avalibalTimesSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        fromKey = timesFrom.get(position);
                        toKey = timesTo.get(position);
                    }
                });


            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                save.clearAnimation();
                save.setAnimation(anim);
                if(avalibalTimesSp.getText().toString().isEmpty()){
                    avalibalTimesSp.setError("!");
                }else {

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar today = Calendar.getInstance();
                    try {
                        Date selected = sdf.parse(dateKey);

                        if(selected.compareTo(today.getTime())>0){



                            //place id and date and time to order

                            Toast.makeText(context,"id : "+serviceId+" "+dateKey +" // "+fromKey + "--" + toKey, Toast.LENGTH_SHORT).show();
                            dialogBuildercard.dismiss();

                        }else {
                            Toast.makeText(context,"Invalid Date"+selected.compareTo(today.getTime()), Toast.LENGTH_SHORT).show();
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }






                }

            }
        });




    }



    public static List<ExpertTime>filterExpertTimes(int day,List<ExpertTime>expertTimes){
        List<ExpertTime>expertTimes1=new ArrayList<>();
        for (int i=0;i<expertTimes.size();i++){
            if(expertTimes.get(i).getDay()==day){
                expertTimes1.add(expertTimes.get(i));
            }

        }
        return expertTimes1;

    }







}
