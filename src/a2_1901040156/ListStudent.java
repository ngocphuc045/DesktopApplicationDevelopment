package a2_1901040156;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListStudent {
    private static Connection conn;
    public static void listStudents() {
        try {
            conn=ConnectDB.connectDB();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        JFrame frame = new JFrame();
        String[] headers = {"ID", "Name", "DOB", "Address", "Email"};
        Object[][] data = {{null, null, null, null, null}};
        try {
            ArrayList<Object[]> rows = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                rows.add(new Object[]{
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("email"),
                });
            }
            data = rows.toArray(data);
        } catch (SQLException e) {
            System.out.println("Cannot fetch data from DB");
        }
        DefaultTableModel tm = new DefaultTableModel(data, headers);
        JTable students = new JTable(tm);
        JScrollPane scrStudents = new JScrollPane(students);
        frame.add(scrStudents);
        frame.pack();
        frame.setVisible(true);
    }
}
