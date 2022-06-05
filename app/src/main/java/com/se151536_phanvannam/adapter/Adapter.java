package com.se151536_phanvannam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.se151536_phanvannam.R;
import com.se151536_phanvannam.menu.MoreOptions;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<MoreOptions> moreOptionsList;
    Context context;

    public Adapter(ArrayList<MoreOptions> moreOptionsList, Context context) {
        this.moreOptionsList = moreOptionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.menu, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MoreOptions moreOptions = moreOptionsList.get(position);
        viewHolder.menuTitle.setText(moreOptions.getTitle());
        viewHolder.descriptionTitle.setText(moreOptions.getDescription());
    }

    @Override
    public int getItemCount() {
        return moreOptionsList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuTitle;
        TextView descriptionTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuTitle = itemView.findViewById(R.id.menu_title);
            descriptionTitle = itemView.findViewById(R.id.menu_description);
        }
    }
}
