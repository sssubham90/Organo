package com.devil.organo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.devil.organo.models.CentreElement;
import com.devil.organo.adapter.CentreListAdapter;
import com.devil.organo.helpers.ListDividerItem;
import com.devil.organo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CentreActivity extends AppCompatActivity {
    private List<CentreElement> CentreList = new ArrayList<>();
    private CentreListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        getData();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new CentreListAdapter(CentreActivity.this, CentreList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CentreActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListDividerItem(CentreActivity.this, LinearLayoutManager.VERTICAL, R.drawable.listdivider));
        recyclerView.setAdapter(mAdapter);
    }

    void getData(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("centre");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long n = dataSnapshot.getChildrenCount();
                for(long i=1;i<=n;i++){
                    String centreName = dataSnapshot.child(Long.toString(i)).child("name").getValue().toString();
                    String centreID = dataSnapshot.child(Long.toString(i)).child("id").getValue().toString();
                    String address = dataSnapshot.child(Long.toString(i)).child("address").getValue().toString();
                    String lat = dataSnapshot.child(Long.toString(i)).child("lat").getValue().toString();
                    String lng = dataSnapshot.child(Long.toString(i)).child("lng").getValue().toString();
                    CentreList.add(new CentreElement(centreName,centreID,address,Double.parseDouble(lat),Double.parseDouble(lng)));
                    mAdapter.notifyItemInserted((int) (i-1));
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CentreActivity", "Failed to read value.", error.toException());
            }
        });
    }
}