## Bug 1

* bug type:

```text
Illegal field declarations inside constructor
```

* original code containing the bug:

```java
public FilterAndResetPanel() {

    private final JComboBox<Activity> filterComboBox;
    private final JButton filterButton;
    private final JButton showAllButton;
```

* fix:

```java
private final JComboBox<Activity> filterComboBox;
private final JButton filterButton;
private final JButton showAllButton;

public FilterAndResetPanel() {
```

## Bug 2

* bug type:

```text
Window not visible
```

* original code containing the bug:

```java
frame.setVisible(false);
```

* fix:

```java
frame.setVisible(true);
```

## Bug 3

* bug type:

```text
Off-by-one month error
```

* original code containing the bug:

```java
int month = monthComboBox.getSelectedIndex();
```

* fix:

```java
int month = monthComboBox.getSelectedIndex() + 1;
```

## Bug 4

* bug type:

```text
Missing clear button functionality
```

* original code containing the bug:

```java
// no ActionListener attached to clear button
```

* fix:

```java
userOperationPanel.getClearButton().addActionListener(e -> {

    physicalActivityRecordList.clearAll();

    physicalActivityRecordListTablePanel.refreshTable();
});
```

## Bug 5

* bug type:

```text
Edit operation selected wrong row
```

* original code containing the bug:

```java
return physicalActivityRecordList
        .getAllPhysicalActivities()
        .get(selectedRow);
```

* fix:

```java
int modelRow =
        recordedPhysicalActivityJPanel.convertRowIndexToModel(selectedRow);

return physicalActivityRecordListTable
        .getPhysicalActivityRecord(modelRow);
```

## Bug 6

* bug type:

```text
Missing delete method in activity list
```

* original code containing the bug:

```java
// deletePhysicalActivity method missing
```

* fix:

```java
public void deletePhysicalActivity(PhysicalActivityRecord record) {
    recordedPhysicalActivities.remove(record);
}
```

## Bug 7

* bug type:

```text
Missing replace method in activity list
```

* original code containing the bug:

```java
// replacePhysicalActivity method missing
```

* fix:

```java
public void replacePhysicalActivity(
        PhysicalActivityRecord oldRecord,
        PhysicalActivityRecord newRecord) {

    int index = recordedPhysicalActivities.indexOf(oldRecord);

    if (index != -1) {
        recordedPhysicalActivities.set(index, newRecord);
    }
}
```

## Bug 8

* bug type:

```text
Missing selected record retrieval method
```

* original code containing the bug:

```java
// getSelectedPhysicalActivityRecord method missing
```

* fix:

```java
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
```

## Bug 9

* bug type:

```text
Missing filter clearing functionality
```

* original code containing the bug:

```java
// clearFilter method missing
```

* fix:

```java
public void clearFilter() {
    currentFilterActivity = null;
    refreshTable();
}
```

## Bug 10

* bug type:

```text
Missing table model accessor method
```

* original code containing the bug:

```java
// getPhysicalActivityRecord method missing
```

* fix:

```java
public PhysicalActivityRecord getPhysicalActivityRecord(int rowIndex) {
    return physicalActivityRecords.get(rowIndex);
}
```

1. What went well in your bug-fixing process, and what was the main difficulty you encountered?

One thing that went well in this coursework was understanding how the code base was structured. At the beginning the project felt difficult to follow because many classes were connected together, but after testing the GUI and tracing how the program behaved, it became easier to identify where bugs were coming from. I also found that focusing on one feature at a time, such as save, edit, delete, filter, and clear, made the debugging process more manageable. The main difficulty was dealing with compilation errors caused by missing methods, incorrect imports, and code being placed in the wrong location. In several cases, fixing one bug created additional errors somewhere else in the project, which became frustrating at times. To deal with this, I slowed down, fixed problems one at a time, and tested the application regularly after each change.



2. What have you learned from this coursework?

This coursework helped me understand how different it is to work on an existing code base compared to creating a small program from scratch. I learned that debugging requires patience and careful attention because even small mistakes, such as incorrect method names or off-by-one errors, can stop the whole application from working correctly. I also improved my understanding of event-driven programming in Java Swing, especially how buttons, listeners, tables, and GUI components interact with each other. Another thing I learned was that making small focused fixes is usually better than rewriting large sections of code unnecessarily which could be stressful sometimes.


3. How did you use Git/version control to support your work?

I used Git during the coursework to keep track of the changes I made while fixing bugs. Making commits helped me organise my progress and made it easier to see which fixes had already been completed. Using GitHub also provided a backup of the project, which was useful once parts of the application started working correctly because I could save stable versions before continuing with more changes. Version control became especially useful near the end of the coursework because it reduced the risk of losing working code while making further edits and improvements.