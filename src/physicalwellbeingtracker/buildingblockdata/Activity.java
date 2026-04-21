package physicalwellbeingtracker.buildingblockdata;

public enum Activity {
    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SPORT("Sport"),
    GYM("Gym"),
    SWIMMING("Swimming");


    private final String displayName;

    Activity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}