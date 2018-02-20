package rihanna.appsmatic.com.rihanna;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Eng Ali on 2/20/2018.
 */
public class RestartService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        context.startService(new Intent(context,NotificationsService.class));
    }

}
