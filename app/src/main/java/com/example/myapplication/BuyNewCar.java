package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BuyNewCar extends AppCompatActivity implements View.OnClickListener {

    private Button backToMain,AddCar;
    private EditText carNamelbl,carPricelbl,carDescriptionlbl,carImagelbl;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    public static final String KEY_CAR_NAME = "carName";
    public static final String KEY_CAR_ID = "id";
    public static final String KEY_CAR_PRICE = "carPrice";
    public static final String KEY_CAR_DESCRIPTION = "carDescription";
    public static final String KEY_CAR_IMAGE = "carImage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_new_car);

        backToMain = findViewById(R.id.backToMain);
        AddCar = findViewById(R.id.AddCar);
        carNamelbl = findViewById(R.id.carNamelbl);
        carPricelbl = findViewById(R.id.carPricelbl);
        carDescriptionlbl = findViewById(R.id.carDescriptionlbl);
        //carImagelbl = findViewById(R.id.carImagelbl);

        AddCar.setOnClickListener(this);


        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BuyNewCar.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void CreateNewCar(){
        String carName = carNamelbl.getText().toString().trim();
        String carPrice = carPricelbl.getText().toString().trim();
        String carId = UUID.randomUUID().toString();
        String carDescription = carDescriptionlbl.getText().toString().trim();
        String carImage = "https://www.motortrend.com/uploads/sites/5/2021/01/Tesla_Model_X_Refresh_2.jpg";
        Map<String,Object > data = new HashMap<>();
        data.put(KEY_CAR_NAME, carName);
        data.put(KEY_CAR_ID,carId);
        data.put(KEY_CAR_PRICE, carPrice);
        data.put(KEY_CAR_DESCRIPTION, carDescription);
        data.put(KEY_CAR_IMAGE, carImage);

        database.collection("cars")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Car Created! " , Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error " + e, Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AddCar:
                CreateNewCar();
                break;
        }
    }
}