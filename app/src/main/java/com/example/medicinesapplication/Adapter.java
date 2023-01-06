package com.example.medicinesapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Medicine item, int position);
    }

    private final OnItemClickListener listener;
    LayoutInflater inflater;
    List<Medicine> medicines;

    public Adapter(Context ctx, List<Medicine> medicines, OnItemClickListener listener){
        this.inflater = LayoutInflater.from(ctx);
        this.medicines = medicines;
        this.listener = listener;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.name.setText(medicines.get(position).getName());
        holder.type.setText(medicines.get(position).getType());
        Picasso.get().load(medicines.get(position).getPicture()).into(holder.picture);
        holder.itemView.setOnClickListener(view -> {
            listener.onItemClick(medicines.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,type;
        ImageView picture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            picture = itemView.findViewById(R.id.picture);
        }
    }
}
