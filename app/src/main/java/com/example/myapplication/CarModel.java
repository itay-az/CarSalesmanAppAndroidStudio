package com.example.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.UUID;

public class CarModel implements Parcelable {

    private String carName;
    private String id;
    private String carPrice;
    private String carDescription;
    private String carImage;

    public CarModel(){}

    public CarModel(String id, String carName, String carPrice, String carDescription, String carImage) {
        this.carName = carName;
        this.carPrice = carPrice;
        this.carDescription = carDescription;
        this.carImage = carImage;
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getId() {return id;}

    public void setId(String id) { this.id = id;}

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(carName);
        parcel.writeString(carPrice);
        parcel.writeString(carDescription);
        parcel.writeString(carImage);
    }

    public CarModel(Parcel parcel) {
        id = parcel.readString();
        carName = parcel.readString();
        carPrice = parcel.readString();
        carDescription = parcel.readString();
        carImage = parcel.readString();
    }

    public static final Creator<CarModel> CREATOR = new Creator<CarModel>() {
        @Override
        public CarModel createFromParcel(Parcel in) {
            return new CarModel(in);
        }

        @Override
        public CarModel[] newArray(int size) {
            return new CarModel[size];
        }
    };
}
