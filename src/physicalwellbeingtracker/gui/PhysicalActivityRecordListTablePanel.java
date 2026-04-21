package physicalwellbeingtracker.gui;

import physicalwellbeingtracker.buildingblockdata.Activity;
import physicalwellbeingtracker.physicalactivitydata.PhysicalActivityRecord;
import physicalwellbeingtracker.physicalactivitydata.PhysicalActivityRecordList;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PhysicalActivityRecordListTablePanel extends JPanel {
    private PhysicalActivityRecordListTable physicalActivityRecordListTable;
    private JTable recordedPhysicalActivityJPanel;

    private final PhysicalActivityRecordList physicalActivityRecordList;
    private Activity currentFilterActivity;


    public PhysicalActivityRecordListTablePanel(PhysicalActivityRecordList physicalActivityRecordList) {
        this.physicalActivityRecordList = physicalActivityRecordList;
        this.currentFilterActivity = null;

        setLayout(new BorderLayout());

        physicalActivityRecordListTable = new PhysicalActivityRecordListTable(physicalActivityRecordList.getAllPhysicalActivities());
        recordedPhysicalActivityJPanel = new JTable(physicalActivityRecordListTable);

        JScrollPane scrollPane = new JScrollPane(recordedPhysicalActivityJPanel);
        add(scrollPane, BorderLayout.CENTER);

        refreshTable();
    }


    public void refreshTable() {
        List<PhysicalActivityRecord> recordsToShow;

        if (currentFilterActivity == null) {
            recordsToShow = physicalActivityRecordList.getAllPhysicalActivities();
        } else {
            recordsToShow = physicalActivityRecordList.getRecordedPhysicalActivitiesByActivity(currentFilterActivity);
        }

        physicalActivityRecordListTable.resetPhysicalActivityTable(recordsToShow);
    }

    public void setFilter(Activity activity) {
        currentFilterActivity = activity;
    }

    public void clearFilter() {
        currentFilterActivity = null;
    }


    // return the actual selected record
    public PhysicalActivityRecord getSelectedPhysicalActivityRecord() {
        int viewRow = recordedPhysicalActivityJPanel.getSelectedRow();
        if (viewRow == -1) return null;

        int modelRow = recordedPhysicalActivityJPanel.convertRowIndexToModel(viewRow);
        return physicalActivityRecordListTable.getRecordAt(modelRow);
    }

}
