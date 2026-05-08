package physicalwellbeingtracker.gui;

import physicalwellbeingtracker.physicalactivitydata.PhysicalActivityRecord;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PhysicalActivityRecordListTable extends AbstractTableModel {

    private List<PhysicalActivityRecord> physicalActivityRecords;

    private final String[] columnNames = {
            "Duration",
            "Activity",
            "Intensity",
            "Date"
    };

    public PhysicalActivityRecordListTable(
            List<PhysicalActivityRecord> physicalActivityRecords) {

        this.physicalActivityRecords = physicalActivityRecords;
    }

    public void setPhysicalActivityRecords(
            List<PhysicalActivityRecord> physicalActivityRecords) {

        this.physicalActivityRecords = physicalActivityRecords;
    }

    public PhysicalActivityRecord getPhysicalActivityRecord(int rowIndex) {
        return physicalActivityRecords.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return physicalActivityRecords.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        PhysicalActivityRecord record =
                physicalActivityRecords.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> record.duration();
            case 1 -> record.activity();
            case 2 -> record.intensity();
            case 3 -> record.date();
            default -> null;
        };
    }
}