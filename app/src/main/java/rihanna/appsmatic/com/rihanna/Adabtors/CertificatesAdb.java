package rihanna.appsmatic.com.rihanna.Adabtors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import rihanna.appsmatic.com.rihanna.API.Models.Certificates.CertificatesList;
import rihanna.appsmatic.com.rihanna.R;

/**
 * Created by Eng Ali on 12/17/2017.
 */
public class CertificatesAdb extends RecyclerView.Adapter<CertificatesAdb.certVh> {

    private Context context;
    private CertificatesList certificatesList;

    public CertificatesAdb(Context context, CertificatesList certificatesList) {
        this.context = context;
        this.certificatesList = certificatesList;
    }

    @Override
    public certVh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new certVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_certificate,parent,false));
    }

    @Override
    public void onBindViewHolder(certVh holder, int position) {

        animate(holder);
        holder.name.setText(certificatesList.getCertificates().get(position).getName());
        holder.spicialty.setText(certificatesList.getCertificates().get(position).getServiceCategoryName());
        holder.grantor.setText(certificatesList.getCertificates().get(position).getAuthorizedBy());
        holder.year.setText(certificatesList.getCertificates().get(position).getYearAcquired()+"");
    }

    @Override
    public int getItemCount() {
        return certificatesList.getCertificates().size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    public static class certVh extends RecyclerView.ViewHolder{

        private TextView name,spicialty,grantor,year;
        public certVh(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.cert_name_tv);
            spicialty=(TextView)itemView.findViewById(R.id.cert_sicilaty_tv);
            grantor=(TextView)itemView.findViewById(R.id.cert_granter_tv);
            year=(TextView)itemView.findViewById(R.id.cert_year_tv);


        }
    }
}
