package com.example.srikencono_lt;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.srikencono_lt.adapter.AdapterFoto;
import com.example.srikencono_lt.adapter.AdapterKegiatan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    View view;
    private ArrayList<String> urlGambar = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> subtitle = new ArrayList<>();
    private ArrayList<String> kategori = new ArrayList<>();

    private ArrayList<String> FurlGambar = new ArrayList<>();
    private ArrayList<String> Ftitle = new ArrayList<>();
    private ArrayList<String> Fsubtitle = new ArrayList<>();
    private ArrayList<String> FKategori = new ArrayList<>();

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView textView;
    ImageView imge;
    Button pkk,ktaruna,desa;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        pkk = view.findViewById(R.id.pkk);
        ktaruna = view.findViewById(R.id.ktaruna);
        desa = view.findViewById(R.id.desa);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("kegiatan");
        DatabaseReference refgambar = database.getReference("asets");

        imge = view.findViewById(R.id.img);
        textView = view.findViewById(R.id.keterangan);


        refgambar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(view.getContext()).load(""+dataSnapshot.child("url").getValue()).into(imge);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        rvView = view.findViewById(R.id.rv_main_barang);
        rvView.setHasFixedSize(true);
/*
        for(int a = 0; a < 10 ; a++){
            urlGambar.add("https://firebasestorage.googleapis.com/v0/b/desa-d3f44.appspot.com/o/gambar%2FIMG_4394.JPG?alt=media&token=65468fc5-fa16-4cbd-9c03-56130a56a6e1");
            title.add("Judul Gambar "+(a+1));
        }
*/

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child : dataSnapshot.getChildren()){
                    urlGambar.add(child.child("url").getValue().toString());
                    title.add(child.child("title").getValue().toString());
                    subtitle.add(child.child("isi").getValue().toString());
                    kategori.add(child.child("kategori").getValue().toString());
                }
                if (urlGambar.isEmpty()) {
                    rvView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
                else {
                    rvView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        rvView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        adapter = new AdapterFoto(view.getContext(),urlGambar,title,subtitle);

        if (urlGambar.isEmpty()) {
            rvView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
        else {
            rvView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }


        pkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FurlGambar.clear();
                Ftitle.clear();
                Fsubtitle.clear();
                FKategori.clear();
                for(int a=0; a < kategori.size(); a++){
                    if(kategori.get(a).toString().equals("pkk")){
                        FurlGambar.add(urlGambar.get(a).toString());
                        Ftitle.add(title.get(a).toString());
                        Fsubtitle.add(subtitle.get(a).toString());
                        FKategori.add(kategori.get(a).toString());
                    }
                }
                adapter = new AdapterFoto(view.getContext(),FurlGambar,Ftitle,Fsubtitle);
                rvView.setAdapter(adapter);
            }
        });

        ktaruna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FurlGambar.clear();
                Ftitle.clear();
                Fsubtitle.clear();
                FKategori.clear();
                for(int a=0; a < kategori.size(); a++){
                    if(kategori.get(a).toString().equals("taruna")){
                        FurlGambar.add(urlGambar.get(a).toString());
                        Ftitle.add(title.get(a).toString());
                        Fsubtitle.add(subtitle.get(a).toString());
                        FKategori.add(kategori.get(a).toString());
                    }
                }
                adapter = new AdapterFoto(view.getContext(),FurlGambar,Ftitle,Fsubtitle);
                rvView.setAdapter(adapter);

            }
        });

        desa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FurlGambar.clear();
                Ftitle.clear();
                Fsubtitle.clear();
                FKategori.clear();
                for(int a=0; a < kategori.size(); a++){
                    if(kategori.get(a).toString().equals("desa")){
                        FurlGambar.add(urlGambar.get(a).toString());
                        Ftitle.add(title.get(a).toString());
                        Fsubtitle.add(subtitle.get(a).toString());
                        FKategori.add(kategori.get(a).toString());
                    }
                }
                adapter = new AdapterFoto(view.getContext(),FurlGambar,Ftitle,Fsubtitle);
                rvView.setAdapter(adapter);
            }
        });
        rvView.setAdapter(adapter);
        // Inflate the layout for this fragment


        return view;
    }

}
