package com.devil.organo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.devil.organo.helpers.ListDividerItem;
import com.devil.organo.R;
import com.devil.organo.helpers.SessionManager;
import com.devil.organo.models.NotificationElement;
import com.devil.organo.models.RecentElement;
import com.devil.organo.adapter.RecentListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecentActivity extends AppCompatActivity {
    private List<RecentElement> RecentList = new ArrayList<>();
    private SessionManager sessionManager;
    private RecentListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        sessionManager = new SessionManager(this);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        getData();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new RecentListAdapter(RecentActivity.this, RecentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(RecentActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListDividerItem(RecentActivity.this, LinearLayoutManager.VERTICAL, R.drawable.listdivider));
        recyclerView.setAdapter(mAdapter);
    }

    void getData(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(sessionManager.getEmail()+"/activity");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long n = dataSnapshot.getChildrenCount();
                for(long i=0;i<n;i++){
                    String date = dataSnapshot.child(Long.toString(i)).child("date").getValue().toString();
                    String id = dataSnapshot.child(Long.toString(i)).child("id").getValue().toString();
                    String name = dataSnapshot.child(Long.toString(i)).child("name").getValue().toString();
                    String centreName = dataSnapshot.child(Long.toString(i)).child("centreName").getValue().toString();
                    String organ = dataSnapshot.child(Long.toString(i)).child("organ").getValue().toString();
                    RecentList.add(new RecentElement(date,id,name,centreName,organ));
                    mAdapter.notifyItemInserted((int) i);
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