package com.matrimony.Design;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.matrimony.API.ApiClient;
import com.matrimony.API.ApiInterface;
import com.matrimony.Adapter.Adapter_Country;
import com.matrimony.Bean.Bean_Country;
import com.matrimony.Bean.Bean_CountryList;
import com.matrimony.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListActivity extends AppCompatActivity {

    ListView lst;
    ApiInterface apiService;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        setTitle("Country List");
        lst=(ListView)findViewById(R.id.country_lst);
        pd=new ProgressDialog(this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        getData();


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvID=(TextView)view.findViewById(R.id.list_country_tv_id);
                TextView tvName=(TextView)view.findViewById(R.id.list_country_tv_name);

                Intent in=new Intent(CountryListActivity.this,StateListActivity.class);
                in.putExtra("Name",tvName.getText().toString());
                in.putExtra("CID",tvID.getText().toString());
                startActivity(in);

            }
        });

    }

    void getData(){
        pd.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Bean_Country> call = apiService.getContryList("1234");
        call.enqueue(new Callback<Bean_Country>() {
            @Override
            public void onResponse(Call<Bean_Country>call, Response<Bean_Country> response) {
               if(response.body().getIsResult()==1) {
                   List<Bean_CountryList> countryList = response.body().getResultList();
                   lst.setAdapter(new Adapter_Country(CountryListActivity.this,countryList));
               }else
               {
                   Toast.makeText(CountryListActivity.this,"Data not found",Toast.LENGTH_LONG).show();
               }
                if(pd.isShowing())
                    pd.dismiss();
            }

            @Override
            public void onFailure(Call<Bean_Country>call, Throwable t) {
                // Log error here since request failed
                if(pd.isShowing())
                    pd.dismiss();
                Toast.makeText(CountryListActivity.this,"Server not responding",Toast.LENGTH_LONG).show();
            }
        });
    }
}
