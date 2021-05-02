package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JsonAdapter extends ArrayAdapter<Air> {
//public class JsonAdapter extends BaseAdapter{

    private List<Air> list;

    private Context context;

    private LayoutInflater inflater;


    /*public JsonAdapter(Context context,List<Air> list){
        this.list=list;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }*/

    public JsonAdapter(Activity context, ArrayList<Air> list){
        super(context, 0, list);
    }

    @Override
    //改寫getView()方法
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        //listItemView可能會是空的，例如App剛啟動時，沒有預先儲存的view可使用
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        //找到data，並在View上設定正確的data
        Air currentName = getItem(position);

        //找到ListItem.xml中的兩個TextView(物種學名和中文名)
        TextView tvSiteName = listItemView.findViewById(R.id.tvSiteName);
        tvSiteName.setText(currentName.getSiteName());

        TextView tvCounty = listItemView.findViewById(R.id.tvCounty);
        tvCounty.setText(currentName.getCounty());

        TextView tvAQI = listItemView.findViewById(R.id.tvAQI);
        tvAQI.setText(currentName.getAQI());

        TextView tvPM2_5 = listItemView.findViewById(R.id.tvPM2_5);
        tvPM2_5.setText(currentName.getPM2_5());

        TextView tvStatus = listItemView.findViewById(R.id.tvStatus);
        tvStatus.setText(currentName.getStatus());

        TextView tvPublishTime = listItemView.findViewById(R.id.tvPublishTime);
        tvPublishTime.setText(currentName.getPublishTime());

        return listItemView;
    }
}

    /*@Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }*/

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        Holder holder=null;

        if(convertView == null){
            convertView=inflater.inflate(R.layout.item,null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(Holder) convertView.getTag();
        }


        Air road = (Air) list.get(position);
        holder.tvSiteName.setText(road.getSiteName());
        holder.tvCounty.setText(road.getCounty());
        holder.tvAQI.setText(road.getAQI());
        holder.tvPM2_5.setText(road.getPM2_5());
        holder.tvStatus.setText(road.getStatus());
        holder.tvPublishTime.setText(road.getPublishTime());

        return convertView;
    }

    class Holder{
        private TextView tvSiteName, tvCounty, tvAQI, tvPM2_5, tvStatus, tvPublishTime;

        public  Holder(View view){
            tvSiteName=(TextView) view.findViewById(R.id.tvSiteName);
            tvCounty=(TextView) view.findViewById(R.id.tvCounty);
            tvAQI=(TextView) view.findViewById(R.id.tvAQI);
            tvPM2_5=(TextView) view.findViewById(R.id.tvPM2_5);
            tvStatus=(TextView) view.findViewById(R.id.tvStatus);
            tvPublishTime=(TextView) view.findViewById(R.id.tvPublishTime);
        }
    }*/
//}
