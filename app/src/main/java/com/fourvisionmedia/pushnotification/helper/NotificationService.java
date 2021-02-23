package com.fourvisionmedia.pushnotification.helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.fourvisionmedia.pushnotification.R;
import com.fourvisionmedia.pushnotification.base.Constant;
import com.fourvisionmedia.pushnotification.model.Message;
import com.fourvisionmedia.pushnotification.ui.detail.DetailActivity;

public class NotificationService extends BroadcastReceiver {

    private static final String TAG = "NotificationServiceTag";

    public static final String NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ONE";
    public static final String NOTIFICATION_INTENT_ID = "NOTIFICATION_ID";
    public static final String NOTIFICATION_INTENT_TITLE = "NOTIFICATION_INTENT_TITLE";
    public static final String NOTIFICATION_INTENT_MESSAGE = "NOTIFICATION_INTENT_MESSAGE";


    @Override
    public void onReceive(Context context, Intent intent) {
        //inisialisasi notifikasi
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        int id = intent.getIntExtra(NOTIFICATION_INTENT_ID, 0);
        String titleIntent = intent.getStringExtra(NOTIFICATION_INTENT_TITLE);
        String messageIntent = intent.getStringExtra(NOTIFICATION_INTENT_MESSAGE);

        Message message = new Message(titleIntent, messageIntent);

        createNotification(id, context, notificationManager, message);
    }

    private void createNotification(int id, Context context, NotificationManager notificationManager, Message message) {
        //Get data from form intent
        Intent intent = new Intent(context, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(Constant.INTENT_DETAIL, message);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, Constant.NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(message.getTitle())
                        .setContentText(message.getMessage())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        notificationManager.notify(id, notificationBuilder.build());
    }
}
