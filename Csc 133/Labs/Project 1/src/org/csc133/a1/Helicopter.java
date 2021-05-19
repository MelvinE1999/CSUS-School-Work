package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Vector;

public class Helicopter extends Movable implements ISteerable {

    private int stickAngle;
    private int maximumSpeed;
    private int fuelLevel;
    private final int fuelConsumptionRate = 5; //this is done in percentages
    private int damageLevel;
    private int lastSkyScrapperReached;
    private int currentMaxSpeed;

    public Helicopter(double xCord, double yCord, int heliMaximumSpeed) {
        super(ColorUtil.BLACK, 40);
        super.setCoordinates(xCord,yCord);
        this.stickAngle = 0;
        this.maximumSpeed = heliMaximumSpeed;
        this.currentMaxSpeed = heliMaximumSpeed;
        this.fuelLevel = 100; //this is in percentages
        this.damageLevel = 0; //percentages as well
        this.lastSkyScrapperReached = 1;
    }

    public void increaseSpeed(){
        int currentSpeed = super.getSpeed();
        if(currentSpeed == this.currentMaxSpeed){
            System.out.println("You can not go any faster as you are already " +
                                "at max speed.");
        }
        else if(this.currentMaxSpeed - currentSpeed >= 5){
            super.setSpeed(currentSpeed + 5);
        }
        else{
            super.setSpeed(this.currentMaxSpeed - currentSpeed);
        }
    }

    public void decreaseSpeed(){
        int currentSpeed = super.getSpeed();
        if(currentSpeed == 0){
            System.out.println("You can not go any slower as you are already " +
                    "at 0 speed.");
        }
        else if(currentSpeed >= 5){
            super.setSpeed(currentSpeed - 5);
        }
        else{
            super.setSpeed(0);
        }
    }

    public void setSkyScraperReached(){
        this.lastSkyScrapperReached++;
    }

    public int getLastSkyScraperReached(){
        return this.lastSkyScrapperReached;
    }

    public void setCurrentMaxSpeed(){
        this.currentMaxSpeed = this.maximumSpeed -
                ((this.maximumSpeed * damageLevel) / 100);

        if(this.currentMaxSpeed < super.getSpeed()){ //here to make sure can't
                                                     //go over max speed
            super.setSpeed(this.currentMaxSpeed);
        }
    }

    private int getCurrentMaxSpeed(){
        return this.currentMaxSpeed;
    }

    public void setFuelLevel(){
        this.fuelLevel = this.fuelLevel - fuelConsumptionRate;
    }

    public void setFuelLevel(int fuelLevel){
        this.fuelLevel = fuelLevel;
        if(this.fuelLevel > 100){ //add to make sure fuel tank is not overfilled
            this.fuelLevel = 100;
        }
    }

    public int getFuelLevel(){
        return this.fuelLevel;
    }

    public int getDamageLevel(){
        return  this.damageLevel;
    }

    public void setDamageLevel(int damageTaken){ // dmg level, color, and speed
        this.damageLevel += damageTaken;
        if(this.damageLevel > 100){
            this.damageLevel = 100;
        }
        setCurrentMaxSpeed();
    }

    public int getStickAngle(){
        return this.stickAngle;
    }

    public void setStickAngle(int updateAngle){
        this.stickAngle = updateAngle;
    }

    @Override
    public void move(){ // updates the heading based off of stick angle
        if(super.getHeading() > this.getStickAngle()){
            super.setHeading(super.getHeading() - 5);
        }
        else if(super.getHeading() < this.getStickAngle()) {
            super.setHeading(super.getHeading() + 5);
        }
        super.move(); //allows use of overridden method
    }


    @Override
    public void turnLeft() { // does not affect heading only stick angle
        int currentStickAngle = this.getStickAngle();
        if(currentStickAngle > -40){
            currentStickAngle -= 5;
            this.setStickAngle(currentStickAngle);
        }
        else
        {
            System.out.println("You are already turned to the left at an " +
                             "angle of -40 so you can not go left anymore.");
        }
    }

    @Override
    public void turnRight() { // does not affect heading only stick angle
        int currentStickAngle = this.getStickAngle();
        if(currentStickAngle < 40){
            currentStickAngle += 5;
            this.setStickAngle(currentStickAngle);
        }
        else
        {
            System.out.println("You are already turned to the right at an " +
                    "angle of 40 so you can not go right anymore.");
        }
    }

    @Override
    public String toString(){
        Vector<Double> coordinates = super.getCoordinates();
        double xValue = coordinates.elementAt(0);
        double yValue = coordinates.elementAt(1);

        return "Helicopter: " +
                "loc=" + (Math.round(xValue * 10.0) / 10.0) + "," +
                        (Math.round(yValue * 10.0) / 10.0) +
                " color=" + super.toStringForGetColor() +
                " heading=" + super.getHeading() +
                " speed=" + super.getSpeed() +
                " size=" + super.getSize() +
                " maxSpeed=" + this.getCurrentMaxSpeed() +
                " stickAngle=" + this.getStickAngle() +
                " fuelLevel=" + this.getFuelLevel() +
                " damageLevel=" + this.getDamageLevel();
    }

}
