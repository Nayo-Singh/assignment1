
public abstract class Car implements Movable {

    protected double enginePower;
    protected double currentSpeed;
    protected String color;
    protected int nrDoors;

    // Position and direction
    protected int x;
    protected int y;
    protected int direction;

    public Car(int nrDoors, double enginePower, String color) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.currentSpeed = 0;

        this.x = 0;
        this.y = 0;
        this.direction = 0;
    }

    protected abstract double speedFactor();

    public void gas(double amount) {
        currentSpeed += speedFactor() * amount;
    }

    public void brake(double amount) {
        currentSpeed -= speedFactor() * amount;
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

    public void move() {
        switch (direction) {
            case 0:
                y += currentSpeed;
                break;
            case 1:
                x += currentSpeed;
                break;
            case 2:
                y -= currentSpeed;
                break;
            case 3:
                x -= currentSpeed;
                break;
        }
    }

    public void turnLeft() {
        direction = (direction + 3) % 4;
    }

    public void turnRight() {
        direction = (direction + 1) % 4;
    }
}
