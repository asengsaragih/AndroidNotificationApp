package com.fourvisionmedia.pushnotification.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fourvisionmedia.pushnotification.database.Notification;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final MainRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new MainRepository(application);
    }

    public void update(int id) {
        mRepository.update(id);
    }

    public LiveData<List<Notification>> getNotifications() {
        return mRepository.getNotifications();
    }
}
