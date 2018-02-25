package com.devil.organo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.devil.organo.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        if(this.getSupportActionBar()!=null)
            this.getSupportActionBar().hide();
    }
}
