package com.example.katalogbuku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.katalogbuku.database.entitas.buku;

import java.util.List;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.ViewAdapter> {
    private List<buku> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public BukuAdapter(Context context, List<buku> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int view) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_buku,parent,false);
        return new ViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.tvJudulBuku.setText(list.get(position).judul);
        holder.tvDeskripsi.setText(list.get(position).harga);
        holder.tvStok.setText(list.get(position).stok);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{

        TextView tvJudulBuku, tvDeskripsi, tvStok;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            tvJudulBuku = itemView.findViewById(R.id.tv_judulBuku);
            tvDeskripsi =  itemView.findViewById(R.id.tv_deskripsi);
            tvStok = itemView.findViewById(R.id.tv_stokTersedia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }

                }
            });
        }
    }


}
