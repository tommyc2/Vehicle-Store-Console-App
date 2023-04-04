package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    CarbonFuelCar carValidAtLowValue, carValidAtHighValue, carInvalidBelowLowValue, carInvalidAboveHighValue;
    // 250-1000 249,250,1000,1001


   /*  private int secs0To60 = 4; // 4-25
    private int power = 120; // 120-300
    private float torque = 100; // 100-400
    private int topSpeed = 50; // 50-3000
    */


    @BeforeEach
    void setUp() {
    carInvalidBelowLowValue = new CarbonFuelCar("notTesting","notTesting",0,null,0,119,3,49,99,"notTesting",0,0,0,false);
    carValidAtLowValue = new CarbonFuelCar("notTesting","notTesting",0,null,0,120,4,50,100,"notTesting",0,0,0,false);
    carValidAtHighValue = new CarbonFuelCar("notTesting","notTesting",0,null,0,300,25,3000,400,"notTesting",0,0,0,false);
    carInvalidAboveHighValue = new CarbonFuelCar("notTesting","notTesting",0,null,0,301,26,3001,401,"notTesting",0,0,0,false);
    }


    @Nested
    class constructorTests {
        @Test
        void validatingSecsTo60() {
            assertEquals(4,carInvalidBelowLowValue.getSecs0To60());
            assertEquals(4,carValidAtLowValue.getSecs0To60());
            assertEquals(25,carValidAtHighValue.getSecs0To60());
            assertEquals(4,carInvalidAboveHighValue.getSecs0To60());
        }
        @Test
        void validatingPower() {
            assertEquals(120, carInvalidBelowLowValue.getPower());
            assertEquals(120,carInvalidAboveHighValue.getPower());
            assertEquals(300,carValidAtHighValue.getPower());
            assertEquals(120,carValidAtLowValue.getPower());
        }

        @Test
        void validatingTorque() {
            assertEquals(100,carInvalidBelowLowValue.getTorque(),0.01);
            assertEquals(100,carValidAtLowValue.getTorque(),0.01);
            assertEquals(400,carValidAtHighValue.getTorque(),0.01);
            assertEquals(100,carInvalidAboveHighValue.getTorque(),0.01);
        }
        @Test
        void validatingTopSpeed() {
            assertEquals(50,carInvalidBelowLowValue.getTopSpeed());
            assertEquals(50,carValidAtLowValue.getTopSpeed());
            assertEquals(3000,carValidAtHighValue.getTopSpeed());
            assertEquals(50,carInvalidAboveHighValue.getTopSpeed());
        }
    }
    /*  private int secs0To60 = 4; // 4-25
    private int power = 120; // 120-300
    private float torque = 100; // 100-400
    private int topSpeed = 50; // 50-3000
    */

    @Nested
    class settersAndGetters {
        @Test
        void settingSecs0To60() {
        assertEquals(4, carValidAtLowValue.getSecs0To60());
        carValidAtLowValue.setSecs0To60(3);
        assertEquals(4, carValidAtLowValue.getSecs0To60());
        carValidAtLowValue.setSecs0To60(4);
        assertEquals(4,carValidAtLowValue.getSecs0To60());
        carValidAtLowValue.setSecs0To60(25);
        assertEquals(25,carValidAtLowValue.getSecs0To60());
        carValidAtLowValue.setSecs0To60(26);
        assertEquals(25,carValidAtLowValue.getSecs0To60());
        }
        @Test
        void settingPower() {
            assertEquals(120,carValidAtLowValue.getPower());
            carValidAtLowValue.setPower(119);
            assertEquals(120,carValidAtLowValue.getPower());
            carValidAtLowValue.setPower(120);
            assertEquals(120,carValidAtLowValue.getPower());
            carValidAtLowValue.setPower(300);
            assertEquals(300,carValidAtLowValue.getPower());
            carValidAtLowValue.setPower(301);
            assertEquals(300,carValidAtLowValue.getPower());

        }

        @Test
        void settingTorque() {
            assertEquals(100,carValidAtLowValue.getTorque(),0.01);
            carValidAtLowValue.setTorque(99);
            assertEquals(100,carValidAtLowValue.getTorque(),0.01);
            carValidAtLowValue.setTorque(100);
            assertEquals(100,carValidAtLowValue.getTorque(),0.01);
            carValidAtLowValue.setTorque(399);
            assertEquals(399,carValidAtLowValue.getTorque(),0.01);
            carValidAtLowValue.setTorque(400);
            assertEquals(400,carValidAtLowValue.getTorque(),0.01);
            carValidAtLowValue.setTorque(401);
            assertEquals(400,carValidAtLowValue.getTorque(),0.01);
        }

        @Test
        void settingTopSpeed() {
            assertEquals(50,carValidAtLowValue.getTopSpeed());
            carValidAtLowValue.setTopSpeed(49);
            assertEquals(50,carValidAtLowValue.getTopSpeed());
            carValidAtLowValue.setTopSpeed(50);
            assertEquals(50,carValidAtLowValue.getTopSpeed());
            carValidAtLowValue.setTopSpeed(3000);
            assertEquals(3000,carValidAtLowValue.getTopSpeed());
            carValidAtLowValue.setTopSpeed(3001);
            assertEquals(3000,carValidAtLowValue.getTopSpeed());
        }
    }

    @Test
    void testToString() {
        CarbonFuelCar car = new CarbonFuelCar("ABCD5678","toyota789012345",1000, new Manufacturer("Toyota", 1000), 2023,120,4,50,100,"diesel",5,1,800,false);
        String strToTest = car.toString();

        assertTrue(strToTest.contains("ABCD5678"));
        assertTrue(strToTest.contains("toyota789012345"));
        assertTrue(strToTest.contains("Toyota"));
        assertTrue(strToTest.contains("1000"));
        // Car tests
        assertTrue(strToTest.contains("| Seconds To 60: 4"));
        assertTrue(strToTest.contains("Top Speed: 50"));
        assertTrue(strToTest.contains(" Power: 120"));
        assertTrue(strToTest.contains("Torque: 100"));
    }
}