package models;

import utils.Utilities;

import java.util.Objects;

public abstract class Car extends Vehicle {

    private int secs0To60 = 4; // 4-25
    private int power = 120; // 120-300
    private float torque = 100; // 100-400
    private int topSpeed = 50; // 50-3000

    public Car(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int secs0To60, int power, float torque, int topSpeed) {
        super(regNumber, model, cost, manufacturer, year);
        setSecs0To60(secs0To60);
        setPower(power);
        setTorque((torque));
        setTopSpeed(topSpeed);
    }

    public int getSecs0To60() {
        return secs0To60;
    }

    public void setSecs0To60(int secs0To60) {
        if (Utilities.validRange(secs0To60,4,25)){
            this.secs0To60 = secs0To60;
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if(Utilities.validRange(power,120,300)){
            this.power = power;
        }
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(float torque) {
       if (Utilities.validRange(torque,100,400,0.01F)) {
            this.torque = torque;
        }
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        if (Utilities.validRange(topSpeed,50,3000)) {
            this.topSpeed = topSpeed;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        if (!super.equals(o)) return false;
        return secs0To60 == car.secs0To60 && power == car.power && Float.compare(car.torque, torque) == 0 && topSpeed == car.topSpeed;
    }

    public abstract double getCarbonFootPrint();

    public String toString(){
        String superStr = super.toString();

        superStr += "| Seconds To 60: " + this.secs0To60 + " Power: " + this.power + " Torque: " + this.torque
                + "Top Speed: " + this.topSpeed;

        return superStr;
    }


}
