package com.example.srikencono_lt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.srikencono_lt.DetailKegiatan;
import com.example.srikencono_lt.R;

import java.util.ArrayList;

public class AdapterKegiatan extends RecyclerView.Adapter<AdapterKegiatan.ViewHolder> {
    private ArrayList<String> title;
    private ArrayList<String> subtitle;
    Context ctx;

    public AdapterKegiatan (Context context,ArrayList<String> Title, ArrayList<String> SubTitle){
        ctx = context;
        title = Title;
        subtitle = SubTitle;

    }

    @NonNull
    @Override
    public AdapterKegiatan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_kegiatan, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        AdapterKegiatan.ViewHolder vh = new AdapterKegiatan.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKegiatan.ViewHolder holder, final int position) {
        holder.tvtitle.setText(title.get(position));
        holder.tvSubtitle.setText(subtitle.get(position));

    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle;
        public TextView tvSubtitle;
        public CardView cvMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = (TextView) itemView.findViewById(R.id.txt_title);
            tvSubtitle = (TextView) itemView.findViewById(R.id.txt_isi);
            cvMain = (CardView) itemView.findViewById(R.id.cv_main);

        }
    }
}
