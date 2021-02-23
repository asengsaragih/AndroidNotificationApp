package com.fourvisionmedia.pushnotification.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fourvisionmedia.pushnotification.database.DatabaseClient;
import com.fourvisionmedia.pushnotification.database.Notification;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository {
    private final DatabaseClient mDatabase;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MainRepository(Application application) {
        this.mDatabase = new DatabaseClient(application);
    }

    public LiveData<List<Notification>> getNotifications() {
        return mDatabase.getNotifications();
    }

    public void update(int id) {
        executorService.execute(() -> mDatabase.update(id));
    }
}
