package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class CarsAdapter extends ArrayAdapter<CarModel>{

    private ArrayList<CarModel> carList;
    Context context;


    public CarsAdapter(ArrayList<CarModel> data, Context context) {
        super(context, R.layout.car_item,data);
        this.carList = data;
        this.context = context;
    }

    private static class ViewHolder {
        TextView carName,carPrice;
        ImageView carImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CarModel carModel = getItem(position);
        ViewHolder viewHolder;

        System.out.println(carModel.getCarImage().toString());

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.car_item,parent,false);

            viewHolder.carName = convertView.findViewById(R.id.carName);
            viewHolder.carPrice = convertView.findViewById(R.id.carPrice);
            viewHolder.carImage = convertView.findViewById(R.id.carImage);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)  convertView.getTag();
        }

        viewHolder.carName.setText(carModel.getCarName());
        viewHolder.carPrice.setText(carModel.getCarPrice());
        Picasso.get().load(carModel.getCarImage()).resize(90,90).centerCrop().into(viewHolder.carImage);

        return convertView;
    }
}
