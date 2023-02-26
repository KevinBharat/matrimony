package my.matrimony.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import my.matrimony.Bean.Bean_StateList;
import my.matrimony.Design.StateListActivity;
import my.matrimony.R;

public class Adapter_State extends BaseAdapter {

    Activity act;
    List<Bean_StateList> arrayState;


    public Adapter_State(Activity act, List<Bean_StateList> arrayState) {
        this.act = act;
        this.arrayState = arrayState;


    }

    public Adapter_State(StateListActivity act, List<Bean_StateList> stateList) {
    }

    @Override
    public int getCount() {
        return arrayState.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return arrayState.get(i).getStateID();
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


        viewHolder.tvName.setText(arrayState.get(i).getStateName());

        viewHolder.tvID.setText(arrayState.get(i).getStateID()+"");


        return view;
    }
}
