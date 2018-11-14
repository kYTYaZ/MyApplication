package com.andy.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.andy.myapplication.R;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Context mContext;
    private int imageView;


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;
        private ImageView Picture;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Picture = itemView.findViewById(R.id.picture);
        }
    }

    public GridAdapter(Context context,int image) {
        this.mContext = context;
        this.imageView = image;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_gridrecyclerview,parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Name.setText("Andy");
        holder.Picture.setImageResource(imageView);
    }

    @Override
    public int getItemCount() {
        return 40;
    }


}

