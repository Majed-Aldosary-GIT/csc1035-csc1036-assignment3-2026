package physicalwellbeingtracker;

import physicalwellbeingtracker.buildingblockdata.*;
import physicalwellbeingtracker.physicalactivitydata.PhysicalActivityRecord;
import physicalwellbeingtracker.gui.*;
import physicalwellbeingtracker.physicalactivitydata.PhysicalActivityRecordList;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Physical Well-being Activity Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        PhysicalActivityRecordList physicalActivityRecordList = new PhysicalActivityRecordList();

        UserInputPanel userInputPanel = new UserInputPanel();
        UserOperationPanel userOperationPanel = new UserOperationPanel();
        PhysicalActivityRecordListTablePanel physicalActivityRecordListTablePanel = new PhysicalActivityRecordListTablePanel(physicalActivityRecordList);
        FilterAndResetPanel filterAndResetPanel = new FilterAndResetPanel();

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        containerPanel.add(userInputPanel);
        containerPanel.add(userOperationPanel);
        containerPanel.add(physicalActivityRecordListTablePanel);
        containerPanel.add(filterAndResetPanel);

        frame.add(containerPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

        // Save
        userOperationPanel.getSaveButton().addActionListener(e -> {
            Duration duration = userInputPanel.getDuration();
            if (duration <= 0) {
                JOptionPane.showMessageDialog(
                        userInputPanel,
                        "Duration must be greater than zero.",
                        "Invalid Duration",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Activity activity = userInputPanel.getActivity();
            Intensity intensity = userInputPanel.getIntensity();
            LocalDate date = userInputPanel.getDate();

            PhysicalActivityRecord newPhysicalActivityRecord = new PhysicalActivityRecord(duration, activity, intensity, date);
            physicalActivityRecordList.addPhysicalActivity(newPhysicalActivityRecord);
            physicalActivityRecordListTablePanel.refreshTable();
        });

        EditDialog editDialog =
                new EditDialog(frame);

        // Edit
        userOperationPanel.getEditButton().addActionListener(e -> {
            PhysicalActivityRecord selected = physicalActivityRecordListTablePanel.getSelectedPhysicalActivityRecord();
            if (selected == null) return;

            boolean saved = editDialog.showDialog(selected);
            if (!saved) return;

            PhysicalActivityRecord edited = editDialog.getEditedSinglePhysicalActivity();
            if (edited == null) return;

            physicalActivityRecordList.replacePhysicalActivity(selected, selected);
            physicalActivityRecordListTablePanel.refreshTable();
        });

        // Delete
        userOperationPanel.getDeleteButton().addActionListener(e -> {
            PhysicalActivityRecord selected = physicalActivityRecordListTablePanel.getSelectedPhysicalActivityRecord();
            if (selected == null) return;

            physicalActivityRecordList.deletePhysicalActivity(selected);
            physicalActivityRecordListTablePanel.refreshTable();
        });

        // Clear (also clears filter)
        userOperationPanel.getClearButton().addActionListener(e -> {
            clearPhysicalActivities();
            physicalActivityRecordListTablePanel.clearFilter();
            physicalActivityRecordListTablePanel.refreshTable();
        });

        // Filter
        filterAndResetPanel.getFilterButton().addActionListener(e -> {
            Activity selectedActivity = (Activity) filterAndResetPanel.getFilterComboBox().getSelectedItem();
            physicalActivityRecordListTablePanel.setFilter(selectedActivity);
            physicalActivityRecordListTablePanel.refreshTable();
        });

        // Show All
        filterAndResetPanel.getShowAllButton().addActionListener(e -> {
            physicalActivityRecordListTablePanel.refreshTable();
        });
    }
}