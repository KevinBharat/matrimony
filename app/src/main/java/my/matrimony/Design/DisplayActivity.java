package my.matrimony.Design;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import my.matrimony.Adapter.Adapter_Registration;
import my.matrimony.Bean.Bean_Registration;
import my.matrimony.DBHelper.DB_Registration;
import my.matrimony.R;

public class DisplayActivity extends AppCompatActivity {

    ListView lst;
    EditText etSearch;
    DB_Registration dbr;
    ArrayList<Bean_Registration> arrayReg;
    ArrayList<Bean_Registration> arrayRegTemp;
    String strID = "";
    String strFrom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        strFrom = getIntent().getStringExtra("From");
        setTitle(strFrom);


        lst = (ListView) findViewById(R.id.display_lst);
        etSearch = (EditText) findViewById(R.id.display_et_search);

        registerForContextMenu(lst);
        dbr = new DB_Registration(this);

        arrayReg = dbr.selectAll(strFrom);
        lst.setAdapter(new Adapter_Registration(this, arrayReg));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvID = (TextView) view.findViewById(R.id.list_profiles_tv_regid);
                strID = tvID.getText().toString();
                Intent in = new Intent(DisplayActivity.this, DetailActivity.class);
                in.putExtra("RegID", strID);
                startActivity(in);
            }
        });


        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvID = (TextView) view.findViewById(R.id.list_profiles_tv_regid);
                strID = tvID.getText().toString();


                return false;
            }
        });


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = etSearch.getText().toString();
                arrayRegTemp = new ArrayList<Bean_Registration>();

                if (str.length() > 0) {
                    for (int ii = 0; ii < arrayReg.size(); ii++) {
                        if (arrayReg.get(ii).getName().startsWith(str) || arrayReg.get(ii).getCityName().startsWith(str))
                            arrayRegTemp.add(arrayReg.get(ii));
                    }
                    lst.setAdapter(new Adapter_Registration(DisplayActivity.this, arrayRegTemp));
                } else
                    lst.setAdapter(new Adapter_Registration(DisplayActivity.this, arrayReg));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Edit")) {
            Intent in = new Intent(DisplayActivity.this, RegistrationActivity.class);
            in.putExtra("From", "Edit");
            in.putExtra("RegID", strID);
            startActivity(in);
        }
        if (item.getTitle().equals("Delete")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Are you sure want to delete?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dbr.delete(Integer.parseInt(strID));
                    arrayReg = dbr.selectAll(strFrom);
                    lst.setAdapter(new Adapter_Registration(DisplayActivity.this, arrayReg));
                    Toast.makeText(DisplayActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();

            // Create the AlertDialog object and return it

        }
        return super.onContextItemSelected(item);
    }
}
