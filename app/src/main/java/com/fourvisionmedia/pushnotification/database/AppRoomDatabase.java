package com.fourvisionmedia.pushnotification.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fourvisionmedia.pushnotification.base.Constant;

@Database(entities = {Notification.class}, version = Constant.DATABASE_VERSION)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract NotificationDAO notificationDAO();

    private static volatile AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, Constant.DATABASE_NAME)
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
