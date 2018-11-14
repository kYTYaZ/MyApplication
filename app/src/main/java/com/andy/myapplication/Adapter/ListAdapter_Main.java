package com.andy.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.myapplication.R;
import com.bumptech.glide.Glide;

public class ListAdapter_Main extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater mlayoutInflater;

    public ListAdapter_Main(Context context) {
        this.mcontext = context;
        this.mlayoutInflater = LayoutInflater.from(context);
    }

    @Override//列表长度（一般不是固定的）
    public int getCount() {
        return 10;
    }

    @Override//不用
    public Object getItem(int position) {
        return null;
    }

    @Override//不用
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        private ImageView IV_PT, IV_Time;
        private TextView Name, content, time;
    }

    @Override//重点
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mlayoutInflater.inflate(R.layout.layout_main, null);
            holder = new ViewHolder();
            holder.IV_PT = convertView.findViewById(R.id.imageView6);
            holder.IV_Time = convertView.findViewById(R.id.imageView8);
            holder.Name = convertView.findViewById(R.id.textView7);
            holder.content = convertView.findViewById(R.id.text_content);
            holder.time = convertView.findViewById(R.id.text_time);
            convertView.setTag(holder);
        } else {
            //增加一个格子
            holder = (ViewHolder) convertView.getTag();
        }

        //给控件赋值
        Glide.with(mcontext).load("https://timgsa.baidu" +
                ".com/timg?image&quality=80&size=b9999_10000&sec=1540069972855&di" +
                "=6a45b5b3ba56f1ea919f384f4c7f0236&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu" +
                ".com%2Fzhidao%2Fpic%2Fitem%2F962bd40735fae6cd5919273907b30f2442a70f3c.jpg").into
                (holder.IV_PT);
        holder.content.setText("这是内容");
        holder.time.setText("20：20");
        holder.Name.setText("这是名字");

        return convertView;
    }
}
