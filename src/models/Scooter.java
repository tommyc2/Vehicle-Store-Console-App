package models;

import utils.Utilities;

import java.util.Objects;

public class Scooter extends Vehicle {

    private int power = 250;
    private int topRiderWeight = 100;
    private float weight = 5;

    public Scooter(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, float weight, int topRiderWeight) {
        super(regNumber, model, cost, manufacturer, year);
        setPower(power);
        setWeight(weight);
        setTopRiderWeight(topRiderWeight);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (Utilities.validRange(power, 250, 1000))
            this.power = power;
    }

    public int getTopRiderWeight() {
        return topRiderWeight;
    }

    public void setTopRiderWeight(int topRiderWeight) {
       if (Utilities.validRange(topRiderWeight,100,120)) {
            this.topRiderWeight = topRiderWeight;
        }
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
       if (Utilities.validRange(weight,5,100,0.01F)){
           this.weight = weight;
       }
    }
    @Override
    public double getCarbonFootPrint() {
        return ((power)*(weight)*(this.getAge())) / 15000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter scooter)) return false;
        if (!super.equals(o)) return false;
        return power == scooter.power && topRiderWeight == scooter.topRiderWeight && Float.compare(scooter.weight, weight) == 0;
    }

    @Override
    public String toString() {
        String superStr = super.toString();

        superStr +=
        "Power: " + this.power + "Top Rider Weight: " + this.topRiderWeight + "Weight : " + this.weight + "kg";

        return superStr;
    }
}
