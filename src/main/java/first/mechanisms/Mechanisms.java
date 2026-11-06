package first.mechanisms;

public class Mechanisms {
    
    private static Spindexer spindexer;
    private static Kicker kicker;

    private static Drivetrain drivetrain;

    public static void init() {
        spindexer = new Spindexer();
        kicker = new Kicker();
        drivetrain = new Drivetrain();
    }

    public static Spindexer spindexer() {
        assertNonNull(spindexer);
        return spindexer;
    }

    public static Kicker kicker() {
        assertNonNull(kicker);
        return kicker;
    }

    public static Drivetrain drivetrain() {
        assertNonNull(drivetrain);
        return drivetrain;
    }

    private static void assertNonNull(Object object) {
        if (object == null) {
            throw new IllegalStateException("Mechanism used before init() was called");
        }
    }
}
