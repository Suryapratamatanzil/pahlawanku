package com.mdp.pahlawanku;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class adaptergrid extends RecyclerView.Adapter<adaptergrid.ViewHolder> {
    private ArrayList<ModelPahlawan> datapahlawan;

    public adaptergrid(ArrayList<ModelPahlawan> datapahlawan) {
        this.datapahlawan = datapahlawan;
    }

    @NonNull
    @Override
    public adaptergrid.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptergrid.ViewHolder holder, int position) {
        ModelPahlawan pahlawan = datapahlawan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(pahlawan.getFoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivgrid);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = pahlawan.getNama();
                String tentang = pahlawan.getTentang();
                String foto = pahlawan.getFoto();
                Intent intent = new Intent(holder.itemView.getContext(),DetailActivity.class);
                intent.putExtra("varnama", nama);
                intent.putExtra("vartentang", tentang);
                intent.putExtra("varfoto", foto);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datapahlawan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivgrid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivgrid = itemView.findViewById(R.id.iv_grid);
        }
    }
}
