package physicalwellbeingtracker;

import physicalwellbeingtracker.buildingblockdata.*;
import physicalwellbeingtracker.gui.*;
import physicalwellbeingtracker.physicalactivitydata.*;

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

        PhysicalActivityRecordList physicalActivityRecordList =
                new PhysicalActivityRecordList();

        UserInputPanel userInputPanel = new UserInputPanel();
        UserOperationPanel userOperationPanel = new UserOperationPanel();

        PhysicalActivityRecordListTablePanel physicalActivityRecordListTablePanel =
                new PhysicalActivityRecordListTablePanel(physicalActivityRecordList);

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
        frame.setVisible(true);

        userOperationPanel.getSaveButton().addActionListener(e -> {

            double duration = userInputPanel.getDuration();

            if (duration <= 0) {
                JOptionPane.showMessageDialog(
                        userInputPanel,
                        "Duration must be greater than zero.",
                        "Invalid Duration",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            PhysicalActivityRecord record =
                    new PhysicalActivityRecord(
                            duration,
                            userInputPanel.getActivity(),
                            userInputPanel.getIntensity(),
                            userInputPanel.getDate());

            physicalActivityRecordList.addPhysicalActivity(record);
            physicalActivityRecordListTablePanel.refreshTable();
        });

        userOperationPanel.getDeleteButton().addActionListener(e -> {

            PhysicalActivityRecord selectedRecord =
                    physicalActivityRecordListTablePanel.getSelectedPhysicalActivityRecord();

            if (selectedRecord != null) {
                physicalActivityRecordList.deletePhysicalActivity(selectedRecord);
                physicalActivityRecordListTablePanel.refreshTable();
            }
        });

        userOperationPanel.getClearButton().addActionListener(e -> {
            physicalActivityRecordList.clearAll();
            physicalActivityRecordListTablePanel.refreshTable();
        });

        userOperationPanel.getEditButton().addActionListener(e -> {

            PhysicalActivityRecord selectedRecord =
                    physicalActivityRecordListTablePanel.getSelectedPhysicalActivityRecord();

            if (selectedRecord == null) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please select a record to edit.",
                        "No Record Selected",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            PhysicalActivityRecord editedRecord =
                    new PhysicalActivityRecord(
                            userInputPanel.getDuration(),
                            userInputPanel.getActivity(),
                            userInputPanel.getIntensity(),
                            userInputPanel.getDate());

            physicalActivityRecordList.replacePhysicalActivity(
                    selectedRecord,
                    editedRecord);

            physicalActivityRecordListTablePanel.refreshTable();
        });

        filterAndResetPanel.getFilterButton().addActionListener(e -> {

            Activity selectedActivity =
                    (Activity) filterAndResetPanel
                            .getFilterComboBox()
                            .getSelectedItem();

            physicalActivityRecordListTablePanel.setFilter(selectedActivity);
        });

        filterAndResetPanel.getShowAllButton().addActionListener(e -> {
            physicalActivityRecordListTablePanel.clearFilter();
        });
    }
}