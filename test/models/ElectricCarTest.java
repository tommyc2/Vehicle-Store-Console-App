package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest {

    ElectricCar electricCarInvalidBelowLowValue, electricCarValidAtLowValue, electricInvalidAboveHighValue, electricCarValidAtHighValue;
    @BeforeEach
    void setUp() {
    }

    @Nested
    class constructorTests {
        @Test
        void validatingRange() {
        }
        @Test
        void validatingEngineKWatts() {
        }
    }

    @Nested
    class settersTests {
        @Test
        void settingRange() {
        }

        @Test
        void settingEngineKWatts() {
        }
    }

    @Test
    void gettingCarbonFootPrint() {
    }

    @Test
    void testToString() {
    }

}