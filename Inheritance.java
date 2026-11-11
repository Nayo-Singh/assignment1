imp java

public abstract class Car {
    protected double enginePower;
    protected double currentSpeed;
    protected String color;
    protected int nrDoors;

    public Car(int nrDoors, double enginePower, String color) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.currentSpeed = 0;
    }

    public void gas(double amount) {
        // Acceleration logic
    }

    public void brake(double amount) {
        // Braking logic
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public String getColor() {
        return color;
    }

    public int getNrDoors() {
        return nrDoors;
    }
}
