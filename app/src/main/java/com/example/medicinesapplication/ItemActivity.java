package com.example.medicinesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        TextView valueId = findViewById(R.id.name);
        TextView valueId2 = findViewById(R.id.type);
        TextView valueId3 = findViewById(R.id.purpose);
        ImageView valueId4 = findViewById(R.id.picture);
        String vl = getIntent().getStringExtra("name");
        String vl2 = getIntent().getStringExtra("type");
        String vl3 = getIntent().getStringExtra("purpose");
        String vl4 = getIntent().getStringExtra("picture");
        Picasso.get().load(vl4).into(valueId4);
        valueId.setText(vl);
        valueId2.setText("Typ: " + vl2);
        valueId3.setText("Zastosowanie: " + vl3);
    }
}