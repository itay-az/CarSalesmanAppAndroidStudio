package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Item extends AppCompatActivity {

    private TextView carName,carPrice,carDecs;

    private Button update,delete;

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private ArrayList<CarModel> cars;

    private ImageView carImage;


    private String cName,cPrice,cDesc,cImage,id,img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);



        carName = findViewById(R.id.carName);
        carPrice = findViewById(R.id.carPrice);
        carDecs = findViewById(R.id.carDescription);
        carImage = findViewById(R.id.carImageitem);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        Intent content = getIntent();



        id =content.getStringExtra("Id");

        getCarById(id);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCarById(id);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateByCar(id);
            }
        });

    }

    public void getCarById(String id){
        database.collection("cars")
                .document(id)
                .get()
                .addOnSuccessListener( task -> {
                    DocumentSnapshot document = task;
                    cName = document.getString("carName");
                    carName.setText("car name: " + cName);
                    cPrice = document.getString("carPrice");
                    carPrice.setText("car price: " +cPrice);
                    cDesc = document.getString("carDescription");
                    carDecs.setText("Car Description: " + cDesc);
                    cImage = document.getString("carImage");
                    Picasso.get().load(cImage).into(carImage);
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error: " + e,Toast.LENGTH_LONG).show();
                    }
                });
    }



    public void deleteCarById(String id){
        database.collection("cars")
                .document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"car deleted",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Item.this,Cars.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"error:" + e,Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void UpdateByCar(String id){
        Intent i = new Intent(Item.this,UpdateItem.class);
        i.putExtra("Id",id);
        i.putExtra("name",cName );
        i.putExtra("desc",cDesc );
        i.putExtra("price",cPrice );
        i.putExtra("image",cImage );
        startActivity(i);
    }
}