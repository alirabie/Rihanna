package rihanna.appsmatic.com.rihanna.Dilaogs;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import junit.framework.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Certificates.CertificatesList;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Adabtors.CertificatesAdb;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/17/2017.
 */
public class FireDialog {

    //Comments Dialog
    public static void CommentsDialog(final Context context,View view, String exId,String name){


        final TextView send,title,custreviewNum,emptyFlag;
        RatingBar ratingBar;
        ImageView back;
        RecyclerView commentsList;


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
        send=(TextView)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_send_btn);
        custreviewNum=(TextView)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_userscomments);
        emptyFlag=(TextView)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_empty_flag);
        ratingBar=(RatingBar)dialogBuildercard.findViewById(R.id.expert_details_ratingfrag_users_rating);










    }


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














}
