package com.fourvisionmedia.pushnotification.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.fourvisionmedia.pushnotification.database.Notification;

public class BaseViewModel extends AndroidViewModel {

    private final BaseRepository mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new BaseRepository(application);
    }

    public void insert(Notification notification) {
        mRepository.insert(notification);
    }

    public void update(int id) {
        mRepository.update(id);
    }

    public void delete(Notification notification) {
        mRepository.delete(notification);
    }
}
