package com.example.tokokita.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tokokita.DetailActivity;
import com.example.tokokita.R;
import com.example.tokokita.model.Barang;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {
    private Context context;
    private List<Barang> barangList;

    public BarangAdapter(Context context, List<Barang> barangList) {
        this.context = context;
        this.barangList = barangList;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        Barang barang = barangList.get(position);

        holder.tvNamaBarang.setText(barang.getNmbarang());

        // Format harga
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedPrice = formatter.format(barang.getHarga());
        holder.tvHarga.setText(formattedPrice);

        // Load image dengan Glide
        Glide.with(context)
                .load(barang.getFoto())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .into(holder.imgBarang);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("kdbarang", barang.getKdbarang());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public void updateData(List<Barang> newData) {
        this.barangList.clear();
        this.barangList.addAll(newData);
        notifyDataSetChanged();
    }

    static class BarangViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBarang;
        TextView tvNamaBarang, tvHarga;

        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBarang = itemView.findViewById(R.id.imgBarang);
            tvNamaBarang = itemView.findViewById(R.id.tvNamaBarang);
            tvHarga = itemView.findViewById(R.id.tvHarga);
        }
    }
}