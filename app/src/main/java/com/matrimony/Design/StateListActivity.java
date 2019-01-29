package com.matrimony.Design;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.matrimony.API.ApiClient;
import com.matrimony.API.ApiInterface;
import com.matrimony.Adapter.Adapter_Country;
import com.matrimony.Adapter.Adapter_State;
import com.matrimony.Bean.Bean_Country;
import com.matrimony.Bean.Bean_CountryList;
import com.matrimony.Bean.Bean_State;
import com.matrimony.Bean.Bean_StateList;
import com.matrimony.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateListActivity extends AppCompatActivity {
    ListView lst;
    ApiInterface apiService;
    ProgressDialog pd;
    String strCountryID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        String strName=getIntent().getStringExtra("Name");
        strCountryID=getIntent().getStringExtra("CID");
        setTitle("State List of "+strName);

        lst=(ListView)findViewById(R.id.country_lst);
        pd=new ProgressDialog(this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        getData();
    }

    void getData(){
        pd.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Bean_State> call = apiService.getStateList("1234",strCountryID);
        call.enqueue(new Callback<Bean_State>() {
            @Override
            public void onResponse(Call<Bean_State>call, Response<Bean_State> response) {
                if(response.body().getIsResult()==1) {
                    List<Bean_StateList> stateList = response.body().getResultList();
                    lst.setAdapter(new Adapter_State(StateListActivity.this,stateList));
                }else
                {
                    Toast.makeText(StateListActivity.this,"Data not found",Toast.LENGTH_LONG).show();
                }
                if(pd.isShowing())
                    pd.dismiss();
            }

            @Override
            public void onFailure(Call<Bean_State>call, Throwable t) {
                // Log error here since request failed
                if(pd.isShowing())
                    pd.dismiss();
                Toast.makeText(StateListActivity.this,"Server not responding",Toast.LENGTH_LONG).show();
            }
        });
    }
}
