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
        TextView valueId5 = findViewById(R.id.quantity);
        TextView valueId6 = findViewById(R.id.allergens);
        TextView valueId7 = findViewById(R.id.activeSubstance);
        TextView valueId8 = findViewById(R.id.price);
        TextView valueId9 = findViewById(R.id.available);
        TextView valueId10 = findViewById(R.id.manufacturer);
        String vl = getIntent().getStringExtra("name");
        String vl2 = getIntent().getStringExtra("type");
        String vl3 = getIntent().getStringExtra("purpose");
        String vl4 = getIntent().getStringExtra("picture");
        String vl5 = getIntent().getStringExtra("quantity");
        String vl6 = getIntent().getStringExtra("allergen1") + ", " + getIntent().getStringExtra("allergen2");
        String vl7 = getIntent().getStringExtra("amount1") + "/" + getIntent().getStringExtra("amount2");
        String vl8 = getIntent().getStringExtra("price");
        String vl9 = getIntent().getStringExtra("available");
        String vl10 = getIntent().getStringExtra("manufacturer");

        Picasso.get().load(vl4).into(valueId4);
        valueId.setText(vl);
        valueId2.setText("Typ: " + vl2);
        valueId3.setText("Zastosowanie: " + vl3);
        valueId5.setText("Ilość: " + vl5);
        valueId6.setText("Alergeny: " + vl6);
        valueId7.setText("Ilość substancji aktywnej: " + vl7);
        valueId8.setText("Cena: " + vl8 + "zł");
        valueId9.setText("Dostępność: " + vl9);
        valueId10.setText("Producent: " + vl10);
    }
}