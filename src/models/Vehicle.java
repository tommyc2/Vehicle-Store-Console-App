package models;

import utils.Utilities;

public abstract class Vehicle {
    private String regNumber = "No reg";
    private int year = 2000;
    private float cost = 1000;
    Manufacturer manufacturer;
    String model = "No model";

    public Vehicle(String regNumber, String model, float cost, Manufacturer manufacturer, int year) {
        setRegNumber(regNumber);
        setYear(year);
        setCost(cost);
        this.manufacturer = manufacturer;
        setModel(model);
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = Utilities.truncateString(regNumber,8);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (Utilities.validRange(year,2000,2023)){
            this.year = year;
        }
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost >= 1000){
            this.cost = cost;
        }
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
        if (Utilities.validStringlength(model,15)){
            this.model = model;
        }
        else{
            this.model = Utilities.truncateString(model,15);
        }
    }

    @Override
    public String toString() {

    String str = "Vehicle => " + "Reg Number: " + this.regNumber + " Model: " + this.model + " Cost: " +
            this.cost + " | Manufacturer => Name: " + manufacturer.getManufacturerName() +
            " No. of employees: " + manufacturer.getNumEmployees() + " | Age of vehicle: ";

    if (this.getAge() == 0){
        str += "Brand New";
    }
    if (this.getAge() == 1){
        str += "1 year old";
    }
    if (this.getAge() > 1){
        str += this.getAge() + " years old ";
    }

    return str;

    }

    public abstract double getCarbonFootPrint();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return year == vehicle.year && Float.compare(vehicle.cost, cost) == 0 && regNumber.equals(vehicle.regNumber) && manufacturer.equals(vehicle.manufacturer) && model.equals(vehicle.model);
    }

    public int getAge(){
        int currentYear = 2023;
        return currentYear - getYear();
    }

}
