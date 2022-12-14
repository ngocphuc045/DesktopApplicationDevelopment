package a2_1901040156;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AssessmentReport {
    private JFrame assessmentReport;
    private JPanel pnlMiddle;
    private JTable assessmentTable;

    public AssessmentReport(){
        createGUI();
    }

    private void createGUI(){
        assessmentReport = new JFrame("Assessment Report");
        assessmentReport.setSize(600,400);
        assessmentReport.setVisible(true);

        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(new BorderLayout());
        assessmentReport.add(pnlMiddle);

        String[] headers = {"ID", "Student ID","Module code", "Internal mark", "External mark", "Final grade"};
        Object[][] data = {
                {"1", "1", "1", "8","9", "G"},
        };

        DefaultTableModel tm = new DefaultTableModel(data, headers);
        assessmentTable = new JTable(tm);
        JScrollPane scrContacts = new JScrollPane(assessmentTable);
        pnlMiddle.add(scrContacts);

        assessmentReport.setLocationRelativeTo(null);
        assessmentReport.setVisible(true);

    }

    public void display(){
        assessmentTable.setVisible(true);
    }
}
