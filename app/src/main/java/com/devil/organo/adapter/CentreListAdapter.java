package com.devil.organo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.devil.organo.models.CentreElement;
import com.devil.organo.R;

import java.util.List;

public class CentreListAdapter extends RecyclerView.Adapter<CentreListAdapter.MyViewHolder> {
    private List<CentreElement> centreList;
    private Context mcontext;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView centreName, centreID, address;
        Button map;
        MyViewHolder(View view) {
            super(view);
            centreName = view.findViewById(R.id.centreName);
            centreID = view.findViewById(R.id.centreID);
            address = view.findViewById(R.id.address);
            map = view.findViewById(R.id.vMap);
        }
    }


    public CentreListAdapter(Context context, List<CentreElement> centreList) {
        this.centreList = centreList;
        mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.centre_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CentreElement centreElement = centreList.get(position);
        holder.centreName.setText(centreElement.getCentreName());
        holder.centreID.setText(centreElement.getCentreID());
        holder.address.setText(centreElement.getAddress());
        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q="+centreElement.getLat()+","+centreElement.getLng());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mcontext.startActivity(mapIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return centreList.size();
    }
}