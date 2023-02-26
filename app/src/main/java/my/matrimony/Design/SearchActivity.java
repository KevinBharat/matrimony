package my.matrimony.Design;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import my.matrimony.Adapter.Adapter_City;
import my.matrimony.Adapter.Adapter_Registration;
import my.matrimony.Bean.Bean_City;
import my.matrimony.Bean.Bean_Registration;
import my.matrimony.DBHelper.DB_City;
import my.matrimony.DBHelper.DB_Registration;
import my.matrimony.R;

public class SearchActivity extends AppCompatActivity {

    Spinner spCity;
    RadioButton rbMale;
    RadioButton rbFemale;
    Button btnSearch;
    ListView lst;
    ArrayList<Bean_City> arrayCity;
    ArrayList<Bean_Registration> arrayReg;
    DB_Registration dbr;
    DB_City dbc;
    int cityID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search");

        spCity = (Spinner) findViewById(R.id.search_sp_city);
        rbMale=(RadioButton)findViewById(R.id.search_rb_male);
        rbFemale=(RadioButton)findViewById(R.id.search_rb_female);
        btnSearch=(Button)findViewById(R.id.search_btn_search);
        lst=(ListView)findViewById(R.id.search_lst);
        dbr=new DB_Registration(this);



        dbc = new DB_City(this);
        arrayCity = dbc.selectAllCity();
        spCity.setAdapter(new Adapter_City(this, arrayCity));

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvID=(TextView)view.findViewById(R.id.sp_city_tv_cityid);
                cityID=Integer.parseInt(tvID.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strGender="";
                if(rbMale.isChecked())
                    strGender="Male";
                else
                    strGender="Female";

                arrayReg=dbr.selectBySearch(cityID,strGender);
                lst.setAdapter(new Adapter_Registration(SearchActivity.this,arrayReg));
            }
        });
    }
}
