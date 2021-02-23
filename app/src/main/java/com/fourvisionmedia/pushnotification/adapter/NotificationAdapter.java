package com.fourvisionmedia.pushnotification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fourvisionmedia.pushnotification.R;
import com.fourvisionmedia.pushnotification.database.Notification;
import com.fourvisionmedia.pushnotification.model.Message;
import com.google.gson.Gson;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    private final Context context;
    private List<Notification> data;
    private final ClickHandler handler;
    private final Gson gson;

    public NotificationAdapter(Context context, List<Notification> data, ClickHandler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
        this.gson = new Gson();
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_notification, parent, false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        Notification notification = data.get(position);
        Message message = gson.fromJson(notification.getData(), Message.class);

        holder.title.setText(message.getTitle());
        holder.message.setText(message.getMessage());

        if (notification.isOpened())
            holder.isOpened.setVisibility(View.VISIBLE);
        else
            holder.isOpened.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(v -> handler.onItemClicked(notification));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void submitList(List<Notification> notifications) {
        this.data = notifications;
        notifyDataSetChanged();
    }

    static class NotificationHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final TextView message;
        final ImageView isOpened;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView_list_item_title);
            message = itemView.findViewById(R.id.textView_list_item_message);
            isOpened = itemView.findViewById(R.id.imageView_list_item_opened);
        }
    }

    public interface ClickHandler {
        void onItemClicked(Notification notification);
    }
}
