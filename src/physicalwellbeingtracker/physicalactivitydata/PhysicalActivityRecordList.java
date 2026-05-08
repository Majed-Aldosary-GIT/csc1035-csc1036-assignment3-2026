package physicalwellbeingtracker.physicalactivitydata;

import physicalwellbeingtracker.buildingblockdata.Activity;

import java.util.ArrayList;
import java.util.List;

public class PhysicalActivityRecordList {

    private final List<PhysicalActivityRecord> recordedPhysicalActivities;

    public PhysicalActivityRecordList() {
        recordedPhysicalActivities = new ArrayList<>();
    }

    public void addPhysicalActivity(PhysicalActivityRecord record) {
        recordedPhysicalActivities.add(record);
    }

    public void removePhysicalActivity(int index) {
        recordedPhysicalActivities.remove(index);
    }

    public void deletePhysicalActivity(PhysicalActivityRecord record) {
        recordedPhysicalActivities.remove(record);
    }

    public void replacePhysicalActivity(
            PhysicalActivityRecord oldRecord,
            PhysicalActivityRecord newRecord) {

        int index = recordedPhysicalActivities.indexOf(oldRecord);

        if (index != -1) {
            recordedPhysicalActivities.set(index, newRecord);
        }
    }

    public List<PhysicalActivityRecord> getAllPhysicalActivities() {
        return recordedPhysicalActivities;
    }

    public List<PhysicalActivityRecord> getRecordedPhysicalActivitiesByActivity(Activity activity) {

        List<PhysicalActivityRecord> filteredActivities = new ArrayList<>();

        for (PhysicalActivityRecord record : recordedPhysicalActivities) {
            if (record.activity() == activity) {
                filteredActivities.add(record);
            }
        }

        return filteredActivities;
    }

    public void clearAll() {
        recordedPhysicalActivities.clear();
    }
}