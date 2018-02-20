package rihanna.appsmatic.com.rihanna;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.Order;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.ResOrderCreation;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;

/**
 * Created by Eng Ali on 2/20/2018.
 */
public class NotificationsService extends IntentService {
    public static int ordersCount=0;
    public static NotificationManager manager;
    static int notId=0;
    public NotificationsService() {
        super("");

    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.+s

        setOrdersCount(getApplicationContext());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Check every 1/2 min for orders
        final android.os.Handler mHandler = new android.os.Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(30000);

                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                Generator.createService(RihannaAPI.class).getCustomerOrdersById(SaveSharedPreference.getCustomerId(getApplicationContext())).enqueue(new Callback<ResOrderCreation>() {
                                    @Override
                                    public void onResponse(Call<ResOrderCreation> call, Response<ResOrderCreation> response) {
                                        if(response.isSuccessful()){

                                            if(response.body()!=null){
                                                List<Order>acceptedOrders=new ArrayList<Order>();
                                                //get Accepeted Oerders
                                                for (Order order :response.body().getOrders()){
                                                    if(order.getOrderStatus().equals("Processing")){
                                                        acceptedOrders.add(order);
                                                    }
                                                }

                                                if(acceptedOrders.size()>ordersCount) {
                                                    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                    NotificationCompat.Builder builder =
                                                            (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                                                                    .setSmallIcon(R.drawable.logo)
                                                                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                                                                    .setSound(alarmSound)
                                                                    .setContentTitle(getResources().getString(R.string.app_name))
                                                                    .setAutoCancel(true)
                                                                    .setContentText(getResources().getString(R.string.notifiction));
                                                    Intent notificationIntent = new Intent(getApplicationContext(), Home.class).putExtra("target", "orders");
                                                    TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getApplicationContext());
                                                    taskStackBuilder.addParentStack(Home.class);
                                                    taskStackBuilder.addNextIntent(notificationIntent);
                                                    PendingIntent contentIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                                                    builder.setContentIntent(contentIntent);
                                                    manager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                                                    manager.notify(notId, builder.build());
                                                    notId++;
                                                    ordersCount = acceptedOrders.size();
                                                }







                                            }else {
                                                Toast.makeText(getApplicationContext(),"Null from customer orders API",Toast.LENGTH_SHORT).show();
                                            }

                                        }else {
                                            try {
                                                Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResOrderCreation> call, Throwable t) {

                                        Toast.makeText(getApplicationContext(),"Connection Error from customer orders API" +t.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
      Intent restartService = new Intent("RestartService");
        sendBroadcast(restartService);
    }

    public static void setOrdersCount(final Context context){
        Generator.createService(RihannaAPI.class).getCustomerOrdersById(SaveSharedPreference.getCustomerId(context)).enqueue(new Callback<ResOrderCreation>() {
            @Override
            public void onResponse(Call<ResOrderCreation> call, Response<ResOrderCreation> response) {
                if(response.isSuccessful()){

                    if(response.body()!=null){
                        List<Order> acceptedOrders=new ArrayList<Order>();
                        //get Accepeted Oerders
                        for (Order order :response.body().getOrders()){
                            if(order.getOrderStatus().equals("Processing")){
                                acceptedOrders.add(order);
                            }
                        }

                        ordersCount=acceptedOrders.size();

                    }else {
                        Toast.makeText(context, "Null from customer orders API", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    try {
                        Toast.makeText(context,response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResOrderCreation> call, Throwable t) {

                Toast.makeText(context,"Connection Error from customer orders API" +t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
