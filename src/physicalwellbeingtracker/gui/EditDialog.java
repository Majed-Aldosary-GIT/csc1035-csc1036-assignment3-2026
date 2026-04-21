package physicalwellbeingtracker.gui;

import physicalwellbeingtracker.buildingblockdata.*;
import physicalwellbeingtracker.physicalactivitydata.PhysicalActivityRecord;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditDialog extends JDialog {
    private final JTextField durationField = new JTextField(10);

    private final JComboBox<Activity> activityComboBox = new JComboBox<>(Activity.values());
    private final JComboBox<Intensity> intensityComboBox = new JComboBox<>(Intensity.values());
    private final JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
    private boolean saved = false;

    public EditDialog(Frame owner) {
        super(owner, "Edit Physical Activity", true);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Duration:"));
        add(durationField);

        add(new JLabel("Activity:"));
        add(activityComboBox);


        add(new JLabel("Intensity:"));
        add(intensityComboBox);


        add(new JLabel("Date:"));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        add(dateSpinner);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            setVisible(false); // do not show dialog unless a to-be-edited physical activity is selected
        });
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);

        pack();
    }

    public boolean showDialog(PhysicalActivityRecord physicalActivityRecord) {
        // Initialize dialog fields with physical activity details
        durationField.setText(String.valueOf(physicalActivityRecord.duration()));
        activityComboBox.setSelectedItem(physicalActivityRecord.activity());
        intensityComboBox.setSelectedItem(physicalActivityRecord.intensity());
        dateSpinner.setValue(java.sql.Date.valueOf(physicalActivityRecord.date()));

        saved = false; // Reset saved state

        // Set location relative to the owner frame
        setLocationRelativeTo(getOwner());

        setVisible(true); // Show dialog

        return saved; // Return true if saved, false otherwise
    }

    public PhysicalActivityRecord getEditedSinglePhysicalActivity() {
        double duration = Double.parseDouble(durationField.getText());
        if (duration <= 0) {
            // show error message
            JOptionPane.showMessageDialog(this, "Duration must be greater than zero.", "Invalid Duration", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            Activity activity = (Activity) activityComboBox.getSelectedItem();
            Intensity intensity = (Intensity) intensityComboBox.getSelectedItem();
            Date date = (Date) dateSpinner.getValue();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return new PhysicalActivityRecord(duration, activity, intensity, localDate);
        }

    }
}
