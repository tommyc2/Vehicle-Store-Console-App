package controllers;

import models.*;
import utils.FuelTypeUtility;
import utils.Serializer;
import utils.Utilities;

import java.io.*;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import static utils.Utilities.isValidIndex;


public class VehicleAPI {

    private List<Vehicle> vehicles;
    private File file;
    public VehicleAPI(File file) {
        this.file = file;
        vehicles = new ArrayList<>();
    }

    // todo implements Serializer {   (when load and saved written, include the 'implements Serializer here)


    // -------------------------- //
    // CRUD ON Vehicles ArrayList //
    // -------------------------- //
    public boolean addVehicle(Vehicle vehicle){
        return vehicles.add(vehicle);
    }

    public Vehicle deleteVehicleByIndex(int indexToDelete){
        if (isValidIndex(vehicles,indexToDelete)){
             return vehicles.remove(indexToDelete);
        }
        else{
            return null;
        }
    }

    public Vehicle deleteVehicleByRegNumber(String regNumber){
        if (isValidNewRegNumber(regNumber)){
            for(Vehicle vehicle : vehicles){
                if (vehicle.getRegNumber().equals(regNumber)){
                    return vehicles.remove(vehicles.indexOf(vehicle));
                }
            }
        }
        return null;
    }

    public Vehicle getVehicleByRegNumber(String regNumber){
        if(isValidNewRegNumber(regNumber)){
            for(Vehicle vehicle : vehicles){
                if (vehicle.getRegNumber().equals(regNumber)){
                    return vehicles.get(vehicles.indexOf(vehicle));
                }
            }
        }
        return null;
    }

    public Vehicle getVehicleByIndex(int index){
        if (isValidIndex(vehicles,index)){
            return vehicles.get(index);
        }
        return null;
    }

    // Update Methods for Electric, Carbon, Scooter //
    // -------------------------------------------- //
    public boolean updateElectricCar(int indexToUpdate, String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power,int secs0To60,int topSpeed, float torque, float engineKWatts, int range ){
        Vehicle updatedElectricCar = getVehicleByIndex(indexToUpdate);

        if ((updatedElectricCar != null) && (updatedElectricCar instanceof ElectricCar)){
            updatedElectricCar.setRegNumber(regNumber);
            updatedElectricCar.setModel(model);
            updatedElectricCar.setCost(cost);
            updatedElectricCar.setManufacturer(manufacturer);
            updatedElectricCar.setYear(year);
            ((ElectricCar) updatedElectricCar).setPower(power);
            ((ElectricCar) updatedElectricCar).setSecs0To60(secs0To60);
            ((ElectricCar) updatedElectricCar).setTopSpeed(topSpeed);
            ((ElectricCar) updatedElectricCar).setTorque(torque);
            ((ElectricCar) updatedElectricCar).setEngineKWatts(engineKWatts);
            ((ElectricCar) updatedElectricCar).setRange(range);
            return true;
        }
        return false;
    }

    public boolean updateCarbonCar(int indexToUpdate, String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, int secs0To60, int topSpeed, float torque,String fuelType, float fuelConsumption, float carbonEmission,int engineSize, boolean automatic){
       Vehicle updatedCarbonCar = getVehicleByIndex(indexToUpdate);

       if (updatedCarbonCar != null && updatedCarbonCar instanceof CarbonFuelCar){
           updatedCarbonCar.setRegNumber(regNumber);
           updatedCarbonCar.setModel(model);
           updatedCarbonCar.setCost(cost);
           updatedCarbonCar.setManufacturer(manufacturer);
           updatedCarbonCar.setYear(year);
           ((CarbonFuelCar) updatedCarbonCar).setPower(power);
           ((CarbonFuelCar) updatedCarbonCar).setTorque(torque);
           ((CarbonFuelCar) updatedCarbonCar).setTopSpeed(topSpeed);
           ((CarbonFuelCar) updatedCarbonCar).setSecs0To60(secs0To60);
           updatedCarbonCar.setManufacturer(manufacturer);
           ((CarbonFuelCar) updatedCarbonCar).setFuelType(fuelType);
           ((CarbonFuelCar) updatedCarbonCar).setCarbonEmission(carbonEmission);
           ((CarbonFuelCar) updatedCarbonCar).setFuelConsumption(fuelConsumption);
           ((CarbonFuelCar) updatedCarbonCar).setEngineSize(engineSize);
           ((CarbonFuelCar) updatedCarbonCar).setAutomatic(automatic);
           return true;
       }
       return false;
    }

    public boolean updateScooter(int indexToUpdate, String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, float weight, int topRiderWeight){
        Vehicle updatedScooter = getVehicleByIndex(indexToUpdate);

        if (updatedScooter != null && updatedScooter instanceof Scooter){
            updatedScooter.setRegNumber(regNumber);
            updatedScooter.setModel(model);
            updatedScooter.setCost(cost);
            updatedScooter.setManufacturer(manufacturer);
            updatedScooter.setYear(year);
            return true;
        }
        return false;
    }



    // -------------------------- //
    //     Reporting Methods     //
    // -------------------------- //

    public String listAllCarbonFuelsByFuelType(String fuelType){
        String carbonFuelsByType = "";
        if (FuelTypeUtility.validFuelType(fuelType)){
            for(Vehicle vehicle : vehicles){
                if (vehicle instanceof CarbonFuelCar){
                    if(((CarbonFuelCar) vehicle).getFuelType().equals(fuelType)){
                        carbonFuelsByType += vehicles.indexOf(vehicle) + ": " + vehicle.toString() + "\n";
                    }
                }
            }
        }
        else{
            if (!FuelTypeUtility.validFuelType(fuelType)) {
                return "Not a valid fuel type!";
            }
            if (vehicles.isEmpty()){
                return "No vehicles stored";
            }
        }
        return carbonFuelsByType;
    }

    public String listAllVehiclesAfterAGivenYear(int year){
        String listOfVehicles = "";
        if (isValidIndex(vehicles,year)){

            for(Vehicle vehicle : vehicles){
                if (year > vehicle.getYear()){
                    listOfVehicles += vehicles.indexOf(vehicle) + ": " + vehicle.toString() + "\n";
                }
            }

            if(listOfVehicles.equals("")){
            return "No vehicles exist later than this year: " + year;
            }
            return listOfVehicles;

        }
        else{
            return "Not a valid year";
        }
    }

    public String listAllVehicles(){
        String listOfVehicles = "";

        for(Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                listOfVehicles += vehicles.indexOf(vehicle) + ": " + vehicle.toString() + "\n";
            }
            }

        if (listOfVehicles.equals("")){
            return "No vehicles";
        }
        else{
            return listOfVehicles;
        }

    }

    public String listAllScooters(){
        String listOfVehicles = "";

        for(Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scooter){
                listOfVehicles += vehicles.indexOf(vehicle) + ": " + vehicle.toString() + "\n";
            }
        }

        if (listOfVehicles.equals("")){
            return "Sorry, no scooters in the list!";
        }
        else{
            return listOfVehicles;
        }

    }

    public String listAllElectricCars(){
        String listOfVehicles = "";

        for(Vehicle vehicle : vehicles) {
            if (vehicle instanceof ElectricCar){
                listOfVehicles += vehicles.indexOf(vehicle) + ": " + vehicle.toString() + "\n";
            }
        }

        if (listOfVehicles.equals("")){
            return "Sorry, no electric cars in the list!";
        }
        else{
            return listOfVehicles;
        }

    }

    public String listAllCarbonFuelCars(){
        String listOfVehicles = "";

        for(Vehicle vehicle : vehicles) {
            if (vehicle instanceof CarbonFuelCar){
                listOfVehicles += vehicles.indexOf(vehicle) + ": " + vehicle.toString() + "\n";
            }
        }

        if (listOfVehicles.equals("")){
            return "Sorry, no carbon fuel cars in the list!";
        }
        else{
            return listOfVehicles;
        }

    }

    public String listAllVehiclesByChosenManufacturer(Manufacturer manufacturer){
        String list = "";
        for(Vehicle vehicle : vehicles){
            if(vehicle.getManufacturer().getManufacturerName().equalsIgnoreCase(manufacturer.getManufacturerName())){
                list += vehicles.indexOf(vehicle) + ": " + vehicle.toString() + "\n";
            }
        }

        if (list.equals("")){
            return "No vehicles match this manufacturer: " + manufacturer.getManufacturerName();
        }
        return list;
    }

    public String listAllVehiclesEqualToAGivenYear(int year){
        String vehiclesEqualToGivenYear = "";

        for(Vehicle vehicle : vehicles){
            if (vehicle.getYear() == year){
                vehiclesEqualToGivenYear += vehicles.indexOf(vehicle) + vehicle.toString() + "\n";
            }
        }

        if (!vehiclesEqualToGivenYear.isEmpty()){
            return vehiclesEqualToGivenYear;
        }
        else{
            return "No vehicles";
        }

    }

    public int numberOfVehicles(){
        int numberOfVehicles = 0;
        for(Vehicle vehicle : vehicles){
            numberOfVehicles++;
        }
        return numberOfVehicles;
    }

    public int numberOfScooters(){
        int numOfScooters = 0;
        for(Vehicle vehicle : vehicles){
            if (vehicle instanceof Scooter){
                numOfScooters++;
            }
        }
        return numOfScooters;
    }

    public int numberOfElectricCars(){
        int numberOfElectricCars = 0;
        for(Vehicle vehicle : vehicles){
            if (vehicle instanceof ElectricCar){
                numberOfElectricCars++;
            }
        }
        return numberOfElectricCars;
    }

    public int numberOfCarbonCars(){
        int numOfCarbonCars = 0;
        for(Vehicle vehicle : vehicles){
            if (vehicle instanceof CarbonFuelCar){
                numOfCarbonCars++;
            }
        }
        return numOfCarbonCars;
    }

    public int numberOfVehiclesByChosenManufacturer(Manufacturer manufacturerObj){
        int numVehiclesByManu = 0;

        for(Vehicle vehicle : vehicles){
            if (vehicle.getManufacturer().getManufacturerName().equalsIgnoreCase(manufacturerObj.getManufacturerName())){
                numVehiclesByManu++;
            }

        }
        return numVehiclesByManu;
    }

    // -------------------------- //
    //     Validation Methods     //
    // -------------------------- //

    public boolean isValidNewRegNumber(String regNumber){
        for(Vehicle vehicle: vehicles) {
            if (vehicle.getRegNumber().equals(regNumber))
                return true;
        }
        return false;
    }

    // -------------------------- //
    //     Sorting Methods        //
    // -------------------------- //

    public void sortByCarbonFootprintDescending(){
    }

    public void sortByCostDescending(){
    }

    public void sortByAgeAscending(){
    }

    public void sortByCarbonFootprintAscending(){

    }

    public void swapVehicles(List<Vehicle> vehicles, int i, int j){
    }

    // -------------------------- //
    //     Other Methods          //
    // -------------------------- //
    public void topFiveCarbonVehicles(List<Vehicle> vehicles){

    }

    
    
    //---------------------
    // Persistence methods
    //---------------------

    /**
     * The load method uses the XStream component to read all the objects from the xml
     * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */

    @SuppressWarnings("unchecked")
    public void load() throws Exception {

        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Vehicle.class, Car.class, CarbonFuelCar.class,
                                            ElectricCar.class, Scooter.class, Manufacturer.class};

        //setting up the xstream object with default security and the above classes
       XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);


        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        vehicles = (List<Vehicle>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the ArrayList
     * to the xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(vehicles);
        out.close();
    }

    public String fileName(){
        return this.file.toString();
    }

}
