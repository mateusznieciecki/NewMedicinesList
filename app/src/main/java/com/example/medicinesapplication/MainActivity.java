package com.example.medicinesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    List<Medicine> medicines;
    private static String JSON_URL = "https://raw.githubusercontent.com/mateusznieciecki/MedicinesAndroid/main/AplikacjaLeki/src/main/medicines.json";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.medicinesList);
        medicines = new ArrayList<>();
        extractMedicines();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, medicines, new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Medicine item, int position) {
                showToast("x");
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void extractMedicines(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject medicineObject = response.getJSONObject(i);

                        Medicine medicine = new Medicine();
                        medicine.setName(medicineObject.getString("name").toString());
                        medicine.setType(medicineObject.getString("type".toString()));
                        medicine.setPicture(medicineObject.getString("picture"));
                        medicine.setPurpose(medicineObject.getString("purpose".toString()));
                        medicines.add(medicine);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), medicines, new Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Medicine item, int position) {
                        Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                        intent.putExtra("name",medicines.get(position).getName());
                        intent.putExtra("type",medicines.get(position).getType());
                        intent.putExtra("purpose",medicines.get(position).getPurpose());
                        intent.putExtra("picture",medicines.get(position).getPicture());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}