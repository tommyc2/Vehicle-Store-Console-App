package main;

import controllers.ManufacturerAPI;
import controllers.VehicleAPI;
import models.*;
import utils.ScannerInput;
import utils.Utilities;

// Importing utilities method to reduce code amount and long-winded approach
import static utils.Utilities.isValidIndex;

import java.io.File;
import java.util.Scanner;

public class Driver {
        private VehicleAPI vehicleAPI;
        private ManufacturerAPI manufacturerAPI;

        public static void main(String[] args) throws Exception {
            new main.Driver().start();
        }

        public void start() {

            vehicleAPI = new VehicleAPI(new File("vehicles.xml"));
            manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));

            loadAllData();
            runMainMenu();
        }

    private int mainMenu() {
        System.out.println("""
                         -------Vehicle Store-------------
                        |  1) Manufacturer CRUD MENU     |
                        |  2) Vehicle Store CRUD MENU    |
                        |  3) Reports MENU               |
                        |--------------------------------|
                        |  4) Search Manufacturers       |
                        |  5) Search Vehicles            |  
                        |  6) Sort Vehicles              | 
                        |--------------------------------|
                        |  10) Save all                  |
                        |  11) Load all                  |
                        |--------------------------------|
                        |  0) Exit                       |
                         --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }

        private void runMainMenu() {
            int option = mainMenu();
            while (option != 0) {
                switch (option) {
                    case 1-> runManufacturerMenu();
                    case 2 -> runVehicleCRUDMenu();
                   // case 3 -> TODO run the Reports Menu and the associated methods (your design here)
                    //case 4 -> TODO run the search Manufacturers menu and associated methods (your design here)
                   // case 5 ->TODO run the search Vehicles menu and associated methods (your design here)
                    // case 6 ->TODO sorting menu and associated (your design here)
                    case 10 -> saveAllData();
                    case 11 -> loadAllData();
                    default ->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = mainMenu();
            }
            exitApp();
        }

        private void exitApp(){
            saveAllData();
            System.out.println("Exiting....");
            System.exit(0);
        }

        //---------------------------\\
        //---- Vehicle CRUD Menu -----\\
        //----------------------------\\

    private int vehicleCRUDMenu() {
        System.out.println("""
               --------- Vehicle CRUD Menu --------
               |  1) Add a vehicle                |
               |  2) Delete a vehicle             |
               |  3) Update vehicle details       |
               |  4) List all vehicles            |
               |  0) Return to main menu          |
                ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }


    private void runVehicleCRUDMenu() {
        int option = vehicleCRUDMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addVehicle();
                case 2 -> deleteVehicle();
        //todo  case 3 -> updateVehicleMenu();
                case 4 -> listALlVehicles();
                default ->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = vehicleCRUDMenu();
        }
        runMainMenu();
    }

    private void listALlVehicles(){
            System.out.println(vehicleAPI.listAllVehicles());
    }

    private void addVehicle(){
        int vehicleType =  ScannerInput.readNextInt("""
            Which type of vehicle do you wish to add? 
            1) Carbon Fuel Car
            2) Electric Car
            3) Scooter """);

        String manuName = ScannerInput.readNextLine("Enter manufacturer name:  ");
        int numOfEmployees = ScannerInput.readNextInt("Enter the no. of employees for the manufacturer:  ");

        Manufacturer manufacturer = new Manufacturer(manuName,numOfEmployees);
        if (manufacturer != null){
            String regNumber = ScannerInput.readNextLine("Please enter Reg number of new Vehicle: ");

            if (vehicleAPI.isValidNewRegNumber(regNumber)){
                String  model = ScannerInput.readNextLine("\tmodel : ");
                float cost = ScannerInput.readNextFloat("\tcost : ");
                int  year = ScannerInput.readNextInt("\tYear of registration");
                switch (vehicleType) {
                    case 1, 2 -> {
                        int power = ScannerInput.readNextInt("\tpower : ");
                        int secs0To60 = ScannerInput.readNextInt("\ttime from 0 to 60 :  ");
                        int topSpeed = ScannerInput.readNextInt("\ttop speed : ");
                        float torque = ScannerInput.readNextFloat("\tpower: ");
                        switch (vehicleType) {
                            case 1-> {
                                // Carbon car
                                float fuelConsumption = ScannerInput.readNextFloat("\tEnter fuel consumption:  ");
                                float carbonEmission = ScannerInput.readNextFloat("\tEnter carbon emission:  ");
                                boolean automatic = false;
                                String fuelType = ScannerInput.readNextLine("\tEnter valid fuel type (e.g. petrol, diesel):  ");
                                int engineSize = ScannerInput.readNextInt("\tEnter engine size");

                                //   boolean carbonCarAdded = vehicleAPI.addVehicle(new CarbonFuelCar(regNumber,model,cost,manufacturer,
                                //     ));
                            }
                            case 2 -> {
                                // Electric Car
                                int range = ScannerInput.readNextInt("\trange: ");
                                float engineKWatts = ScannerInput.readNextFloat("\tengine power (kilowatts): ");

                                //   boolean electricCarAdded = vehicleAPI.addVehicle(new CarbonFuelCar(regNumber,model,cost,manufacturer,
                                //     ));
                            }
                        }
                    }
                    case 3 -> {
                        //Scooter
                        int power = ScannerInput.readNextInt("\tpower : ");
                        float weight = ScannerInput.readNextFloat("\tweight : ");
                        int topRiderWeight = ScannerInput.readNextInt("\ttop rider weight");
                        boolean scooterAdded = vehicleAPI.addVehicle(new Scooter(regNumber, model, cost, manufacturer, year, power, weight,topRiderWeight));

                        if (scooterAdded){
                            System.out.println("Scooter added successfully");
                        }
                        else{
                            System.out.println("Scooter not added, please try again");
                        }
                    }
                }
            }
            else{
                System.out.println("Vehicle reg number  already exists.");
            }
        }

        else{
            System.out.println("Manufacturer name is NOT valid");
        }
    }

    private void deleteVehicle(){
            listALlVehicles();

            int indexToDelete = ScannerInput.readNextInt("Enter the index of the vehicle to delete:  ");

            Vehicle postToDelete = vehicleAPI.deleteVehicleByIndex(indexToDelete);

            if (postToDelete != null){
                System.out.println("Successfully deleted this post:" + "\n" +
                        postToDelete.toString());
            }
            else{
                System.out.println("Unsuccessful deletion, please try a different index");
            }
    }

    private int updateMenu() {
        System.out.println("""
               -------- Vehicle Update Menu -------
               |  1) Update Scooter               |
               |  2) Update Carbon Fuel Car       |
               |  3) Update Electric Car          |
               |  0) Return to vehicle CRUD Menu  |
                ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    private void runUpdateMenu() {
            if (vehicleAPI.numberOfVehicles() > 0) {
                int option = updateMenu();
                while (option != 0) {
                    switch (option) {

                  // Update Scooter //
                    case 1 -> {
                        if (vehicleAPI.numberOfScooters() > 0){
                            int scooterIndex = ScannerInput.readNextInt("Enter scooter vehicle index:  ");
                            if (isValidIndex(vehicleAPI.getVehicleArrayList(),scooterIndex) && vehicleAPI.getVehicleByIndex(scooterIndex) instanceof Scooter){

                                //TODO validate regNumber, manufacturer != null parts in Driver

                                String regNumber = ScannerInput.readNextLine("Enter a new valid reg number:  ");

                                    // Vehicle Fields
                                    String model = ScannerInput.readNextLine("Enter model:  ");
                                    float cost = ScannerInput.readNextFloat("Enter the cost:  ");
                                    String manufacturerName = ScannerInput.readNextLine("Enter manufacturer name:  ");
                                    int numOfEmployees = ScannerInput.readNextInt("Enter new number of employees:  ");
                                    int year = ScannerInput.readNextInt("Enter year:  ");

                                    // Scooter fields
                                    int power = ScannerInput.readNextInt("Enter power:  ");
                                    float weight = ScannerInput.readNextFloat("Enter weight:  ");
                                    int topRiderWeight = ScannerInput.readNextInt("Enter top rider weight:  ");

                                    Manufacturer newManufacturerDetails = new Manufacturer(manufacturerName,numOfEmployees);

                                    boolean isUpdated = vehicleAPI.updateScooter(scooterIndex,regNumber,model,
                                            cost,newManufacturerDetails,year,power,weight,topRiderWeight);

                                    if (isUpdated){
                                        System.out.println("Updated vehicle successfully");
                                    }
                                    else{
                                        System.out.println("Update not successful. Please try again");
                                    }
                                }
                                else{
                                    System.out.println("Not a valid index");
                            }


                            }
                        else{
                            System.out.println("Not scooters in store");
                        }
                    }

                        // Update Carbon Fuel Car

                        case 2 -> {
                            if (vehicleAPI.numberOfCarbonCars() > 0){
                                int carbonCarIndex = ScannerInput.readNextInt("Enter carbon car vehicle index:  ");
                                if (isValidIndex(vehicleAPI.getVehicleArrayList(),carbonCarIndex)
                                        && vehicleAPI.getVehicleByIndex(carbonCarIndex) instanceof CarbonFuelCar){

                                    //TODO validate regNumber, manufacturer != null parts in Driver

                                    // Vehicle Fields
                                    String regNumber = ScannerInput.readNextLine("Enter a new valid reg number:  ");

                                    String model = ScannerInput.readNextLine("Enter model:  ");
                                    float cost = ScannerInput.readNextFloat("Enter the cost:  ");
                                    String manufacturerName = ScannerInput.readNextLine("Enter manufacturer name:  ");
                                    int numOfEmployees = ScannerInput.readNextInt("Enter new number of employees:  ");
                                    int year = ScannerInput.readNextInt("Enter year:  ");

                                    Manufacturer newManufacturerDetails = new Manufacturer(manufacturerName,numOfEmployees);

                                    // Car Details
                                    int secs0To60 = ScannerInput.readNextInt("Enter new time in seconds to 60:  ");
                                    int power = ScannerInput.readNextInt("Enter new power:  ");
                                    float torque = ScannerInput.readNextFloat("Enter new torque: ");
                                    int topSpeed = ScannerInput.readNextInt("Enter new top speed:  ");

                                    // Carbon Car Details
                                    float fuelConsumption = ScannerInput.readNextInt("Enter fuel consumption:  ");
                                    float carbonEmission = ScannerInput.readNextInt("Enter carbon emission:  ");
                                    boolean automatic = false;
                                    String fuelType = ScannerInput.readNextLine("Enter valid fuel type (e.g. petrol, diesel):  ");
                                    int engineSize = ScannerInput.readNextInt("Enter engine size");



                                    boolean isUpdated = vehicleAPI.updateCarbonCar(carbonCarIndex,regNumber,model,cost,newManufacturerDetails,year
                                            ,power,secs0To60,topSpeed,torque,fuelType,fuelConsumption,carbonEmission,engineSize,automatic);

                                    if (isUpdated){
                                        System.out.println("Updated vehicle successfully");
                                    }
                                    else{
                                        System.out.println("Update not successful. Please try again");
                                    }
                                }
                                else{
                                    System.out.println("Not a valid index");
                                }


                            }
                            else{
                                System.out.println("Not carbon cars in store");
                            }
                        }

                        // Update Electric Car
                        case 3 -> {
                            if (vehicleAPI.numberOfElectricCars() > 0){
                                int electricCarIndex = ScannerInput.readNextInt("Enter electric vehicle index:  ");
                                if (isValidIndex(vehicleAPI.getVehicleArrayList(),electricCarIndex)
                                        && vehicleAPI.getVehicleByIndex(electricCarIndex) instanceof ElectricCar){

                                    //TODO validate regNumber, manufacturer != null parts in Driver

                                    // Vehicle Fields
                                    String regNumber = ScannerInput.readNextLine("Enter a new valid reg number:  ");

                                    String model = ScannerInput.readNextLine("Enter model:  ");
                                    float cost = ScannerInput.readNextFloat("Enter the cost:  ");
                                    String manufacturerName = ScannerInput.readNextLine("Enter manufacturer name:  ");
                                    int numOfEmployees = ScannerInput.readNextInt("Enter new number of employees:  ");
                                    int year = ScannerInput.readNextInt("Enter year:  ");

                                    Manufacturer newManufacturerDetails = new Manufacturer(manufacturerName,numOfEmployees);

                                    // Car Details
                                    int secs0To60 = ScannerInput.readNextInt("Enter new time in seconds to 60:  ");
                                    int power = ScannerInput.readNextInt("Enter new power:  ");
                                    float torque = ScannerInput.readNextFloat("Enter new torque: ");
                                    int topSpeed = ScannerInput.readNextInt("Enter new top speed:  ");

                                    // Electric Car Details
                                    int range = ScannerInput.readNextInt("Enter new range value:  ");
                                    float engineKWatts = ScannerInput.readNextFloat("Enter new engine kilowatt value:  ");

                                    boolean isUpdated = vehicleAPI.updateElectricCar(electricCarIndex,regNumber,model,cost,newManufacturerDetails,year
                                            ,power,secs0To60,topSpeed,torque,engineKWatts,range);

                                    if (isUpdated){
                                        System.out.println("Updated vehicle successfully");
                                    }
                                    else{
                                        System.out.println("Update not successful. Please try again");
                                    }
                                }
                                else{
                                    System.out.println("Not a valid index");
                                }


                            }
                            else{
                                System.out.println("Not electric cars in store");
                            }
                        }
                    default -> System.out.println("Invalid option entered" + option);
                    }
                    ScannerInput.readNextLine("\n Press the enter key to continue");
                    option = updateMenu();
                }
                runVehicleCRUDMenu();
            }
            else{
                System.out.println("Option Invalid - No vehicles stored");
            }
    }

        //----------------------
        //  Manufacturer Menu Items
        //----------------------
        private int manufacturerMenu() {
            System.out.println("""
               -------- Manufacturer CRUD Menu ----
               |  1) Add a manufacturer           |
               |  2) Delete a manufacturer        |
               |  3) Update manufacturer details  |
               |  4) List all manufacturers       |
               |  5) Find a manufacturer          |
               |  0) Return to main menu          |
                ----------------------------------""");
            return ScannerInput.readNextInt("==>>");
        }

        private void runManufacturerMenu() {
            int option = manufacturerMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addManufacturer();
                    case 2 -> deleteManufacturer();
                    case 3 -> updateManufacturer();
                    case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                    case 5-> findManufacturer();
                    default->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = manufacturerMenu();
            }

        }

        private void addManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
            int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

            if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))){
                System.out.println("Add successful");
            }
            else{
                System.out.println("Add not successful");
            }
        }

        private void deleteManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the developer name: ");
            if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null){
                System.out.println("Delete successful");
            }
            else{
                System.out.println("Delete not successful");
            }
        }

        private void updateManufacturer(){
            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null){
                int numEmployees= ScannerInput.readNextInt("Please enter number of Employees: ");
                if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                    System.out.println("Number of Employees Updated");
                else
                    System.out.println("Number of Employees NOT Updated");
            }
            else
                System.out.println("Manufacturer name is NOT valid");
        }

        private void findManufacturer(){
            Manufacturer manu = getManufacturerByName();
            if (manu == null){
                System.out.println("No such manufacturer exists");
            }
            else{
                System.out.println(manu);
            }
        }

        //---------------------
        //  Vehicle Menu
        //---------------------

    private int vehicleReportsMenu() {
        System.out.println(""" 
                ---------- Vehicle Reports Menu  ---------------------
               | 1) List all vehicles                                 | 
               | 2) List all Electric Cars                            |
               | 3) List all Carbon Fuel Cars                         |
               | 4) List all Scooters                                 |
               | 5) List all Vehicles registered in a given year      |
               | 6) List all Vehicles registered after a given year   |
               | 7) List all carbon fuel by fuel type                 |
               | 8) List the top five carbon vehicles                 |
               | 0) Return to main menu                               | 
                 ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    private int manufacturerReportsMenu() {
        System.out.println(""" 
                ---------- Manufacturers Reports Menu  -------------
               | 1) List Manufacturers                              | 
               | 2) List all vehicles from a given manufacturer     |
               | 3) List Manufacturers by a given name              |
               | 0) Return to main menu                             | 
                 ---------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    private void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(manufacturerAPI.listManufacturers());
                case 2-> listAllVehiclesFromaGivenManufacturer();
                case 3-> listAllManufacturersByInputtedName();
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manufacturerReportsMenu();
        }
    }


    private void listAllVehiclesFromaGivenManufacturer() {
        String manu  = ScannerInput.readNextLine("What manufacturer you want a list of cars for?  : ");
        Manufacturer m = manufacturerAPI.getManufacturerByName(manu);
        if (!(m == null))
        System.out.println(vehicleAPI.listAllVehiclesByChosenManufacturer(m));
        else
            System.out.println("No manufacturer with tha name exists");
    }

    private void listAllManufacturersByInputtedName(){
            String manufacturerName = ScannerInput.readNextLine("Type in a manufacturer name for similar/exact names: ");
            System.out.println(manufacturerAPI.listAllManufacturersByManufacturerName(manufacturerName));
    }

    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        // TODO try-catch to save the developers to XML file
        // TODO try-catch to save the apps in the store to XML file
    }

    private void loadAllData() {
        // TODO try-catch to load the developers from XML file
        // TODO try-catch to load the apps in the store from XML file
    }

    private String getValidRegNumber(){
            String vehicleRegNumber = ScannerInput.readNextLine("\tVehicle Reg Number (must be unique): ");
            if (vehicleAPI.isValidNewRegNumber(vehicleRegNumber)) {
                return vehicleRegNumber;
            } else {
               System.err.println("\tReg name already exists / is not valid.");
                return "";
            }
        }

        private Manufacturer getManufacturerByName(){
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
            if (manufacturerAPI.isValidManufacturer(manufacturerName)){
                return manufacturerAPI.getManufacturerByName(manufacturerName);
            }
            else{
                return null;
            }
        }


    }

