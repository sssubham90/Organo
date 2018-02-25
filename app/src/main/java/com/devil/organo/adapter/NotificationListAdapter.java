package com.devil.organo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.devil.organo.R;
import com.devil.organo.models.NotificationElement;
import com.devil.organo.models.RecentElement;

import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {
    private List<NotificationElement> notificationList;
    private Context mcontext;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, message;
        MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
            message = view.findViewById(R.id.message);
        }
    }


    public NotificationListAdapter(Context context, List<NotificationElement> notificationList) {
        this.notificationList = notificationList;
        mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final NotificationElement notificationElement = notificationList.get(position);
        holder.date.setText(notificationElement.getDate());
        holder.message.setText(notificationElement.getMessage());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}