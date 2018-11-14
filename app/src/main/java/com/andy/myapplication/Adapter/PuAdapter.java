package com.andy.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.andy.myapplication.R;

public class PuAdapter extends RecyclerView.Adapter<PuAdapter.ViewHolder> {
    private Context mContext;


    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView Picture;

        public ViewHolder(View itemView) {
            super(itemView);
            Picture = itemView.findViewById(R.id.picture);
        }
    }

    public PuAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_purecyclerview,parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position %2 !=0 ){
        holder.Picture.setImageResource(R.drawable.huaban1);
        }else{
        holder.Picture.setImageResource(R.drawable.huaban2);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }


}

