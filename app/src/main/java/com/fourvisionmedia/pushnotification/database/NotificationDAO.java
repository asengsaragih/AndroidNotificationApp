package com.fourvisionmedia.pushnotification.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Notification notification);

    @Delete
    void delete(Notification notification);

    @Query("UPDATE notification SET notification_is_opened = 1 WHERE notification_id = :id")
    void update(int id);

    @Query("SELECT * FROM notification ORDER BY notification_created_at DESC")
    LiveData<List<Notification>> getNotifications();
}
