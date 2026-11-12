import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95() {
        super(2, 125, "red");
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    protected double speedFactor() {
        if (turboOn) {
            return enginePower * 0.01 * 1.3;
        }
        return enginePower * 0.01;
    }
}