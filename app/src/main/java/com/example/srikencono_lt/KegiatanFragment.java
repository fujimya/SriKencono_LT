package com.example.srikencono_lt;


import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class KegiatanFragment extends Fragment {
    private ArrayList<String> urlGambar = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> subtitle = new ArrayList<>();

    View view;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    TextView textView;

    public KegiatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kegiatan, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("notif");

        textView = view.findViewById(R.id.keterangan);

        rvView = view.findViewById(R.id.rv_main_barang);
        rvView.setHasFixedSize(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child : dataSnapshot.getChildren()){
//                    urlGambar.add(child.child("url").getValue().toString());
                    title.add(child.child("title").getValue().toString());
                    subtitle.add(child.child("isi").getValue().toString());
                }
                if (title.isEmpty()) {
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
        adapter = new AdapterKegiatan(view.getContext(),title,subtitle);

        if (title.isEmpty()) {
            rvView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
        else {
            rvView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        rvView.setAdapter(adapter);


        return view;
    }

}
