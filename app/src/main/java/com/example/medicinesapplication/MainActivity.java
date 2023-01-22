package com.example.medicinesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    List<Medicine> medicines;
    private static String JSON_URL = "https://raw.githubusercontent.com/mateusznieciecki/NewMedicinesList/master/app/src/main/medicines.json";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.medicinesList);
        medicines = new ArrayList<>();
        try {
            extractMedicines();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, medicines, new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Medicine item, int position) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("name",medicines.get(position).getName());
                intent.putExtra("type",medicines.get(position).getType());
                intent.putExtra("purpose",medicines.get(position).getPurpose());
                intent.putExtra("picture",medicines.get(position).getPicture());
                intent.putExtra("quantity",medicines.get(position).getQuantity());
                intent.putExtra("price",medicines.get(position).getPrice());
                intent.putExtra("available",medicines.get(position).getAvailable());
                intent.putExtra("manufacturer",medicines.get(position).getManufacturer());

                intent.putExtra("allergen1",medicines.get(position).getAllergen1());
                intent.putExtra("allergen2",medicines.get(position).getAllergen2());

                intent.putExtra("amount1",medicines.get(position).getAmount1());
                intent.putExtra("amount1",medicines.get(position).getAmount2());

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void extractMedicines() throws FileNotFoundException, JSONException {
        int x = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected())
            x = 1;
        System.out.println(x);
        if(x == 1){
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    String cacheFileName = "cachedMedicines.json";
                    File file = new File(getCacheDir(), cacheFileName);
                    System.out.println("filedata: " + response);
                    writeDataToFile(file, String.valueOf(response));
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject medicineObject = response.getJSONObject(i);
                            JSONObject secondaryObject = medicineObject.getJSONObject("allergens");
                            JSONObject anotherObject = medicineObject.getJSONObject("activeSubstance");

                            Medicine medicine = new Medicine();
                            medicine.setName(medicineObject.getString("name").toString());
                            medicine.setType(medicineObject.getString("type".toString()));
                            medicine.setPicture(medicineObject.getString("picture"));
                            medicine.setPurpose(medicineObject.getString("purpose".toString()));
                            medicine.setQuantity(medicineObject.getString("quantity".toString()));
                            medicine.setPrice(medicineObject.getString("price".toString()));
                            medicine.setAvailable(medicineObject.getString("available".toString()));
                            medicine.setManufacturer(medicineObject.getString("manufacturer".toString()));

                            medicine.setAllergen1(secondaryObject.getString("allergen1".toString()));
                            medicine.setAllergen2(secondaryObject.getString("allergen2".toString()));

                            medicine.setAmount1(anotherObject.getString("amount1".toString()));
                            medicine.setAmount2(anotherObject.getString("amount2".toString()));

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
                            intent.putExtra("quantity",medicines.get(position).getQuantity());
                            intent.putExtra("price",medicines.get(position).getPrice());
                            intent.putExtra("available",medicines.get(position).getAvailable());
                            intent.putExtra("manufacturer",medicines.get(position).getManufacturer());

                            intent.putExtra("allergen1",medicines.get(position).getAllergen1());
                            intent.putExtra("allergen2",medicines.get(position).getAllergen2());

                            intent.putExtra("amount1",medicines.get(position).getAmount1());
                            intent.putExtra("amount2",medicines.get(position).getAmount2());

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
        else{
            String fileData = null;
            File cacheFileDir = new File(getCacheDir(), "cachedMedicines.json");
            FileInputStream fileInputStream = new FileInputStream(cacheFileDir);
            fileData = "{\n" +
                    "\"medicines\": ";
            fileData += readDataFromFile(fileInputStream);
            fileData += "}\n";

            JSONObject jsonObject = new JSONObject(fileData);
            JSONArray jsonArray = jsonObject.getJSONArray("medicines");
            for(int i=0;i<jsonArray.length(); i++){
                JSONObject medicineObject = jsonArray.getJSONObject(i);
                JSONObject secondaryObject = medicineObject.getJSONObject("allergens");
                JSONObject anotherObject = medicineObject.getJSONObject("activeSubstance");

                Medicine medicine = new Medicine();
                medicine.setName(medicineObject.getString("name").toString());
                medicine.setType(medicineObject.getString("type".toString()));
                medicine.setPicture(medicineObject.getString("picture"));
                medicine.setPurpose(medicineObject.getString("purpose".toString()));
                medicine.setQuantity(medicineObject.getString("quantity".toString()));
                medicine.setPrice(medicineObject.getString("price".toString()));
                medicine.setAvailable(medicineObject.getString("available".toString()));
                medicine.setManufacturer(medicineObject.getString("manufacturer".toString()));

                medicine.setAllergen1(secondaryObject.getString("allergen1".toString()));
                medicine.setAllergen2(secondaryObject.getString("allergen2".toString()));

                medicine.setAmount1(anotherObject.getString("amount1".toString()));
                medicine.setAmount2(anotherObject.getString("amount2".toString()));

                medicines.add(medicine);
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
                    intent.putExtra("quantity",medicines.get(position).getQuantity());
                    intent.putExtra("price",medicines.get(position).getPrice());
                    intent.putExtra("available",medicines.get(position).getAvailable());
                    intent.putExtra("manufacturer",medicines.get(position).getManufacturer());

                    intent.putExtra("allergen1",medicines.get(position).getAllergen1());
                    intent.putExtra("allergen2",medicines.get(position).getAllergen2());

                    intent.putExtra("amount1",medicines.get(position).getAmount1());
                    intent.putExtra("amount2",medicines.get(position).getAmount2());

                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
        }
    }

    private void writeDataToFile(File file, String data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.writeDataToFile(fileOutputStream, data);
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void writeDataToFile(FileOutputStream fileOutputStream, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String readDataFromFile(FileInputStream fileInputStream) {
        StringBuffer retBuf = new StringBuffer();
        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    retBuf.append(lineData);
                    lineData = bufferedReader.readLine();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return retBuf.toString();
        }
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}