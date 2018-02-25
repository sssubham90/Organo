package com.devil.organo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.devil.organo.R;

public class HowToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        if(this.getSupportActionBar()!=null)
            this.getSupportActionBar().hide();
    }
}
