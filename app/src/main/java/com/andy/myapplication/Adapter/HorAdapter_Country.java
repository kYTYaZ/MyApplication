package com.andy.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.andy.myapplication.R;

import java.util.List;
import java.util.Map;

public class HorAdapter_Country extends RecyclerView.Adapter<HorAdapter_Country.ViewHolder> {
    private Context mContext;
    private List<Map> list;


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name, Number;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.textView8);
            Number = itemView.findViewById(R.id.number);
        }
    }

    public HorAdapter_Country(Context context, List<Map> dataList) {
        this.mContext = context;
        this.list = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_country_horrecyclerview,parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Name.setText(list.get(position).get("Country_name").toString());
        holder.Number.setText(list.get(position).get("Country_number").toString());

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


}

