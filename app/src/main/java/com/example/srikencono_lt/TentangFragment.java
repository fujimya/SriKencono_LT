package com.example.srikencono_lt;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class TentangFragment extends Fragment {

    View view;
    TextView title,isi;
    ImageView imge;

    public TentangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tentang, container, false);
        imge = view.findViewById(R.id.img);
        title = view.findViewById(R.id.nama);
        isi = view.findViewById(R.id.versi);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tentangref = database.getReference("tentang");

        tentangref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(view.getContext()).load(""+dataSnapshot.child("url").getValue()).into(imge);
                title.setText(""+dataSnapshot.child("nama").getValue());
                isi.setText(""+dataSnapshot.child("versi").getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
