package com.matrimony.Design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.matrimony.Bean.Bean_Registration;
import com.matrimony.DBHelper.DB_Registration;
import com.matrimony.R;

public class DetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvAddress;
    TextView tvCity;
    TextView tvEmail;
    TextView tvMobile;
    TextView tvGender;
    TextView tvDOB;
    TextView tvHobbies;

    Bean_Registration br;
    DB_Registration dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail");

        tvName=(TextView)findViewById(R.id.detail_tv_name);
        tvAddress=(TextView)findViewById(R.id.detail_tv_address);
        tvCity=(TextView)findViewById(R.id.detail_tv_city);
        tvEmail=(TextView)findViewById(R.id.detail_tv_email);
        tvMobile=(TextView)findViewById(R.id.detail_tv_mobile);
        tvGender=(TextView)findViewById(R.id.detail_tv_gender);
        tvDOB=(TextView)findViewById(R.id.detail_tv_dob);
        tvHobbies=(TextView)findViewById(R.id.detail_tv_hobbies);

        String strID=getIntent().getStringExtra("RegID");
        dbr=new DB_Registration(this);
        br=dbr.selectByID(Integer.parseInt(strID));

        tvName.setText(br.getName());
        tvAddress.setText(br.getAddress());
        tvCity.setText(br.getCityName());
        tvEmail.setText(br.getEmail());
        tvMobile.setText(br.getMobile());
        tvGender.setText(br.getGender());
        tvDOB.setText(br.getDOB());
        tvHobbies.setText(br.getHobbies());


    }
}
