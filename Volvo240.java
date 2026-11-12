import java.awt.*;

public class Volvo240 extends Car {

    private static final double trimFactor = 1.25;

    public Volvo240() {
        super(4, 100, "black");
    }

    protected double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }
}