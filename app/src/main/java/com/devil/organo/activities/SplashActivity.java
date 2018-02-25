package com.devil.organo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.devil.organo.R;
import com.devil.organo.helpers.SessionManager;

public class SplashActivity extends AppCompatActivity {

    SessionManager session;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(this.getSupportActionBar()!=null)
            this.getSupportActionBar().hide();
        session = new SessionManager(getApplicationContext());
        mHandler.postDelayed(new Runnable() {
            public void run() {
                start();
            }
        }, 2000);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
    }

    public void start(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

}
