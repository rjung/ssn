package org.rjung.ssn.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import org.rjung.ssn.NotificationPublisher;
import org.rjung.ssn.R;
import org.rjung.ssn.db.AttemptDatabase;


public class NotificationBuilder {

    public final static String NOTIFICATION = "ssn-notification";
    public final static String NOTIFICATION_ID = "ssn-notification-id";

    public static void scheduleNotification(Context context) {
        AttemptDatabase attempts = new AttemptDatabase(context);

        Notification notification =
                new NotificationCompat.Builder(context)
                        .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT))
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(context.getResources().getString(R.string.app_name))
                        .setContentText(context.getResources().getString(R.string.message_smoked_question)).build();
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.setAction(NOTIFICATION);
        notificationIntent.putExtra(NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, attempts.notifyIn(), pendingIntent);
    }

}
