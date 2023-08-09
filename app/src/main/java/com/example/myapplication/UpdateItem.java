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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateItem extends AppCompatActivity {

    private EditText name,price,desc,image;
    private Button btnUpdate;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);


        name = findViewById(R.id.updateCarName);
        price = findViewById(R.id.updateCarPrice);
        desc = findViewById(R.id.updateCarDesc);
        image = findViewById(R.id.updateCarImage);
        btnUpdate = findViewById(R.id.btn_update);

        String cname,cprice,cdesc,cimage,id;

        Intent content = getIntent();
        id = content.getStringExtra("Id");
        cname = content.getStringExtra("name");
        cprice = content.getStringExtra("price");
        cdesc = content.getStringExtra("desc");
        cimage = content.getStringExtra("image");

        name.setText(cname);
        price.setText(cprice);
        desc.setText(cdesc);
        image.setText(cimage);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateById(id);
                Intent i = new Intent(UpdateItem.this,Cars.class);
                startActivity(i);

            }
        });

    }

    public void updateById(String id) {
        System.out.println(id);
        Task<Void> documentReference = database.collection("cars")
                .document(id)
                .update(
                        "carName", name.getText().toString(),
                        "carPrice", price.getText().toString(),
                        "carDescription", desc.getText().toString(),
                        "carImage", image.getText().toString()
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Car Updated!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "error: " + e, Toast.LENGTH_LONG).show();
                    }
                });
    }
}