package my.matrimony.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import my.matrimony.Bean.Bean_CountryList;
import my.matrimony.R;
public class Adapter_Country extends BaseAdapter {

    Activity act;
    List<Bean_CountryList> arrayCountry;


    public Adapter_Country(Activity act, List<Bean_CountryList> arrayCountry) {
        this.act = act;
        this.arrayCountry = arrayCountry;


    }

    @Override
    public int getCount() {
        return arrayCountry.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return arrayCountry.get(i).getCountryID();
    }

    private class ViewHolder {
        TextView tvName;
        TextView tvID;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        LayoutInflater inflater = act.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.list_country, null);
            viewHolder = new ViewHolder();


            viewHolder.tvName = (TextView) view.findViewById(R.id.list_country_tv_name);
            viewHolder.tvID = (TextView) view.findViewById(R.id.list_country_tv_id);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }


        viewHolder.tvName.setText(arrayCountry.get(i).getCountryName());

        viewHolder.tvID.setText(arrayCountry.get(i).getCountryID()+"");


        return view;
    }
}
