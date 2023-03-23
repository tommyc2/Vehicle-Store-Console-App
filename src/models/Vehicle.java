package models;

public abstract class Vehicle {
    String regNumber = "";
    int year;
    float cost;
    Manufacturer manufacturer; // edit later
    String model = "No model";

    public Vehicle(String regNumber, int year, float cost, Manufacturer manufacturer, String model) {
        this.regNumber = regNumber;
        this.year = year;
        this.cost = cost;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
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
}
