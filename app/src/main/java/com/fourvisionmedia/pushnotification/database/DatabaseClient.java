package com.fourvisionmedia.pushnotification.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DatabaseClient {
    private final AppRoomDatabase mDatabase;

    public DatabaseClient(Context context) {
        this.mDatabase = AppRoomDatabase.getDatabase(context);
    }

    public Long insert(Notification notification) {
        return mDatabase.notificationDAO().insert(notification);
    }

    public void delete(Notification notification) {
        mDatabase.notificationDAO().delete(notification);
    }

    public void update(int id) {
        mDatabase.notificationDAO().update(id);
    }

    public LiveData<List<Notification>> getNotifications() {
        return mDatabase.notificationDAO().getNotifications();
    }
}
