package physicalwellbeingtracker.physicalactivitydata;

import physicalwellbeingtracker.buildingblockdata.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhysicalActivityRecordList {
    private List<PhysicalActivityRecord> records;


    public PhysicalActivityRecordList() {
        // initially no physical activity
        records = new ArrayList<>;
    }

    // add a physical activity to the existing list of physical activities.
    public void addPhysicalActivity(PhysicalActivityRecord physicalActivityRecord) {
        records.add(physicalActivityRecord);
    }

    // Edit a physical activity.
    public void replacePhysicalActivity(PhysicalActivityRecord oldRecord, PhysicalActivityRecord newRecord) {
        int idx = records.indexOf(oldRecord);
        if (idx != -1) {
            records.set(idx, newRecord);
        }
    }


    // Delete a physical activity
    public void deletePhysicalActivity(PhysicalActivityRecord physicalActivityRecord) {
        records.remove(physicalActivityRecord);
    }

    // Clear all physical activities
    public void clearPhysicalActivities() {
        records.clear();
    }


    // return the entire list of physical activities added so far
    // implement list as ArrayList
    public List<PhysicalActivityRecord> getAllPhysicalActivities() {
        return new ArrayList<>(records);
    }


    // get the physical activities based on activity type for filter panel
    public List<PhysicalActivityRecord> getRecordedPhysicalActivitiesByActivity(Activity activity) {
        return records.stream()
                .filter(physicalActivity -> physicalActivity.activity().equals(activity))
                .collect(Collectors.toList());
    }




}
