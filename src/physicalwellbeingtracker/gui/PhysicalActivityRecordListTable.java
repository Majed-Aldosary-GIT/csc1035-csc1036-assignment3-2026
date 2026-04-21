package physicalwellbeingtracker.gui;

import physicalwellbeingtracker.physicalactivitydata.PhysicalActivityRecord;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class PhysicalActivityRecordListTable extends AbstractTableModel {
    private final String[] columnNames = {"Duration", "Activity", "Intensity", "Date"};
    private List<PhysicalActivityRecord> displayedRecords;

    public PhysicalActivityRecordListTable(List<PhysicalActivityRecord> displayedRecords) {
        this.displayedRecords = displayedRecords;
    }

    @Override
    public int getRowCount() {
        return displayedRecords.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PhysicalActivityRecord physicalActivityRecord = displayedRecords.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> physicalActivityRecord.duration();
            case 1 -> physicalActivityRecord.intensity().getDisplayName();
            case 2 -> physicalActivityRecord.activity().getDisplayName();
            case 3 -> physicalActivityRecord.date().toString();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }


    public void resetPhysicalActivityTable(List<PhysicalActivityRecord> physicalActivityRecordList) {
        this.displayedRecords = physicalActivityRecordList;
        this.fireTableDataChanged(); // Notifies the table that the data has changed
    }

    public PhysicalActivityRecord getRecordAt(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= displayedRecords.size()) return null;
        return displayedRecords.get(rowIndex);
    }
}
