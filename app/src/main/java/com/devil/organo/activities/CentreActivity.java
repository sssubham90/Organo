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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        getData();
        CentreListAdapter mAdapter = new CentreListAdapter(this, CentreList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListDividerItem(this, LinearLayoutManager.VERTICAL, R.drawable.listdivider));
        recyclerView.setAdapter(mAdapter);
    }

    void getData(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("centres");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long n = dataSnapshot.getChildrenCount();
                for(long i=0;i<n;i++){
                    String centreName = dataSnapshot.child("i").child("name").getValue().toString();
                    String centreID = dataSnapshot.child("i").child("id").getValue().toString();
                    String address = dataSnapshot.child("i").child("address").getValue().toString();
                    String lat = dataSnapshot.child("i").child("lat").getValue().toString();
                    String lng = dataSnapshot.child("i").child("lng").getValue().toString();
                    CentreList.add(new CentreElement(centreName,centreID,address,Double.parseDouble(lat),Double.parseDouble(lng)));
                }
                String value = dataSnapshot.getValue(String.class);
                Log.d("CentreActivity", "Value is: " + value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CentreActivity", "Failed to read value.", error.toException());
            }
        });
    }
}