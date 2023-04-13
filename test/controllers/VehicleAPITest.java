package controllers;

import models.CarbonFuelCar;
import models.ElectricCar;
import models.Manufacturer;
import models.Scooter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleAPITest {

    private Scooter scooterBelowBoundary, scooterOnBoundary, scooterAboveBoundary, scooterInvalidData;
    private ElectricCar electricCarBelowBoundary, electricCarOnBoundary, electricCarAboveBoundary, electricCarInvalidData, electricCarValidForEmptyArrayList;
    private CarbonFuelCar carbonFuelBelowBoundary, carbonFuelOnBoundary, carbonFuelAboveBoundary, carbonFuelInvalidData;

    private Manufacturer ford = new Manufacturer("Ford", 1020);
    private Manufacturer kia = new Manufacturer("Kia", 1200);
    private Manufacturer audi = new Manufacturer("Audi", 1325);
    private Manufacturer tesla = new Manufacturer("Tesla", 3245);
    private Manufacturer mazda = new Manufacturer("Mazda", 2377);
    private Manufacturer hyundai = new Manufacturer("Hyundai", 2765);

   private VehicleAPI populatedVehicles = new VehicleAPI(new File("vehicles.xml"));
   private VehicleAPI emptyVehicles = new VehicleAPI(new File("vehiclesempty.xml"));

    @BeforeEach
    void setUp() {
        // Vehicle Validation Rules:
        //    regNumber (max 8 chars, default ""), model (max 15 chars, default ""),
        //    cost (>= 1000, default 1000), year (2000 - 2023, default 2000)

        // Scooter Validation Rules:
        //    power (250 -> 1000, default 250), weight (5 -> 100, default 5), topRiderWeight (100 -> 120, default 100)
        scooterBelowBoundary = new Scooter("SCOOT12", "Scootn1 Master", 999,
                ford, 1999, 119, 4, 99);

        scooterOnBoundary = new Scooter("SCOOT321", "MasterScooter-3", 1000,
                kia, 2000, 120, 5, 100);

        scooterAboveBoundary = new Scooter("SC 123456", "Speed Scooter X1", 1001,
                tesla, 2001, 121, 6, 101);

        scooterInvalidData = new Scooter(null, null, -1,
                null, -1, -1, -1, -1);


        //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0),
        electricCarBelowBoundary = new ElectricCar("Elec987","Electric 12111", 999, mazda, 2020, 120, 5, 100, 100, 50, 100);

       electricCarOnBoundary = new ElectricCar("Elec5678", "Electric 123456", 1000, kia, 2000, 120, 4, 50, 100, 40, 100);

        electricCarAboveBoundary = new ElectricCar("Elec12345", "Electric 1234567", 1001,
                tesla, 2001, 121, 5, 51, 101, 41, 101);

        electricCarInvalidData = new ElectricCar(null, null, -1,
                null, -1, -1, -1, -1, -1, -1, -1);

        electricCarValidForEmptyArrayList = new ElectricCar("Elec12345", "Electric 1234567", 1001,
                tesla, 2001, 121, 5, 51, 101, 41, 101);


        // Carbon Fuel Car Validation Rules:
        //    fuelType (diesel or petrol, default petrol), fuelConsumption (5 -> 20, default 5),
        //    carbonEmission (>0, default 0.1), engineSize (800->2500, default 800)
        carbonFuelBelowBoundary = new CarbonFuelCar("Car1234", "CarbonCar 1234", 999,
                ford, 1999, 119, 3, 49, 99, "diesel",
                4, 0, 799, false);

        carbonFuelOnBoundary = new CarbonFuelCar("Car54321", "CarbonCar 12345", 1000,
                kia, 2000, 120, 4, 50, 100, "diesel",
                5, 1, 800, false);

        carbonFuelAboveBoundary = new CarbonFuelCar("Car345678", "Carbon Car 12345", 1001,
                tesla, 2001, 121, 5, 51, 101, "petrol",
                6, 2, 801, false);

        carbonFuelInvalidData = new CarbonFuelCar(null, null, -1,
                null, -1, -1, -1, -1, -1, null,
                -1, -1, -1, false);

        //not included - scooterOnBoundary, scooterInvalidData, electricCarAboveBoundary,
        //               carbonFuelBelowBoundary, carbonFuelInvalidData.
        populatedVehicles.addVehicle(scooterBelowBoundary);     //SCOOT12
        populatedVehicles.addVehicle(electricCarOnBoundary);    //
        populatedVehicles.addVehicle(carbonFuelAboveBoundary);  //Car34567
        populatedVehicles.addVehicle(electricCarBelowBoundary); //MAZ123
        populatedVehicles.addVehicle(scooterAboveBoundary);     //SC 12345
       // populatedVehicles.addVehicle(electricCarInvalidData);   //not added as the reg is null
        // TODO Fix error with null object being added to arraylist

        populatedVehicles.addVehicle(carbonFuelOnBoundary);     //Car54321
    }

    @AfterEach
    void tearDown() {
        scooterBelowBoundary = scooterOnBoundary = scooterAboveBoundary = scooterInvalidData = null;
        carbonFuelBelowBoundary = carbonFuelOnBoundary = carbonFuelAboveBoundary = carbonFuelInvalidData = null;
        electricCarBelowBoundary = electricCarOnBoundary = electricCarAboveBoundary = electricCarInvalidData = null;
        mazda = audi = tesla = ford = hyundai = null;
        populatedVehicles = emptyVehicles = null;
    }

    @Nested
    class Getters {

        @Test
        void getVehicleByIndexReturnsVehicle(){

            //populatedVehicles.addVehicle(scooterBelowBoundary);     //SCOOT12
            // populatedVehicles.addVehicle(electricCarOnBoundary);    //Elec5678
           //  populatedVehicles.addVehicle(carbonFuelAboveBoundary);  //Car34567

            assertEquals(scooterBelowBoundary,populatedVehicles.getVehicleByIndex(0));
            assertEquals(electricCarOnBoundary,populatedVehicles.getVehicleByIndex(1));
            assertEquals(carbonFuelAboveBoundary,populatedVehicles.getVehicleByIndex(2));
        }

        @Test
        void getVehicleByIndexReturnsNullWhenNoneExistOrInvalid(){
            assertNull(populatedVehicles.getVehicleByIndex(10));
            assertNull(populatedVehicles.getVehicleByIndex(-1));
            assertNull(populatedVehicles.getVehicleByIndex(11));

            // Checking empty arraylist
            assertNull(emptyVehicles.getVehicleByIndex(0));
        }

        @Test
        void getVehicleByRegReturnsVehicleWhenExists(){

            //populatedVehicles.addVehicle(scooterBelowBoundary);     //SCOOT12
            // populatedVehicles.addVehicle(electricCarOnBoundary);    //Elec5678
            //  populatedVehicles.addVehicle(carbonFuelAboveBoundary);  //Car34567

            assertEquals(scooterBelowBoundary,populatedVehicles.getVehicleByRegNumber("SCOOT12"));
            assertEquals(electricCarOnBoundary,populatedVehicles.getVehicleByRegNumber("Elec5678"));
            assertEquals(carbonFuelAboveBoundary,populatedVehicles.getVehicleByRegNumber("Car34567"));
        }

        @Test
        void getVehicleByRegReturnsNullWhenNoneExistOrInvalid(){
            assertNull(populatedVehicles.getVehicleByRegNumber("InvalidReg"));
            assertNull(populatedVehicles.getVehicleByRegNumber("InvalidReg"));
            assertNull(populatedVehicles.getVehicleByRegNumber("InvalidReg"));

            // Checking empty arraylist
            assertNull(emptyVehicles.getVehicleByRegNumber("InvalidReg"));
        }

    }

    @Nested
    class CRUDMethods {

        @Test
        void addVehicleToList() {
            assertEquals(6, populatedVehicles.numberOfVehicles());

            // Testing Scooter
            assertTrue(populatedVehicles.addVehicle(scooterOnBoundary));
            assertEquals(scooterOnBoundary,populatedVehicles.getVehicleByIndex(populatedVehicles.numberOfVehicles()-1));

            // Testing Electric Car
            assertTrue(populatedVehicles.addVehicle(electricCarAboveBoundary));
            assertEquals(electricCarAboveBoundary, populatedVehicles.getVehicleByIndex(populatedVehicles.numberOfVehicles()-1));

            // Testing Carbon Car
            assertTrue(populatedVehicles.addVehicle(carbonFuelBelowBoundary));
            assertEquals(carbonFuelBelowBoundary,populatedVehicles.getVehicleByIndex(populatedVehicles.numberOfVehicles()-1));

            // Testing empty vehicles array list
            assertEquals(0, emptyVehicles.numberOfVehicles());
            assertTrue(emptyVehicles.addVehicle(electricCarValidForEmptyArrayList));
            assertEquals(electricCarValidForEmptyArrayList, emptyVehicles.getVehicleByIndex(emptyVehicles.numberOfVehicles()-1));

        }

        @Test
        void deletingVehicleFromListWhenNoneExist(){
            assertEquals(0, emptyVehicles.numberOfVehicles());
            assertEquals(6, populatedVehicles.numberOfVehicles());

            assertNull(emptyVehicles.deleteVehicleByIndex(0));
            assertNull(populatedVehicles.deleteVehicleByIndex(-1));
            assertNull(populatedVehicles.deleteVehicleByIndex(populatedVehicles.numberOfVehicles()));
        }

        @Test
        void deletingVehicleFromListWhenObjectsExist(){
            assertEquals(0, emptyVehicles.numberOfVehicles());
            assertEquals(6, populatedVehicles.numberOfVehicles());

            assertEquals(6,populatedVehicles.numberOfVehicles());
            assertEquals(electricCarOnBoundary,populatedVehicles.deleteVehicleByIndex(1));
            assertEquals(5,populatedVehicles.numberOfVehicles());
        }

        @Test
        void updateElectricCarReturnsTrueAndUpdates(){
            ElectricCar foundElectricCar = (ElectricCar) populatedVehicles.getVehicleByIndex(1);
            assertEquals(foundElectricCar,populatedVehicles.getVehicleByIndex(1));

            assertTrue(populatedVehicles.updateElectricCar(1,"Elec567891010",
                    "Electric 1234567", 1001, kia, 2001, 121, 5,
                    51, 101, 41, 101));
            ElectricCar updatedElectricCar = (ElectricCar) populatedVehicles.getVehicleByIndex(1);
            assertEquals("Elec5678",updatedElectricCar.getRegNumber());
            assertEquals("Electric 123456",updatedElectricCar.getModel());
            assertEquals(1001,updatedElectricCar.getCost());
            assertEquals(kia,updatedElectricCar.getManufacturer());
            assertEquals(2001,updatedElectricCar.getYear());
            assertEquals(121,updatedElectricCar.getPower());
            assertEquals(5,updatedElectricCar.getSecs0To60());
            assertEquals(51,updatedElectricCar.getTopSpeed());
            assertEquals(101,updatedElectricCar.getTorque());
            assertEquals(41,updatedElectricCar.getEngineKWatts());
            assertEquals(101,updatedElectricCar.getRange());
        }

        @Test
        void deletesVehicleByRegNumberWhenVehicleExists(){
            assertEquals(6,populatedVehicles.numberOfVehicles());
            assertEquals(electricCarOnBoundary,populatedVehicles.deleteVehicleByRegNumber("Elec5678"));
            assertEquals(5, populatedVehicles.numberOfVehicles());
        }

        @Test
        void deletesVehicleByRegNumberWhenVehicleDoesntExist(){
            assertEquals(0,emptyVehicles.numberOfVehicles());
            assertEquals(6,populatedVehicles.numberOfVehicles());

            assertNull(emptyVehicles.deleteVehicleByRegNumber("Elec5678"));
            assertNull(populatedVehicles.deleteVehicleByRegNumber("InvalidReg"));
        }

        @Test
        void updateScooterReturnsTrueAndUpdates(){
            Scooter foundScooter = (Scooter) populatedVehicles.getVehicleByIndex(0);
            assertEquals(scooterBelowBoundary,foundScooter);

            // Testing if updated details go through to separate object
            assertTrue(populatedVehicles.updateScooter(0,"123456789", "Scootn1Master123", 999,
                    ford, 1999, 119, 4, 99));
            Scooter updatedScooter = (Scooter) populatedVehicles.getVehicleByIndex(0);
            assertEquals("SCOOT12", updatedScooter.getRegNumber());
            assertEquals("Scootn1 Master", updatedScooter.getModel());
            assertEquals(1000, updatedScooter.getCost());
            assertEquals(ford, updatedScooter.getManufacturer());
            assertEquals(2000, updatedScooter.getYear());
            assertEquals(250, updatedScooter.getPower());
            assertEquals(5, updatedScooter.getWeight());
            assertEquals(100, updatedScooter.getTopRiderWeight());

        }

        @Test
        void updateCarbonCarReturnsTrueAndUpdates(){
           CarbonFuelCar foundCarbonCar = (CarbonFuelCar) populatedVehicles.getVehicleByIndex(5);
            assertEquals(carbonFuelOnBoundary,foundCarbonCar);

            // Testing if updated details go through to separate object
            assertTrue(populatedVehicles.updateCarbonCar(2,"Car123487", "CarbonCar 123456", 999,
                    ford, 1999, 119, 3, 49, 99, "diesel",
                    4, 0, 799, false));
           CarbonFuelCar updatedCarbonCar = (CarbonFuelCar) populatedVehicles.getVehicleByIndex(5);
            assertEquals("Car54321", updatedCarbonCar.getRegNumber());
            assertEquals("CarbonCar 12345", updatedCarbonCar.getModel());
            assertEquals(1000, updatedCarbonCar.getCost());
            assertEquals(kia, updatedCarbonCar.getManufacturer());
            assertEquals(2000, updatedCarbonCar.getYear());
            assertEquals(120, updatedCarbonCar.getPower());
            assertEquals(4, updatedCarbonCar.getSecs0To60());
            assertEquals(50, updatedCarbonCar.getTopSpeed());
            assertEquals(100, updatedCarbonCar.getTorque(),0.01F);
            assertEquals("diesel", updatedCarbonCar.getFuelType());
            assertEquals(5, updatedCarbonCar.getFuelConsumption(), 0.01F);
            assertEquals(1,updatedCarbonCar.getCarbonEmission());
            assertEquals(800, updatedCarbonCar.getEngineSize());

        }



        @Test
        void updateVehicleThatDoesNotExistReturnsFalse(){
        assertFalse(populatedVehicles.updateElectricCar(10,"notest","notest",100,ford,2000,901,200,20,30,30,40));
        assertFalse(populatedVehicles.updateScooter(-5,"notest","9082",893,ford,200,200,200,200));
        assertFalse(populatedVehicles.updateCarbonCar(-8,"notest","notest",200,kia,200,200,200,200,200,"diesel",200,1,50,false));

        //Testing empty arraylist
            assertFalse(emptyVehicles.updateScooter(0,"notestest","testtesting",200,kia,
                    2000,120,50,20));
        }
    }

    @Nested
    class ListingMethods {

        @Test
        void listAllReturnsNoVehiclesStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyVehicles.numberOfVehicles());
            assertTrue(emptyVehicles.listAllVehicles().toLowerCase().contains("no vehicles"));
            System.out.println(populatedVehicles.listAllVehicles());
        }

        @Test
        void listAllReturnsVehiclesStoredWhenArrayListHasVehiclesStored() {
            assertEquals(6, populatedVehicles.numberOfVehicles());
            String vehicles = populatedVehicles.listAllVehicles();
            //checks for objects in the string
            assertTrue(vehicles.contains("SC 12345"));
            assertTrue(vehicles.contains("SCOOT12"));
            assertTrue(vehicles.contains("Elec987"));
            assertTrue(vehicles.contains("Elec5678"));
            assertTrue(vehicles.contains("Car54321"));
            assertTrue(vehicles.contains("Car34567"));
        }

        @Test
        void listBySelectedYearReturnsNoVehiclesWhenNoneExistForEnteredYear() {
            assertEquals(6, populatedVehicles.numberOfVehicles());
            String vehicles = populatedVehicles.listAllVehiclesEqualToAGivenYear(2003);
            assertTrue(vehicles.contains("No vehicles"));
        }

        @Test
        void listBySelectedYearReturnsVehiclesWhenVehiclesExistForEnteredYear() {
            System.out.println(populatedVehicles.listAllVehicles());
            assertEquals(6, populatedVehicles.numberOfVehicles());

            String vehicles = populatedVehicles.listAllVehiclesEqualToAGivenYear(2001);

            //checks for the objects in the string
            assertTrue(vehicles.contains("Car34567"));
            assertTrue(vehicles.contains("SC 12345"));
        }
    }

    @Nested
    class ReportingMethods {



    }

    @Nested
    class SearchingMethods {

    }

    @Nested
    class SortingMethods {

        @Test
        void sortByCostDescendingReOrdersList() {
            assertEquals(6, populatedVehicles.numberOfVehicles());
            //checks the order of the objects in the list
            assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(0));
            assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(1));
            assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(2));
            assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(3));
            assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(4));
            assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));

            populatedVehicles.sortByCostDescending();
            assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(0));
            assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(1));
            assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(2));
            assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(3));
            assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(4));
            assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));
        }

        @Test
        void sortByCostDescendingDoesntCrashWhenListIsEmpty() {
            assertEquals(0, emptyVehicles.numberOfVehicles());
            emptyVehicles.sortByCostDescending();
        }
    }

}



