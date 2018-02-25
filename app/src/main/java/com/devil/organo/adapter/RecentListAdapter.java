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
import com.devil.organo.models.RecentElement;

import java.util.List;

public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.MyViewHolder> {
    private List<RecentElement> recentList;
    private Context mcontext;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, donorID, donorName, centreName, donated;
        Button share;
        MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
            donorID = view.findViewById(R.id.donorID);
            donorName = view.findViewById(R.id.donorName);
            centreName = view.findViewById(R.id.centreName);
            donated = view.findViewById(R.id.donated);
            share = view.findViewById(R.id.share);
        }
    }


    public RecentListAdapter(Context context, List<RecentElement> recentList) {
        this.recentList = recentList;
        mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final RecentElement recentElement = recentList.get(position);
        holder.date.setText(recentElement.getDate());
        holder.donorID.setText(recentElement.getDonorID());
        holder.donorName.setText(recentElement.getDonorName());
        holder.centreName.setText(recentElement.getCentreName());
        holder.donated.setText(recentElement.getDonated());
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcontext.startActivity(new Intent().setAction(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT,
                        "I have donated "+recentElement.getDonated()+" at "+recentElement.getCentreName()+" on "+ recentElement.getDate()+" with Organo."
                        ).setType("text/plain"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentList.size();
    }
}