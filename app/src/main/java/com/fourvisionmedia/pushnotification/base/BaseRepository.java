package com.fourvisionmedia.pushnotification.base;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fourvisionmedia.pushnotification.database.DatabaseClient;
import com.fourvisionmedia.pushnotification.database.Notification;
import com.fourvisionmedia.pushnotification.helper.NotificationService;
import com.fourvisionmedia.pushnotification.model.Message;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseRepository {
    private final DatabaseClient mDatabase;
    private final Application mApplication;
    private static final String TAG = "BaseRepository";

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public BaseRepository(Application application) {
        this.mApplication = application;
        this.mDatabase = new DatabaseClient(application);
    }

    public void insert(Notification notification) {
        executorService.execute(() -> {
            Long id = mDatabase.insert(notification);

            String rawString = notification.getData();
            Gson gson = new Gson();

            Message message = gson.fromJson(rawString, Message.class);

            setNotification(id.intValue(), mApplication.getApplicationContext(), message);
        });
    }

    public void update(int id) {
        executorService.execute(() -> {
            mDatabase.update(id);
        });
    }

    public void delete(Notification notification) {
        executorService.execute(() -> {
            mDatabase.delete(notification);
        });
    }

    private void setNotification(int id, Context context, Message message) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.putExtra(NotificationService.NOTIFICATION_INTENT_ID, id);
        intent.putExtra(NotificationService.NOTIFICATION_INTENT_TITLE, message.getTitle());
        intent.putExtra(NotificationService.NOTIFICATION_INTENT_MESSAGE, message.getMessage());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
