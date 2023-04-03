package controllers;

import models.*;
import utils.FuelTypeUtility;
import utils.Serializer;
import utils.Utilities;

import java.io.*;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class VehicleAPI {

    private List<Vehicle> vehicles;
    private File file;

    public VehicleAPI(File file) {
        this.file = file;
    }

    // todo implements Serializer {   (when load and saved written, include the 'implements Serializer here)

    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)

    public boolean updateElectricCar(String dummy1, ElectricCar dummy){
        return false;
    }


    // -------------------------- //
    // CRUD ON Vehicles ArrayList //
    // -------------------------- //
    public boolean addVehicle(Vehicle dummy){
        return false;
    }

    public Vehicle deleteVehicleByIndex(int dummy){
        return null;
    }

    public Vehicle deleteVehicleByRegNumber(String dummy){
        return null;
    }

    public Vehicle getVehicleByRegNumber(String dummy){
        return null;
    }

    public Vehicle getVehicleByIndex(int dummy){
        return null;
    }

    // -------------------------- //
    //     Reporting Methods     //
    // -------------------------- //

    public String listAllCarbonFuelsByFuelType(String dummy){
        return "";
    }

    public String listAllVehiclesAfterAGivenYear(int year){
        return "";
    }

    public String listAllVehicles(){
    return "No vehicles";
    }

    public String listAllScooters(){
        return "";
    }

    public String listAllElectricCars(){
        return "";
    }

    public String listAllCarbonFuelCars(){
        return "";
    }

    public String listAllVehiclesByChosenManufacturer(Manufacturer dummy){
        return "";
    }

    public String listAllVehiclesEqualToAGivenYear(int year){
        return "";
    }

    public String numberOfVehicles(){
        return "";
    }

    public String numberOfScooters(){
        return "";
    }

    public String numberOfElectricCars(){
        return "";
    }

    public String numberOfCarbonCars(){
        return "";
    }

    public String numberOfVehiclesByChosenManufacturer(Manufacturer manufacturerObj){
        return "";
    }

    // -------------------------- //
    //     Validation Methods     //
    // -------------------------- //


    public boolean isValidNewRegNumber(String regNumber){
        for(Vehicle vehicle: vehicles)
           if (vehicle.getRegNumber().equals(regNumber))
               return false;
        return true;
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
//    @SuppressWarnings("unchecked")
//    public void load() throws Exception {
//        //list of classes that you wish to include in the serialisation, separated by a comma
//        Class<?>[] classes = new Class[]{Vehicle.class, Car.class, CarbonFuelCar.class,
//                                            ElectricCar.class, Scooter.class, Manufacturer.class};
//
//        //setting up the xstream object with default security and the above classes
//        XStream xstream = new XStream(new DomDriver());
//        XStream.setupDefaultSecurity(xstream);
//        xstream.allowTypes(classes);
//
//        //doing the actual serialisation to an XML file
//        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
//        vehicles = (List<Vehicle>) in.readObject();
//        in.close();
//    }

    public void load() throws Exception {

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
