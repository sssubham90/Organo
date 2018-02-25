package com.devil.organo.activities;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.devil.organo.fragments.AfterDeathFormFragment;
import com.devil.organo.fragments.BeforeDeathFormFragment;
import com.devil.organo.R;

public class DonorFormsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int value = getIntent().getIntExtra("value",0);
        setContentView(R.layout.activity_donor_forms);
        if(value==0){
            Fragment mFragment = new AfterDeathFormFragment();
            getFragmentManager().beginTransaction().add(R.id.form, mFragment).commit();
        }
        else {
            Fragment mFragment = new BeforeDeathFormFragment();
            getFragmentManager().beginTransaction().add(R.id.form, mFragment).commit();
        }
    }
}
