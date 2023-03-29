package models;

import java.util.Objects;

public class ElectricCar extends Car {

    private int range = 0;
    private float engineKWatts = 0;

    public ElectricCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int secs0To60, int power, float torque, int topSpeed, int range, float engineKWatts) {
        super(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed);
        this.range = range;
        this.engineKWatts = engineKWatts;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }

    public void setEngineKWatts(float engineKWatts) {
        this.engineKWatts = engineKWatts;
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
        return 0;
    }
}
