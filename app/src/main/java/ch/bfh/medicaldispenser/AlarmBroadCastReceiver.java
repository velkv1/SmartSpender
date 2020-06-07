package ch.bfh.medicaldispenser;

import android.app.AppComponentFactory;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBroadCastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        /*if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
        }
        */

            Intent intent1 = new Intent(context, AlarmActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);

    }
}