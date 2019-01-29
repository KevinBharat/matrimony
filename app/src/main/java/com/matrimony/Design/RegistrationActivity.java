package com.matrimony.Design;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.matrimony.Bean.Bean_Registration;
import com.matrimony.DBHelper.DB_City;
import com.matrimony.DBHelper.DB_Registration;
import com.matrimony.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity {

    EditText etDOB;
    EditText etName;
    EditText etAddress;
    EditText etEmail;
    EditText etMobile;
    Spinner spCity;
    RadioButton rbMale;
    RadioButton rbFemale;
    CheckBox chReading;
    CheckBox chMusic;
    CheckBox chTimePass;
    Button btnSave;
    Button btnReset;


    DatePickerDialog dpd;
    SimpleDateFormat sdf;

    DB_Registration dbr;
    DB_City dbc;
    Bean_Registration br;

    String strFrom="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");

        strFrom=getIntent().getStringExtra("From");

        etDOB = (EditText) findViewById(R.id.reg_et_dob);
        etName = (EditText) findViewById(R.id.reg_et_name);
        etAddress = (EditText) findViewById(R.id.reg_et_address);
        etEmail = (EditText) findViewById(R.id.reg_et_email);
        etMobile = (EditText) findViewById(R.id.reg_et_mobile);
        spCity = (Spinner) findViewById(R.id.reg_sp_city);
        rbMale = (RadioButton) findViewById(R.id.reg_rb_male);
        rbFemale = (RadioButton) findViewById(R.id.reg_rb_female);
        chReading = (CheckBox) findViewById(R.id.reg_ch_reading);
        chMusic = (CheckBox) findViewById(R.id.reg_ch_music);
        chTimePass = (CheckBox) findViewById(R.id.reg_ch_timepass);
        btnSave = (Button) findViewById(R.id.reg_btn_save);
        btnReset = (Button) findViewById(R.id.reg_btn_reset);

        etDOB.setInputType(InputType.TYPE_NULL);

        sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        etDOB.setText(sdf.format(new Date()));
        setDateTimeField();

        dbr=new DB_Registration(this);
        dbc=new DB_City(this);
        br=new Bean_Registration();

        if(strFrom.equalsIgnoreCase("Edit")){
            String strID=getIntent().getStringExtra("RegID");
            br=dbr.selectByID(Integer.parseInt(strID));

            etDOB.setText(br.getDOB());
            etName.setText(br.getName());
            etAddress.setText(br.getAddress());
            etEmail.setText(br.getEmail());
            etMobile.setText(br.getMobile());

            if(br.getCityName().equalsIgnoreCase("Rajkot"))
            spCity.setSelection(0);
            if(br.getCityName().equalsIgnoreCase("Junagadh"))
                spCity.setSelection(1);
            if(br.getCityName().equalsIgnoreCase("Ahmedabad"))
                spCity.setSelection(2);

            if(br.getGender().equalsIgnoreCase("Male"))
            rbMale.setChecked(true);
            else
            rbFemale.setChecked(true);
            if(br.getHobbies().contains("Reading"))
            chReading.setChecked(true);
            if(br.getHobbies().contains("Music"))
            chMusic.setChecked(true);
            if(br.getHobbies().contains("TimePass"))
            chTimePass.setChecked(true);

        }





        etDOB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dpd.show();
                return false;
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = 0;
                if (etName.getText().length() > 0) {
                    br.setName(etName.getText().toString());
                } else {
                    etName.setError("Enter Proper Name");
                    flag = 1;
                }

                if (etAddress.getText().length() > 0) {
                    br.setAddress(etAddress.getText().toString());
                } else {
                    etAddress.setError("Enter Proper Address");
                    flag = 1;
                }

                if (etEmail.getText().length() > 0) {
                    br.setEmail(etEmail.getText().toString());
                } else {
                    etEmail.setError("Enter Proper Email");
                    flag = 1;
                }

                if (etMobile.getText().length() ==10) {
                    br.setMobile(etMobile.getText().toString());
                } else {
                    etMobile.setError("Enter Proper Mobile");
                    flag = 1;
                }

                br.setDOB(etDOB.getText().toString());
                int cityID=dbc.getCityID(spCity.getSelectedItem().toString());
                Log.d("City",cityID+"");
                br.setCityID(cityID);

                if (rbMale.isChecked())
                    br.setGender("Male");
                else
                    br.setGender("Female");

                String strHobbies = "";
                if (chReading.isChecked())
                    strHobbies = strHobbies + "Reading" + ",";
                if (chMusic.isChecked())
                    strHobbies = strHobbies + "Music" + ",";
                if (chTimePass.isChecked())
                    strHobbies = strHobbies + "TimePass" + ",";
                if (strHobbies.length() > 0) {
                    strHobbies = strHobbies.substring(0, strHobbies.length() - 1);
                    br.setHobbies(strHobbies);
                }
                else {
                    flag = 1;
                    Toast.makeText(RegistrationActivity.this, "Please Select any Hobby", Toast.LENGTH_LONG).show();
                }

                if (flag == 0) {
                    if(strFrom.equalsIgnoreCase("New")) {
                        dbr.insert(br);
                        Toast.makeText(RegistrationActivity.this, "Saved", Toast.LENGTH_LONG).show();
                        btnReset.performClick();
                    }else
                    {
                        dbr.update(br);
                        Toast.makeText(RegistrationActivity.this, "Update Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });



        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDOB.setText(sdf.format(new Date()));

                etName.setText("");
                etAddress.setText("");
                etEmail.setText("");
                etMobile.setText("");
                spCity.setSelection(0);
                rbMale.setChecked(true);
                chReading.setChecked(false);
                chMusic.setChecked(false);
                chTimePass.setChecked(false);
                etName.requestFocus();
            }
        });


    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etDOB.setText(sdf.format(newDate.getTime()));
                br.setAge((new Date().getYear())-(newDate.getTime().getYear()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

}
