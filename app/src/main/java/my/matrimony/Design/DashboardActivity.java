package my.matrimony.Design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import my.matrimony.R;

public class DashboardActivity extends AppCompatActivity {

    Button btnRegistration;
    Button btnDisplay;
    Button btnSearch;
    Button btnFavourite;
    Button btnOnlineExample;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DashboardActivity.this, RegistrationActivity.class);
                in.putExtra("From", "New");
                startActivity(in);

            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DashboardActivity.this, DisplayActivity.class);
                in.putExtra("From", "Profiles");
                startActivity(in);

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DashboardActivity.this, SearchActivity.class);
                startActivity(in);

            }
        });

        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DashboardActivity.this, DisplayActivity.class);
                in.putExtra("From", "Favourites");
                startActivity(in);

            }
        });

        btnOnlineExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DashboardActivity.this, CountryListActivity.class);
                startActivity(in);
            }
        });


    }







    void init() {
        btnRegistration = (Button) findViewById(R.id.dashboard_btn_reg);
        btnDisplay = (Button) findViewById(R.id.dashboard_btn_display);
        btnSearch = (Button) findViewById(R.id.dashboard_btn_search);
        btnFavourite = (Button) findViewById(R.id.dashboard_btn_fav);
        btnOnlineExample = (Button) findViewById(R.id.dashboard_btn_retrofit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "Please Download this App: url");
                startActivity(Intent.createChooser(i, "Share via"));
                return true;
            case R.id.registration:
                btnRegistration.performClick();
                return true;
            case R.id.display:
                btnDisplay.performClick();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
