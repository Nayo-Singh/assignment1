public class CarTests {
    private static void reportResult(String testName, boolean success) {
        IO.println((success ? "SUCCESS: " : "FAILURE: ") + testName);
    }

    /**
     * A fresh car should have speed 0 and sit at 0,0.
     */
    private static void testInitialState() {
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();

        reportResult("Saab starts at speed 0", saab.getCurrentSpeed() == 0.0);
        reportResult("Volvo starts at speed 0", volvo.getCurrentSpeed() == 0.0);
        reportResult("Saab starts at position (0,0)", saab.getX() == 0 && saab.getY() == 0);
        reportResult("Volvo starts at position (0,0)", volvo.getX() == 0 && volvo.getY() == 0);
    }

    /**
     * Calling gas(0) or brake(0) should have no effect on the speed.
     */
    private static void testZeroGasAndBrake() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(0);
        reportResult("gas(0) leaves speed at 0", volvo.getCurrentSpeed() == 0.0);

        volvo.gas(1.0); // get some speed first
        double speedBefore = volvo.getCurrentSpeed();
        volvo.brake(0);
        reportResult("brake(0) does not change speed", volvo.getCurrentSpeed() == speedBefore);
    }

    /**
     * Speed must never go negative: braking a stationary car should keep the speed at 0.
     */
    private static void testSpeedNeverNegative() {
        Saab95 saab = new Saab95();
        saab.brake(1.0);
        reportResult("Speed never goes negative after braking from 0", saab.getCurrentSpeed() >= 0.0);
    }

    /**
     * Speed must never exceed enginePower: gassing repeatedly should not produce an unrealistic speed.
     */
    private static void testSpeedNeverExceedsEnginePower() {
        Volvo240 volvo = new Volvo240();
        for (int i = 0; i < 100; i++) {
            volvo.gas(1.0);
        }
        // Volvo's enginePower is 100
        reportResult("Volvo speed never exceeds enginePower", volvo.getCurrentSpeed() <= 100.0);

        Saab95 saab = new Saab95();
        saab.setTurboOn();
        for (int i = 0; i < 100; i++) {
            saab.gas(1.0);
        }
        // Saab's enginePower is 125
        reportResult("Saab speed never exceeds enginePower", saab.getCurrentSpeed() <= 125.0);
    }

    /**
     * A Saab with turbo on should accelerate faster than with turbo off.
     */
    private static void testTurboYieldsMoreSpeed() {
        Saab95 noTurbo = new Saab95();
        Saab95 withTurbo = new Saab95();
        withTurbo.setTurboOn();

        noTurbo.gas(0.5);
        withTurbo.gas(0.5);

        reportResult("Turbo car is faster than car with no turbo", withTurbo.getCurrentSpeed() > noTurbo.getCurrentSpeed());
    }

    /**
     * Both car types must be of Movable interface
     */
    private static void testCarsAreMovable() {
        Saab95 m1 = new Saab95();
        Volvo240 m2 = new Volvo240();
        reportResult("Saab95 is Movable", m1 instanceof Movable);
        reportResult("Volvo240 is Movable", m2 instanceof Movable);
    }

    /**
     * Default direction is north, after gassing and moving, y should increase, but x should stay the same.
     */
    private static void testMoveNorthByDefault() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(1.0);  // gain some speed
        int speedBefore = (int) volvo.getCurrentSpeed();
        volvo.move();
        reportResult("Moving north leaves x unchanged", volvo.getX() == 0);
        reportResult("Moving north increases y", volvo.getY() == speedBefore);
    }

    /**
     * After one turnRight from north the car faces east, moving should increase x.
     */
    private static void testMoveEastAfterTurnRight() {
        Saab95 saab = new Saab95();
        saab.gas(1.0);
        int speed = (int) saab.getCurrentSpeed();
        saab.turnRight();   // north → east
        saab.move();
        reportResult("After turnRight, move increases x", saab.getX() == speed);
        reportResult("After turnRight, move leaves y unchanged", saab.getY() == 0);
    }

    /**
     * After one turnLeft from north the car faces west, moving should decrease x.
     */
    private static void testMoveWestAfterTurnLeft() {
        Saab95 saab = new Saab95();
        saab.gas(1.0);
        int speed = (int) saab.getCurrentSpeed();
        saab.turnLeft();    // north → west
        saab.move();
        reportResult("After turnLeft, move decreases x", saab.getX() == -speed);
        reportResult("After turnLeft, move leaves y unchanged", saab.getY() == 0);
    }

    /**
     * Four consecutive turnRight should bring the car to its original direction (only y changes).
     */
    private static void testFourRightTurnsRestoreDirection() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(1.0);
        int speed = (int) volvo.getCurrentSpeed();

        for (int i = 0; i < 4; i++) volvo.turnRight();

        volvo.move();
        reportResult("4x turnRight restores original direction (y changes)", volvo.getY() == speed);
        reportResult("4x turnRight restores original direction (x unchanged)", volvo.getX() == 0);
    }

    /**
     * turnLeft and turnRight followed by each other should leave the direction unchanged.
     */
    private static void testTurnLeftRightAreInverses() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(1.0);
        int speed = (int) volvo.getCurrentSpeed();

        volvo.turnLeft();
        volvo.turnRight();
        volvo.move();

        reportResult("turnLeft then turnRight: moving to the north (y changes)", volvo.getY() == speed);
        reportResult("turnLeft then turnRight: not moving to the side (x unchanged)", volvo.getX() == 0);
    }

    /**
     * A stationary car (speed 0) should not change position when move() is called.
     */
    private static void testStationaryCarDoesNotMove() {
        Saab95 saab = new Saab95();   // speed is 0 by default
        saab.move();
        reportResult("Stationary car stays at x=0 after move()", saab.getX() == 0);
        reportResult("Stationary car stays at y=0 after move()", saab.getY() == 0);
    }

    public static void main(String[] args) {
        testInitialState();
        testZeroGasAndBrake();
        testSpeedNeverNegative();
        testSpeedNeverExceedsEnginePower();
        testTurboYieldsMoreSpeed();
        testCarsAreMovable();
        testMoveNorthByDefault();
        testMoveEastAfterTurnRight();
        testMoveWestAfterTurnLeft();
        testFourRightTurnsRestoreDirection();
        testTurnLeftRightAreInverses();
        testStationaryCarDoesNotMove();
    }
}
