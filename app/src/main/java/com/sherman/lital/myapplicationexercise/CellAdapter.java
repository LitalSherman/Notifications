package com.sherman.lital.myapplicationexercise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vlad on 12/2/2019.
 */

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder> {
    private List<Cell> cells;
    private Context context;
    private OnClick onClick;

    public CellAdapter(List<Cell> cellList, OnClick onClick) {
        this.cells = cellList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Cell cell = cells.get(position);
        holder.name.setText(cell.getName());
        holder.address.setText(cell.getAddress());
        holder.age.setText(String.valueOf(cell.getAge()));
        holder.phoneNum.setText(cell.getPhoneNum());
        holder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_launcher_background));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onNameClick(cell.getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView address;
        TextView phoneNum;
        TextView age;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            phoneNum = itemView.findViewById(R.id.phone_num);
            age = itemView.findViewById(R.id.age);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
