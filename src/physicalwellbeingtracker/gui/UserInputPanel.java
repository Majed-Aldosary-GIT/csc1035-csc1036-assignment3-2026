package physicalwellbeingtracker.gui;

import physicalwellbeingtracker.buildingblockdata.*;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;
import java.time.LocalDate;

public class UserInputPanel extends JPanel {
    private final JTextField durationField;
    private final JComboBox<Intensity> intensityJComboBox;
    private final JComboBox<Activity> activityJComboBox;
    private final JComboBox<Integer> dayComboBox;
    private final JComboBox<String> monthComboBox;
    private final JComboBox<Integer> yearComboBox;


    public UserInputPanel() {
        setLayout(new GridLayout(4, 4)); // Adjust layout as needed

        add(new JLabel("Duration (hour):"));
        durationField = new JTextField();
        add(durationField);



        add(new JLabel("Activity:"));
        activityJComboBox = new JComboBox<>(Activity.values());
        add(activityJComboBox);

        add(new JLabel("Intensity:"));
        intensityJComboBox = new JComboBox<>(Intensity.values());
        add(intensityJComboBox);

        add(new JLabel("Date:"));
        // Day, month, and year combo boxes
        dayComboBox = new JComboBox<>(IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new));
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        yearComboBox = new JComboBox<>(IntStream.rangeClosed(1900, 2100).boxed().toArray(Integer[]::new));
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        add(datePanel);



    }




    public double getDuration() {
        try {
            return Double.parseDouble(durationField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Activity getActivity() {
        return (Activity) activityJComboBox.getSelectedItem();
    }
    public Intensity getIntensity() {
        return (Intensity) intensityJComboBox.getSelectedItem();
    }

    public LocalDate getDate() {
        // Construct and return a LocalDate object from the date components
        // with dayComboBox, monthComboBox, yearComboBox
        Integer day = (Integer) dayComboBox.getSelectedItem();
        int month = monthComboBox.getSelectedIndex();
        Integer year = (Integer) yearComboBox.getSelectedItem();

        if (day == null || year == null) {
            // Handle the case where day or year is null as they are objects ,
            return null;
        }

        return LocalDate.of(year, month, day);
    }
}