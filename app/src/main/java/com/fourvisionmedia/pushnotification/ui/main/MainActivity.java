package com.fourvisionmedia.pushnotification.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fourvisionmedia.pushnotification.R;
import com.fourvisionmedia.pushnotification.adapter.NotificationAdapter;
import com.fourvisionmedia.pushnotification.base.BaseActivity;
import com.fourvisionmedia.pushnotification.base.Constant;
import com.fourvisionmedia.pushnotification.database.Notification;
import com.fourvisionmedia.pushnotification.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivityTag";
    private MainViewModel mMainViewModel;
    private RecyclerView mNotificationRecycleview;
    private NotificationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view model
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //init
        init();

        //set Data
        setData();
    }

    private void setData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());

        mNotificationRecycleview.setLayoutManager(layoutManager);
        mNotificationRecycleview.addItemDecoration(itemDecoration);

        List<Notification> notifications = new ArrayList<>();

        mAdapter = new NotificationAdapter(this, notifications, notification -> {
            mMainViewModel.update(notification.getId());

            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(Constant.INTENT_DETAIL, notification);

            startActivity(intent);
        });

        mNotificationRecycleview.setAdapter(mAdapter);

        //get data from viewmodel
        mMainViewModel.getNotifications().observe(this, notificationList -> mAdapter.submitList(notificationList));
    }

    private void init() {
        mNotificationRecycleview = findViewById(R.id.recycle_notification);
    }
}