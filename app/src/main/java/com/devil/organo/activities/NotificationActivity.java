package com.devil.organo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.devil.organo.R;
import com.devil.organo.adapter.NotificationListAdapter;
import com.devil.organo.helpers.ListDividerItem;
import com.devil.organo.helpers.SessionManager;
import com.devil.organo.models.NotificationElement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private List<NotificationElement> NotificationList = new ArrayList<>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        sessionManager = new SessionManager(this);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        getData();
        NotificationListAdapter mAdapter = new NotificationListAdapter(this, NotificationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListDividerItem(this, LinearLayoutManager.VERTICAL, R.drawable.listdivider));
        recyclerView.setAdapter(mAdapter);
    }

    void getData(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user"+sessionManager.getEmail()+"/notification");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long n = dataSnapshot.getChildrenCount();
                for(long i=0;i<n;i++){
                    String date = dataSnapshot.child("i").child("date").getValue().toString();
                    String message = dataSnapshot.child("i").child("message").getValue().toString();
                    NotificationList.add(new NotificationElement(date,message));
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