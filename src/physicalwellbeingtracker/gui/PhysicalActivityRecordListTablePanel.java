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

    public PhysicalActivityRecordListTablePanel(
            PhysicalActivityRecordList physicalActivityRecordList) {

        this.physicalActivityRecordList = physicalActivityRecordList;
        this.currentFilterActivity = null;

        setLayout(new BorderLayout());

        physicalActivityRecordListTable =
                new PhysicalActivityRecordListTable(
                        physicalActivityRecordList.getAllPhysicalActivities());

        recordedPhysicalActivityJPanel =
                new JTable(physicalActivityRecordListTable);

        JScrollPane scrollPane =
                new JScrollPane(recordedPhysicalActivityJPanel);

        add(scrollPane, BorderLayout.CENTER);

        refreshTable();
    }

    public void refreshTable() {

        List<PhysicalActivityRecord> records;

        if (currentFilterActivity == null) {
            records = physicalActivityRecordList.getAllPhysicalActivities();
        } else {
            records =
                    physicalActivityRecordList
                            .getRecordedPhysicalActivitiesByActivity(
                                    currentFilterActivity);
        }

        physicalActivityRecordListTable.setPhysicalActivityRecords(records);
        physicalActivityRecordListTable.fireTableDataChanged();
    }

    public JTable getRecordedPhysicalActivityJPanel() {
        return recordedPhysicalActivityJPanel;
    }

    public void setFilter(Activity activity) {
        this.currentFilterActivity = activity;
        refreshTable();
    }

    public PhysicalActivityRecord getSelectedPhysicalActivityRecord() {

        int selectedRow =
                recordedPhysicalActivityJPanel.getSelectedRow();

        if (selectedRow == -1) {
            return null;
        }

        int modelRow =
                recordedPhysicalActivityJPanel.convertRowIndexToModel(selectedRow);

        return physicalActivityRecordListTable.getPhysicalActivityRecord(modelRow);
    }

    public void clearFilter() {
        currentFilterActivity = null;
        refreshTable();
    }
}