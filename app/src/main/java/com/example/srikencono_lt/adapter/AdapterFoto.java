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

public class AdapterFoto extends RecyclerView.Adapter<AdapterFoto.ViewHolder> {
    private ArrayList<String> urlGambar;
    private ArrayList<String> title;
    private ArrayList<String> subtitle;
    Context ctx;

    public AdapterFoto (Context context, ArrayList<String> URLGambar, ArrayList<String> Title, ArrayList<String> Subtitle){
        ctx = context;
        urlGambar = URLGambar;
        title = Title;
        subtitle = Subtitle;

    }


    @NonNull
    @Override
    public AdapterFoto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_foto, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        AdapterFoto.ViewHolder vh = new AdapterFoto.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFoto.ViewHolder holder, final int position) {
        Glide.with(ctx)
                .load(urlGambar.get(position))
                .into(holder.img);
        holder.tvsubtitle.setText(subtitle.get(position));
        holder.tvtitle.setText(title.get(position));
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailKegiatan.class);
                intent.putExtra("url", urlGambar.get(position));
                intent.putExtra("title", title.get(position));
                intent.putExtra("isi", subtitle.get(position));
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return urlGambar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle;
        public TextView tvsubtitle;
        public CardView cvMain;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = (TextView) itemView.findViewById(R.id.txt_title);
            tvsubtitle = (TextView) itemView.findViewById(R.id.txt_title);
            cvMain = (CardView) itemView.findViewById(R.id.cv_main);
            img = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
