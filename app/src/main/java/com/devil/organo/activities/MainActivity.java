package com.devil.organo.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.devil.organo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(this.getSupportActionBar()!=null)
            this.getSupportActionBar().hide();
        findViewById(R.id.center).setOnClickListener(this);
        findViewById(R.id.about).setOnClickListener(this);
        findViewById(R.id.howTo).setOnClickListener(this);
        findViewById(R.id.share).setOnClickListener(this);
        findViewById(R.id.donor1).setOnClickListener(this);
        findViewById(R.id.donor).setOnClickListener(this);
        findViewById(R.id.recent1).setOnClickListener(this);
        findViewById(R.id.recent).setOnClickListener(this);
        findViewById(R.id.centre1).setOnClickListener(this);
        findViewById(R.id.centre).setOnClickListener(this);
        findViewById(R.id.notification1).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);
        findViewById(R.id.blood1).setOnClickListener(this);
        findViewById(R.id.blood).setOnClickListener(this);
        findViewById(R.id.weblink).setOnClickListener(this);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        findViewById(R.id.toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.START,true);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.center:
                startActivity(new Intent(MainActivity.this,MapActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                break;
            case R.id.howTo:
                startActivity(new Intent(MainActivity.this,HowToActivity.class));
                break;
            case R.id.share:
                startActivity(new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, "Use ORGANO today and become a certified Organ/Blood Donor.")
                        .setType("text/plain"));
                break;
            case R.id.donor:
            case R.id.donor1:
                startActivity(new Intent(MainActivity.this,DonorActivity.class));
                break;
            case R.id.recent:
            case R.id.recent1:
                startActivity(new Intent(MainActivity.this,RecentActivity.class));
                break;
            case R.id.centre:
            case R.id.centre1:
                startActivity(new Intent(MainActivity.this,CentreActivity.class));
                break;
            case R.id.notification:
            case R.id.notification1:
                startActivity(new Intent(MainActivity.this,NotificationActivity.class));
                break;
            case R.id.blood:
            case R.id.blood1:
                startActivity(new Intent(MainActivity.this,BloodApplicationFormActivity.class));
                break;
            case R.id.weblink:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://riantservices.com/")));
        }
    }
}
