package physicalwellbeingtracker.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class UserOperationPanel extends JPanel {

    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton clearButton;
    private final JButton editButton;

    public UserOperationPanel() {
        super(new FlowLayout(FlowLayout.CENTER));

        // Initialize the buttons
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        editButton = new JButton("Edit");

        // Add the buttons to the panel
        add(saveButton);
        add(deleteButton);
        add(clearButton);
        add(editButton);
    }

    // Getter methods if other classes need to access the buttons
    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getEditButton() {
        return editButton;
    }
}