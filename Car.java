
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

    public boolean checkInterval(double number){ // checks if number is in the interval 0 - 1
        if (number >= 0 && number <= 1) return true;
        else return false;
    }
    public void gas(double amount) {
        if (checkInterval(amount))
            currentSpeed += speedFactor() * amount;
        if (currentSpeed > enginePower) //Checks that the speed does not exceed the enginPower
            currentSpeed = enginePower;
    }

    public void brake(double amount) {
        if (checkInterval(amount))
            currentSpeed -= speedFactor() * amount;
        if (currentSpeed <= 0) currentSpeed = 0; //prevents negative speed
    }

    public int getX() { return x; }
    public int getY() { return y; }

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
                y += (int)currentSpeed;
                break;
            case 1:
                x += (int)currentSpeed;
                break;
            case 2:
                y -= (int)currentSpeed;
                break;
            case 3:
                x -= (int)currentSpeed;
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
