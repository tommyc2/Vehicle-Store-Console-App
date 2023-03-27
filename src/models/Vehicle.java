package models;

import utils.Utilities;

import java.util.Objects;

public abstract class Vehicle {
    String regNumber = "No reg";
    int year;
    float cost;
    Manufacturer manufacturer;
    String model = "No model";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return year == vehicle.year && Float.compare(vehicle.cost, cost) == 0 && regNumber.equals(vehicle.regNumber) && manufacturer.equals(vehicle.manufacturer) && model.equals(vehicle.model);
    }

    public Vehicle(String regNumber, String model, float cost, Manufacturer manufacturer, int year) {
        setRegNumber(regNumber);
        this.year = year;
        this.cost = cost;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        if (Utilities.validStringlength(regNumber,8)){
            this.regNumber = regNumber;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "regNumber='" + regNumber + '\'' +
                ", year=" + year +
                ", cost=" + cost +
                ", manufacturer=" + manufacturer +
                ", model='" + model + '\'' +
                '}';
    }

    public abstract double getCarbonFootprint();

}
