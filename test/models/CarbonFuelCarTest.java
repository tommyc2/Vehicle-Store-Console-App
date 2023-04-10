package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarbonFuelCarTest {

    CarbonFuelCar carbonFuelCarValidAtLowValue, carbonFuelCarInvalidBelowLowValue, carbonFuelCarValidAtHighValue, carbonFuelCarInvalidAboveHighValue;

    @BeforeEach
    void setUp() {
        carbonFuelCarInvalidBelowLowValue = new CarbonFuelCar("12345678","toyota",0,null,0,0,0,0,0,"petrol",4,0,799,false);
        carbonFuelCarValidAtLowValue = new CarbonFuelCar("12345678","nissan",1000,new Manufacturer("tester", 1),2020,120,4,50,100,"petrol",5,1,800,false);
        carbonFuelCarValidAtHighValue = new CarbonFuelCar("12345678","ford",0,null,0,0,0,0,0,"petrol",20,25,2500,false);
        carbonFuelCarInvalidAboveHighValue = new CarbonFuelCar("12345678","merc",0,null,0,0,0,0,0,"diesel",21,100000,2501,false);

    }
    @Nested
    class constructorTestsForCarbon {
    @Test
        void validatingFuelConsumption(){
        assertEquals(5,carbonFuelCarInvalidBelowLowValue.getFuelConsumption(),0.01);
        assertEquals(5,carbonFuelCarValidAtLowValue.getFuelConsumption(),0.01);
        assertEquals(20,carbonFuelCarValidAtHighValue.getFuelConsumption(),0.01);
        assertEquals(5,carbonFuelCarInvalidAboveHighValue.getFuelConsumption(),0.01);

        }
        @Test
        void validatingCarbonEmission(){
        assertEquals(1, carbonFuelCarInvalidBelowLowValue.getCarbonEmission(),0.01);
        assertEquals(1, carbonFuelCarValidAtLowValue.getCarbonEmission(),0.01);
        assertEquals(25, carbonFuelCarValidAtHighValue.getCarbonEmission(),0.01);
        assertEquals(100000, carbonFuelCarInvalidAboveHighValue.getCarbonEmission(),0.01);
        }

        @Test
        void validatingIfItsAutomaticOrNot(){
            assertFalse(carbonFuelCarInvalidBelowLowValue.isAutomatic());
            assertFalse(carbonFuelCarInvalidAboveHighValue.isAutomatic());
            assertFalse(carbonFuelCarValidAtHighValue.isAutomatic());
            assertFalse(carbonFuelCarValidAtLowValue.isAutomatic());
        }

        @Test
        void validatingFuelType(){
        assertEquals("petrol", carbonFuelCarInvalidBelowLowValue.getFuelType());
        assertEquals("petrol", carbonFuelCarValidAtLowValue.getFuelType());
        assertEquals("petrol", carbonFuelCarValidAtHighValue.getFuelType());
        assertEquals("diesel", carbonFuelCarInvalidAboveHighValue.getFuelType());

        }

        @Test
        void validatingEngineSize(){
        assertEquals(800, carbonFuelCarInvalidBelowLowValue.getEngineSize());
        assertEquals(800, carbonFuelCarValidAtLowValue.getEngineSize());
        assertEquals(2500, carbonFuelCarValidAtHighValue.getEngineSize());
        assertEquals(800, carbonFuelCarInvalidAboveHighValue.getEngineSize());
        }
    }

    @Nested
    class setterAndGetterTestsCarbon {

        @Test
        void setFuelConsumption() {
            assertEquals(5, carbonFuelCarValidAtLowValue.getFuelConsumption(),0.01);
            carbonFuelCarValidAtLowValue.setFuelConsumption(4);
            assertEquals(5, carbonFuelCarValidAtLowValue.getFuelConsumption(),0.01);
            carbonFuelCarValidAtLowValue.setFuelConsumption(20);
            assertEquals(20, carbonFuelCarValidAtLowValue.getFuelConsumption(),0.01);
            carbonFuelCarValidAtLowValue.setFuelConsumption(21);
            assertEquals(20, carbonFuelCarValidAtLowValue.getFuelConsumption(),0.01);
        }

        @Test
        void setCarbonEmission() {
            assertEquals(1, carbonFuelCarValidAtLowValue.getCarbonEmission(),0.01);
            carbonFuelCarValidAtLowValue.setCarbonEmission(0);
            assertEquals(1, carbonFuelCarValidAtLowValue.getCarbonEmission(),0.01);
            carbonFuelCarValidAtLowValue.setCarbonEmission(500000.1F);
            assertEquals(500000.1F, carbonFuelCarValidAtLowValue.getCarbonEmission(),0.01);
        }

        @Test
        void setAutomatic() {
            assertEquals(false, carbonFuelCarValidAtLowValue.isAutomatic());
            carbonFuelCarValidAtLowValue.setAutomatic(true);
            assertEquals(true, carbonFuelCarValidAtLowValue.isAutomatic());
        }

        @Test
        void setFuelType() {
            assertEquals("petrol", carbonFuelCarValidAtLowValue.getFuelType());
            carbonFuelCarValidAtLowValue.setFuelType("Diesel");
            assertEquals("petrol", carbonFuelCarValidAtLowValue.getFuelType());
            carbonFuelCarValidAtLowValue.setFuelType("petrol");
            assertEquals("petrol", carbonFuelCarValidAtLowValue.getFuelType());
            carbonFuelCarValidAtLowValue.setFuelType("merrygoround");
            assertEquals("petrol", carbonFuelCarValidAtLowValue.getFuelType());
            carbonFuelCarValidAtLowValue.setFuelType("p");
            assertEquals("petrol", carbonFuelCarValidAtLowValue.getFuelType());

        }

        @Test
        void setEngineSize() {
            assertEquals(800, carbonFuelCarValidAtLowValue.getEngineSize());
            carbonFuelCarValidAtLowValue.setEngineSize(799);
            assertEquals(800, carbonFuelCarValidAtLowValue.getEngineSize());
            carbonFuelCarValidAtLowValue.setEngineSize(800);
            assertEquals(800, carbonFuelCarValidAtLowValue.getEngineSize());
            carbonFuelCarValidAtLowValue.setEngineSize(2500);
            assertEquals(2500, carbonFuelCarValidAtLowValue.getEngineSize());
            carbonFuelCarValidAtLowValue.setEngineSize(2501);
            assertEquals(2500, carbonFuelCarValidAtLowValue.getEngineSize());
        }
    }

    @Test
    void testToString() {
    String strToTestCarbon = carbonFuelCarValidAtLowValue.toString();

                // Testing inherited fields
        assertTrue(strToTestCarbon.contains("12345678"));
        assertTrue(strToTestCarbon.contains("nissan"));
        assertTrue(strToTestCarbon.contains("1000"));
        assertTrue(strToTestCarbon.contains("12345678"));
        assertTrue(strToTestCarbon.contains("tester"));
        assertTrue(strToTestCarbon.contains(" 1"));
        assertTrue(strToTestCarbon.contains("120"));
        assertTrue(strToTestCarbon.contains("4"));
        assertTrue(strToTestCarbon.contains("50"));
        assertTrue(strToTestCarbon.contains("100"));
        assertTrue(strToTestCarbon.contains(" | Age of vehicle: 3 years old"));

                     // Test Carbon Vehicle fields //
        /*
            private float fuelConsumption = 5;
            private float carbonEmission = 1;
            private boolean automatic = false;
            private String fuelType = "petrol";
            private int engineSize = 800;
        */
        assertTrue(strToTestCarbon.contains(" | Engine Size: 800"));
        assertTrue(strToTestCarbon.contains(" Fuel Consumption: 5"));
        assertTrue(strToTestCarbon.contains("Is it an automatic?: No"));
        assertTrue(strToTestCarbon.contains(" Carbon Emission: 1"));

    }

    @Test
    void verifyingCarbonFootprintIsCorrect() {
    assertEquals(46, carbonFuelCarInvalidBelowLowValue.getCarbonFootPrint(),0.01);
    assertEquals(6, carbonFuelCarValidAtLowValue.getCarbonFootPrint(),0.01);
    assertEquals(14375,carbonFuelCarValidAtHighValue.getCarbonFootPrint(),0.01);
    assertEquals(4600000,carbonFuelCarInvalidAboveHighValue.getCarbonFootPrint(),0.01);
    }
}