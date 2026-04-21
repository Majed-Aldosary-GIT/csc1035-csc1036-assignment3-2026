package physicalwellbeingtracker.gui;

import physicalwellbeingtracker.buildingblockdata.Activity;

import javax.swing.*;
import java.awt.*;

public class FilterAndResetPanel extends JPanel {


    public FilterAndResetPanel() {

        private final JComboBox<Activity> filterComboBox;
        private final JButton filterButton;
        private final JButton showAllButton;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        filterComboBox = new JComboBox<>(Activity.values());
        add(filterComboBox);

        filterButton = new JButton("Apply Filter");
        add(filterButton);

        showAllButton = new JButton("Show All");
        add(showAllButton);
    }


    public JComboBox<Activity> getFilterComboBox() {
        return filterComboBox;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getShowAllButton() {
        return showAllButton;
    }
}