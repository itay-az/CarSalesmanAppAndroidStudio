package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class Cars extends AppCompatActivity {

    private ListView cars;
    private CarsAdapter carsAdapter;
    private Button backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);


        cars = findViewById(R.id.carsListView);
        backToMain = findViewById(R.id.backToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cars.this, MainActivity.class);
                startActivity(i);
            }
        });

        Intent i = this.getIntent();
        ArrayList<CarModel> cList = i.getParcelableArrayListExtra("list");
        carsAdapter = new CarsAdapter(cList,getApplicationContext());
        cars.setAdapter(carsAdapter);

        cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent goToProduct = new Intent(getApplicationContext(), Item.class);

                goToProduct.putExtra("Id",carsAdapter.getItem(position).getId());
                goToProduct.putExtra("CarName",carsAdapter.getItem(position).getCarName());
                goToProduct.putExtra("CarImage",carsAdapter.getItem(position).getCarImage());
                goToProduct.putExtra("CarDescription", carsAdapter.getItem(position).getCarDescription());
                goToProduct.putExtra("CarPrice",carsAdapter.getItem(position).getCarPrice());

                startActivity(goToProduct);
            }
        });
    }
}