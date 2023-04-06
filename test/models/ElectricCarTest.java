package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest {

    ElectricCar electricCarInvalidBelowLowValue, electricCarValidAtLowValue, electricInvalidAboveHighValue, electricCarValidAtHighValue;
    @BeforeEach
    void setUp() {
        electricCarInvalidBelowLowValue = new ElectricCar("nottesting", "notTesting", 0, null,0,0,0,0,0,39,99);
        electricCarValidAtLowValue = new ElectricCar("nottesting", "notTesting", 0, null,0,0,0,0,0,40,100);
        electricCarValidAtHighValue = new ElectricCar("nottesting", "notTesting", 0, null,0,0,0,0,0,60,500);
        electricInvalidAboveHighValue = new ElectricCar("nottesting", "notTesting", 0, null,0,0,0,0,0,61,501);
    }

    @Nested
    class constructorTests {
        @Test
        void validatingRange() {
            assertEquals(100, electricCarInvalidBelowLowValue.getRange());
            assertEquals(100, electricCarValidAtLowValue.getRange());
            assertEquals(500, electricCarValidAtHighValue.getRange());
            assertEquals(100, electricInvalidAboveHighValue.getRange());
        }
        @Test
        void validatingEngineKWatts() {
            assertEquals(40, electricCarInvalidBelowLowValue.getEngineKWatts(),.01);
            assertEquals(40, electricCarValidAtLowValue.getEngineKWatts(),.01);
            assertEquals(60, electricCarValidAtHighValue.getEngineKWatts(),.01);
            assertEquals(40, electricInvalidAboveHighValue.getEngineKWatts(),.01);
        }
    }

    @Nested
    class settersTests {
        @Test
        void settingRange() {
            assertEquals(100, electricCarValidAtLowValue.getRange());
            electricCarValidAtLowValue.setRange(99);
            assertEquals(100, electricCarValidAtLowValue.getRange());
            electricCarValidAtLowValue.setRange(100);
            assertEquals(100, electricCarValidAtLowValue.getRange());
            electricCarValidAtLowValue.setRange(500);
            assertEquals(500, electricCarValidAtLowValue.getRange());
            electricCarValidAtLowValue.setRange(501);
            assertEquals(500, electricCarValidAtLowValue.getRange());
        }

        @Test
        void settingEngineKWatts() {
            assertEquals(40,electricCarValidAtLowValue.getEngineKWatts(),0.01);
            electricCarValidAtLowValue.setEngineKWatts(39);
            assertEquals(40,electricCarValidAtLowValue.getEngineKWatts(),0.01);
            electricCarValidAtLowValue.setEngineKWatts(40);
            assertEquals(40,electricCarValidAtLowValue.getEngineKWatts(),0.01);
            electricCarValidAtLowValue.setEngineKWatts(60);
            assertEquals(60,electricCarValidAtLowValue.getEngineKWatts(),0.01);
            electricCarValidAtLowValue.setEngineKWatts(61);
            assertEquals(60,electricCarValidAtLowValue.getEngineKWatts(),0.01);
        }
    }

    @Test
    void gettingCarbonFootPrint() {
        // engineKWatts * age / 20,000
        assertEquals(0.046, electricCarInvalidBelowLowValue.getCarbonFootPrint(),0.01F);
        assertEquals(0.046, electricCarValidAtLowValue.getCarbonFootPrint(),0.01F);
        assertEquals(0.069, electricCarValidAtHighValue.getCarbonFootPrint(),0.01F);
        assertEquals(0.046, electricInvalidAboveHighValue.getCarbonFootPrint(),0.01F);
    }

    @Test
    void testToString() {
        ElectricCar electricCar = new ElectricCar("123678","nissanGt",1000,new Manufacturer("tester2",3),2022,120,4,50,101,40,100);
        String strToTest = electricCar.toString();

        // Testing inherited fields from Car and Vehicles in Models + Electric Car fields
        assertTrue(strToTest.contains("123678"));
        assertTrue(strToTest.contains("nissanGt"));
        assertTrue(strToTest.contains(" 1000"));
        assertTrue(strToTest.contains("tester2"));
        assertTrue(strToTest.contains("3"));
        assertTrue(strToTest.contains("1 year old"));
        assertTrue(strToTest.contains("120"));
        assertTrue(strToTest.contains("4"));
        assertTrue(strToTest.contains("50"));
        assertTrue(strToTest.contains("100"));
        assertTrue(strToTest.contains("40"));
        assertTrue(strToTest.contains("101"));
    }

}