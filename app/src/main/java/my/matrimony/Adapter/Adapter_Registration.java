package my.matrimony.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import my.matrimony.Bean.Bean_Registration;
import my.matrimony.DBHelper.DB_Registration;
import my.matrimony.R;
public class Adapter_Registration extends BaseAdapter {
    Activity act;
    ArrayList<Bean_Registration> arrayReg;
    Typeface tf;
    DB_Registration dbr;

    public Adapter_Registration(Activity act, ArrayList<Bean_Registration> arrayReg) {
        this.act = act;
        this.arrayReg = arrayReg;
        tf = Typeface.createFromAsset(act.getAssets(), "Material.ttf");
        dbr = new DB_Registration(act);
    }

    @Override
    public int getCount() {
        return arrayReg.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return arrayReg.get(i).getRegID();
    }

    private class ViewHolder {
        ImageView imgGender;
        TextView tvName;
        TextView tvCity;
        TextView tvID;
        TextView tvArrow;
        ImageView imgFav;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        LayoutInflater inflater = act.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.list_profiles, null);
            viewHolder = new ViewHolder();


            viewHolder.imgGender = (ImageView) view.findViewById(R.id.list_profiles_img_gender);
            viewHolder.tvName = (TextView) view.findViewById(R.id.list_profiles_tv_name);
            viewHolder.tvCity = (TextView) view.findViewById(R.id.list_profiles_tv_city);
            viewHolder.tvID = (TextView) view.findViewById(R.id.list_profiles_tv_regid);
            viewHolder.tvArrow = (TextView) view.findViewById(R.id.list_profiles_tv_arrow);
            viewHolder.imgFav = (ImageView) view.findViewById(R.id.list_profiles_img_favourite);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        if (arrayReg.get(i).getGender().equalsIgnoreCase("Male"))
            viewHolder.imgGender.setImageResource(R.mipmap.ic_male);
        else
            viewHolder.imgGender.setImageResource(R.mipmap.ic_female);

        viewHolder.tvName.setText(arrayReg.get(i).getName());
        viewHolder.tvCity.setText(arrayReg.get(i).getCityName());
        viewHolder.tvID.setText(arrayReg.get(i).getRegID() + "");
        viewHolder.tvArrow.setTypeface(tf);

        if (arrayReg.get(i).getIsFavourite().equalsIgnoreCase("Yes"))
            viewHolder.imgFav.setImageResource(R.drawable.ic_heartdark);
        else
            viewHolder.imgFav.setImageResource(R.drawable.ic_heartlight);

        viewHolder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayReg.get(i).getIsFavourite().equalsIgnoreCase("Yes")) {
                    viewHolder.imgFav.setImageResource(R.drawable.ic_heartlight);
                    dbr.updateIsFavourite(arrayReg.get(i).getRegID(),"No");
                    arrayReg.get(i).setIsFavourite("No");
                }
                else {
                    viewHolder.imgFav.setImageResource(R.drawable.ic_heartdark);
                    dbr.updateIsFavourite(arrayReg.get(i).getRegID(),"Yes");
                    arrayReg.get(i).setIsFavourite("Yes");
                }
            }
        });


        return view;
    }
}
