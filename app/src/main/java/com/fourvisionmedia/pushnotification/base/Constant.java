package com.fourvisionmedia.pushnotification.base;

public class Constant {
    //base notification key
    public static final String PUSHER_APP_ID = "1159869";
    public static final String PUSHER_KEY = "f31d027b74b6e23973f7";
    public static final String PUSHER_SECRET = "f92204e4e0b8fe612560";
    public static final String PUSHER_CLUSTER = "mt1";
    //end notification keys

    //pusher channel and event
    public static final String PUSHER_CHANNEL = "notification_channel";
    public static final String PUSHER_EVENT = "notification_event";

    //Database Configuration (DONT TOUCH IT)
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notification_database";
    //END Database Configuration

    //database notification
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String NOTIFICATION_DATA = "notification_data";
    public static final String NOTIFICATION_IS_OPENED = "notification_is_opened";
    public static final String NOTIFICATION_CREATED_AT = "notification_created_at";
    public static final String NOTIFICATION_UPDATED_AT = "notification_updated_at";

    //intent
    public static final String INTENT_DETAIL = "detail_intent_id";
    public static final String NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ONE";

    //time format
    public static final String DATE_TIME_FORMAT = "dd MMMM yyyy HH:mm:ss";
}
