package org.rjung.ssn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.rjung.ssn.notification.NotificationBuilder;

public class NotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            NotificationBuilder.scheduleNotification(context);
        }
    }

}
