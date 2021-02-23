package com.fourvisionmedia.pushnotification.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fourvisionmedia.pushnotification.base.Constant;

import java.io.Serializable;

@Entity
public class Notification implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.NOTIFICATION_ID)
    protected Integer id;

    @ColumnInfo(name = Constant.NOTIFICATION_DATA)
    protected String data;

    @ColumnInfo(name = Constant.NOTIFICATION_IS_OPENED)
    protected boolean isOpened;

    @ColumnInfo(name = Constant.NOTIFICATION_CREATED_AT)
    protected Long createdAt;

    @ColumnInfo(name = Constant.NOTIFICATION_UPDATED_AT)
    protected Long updatedAt;

    public Notification(String data) {
        this.data = data;
        this.isOpened = false;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public Integer getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }
}
