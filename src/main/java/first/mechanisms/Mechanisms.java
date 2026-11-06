package first.mechanisms;

public class Mechanisms {
    
    private static Spindexer spindexer;
    private static Kicker kicker;

    public static void init() {
        spindexer = new Spindexer();
        kicker = new Kicker();
    }

    public static Spindexer spindexer() {
        assertNonNull(spindexer);
        return spindexer;
    }

    public static Kicker kicker() {
        assertNonNull(kicker);
        return kicker;
    }

    private static void assertNonNull(Object object) {
        if (object == null) {
            throw new IllegalStateException("Mechanism used before init() was called");
        }
    }
}
