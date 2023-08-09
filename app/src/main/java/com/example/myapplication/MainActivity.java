package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button AddNewCar,ViewAllCar;
    private ArrayList<CarModel> cars;

    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    public static final String KEY_CAR_NAME = "carName";
    public static final String KEY_CAR_PRICE = "carPrice";
    public static final String KEY_CAR_DESCRIPTION = "carDescription";
    public static final String KEY_CAR_IMAGE = "carImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddNewCar =findViewById(R.id.BuyNewCar);
        ViewAllCar = findViewById(R.id.ViewAllCars);

        AddNewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BuyNewCar.class);
                startActivity(i);
            }
        });


        ViewAllCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllCars();
                Intent i = new Intent(MainActivity.this, ViewAllCars.class);
                startActivity((i));
            }
        });
    }

    private void getAllCars(){
        database
                .collection("cars")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            cars = new ArrayList<CarModel>();
                            for(QueryDocumentSnapshot document: task.getResult()){
                                CarModel carModel = document.toObject(CarModel.class);
                                carModel.setId(document.getId());
                                cars.add(carModel);
                            }
                            Intent intent = new Intent(getApplicationContext(), Cars.class);
                            intent.putParcelableArrayListExtra("list",cars);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}