package rihanna.appsmatic.com.rihanna.Activities;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import rihanna.appsmatic.com.rihanna.API.Models.ExpertImages.Image;
import rihanna.appsmatic.com.rihanna.Adabtors.OfflineOrderItemsAdb;
import rihanna.appsmatic.com.rihanna.R;

public class OrderScreen extends AppCompatActivity {

    private RecyclerView orderItemsList;
    private TextView serviceTypeTv,expertName,orderNow;
    public static TextView totlPrice,count;


    private ImageView up,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order_info);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        totlPrice=(TextView)findViewById(R.id.shopping_cart_orderinfo_totalprice);
        up=(ImageView)findViewById(R.id.upcountcustomerts);
        down=(ImageView)findViewById(R.id.dwoncountcustomerts);
        count=(TextView)findViewById(R.id.shopping_cart_orderinfo_client_number);
        serviceTypeTv=(TextView)findViewById(R.id.service_type_tv);
        expertName=(TextView)findViewById(R.id.expert_name_tv);
        orderNow=(TextView)findViewById(R.id.shopping_cart_orderinfo_booknow_btn);

        count.setText(Home.customerCount+"");


        expertName.setText(Home.offOrderModel.getExpertName());
        serviceTypeTv.setText(Home.offOrderModel.getServiceType());

        
        orderItemsList=(RecyclerView)findViewById(R.id.shopping_cart_orderinfo_list);
        final OfflineOrderItemsAdb offlineOrderItemsAdb=new OfflineOrderItemsAdb(Home.orderItems, OrderScreen.this);
        orderItemsList.setAdapter(offlineOrderItemsAdb);
        orderItemsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        totlPrice.setText(offlineOrderItemsAdb.getSum() * Home.customerCount + "");

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                up.clearAnimation();
                up.setAnimation(anim);
                Home.customerCount++;
                count.setText(Home.customerCount + "");
                totlPrice.setText(offlineOrderItemsAdb.getSum() * Home.customerCount + "");
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                down.clearAnimation();
                down.setAnimation(anim);
                if(Home.customerCount==1){
                    return;
                }
                Home.customerCount--;
                count.setText(Home.customerCount + "");
                totlPrice.setText(offlineOrderItemsAdb.getSum() * Home.customerCount + "");
            }
        });




        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                orderNow.clearAnimation();
                orderNow.setAnimation(anim);
                Home.offOrderModel.setOffOrderItems(Home.orderItems);
                Gson gson =new Gson();
                Log.e("order : ",gson.toJson(Home.offOrderModel).toString());
            }
        });

    }



}
