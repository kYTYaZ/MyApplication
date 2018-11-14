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

public class LinearAdapter_Country extends RecyclerView.Adapter<LinearAdapter_Country.ViewHolder> {
    private Context context;
    private List<Map> list;
    private OnItemListener onItemListener;


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name, Number;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.textView8);
            Number = itemView.findViewById(R.id.number);
        }
    }

    public LinearAdapter_Country(Context context, List<Map> dataList) {
        this.context = context;
        this.list = dataList;
    }


    public interface OnItemListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_country_linearrecyclerview, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        if (onItemListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    onItemListener.onClick(position);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    onItemListener.onLongClick(position);
                    return true;
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = list.get(position).get("Country_name").toString();
        String number = list.get(position).get("Country_number").toString();
        holder.Name.setText(name);
        holder.Number.setText(number);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


}

