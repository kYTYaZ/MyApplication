package com.andy.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.andy.myapplication.R;

import java.util.List;
import java.util.Map;

public class ListAdapter_Country extends BaseAdapter {
    private Context context;
    private List<Map> list;

    public ListAdapter_Country(Context context, List<Map> dataList) {
        this.context = context;
        this.list = dataList;
    }

    @Override//获取适配器中数据集中数据的条目数
    public int getCount() {
        return list.size();
    }

    @Override//获取数据集中与指定索引对应的数据项(不用)
    public Object getItem(int position) {
        return null;
    }

    @Override//获取数据集中指定索引对应的项的id(不用)
    public long getItemId(int position) {
        return 0;
    }


    class ViewHolder {
        private TextView Name, Number;
    }

    @Override//重点
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (convertView == null) {
            viewholder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_country_listview, null);
            viewholder.Name = convertView.findViewById(R.id.textView8);
            viewholder.Number = convertView.findViewById(R.id.number);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        String name = list.get(position).get("Country_name").toString();
        String number = list.get(position).get("Country_number").toString();
        viewholder.Name.setText(name);
        viewholder.Number.setText(number);

        return convertView;
    }
}

