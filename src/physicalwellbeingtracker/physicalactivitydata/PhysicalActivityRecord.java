package physicalwellbeingtracker.physicalactivitydata;

import physicalwellbeingtracker.buildingblockdata.Activity;
import physicalwellbeingtracker.buildingblockdata.Intensity;

import java.io.Serializable;
import java.time.LocalDate;



public record PhysicalActivityRecord(double duration, Activity activity, Intensity intensity, LocalDate date) implements Serializable { }
