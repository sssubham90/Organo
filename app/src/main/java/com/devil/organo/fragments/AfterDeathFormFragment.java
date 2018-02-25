package com.devil.organo.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class AfterDeathFormFragment extends android.app.Fragment{
    View rootView;
    RadioButton radioButton;
    String fName,mName,lName,state,dob,sex,bloodGrp, address,city,pincode,email,mobile,kname,kmail;
    int organs[];
    TextView DOB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        organs = new int[6];
        final Calendar c = Calendar.getInstance();
        rootView = inflater.inflate(R.layout.fragment_after_death_form, container, false);
        CheckBox all = rootView.findViewById(R.id.all);
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ((CheckBox)rootView.findViewById(R.id.eye)).setChecked(b);
                ((CheckBox)rootView.findViewById(R.id.kidneys)).setChecked(b);
                ((CheckBox)rootView.findViewById(R.id.heart)).setChecked(b);
                ((CheckBox)rootView.findViewById(R.id.lungs)).setChecked(b);
                ((CheckBox)rootView.findViewById(R.id.liver)).setChecked(b);
                ((CheckBox)rootView.findViewById(R.id.pancrease)).setChecked(b);
            }
        });
        radioButton = rootView.findViewById(R.id.male);
        radioButton.setChecked(true);
        DOB = rootView.findViewById(R.id.dob);
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        DOB.setText(String.format(Locale.ENGLISH,"%02d-%02d-%02d", dayOfMonth, monthOfYear + 1, year));

                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        DOB.setText(String.format(Locale.ENGLISH,"%02d-%02d-%02d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR)));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) rootView.findViewById(R.id.state)).setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.bloodGroups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) rootView.findViewById(R.id.bloodGrp)).setAdapter(adapter);
        rootView.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        return rootView;
    }

    public void submit(){
        fName = ((EditText)rootView.findViewById(R.id.fname)).getText().toString();
        mName = ((EditText)rootView.findViewById(R.id.mname)).getText().toString();
        lName = ((EditText)rootView.findViewById(R.id.lname)).getText().toString();
        dob = DOB.getText().toString();
        if(radioButton.isChecked()) sex = "M"; else sex = "F";
        bloodGrp = ((Spinner)rootView.findViewById(R.id.bloodGrp)).getSelectedItem().toString();
        if(((CheckBox)rootView.findViewById(R.id.eye)).isChecked()) organs[0]=1; else organs[0] = 0;
        if(((CheckBox)rootView.findViewById(R.id.kidneys)).isChecked()) organs[1]=1; else organs[1] = 0;
        if(((CheckBox)rootView.findViewById(R.id.heart)).isChecked()) organs[2]=1; else organs[2] = 0;
        if(((CheckBox)rootView.findViewById(R.id.lungs)).isChecked()) organs[3]=1; else organs[3] = 0;
        if(((CheckBox)rootView.findViewById(R.id.liver)).isChecked()) organs[4]=1; else organs[4] = 0;
        if(((CheckBox)rootView.findViewById(R.id.pancrease)).isChecked()) organs[5]=1; else organs[5] = 0;
        address = ((EditText)rootView.findViewById(R.id.address)).getText().toString();
        city = ((EditText)rootView.findViewById(R.id.city)).getText().toString();
        state = ((Spinner)rootView.findViewById(R.id.state)).getSelectedItem().toString();
        pincode = ((EditText)rootView.findViewById(R.id.pincode)).getText().toString();
        email = ((EditText)rootView.findViewById(R.id.email)).getText().toString();
        mobile = ((EditText)rootView.findViewById(R.id.mobile)).getText().toString();
        kname = ((EditText)rootView.findViewById(R.id.kname)).getText().toString();
        kmail = ((EditText)rootView.findViewById(R.id.kmail)).getText().toString();
        if(validate())
            send();
    }

    private void send() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("form/AD");
        myRef.child("fName").push().setValue(fName);
        myRef.child("mName").push().setValue(mName);
        myRef.child("lName").push().setValue(lName);
        myRef.child("dob").push().setValue(dob);
        myRef.child("sex").push().setValue(sex);
        myRef.child("bloodGrp").push().setValue(bloodGrp);
        myRef.child("eye").push().setValue(organs[0]);
        myRef.child("kidneys").push().setValue(organs[1]);
        myRef.child("heart").push().setValue(organs[2]);
        myRef.child("lungs").push().setValue(organs[3]);
        myRef.child("liver").push().setValue(organs[4]);
        myRef.child("pancrease").push().setValue(organs[5]);
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
            alertDialog("First Name field is empty",getActivity());
            return false;
        }
        else if(mName.isEmpty()){
            alertDialog("Middle Name field is empty",getActivity());
            return false;
        }
        else if(lName.isEmpty()){
            alertDialog("Last Name field is empty",getActivity());
            return false;
        }
        else if(address.isEmpty()){
            alertDialog("Address field is empty",getActivity());
            return false;
        }
        else if(city.isEmpty()){
            alertDialog("City field is empty",getActivity());
            return false;
        }
        else if(pincode.isEmpty()){
            alertDialog("Pincode field is empty",getActivity());
            return false;
        }
        else if(email.isEmpty()){
            alertDialog("Email field is empty",getActivity());
            return false;
        }
        else if(mobile.isEmpty()){
            alertDialog("Mobile field is empty",getActivity());
            return false;
        }
        else if(kname.isEmpty()){
            alertDialog("Please provide complete details of Emergency Contact",getActivity());
            return false;
        }
        else if(kmail.isEmpty()){
            alertDialog("Please provide complete details of Emergency Contact",getActivity());
            return false;
        }
        else {
            int nod=0;
            for(int x : organs){
                nod+=x;
            }
            if(nod == 0){
                alertDialog("Please select an Organ to donate",getActivity());
                return false;
            }
        }
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
