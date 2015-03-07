package de.ericdoerheit.myapplication;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Track implements Serializable, Cloneable {

    static final long serialVersionUID =2433072512043383585L;

    private String carModell;
    private int mileAge;
    private float distance;
    private int avgSpeed;
    private int rotationSpeed;
    private int fuel;
    private int drivingTime;
    private int currentSpeed;
    private int fuelUsage;
    private int gear;
    private float throttlePressure;
    private float breakPressure;
    private float emission;

    public Track() { }

    public Track(String carModell, int fuelUsage, int mileAge, float distance, int avgSpeed, int rotationSpeed, int fuel, int drivingTime, int currentSpeed,
                 int gear, float throttlePressure, float breakPressure, float emission) {
        this.carModell = carModell;
        this.fuelUsage = fuelUsage;
        this.mileAge = mileAge;
        this.distance = distance;
        this.avgSpeed = avgSpeed;
        this.rotationSpeed = rotationSpeed;
        this.fuel = fuel;
        this.drivingTime = drivingTime;
        this.currentSpeed = currentSpeed;
        this.gear = gear;
        this.throttlePressure = throttlePressure;
        this.breakPressure = breakPressure;
        this.emission = emission;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCarModell() {
        return carModell;
    }

    public void setCarModell(String carModell) {
        this.carModell = carModell;
    }

    public int getMileAge() {
        return mileAge;
    }

    public void setMileAge(int mileAge) {
        this.mileAge = mileAge;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(int drivingTime) {
        this.drivingTime = drivingTime;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(int fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public float getThrottlePressure() {
        return throttlePressure;
    }

    public void setThrottlePressure(float throttlePressure) {
        this.throttlePressure = throttlePressure;
    }

    public float getBreakPressure() {
        return breakPressure;
    }

    public void setBreakPressure(float breakPressure) {
        this.breakPressure = breakPressure;
    }

    public float getEmission() {
        return emission;
    }

    public void setEmission(float emission) {
        this.emission = emission;
    }

    public String toString(){
        Date date = new Date((long)drivingTime);
        return date.toLocaleString();
    }
}