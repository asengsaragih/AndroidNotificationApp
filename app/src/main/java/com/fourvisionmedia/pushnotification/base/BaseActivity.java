package com.fourvisionmedia.pushnotification.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.fourvisionmedia.pushnotification.database.Notification;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivityTag";

    protected AppCompatActivity mActivity;

    //pusher
    protected PusherOptions mPusherOptions;
    protected Pusher mPusher;
    protected Channel mChannel;

    //viewmodel
    private BaseViewModel mBaseViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        mBaseViewModel = new ViewModelProvider(this).get(BaseViewModel.class);

        //init pusher
        initPusher();

        //set pusher
        setPusher();
    }

    private void initPusher() {
        //init cluster
        mPusherOptions = new PusherOptions();
        mPusherOptions.setCluster(Constant.PUSHER_CLUSTER);

        //init pusher
        mPusher = new Pusher(Constant.PUSHER_KEY, mPusherOptions);

        //init channel
        mChannel = mPusher.subscribe(Constant.PUSHER_CHANNEL);
    }

    private void setPusher() {
        mPusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.d(TAG, "onConnectionStateChange Prev: " + change.getPreviousState());
                Log.d(TAG, "onConnectionStateChange Current: " + change.getCurrentState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                Log.d(TAG, "onError Message: " + message);
                Log.d(TAG, "onError Code: " + code);
                Log.d(TAG, "onError Exeption: " + e.getMessage());
            }
        }, ConnectionState.ALL);

        mChannel.bind(Constant.PUSHER_EVENT, new SubscriptionEventListener() {
            @Override
            public void onEvent(PusherEvent event) {
                Log.d(TAG, "onEvent: " + event.getData());
                mBaseViewModel.insert(new Notification(event.getData()));
            }
        });
    }

    protected String longToDate(Long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.getDefault());

        calendar.setTimeInMillis(timeInMillis);
        return simpleDateFormat.format(calendar.getTime());
    }
}
