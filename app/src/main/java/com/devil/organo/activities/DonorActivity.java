package com.devil.organo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.devil.organo.R;

public class DonorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        if(this.getSupportActionBar()!=null)
            this.getSupportActionBar().hide();
        findViewById(R.id.afterDeath).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorActivity.this,DonorFormsActivity.class).putExtra("value",0));
            }
        });
        findViewById(R.id.alive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorActivity.this,DonorFormsActivity.class).putExtra("value",1));
            }
        });
    }
}
