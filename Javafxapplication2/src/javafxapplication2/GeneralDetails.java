/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

/**
 *
 * @author AminujB
 */
public class GeneralDetails {
    public int snn;
    
    public String ownerName;
    
    public String identificationNumber;
    
    public String make;
    
    public String chassisNumber;
    
    public String engineNumber;
    
    public String licencePlate;
    
    public String fancyPlate;
    
    public String registrationDate;
    
    public String phoneNumber;
    
    public String address;
    
    public String driverLicence;
    
    public String bearerName;
    
    public String yom;
    
    public String colour;
    
    public String model;
    
    public String vehicleCategory;
    
    public String engineCapacity;
    
    public String fuelType;
    
    public String vehicleType;

    public GeneralDetails(int snn, String ownerName, String identificationNumber, String phoneNumber, String address, String driverLicence, String bearerName) {
        this.snn = snn;
        this.ownerName = ownerName;
        this.identificationNumber = identificationNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.driverLicence = driverLicence;
        this.bearerName = bearerName;
    }

    public GeneralDetails(int snn, String make, String identificationNumber, String chassisNumber, String engineNumber, String yom, String colour, String model, String vehicleCategory, String engineCapacity, String fuelType, String vehicleType) {
        this.snn = snn;
        this.identificationNumber = identificationNumber;
        this.make = make;
        this.chassisNumber = chassisNumber;
        this.engineNumber = engineNumber;
        this.yom = yom;
        this.colour = colour;
        this.model = model;
        this.vehicleCategory = vehicleCategory;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
    }

    public GeneralDetails(int snn, String ownerName, String identificationNumber, String make, String chassisNumber, String engineNumber, String registrationDate, String licencePlate, String fancyPlate ) {
        this.snn = snn;
        this.ownerName = ownerName;
        this.identificationNumber = identificationNumber;
        this.make = make;
        this.chassisNumber = chassisNumber;
        this.engineNumber = engineNumber;
        this.licencePlate = licencePlate;
        this.fancyPlate = fancyPlate;
        this.registrationDate = registrationDate;
    }

    public int getSnn() {
        return snn;
    }

    public void setSnn(int snn) {
        this.snn = snn;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getFancyPlate() {
        return fancyPlate;
    }

    public void setFancyPlate(String fancyPlate) {
        this.fancyPlate = fancyPlate;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverLicence() {
        return driverLicence;
    }

    public void setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
    }

    public String getBearerName() {
        return bearerName;
    }

    public void setBearerName(String bearerName) {
        this.bearerName = bearerName;
    }

    public String getYom() {
        return yom;
    }

    public void setYom(String yom) {
        this.yom = yom;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
}
