package a2_1901040156;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InitialReport {
    private JFrame initialFrame;
    private JPanel panCenter;
    private JTable initialTable;

    public InitialReport(){
        createGUI();
    }

    private void createGUI(){
        initialFrame = new JFrame("Initial Report");
        initialFrame.setSize(600,400);
        initialFrame.setVisible(true);

        panCenter = new JPanel();
        panCenter.setLayout(new BorderLayout());
        initialFrame.add(panCenter);

        String[] headers = {"ID", "Student ID","Student name", "Module code", "Module name"};
        Object[][] data = {
                {"1", "1", "Bui Ngoc Phuc", "1","JSD"},
        };

        DefaultTableModel tm = new DefaultTableModel(data, headers);
        initialTable = new JTable(tm);
        JScrollPane scrContacts = new JScrollPane(initialTable);
        panCenter.add(scrContacts);

        initialFrame.setLocationRelativeTo(null);
        initialFrame.setVisible(true);

    }

    public void display(){
        initialTable.setVisible(true);
    }
}
