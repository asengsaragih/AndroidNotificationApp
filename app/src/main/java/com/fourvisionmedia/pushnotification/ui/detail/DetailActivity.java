package com.fourvisionmedia.pushnotification.ui.detail;

import android.os.Bundle;
import android.widget.TextView;

import com.fourvisionmedia.pushnotification.R;
import com.fourvisionmedia.pushnotification.base.BaseActivity;
import com.fourvisionmedia.pushnotification.base.Constant;
import com.fourvisionmedia.pushnotification.database.Notification;
import com.fourvisionmedia.pushnotification.model.Message;
import com.google.gson.Gson;

public class DetailActivity extends BaseActivity {

    private final Gson gson = new Gson();

    private TextView mIdTextView;
    private TextView mTitleTextView;
    private TextView mMessageTextView;
    private TextView mCreatedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //init
        init();

        //setData
        setData();
    }

    private void init() {
        mIdTextView = findViewById(R.id.textView_detai_id);
        mTitleTextView = findViewById(R.id.textView_detai_title);
        mMessageTextView = findViewById(R.id.textView_detai_message);
        mCreatedTextView = findViewById(R.id.textView_detai_created);
    }

    private void setData() {
        Message message = gson.fromJson(intentData().getData(), Message.class);

        mIdTextView.setText(String.valueOf(intentData().getId()));
        mTitleTextView.setText(message.getTitle());
        mMessageTextView.setText(message.getMessage());
        mCreatedTextView.setText(longToDate(intentData().getCreatedAt()));
    }

    private Notification intentData() {
        return (Notification) getIntent().getSerializableExtra(Constant.INTENT_DETAIL);
    }
}