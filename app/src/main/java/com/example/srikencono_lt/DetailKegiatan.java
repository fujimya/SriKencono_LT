package com.example.srikencono_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailKegiatan extends AppCompatActivity {
    TextView title,isi;
    ImageView imge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kegiatan);

        imge = findViewById(R.id.img);
        title = findViewById(R.id.texttitle);
        isi = findViewById(R.id.isi);

        Glide.with(this).load(""+getIntent().getExtras().getString("url")).into(imge);
        title.setText(""+getIntent().getExtras().getString("title"));
        isi.setText(""+getIntent().getExtras().getString("isi"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
