package models;

import java.util.Objects;

public class Scooter extends Vehicle {

    int power;
    int topRiderWeight;
    float weight;

    public Scooter(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, int topRiderWeight, float weight) {
        super(regNumber, model, cost, manufacturer, year);
        this.power = power;
        this.topRiderWeight = topRiderWeight;
        this.weight = weight;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTopRiderWeight() {
        return topRiderWeight;
    }

    public void setTopRiderWeight(int topRiderWeight) {
        this.topRiderWeight = topRiderWeight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    @Override
    public double getCarbonFootprint() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter scooter)) return false;
        if (!super.equals(o)) return false;
        return power == scooter.power && topRiderWeight == scooter.topRiderWeight && Float.compare(scooter.weight, weight) == 0;
    }

}
