package rihanna.appsmatic.com.rihanna.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Error.ResErrors;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertImages.Image;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.BillingAddress;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Order;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.OrderItem;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.PostOrder;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.ResOrderCreation;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Adabtors.OfflineOrderItemsAdb;
import rihanna.appsmatic.com.rihanna.Dilaogs.FireDialog;
import rihanna.appsmatic.com.rihanna.Fragments.Services;
import rihanna.appsmatic.com.rihanna.OffLineOrder.OffOrderModel;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

public class OrderScreen extends AppCompatActivity {

    private RecyclerView orderItemsList;
    private TextView serviceTypeTv,expertName,orderNow;
    public static TextView totlPrice,count;

    CheckBox paynoline,cod;


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
      //  up=(ImageView)findViewById(R.id.upcountcustomerts);
       // down=(ImageView)findViewById(R.id.dwoncountcustomerts);
      //  count=(TextView)findViewById(R.id.shopping_cart_orderinfo_client_number);
        serviceTypeTv=(TextView)findViewById(R.id.service_type_tv);
        expertName=(TextView)findViewById(R.id.expert_name_tv);
        orderNow=(TextView)findViewById(R.id.shopping_cart_orderinfo_booknow_btn);
        cod=(CheckBox)findViewById(R.id.shopping_cart_orderinfo_payinbutesenter_check);
        paynoline=(CheckBox)findViewById(R.id.shopping_cart_orderinfo_payonline_check);


        paynoline.setEnabled(false);






      //  count.setText(Home.customerCount+"");


        expertName.setText(Home.offOrderModel.getExpertName());


        //Check service type
        if(Home.offOrderModel.getServiceType().equals("in")){
            serviceTypeTv.setText(getResources().getString(R.string.indoor));
        }else if (Home.offOrderModel.getServiceType().equals("out")){
            serviceTypeTv.setText(getResources().getString(R.string.outdoor));
        }

        orderItemsList=(RecyclerView)findViewById(R.id.shopping_cart_orderinfo_list);
        final OfflineOrderItemsAdb offlineOrderItemsAdb=new OfflineOrderItemsAdb(Home.orderItems, OrderScreen.this);
        orderItemsList.setAdapter(offlineOrderItemsAdb);
        orderItemsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        totlPrice.setText(offlineOrderItemsAdb.getSum() * Home.customerCount + "");

        /*
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


*/

        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                orderNow.clearAnimation();
                orderNow.setAnimation(anim);
                Home.offOrderModel.setOffOrderItems(Home.orderItems);

                Gson gson =new Gson();
                Log.e("order : ", gson.toJson(convertToPostOrder(getApplicationContext(), Home.offOrderModel)).toString());
                //Loading Dialog
                final ProgressDialog mProgressDialog = new ProgressDialog(OrderScreen.this);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage(getResources().getString(R.string.pleasewait));
                mProgressDialog.show();
                Generator.createService(RihannaAPI.class).createOrder(convertToPostOrder(getApplicationContext(), Home.offOrderModel)).enqueue(new Callback<ResOrderCreation>() {
                    @Override
                    public void onResponse(Call<ResOrderCreation> call, Response<ResOrderCreation> response) {
                        if (response.isSuccessful()) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            if (response.body().getOrders() != null) {
                                FireDialog.thanksDialog(OrderScreen.this, orderNow, Home.offOrderModel.getExpertName());
                                //reset offline order data
                               // Home.offOrderModel.reset();
                                Home.customerCount=1;
                                Home.SetTimeForAllServices=false;
                                //Home.orderItems.clear();
                            } else {
                                Toast.makeText(getApplicationContext(), "Null from order creation API ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            try {
                                //Handel Error
                                Gson gson = new GsonBuilder().create();
                                ResErrors resErrors = new ResErrors();
                                resErrors = gson.fromJson(response.errorBody().string(), ResErrors.class);
                                if (resErrors.getErrors().getOrderPlacement() != null) {
                                    //Collect Error  Data
                                    String error = "";
                                    StringBuilder stringBuilder = new StringBuilder();
                                    if (!resErrors.getErrors().getOrderPlacement().isEmpty()) {
                                        //Put errors count
                                        for (int i = 0; i < resErrors.getErrors().getOrderPlacement().size(); i++) {
                                            stringBuilder.append(resErrors.getErrors().getOrderPlacement().get(i) + "\n");
                                        }

                                        error = stringBuilder.toString();
                                        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(OrderScreen.this);
                                        dialogBuilder
                                                .withTitle(getResources().getString(R.string.app_name))
                                                .withDialogColor(R.color.colorPrimary)
                                                .withTitleColor("#FFFFFF")
                                                .withIcon(getResources().getDrawable(R.drawable.logo))
                                                .withDuration(700)                                          //def
                                                .withEffect(Effectstype.RotateBottom)
                                                .withMessage(error + "")
                                                .show();
                                    }
                                    }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResOrderCreation> call, Throwable t) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Connection error from order creation API " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }



    public static PostOrder convertToPostOrder(Context context,OffOrderModel offOrderModel){
        PostOrder postOrder=new PostOrder();
        Order order =new Order();
        List<OrderItem> orderItems=new ArrayList<>();
        BillingAddress billingAddress=new BillingAddress();
        billingAddress.setAddress1(offOrderModel.getOffAddress().getAddr());
        billingAddress.setPhoneNumber(offOrderModel.getOffAddress().getPhoneNum());
        billingAddress.setCity(offOrderModel.getOffAddress().getDistrictName());
        billingAddress.setStateProvinceId(offOrderModel.getOffAddress().getStateId());
        billingAddress.setProvince(offOrderModel.getOffAddress().getStateName());


        billingAddress.setCountryId(69);
        billingAddress.setCountry("Saudi Arabia");
        billingAddress.setCreatedOnUtc("2017-09-05T21:12:45.233");
        billingAddress.setZipPostalCode("00");
        billingAddress.setEmail(SaveSharedPreference.getCustomerInfo(context).getCustomers().get(0).getEmail());
        billingAddress.setFirstName(SaveSharedPreference.getCustomerInfo(context).getCustomers().get(0).getFirstName().toString());
        billingAddress.setLastName(SaveSharedPreference.getCustomerInfo(context).getCustomers().get(0).getLastName().toString());

        for(int i=0;i<offOrderModel.getOffOrderItems().size();i++){
            OrderItem orderItem =new OrderItem();
            orderItem.setProductId(Integer.parseInt(offOrderModel.getOffOrderItems().get(i).getId()));
            orderItem.setQuantity(1);
            orderItem.setServiceDate(offOrderModel.getOffOrderItems().get(i).getDate());
            orderItem.setServiceTimeFrom(offOrderModel.getOffOrderItems().get(i).getFromTime());
            orderItem.setServiceTimeTo(offOrderModel.getOffOrderItems().get(i).getToTime());
            orderItems.add(orderItem);

        }

        order.setBillingAddress(billingAddress);
        order.setCustomerId(Integer.parseInt(SaveSharedPreference.getCustomerId(context)));
        order.setExpertId(Integer.parseInt(Home.offOrderModel.getExpertId()));
        order.setOrderItems(orderItems);
        order.setPaymentMethodSystemName("Payments.Manual");

        if(offOrderModel.getServiceType().equals("in"))
        {
            order.setServiceType("indoor");
        }else if(offOrderModel.getServiceType().equals("out"))
        {
            order.setServiceType("outdoor");
        }

        postOrder.setOrder(order);


        return postOrder;

    }


}
