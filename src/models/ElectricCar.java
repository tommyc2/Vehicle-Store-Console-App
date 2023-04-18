package models;

import utils.Utilities;

import java.util.Objects;

public class ElectricCar extends Car {

    private int range = 100;
    private float engineKWatts = 40;

    public ElectricCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power,int secs0To60,int topSpeed, float torque, float engineKWatts, int range) {
        super(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed);
        setRange(range);
        setEngineKWatts((engineKWatts));
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        if (Utilities.validRange(range,100,500)){
            this.range = range;
        }
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }

    public void setEngineKWatts(float engineKWatts) {
        if (Utilities.validRange(engineKWatts,40,60,0.01F)){
            this.engineKWatts = engineKWatts;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricCar that)) return false;
        if (!super.equals(o)) return false;
        return range == that.range && Float.compare(that.engineKWatts, engineKWatts) == 0;
    }

    @Override
    public double getCarbonFootPrint() {
        return (engineKWatts*getAge()) / 20000;
        // engineKWatts * age / 20,000

    }

    public String toString(){
        String superStr = super.toString();

        superStr += " | Engine Power (kW): " + engineKWatts + " Range: " + this.range;

        return superStr;
    }
}
