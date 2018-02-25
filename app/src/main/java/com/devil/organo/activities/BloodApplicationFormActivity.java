package com.devil.organo.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.devil.organo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class BloodApplicationFormActivity extends AppCompatActivity {
    RadioButton radioButton;
    String fName,mName,lName,state,dob,sex,bloodGrp, address,city,pincode,email,mobile,kname,kmail,organ;
    TextView DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_application_form);
        if(this.getSupportActionBar()!=null)
            this.getSupportActionBar().hide();
        final Calendar c = Calendar.getInstance();
        radioButton = findViewById(R.id.male);
        radioButton.setChecked(true);
        DOB = findViewById(R.id.dob);
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BloodApplicationFormActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        DOB.setText(String.format(Locale.ENGLISH,"%02d-%02d-%02d", dayOfMonth, monthOfYear + 1, year));

                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        DOB.setText(String.format(Locale.ENGLISH,"%02d-%02d-%02d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR)));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.state)).setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.bloodGroups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.bloodGrp)).setAdapter(adapter);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    public void submit(){
        fName = ((EditText)findViewById(R.id.fname)).getText().toString();
        mName = ((EditText)findViewById(R.id.mname)).getText().toString();
        lName = ((EditText)findViewById(R.id.lname)).getText().toString();
        dob = DOB.getText().toString();
        if(radioButton.isChecked()) sex = "M"; else sex = "F";
        bloodGrp = ((Spinner)findViewById(R.id.bloodGrp)).getSelectedItem().toString();
        organ = "blood";
        address = ((EditText)findViewById(R.id.address)).getText().toString();
        city = ((EditText)findViewById(R.id.city)).getText().toString();
        state = ((Spinner)findViewById(R.id.state)).getSelectedItem().toString();
        pincode = ((EditText)findViewById(R.id.pincode)).getText().toString();
        email = ((EditText)findViewById(R.id.email)).getText().toString();
        mobile = ((EditText)findViewById(R.id.mobile)).getText().toString();
        kname = ((EditText)findViewById(R.id.kname)).getText().toString();
        kmail = ((EditText)findViewById(R.id.kmail)).getText().toString();
        if(validate())
            send();
    }

    private void send() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("form/NB");
        myRef.child("fName").push().setValue(fName);
        myRef.child("mName").push().setValue(mName);
        myRef.child("lName").push().setValue(lName);
        myRef.child("dob").push().setValue(dob);
        myRef.child("sex").push().setValue(sex);
        myRef.child("bloodGrp").push().setValue(bloodGrp);
        myRef.child("address").push().setValue(address);
        myRef.child("city").push().setValue(city);
        myRef.child("state").push().setValue(state);
        myRef.child("pincode").push().setValue(pincode);
        myRef.child("email").push().setValue(email);
        myRef.child("mobile").push().setValue(mobile);
        myRef.child("kname").push().setValue(kname);
        myRef.child("kmail").push().setValue(kmail);
    }

    public boolean validate(){
        if(fName.isEmpty()) {
            alertDialog("First Name field is empty",this);
            return false;
        }
        else if(mName.isEmpty()){
            alertDialog("Middle Name field is empty",this);
            return false;
        }
        else if(lName.isEmpty()){
            alertDialog("Last Name field is empty",this);
            return false;
        }
        else if(address.isEmpty()){
            alertDialog("Address field is empty",this);
            return false;
        }
        else if(city.isEmpty()){
            alertDialog("City field is empty",this);
            return false;
        }
        else if(pincode.isEmpty()){
            alertDialog("Pincode field is empty",this);
            return false;
        }
        else if(email.isEmpty()){
            alertDialog("Email field is empty",this);
            return false;
        }
        else if(mobile.isEmpty()){
            alertDialog("Mobile field is empty",this);
            return false;
        }
        else if(kname.isEmpty()){
            alertDialog("Please provide complete details of Emergency Contact",this);
            return false;
        }
        else if(kmail.isEmpty()){
            alertDialog("Please provide complete details of Emergency Contact",this);
            return false;
        }
        else
        return true;
    }

    public void alertDialog(String Message, Context context) {

        new AlertDialog.Builder(context).setTitle("Alert").setMessage(Message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

}
