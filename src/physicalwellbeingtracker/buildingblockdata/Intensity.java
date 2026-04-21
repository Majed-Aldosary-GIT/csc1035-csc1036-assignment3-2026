package physicalwellbeingtracker.buildingblockdata;

public enum Intensity {
    LOW("Light"),
    MODERATE("Moderate"),
    HIGH("High");

    private final String displayName;

    Intensity(String displayName) {
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