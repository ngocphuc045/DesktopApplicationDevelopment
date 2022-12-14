package a2_1901040156;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class CourseManProg extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private Connection conn;
    private Statement stmt;
    private JTable tblStudents;
    private AddStudent addStudent;
    private AddModule addModule;
    private NewEnrollment newEnrollment;
    private ListStudent listStudent;
    private InitialReport initialReport;
    private AssessmentReport assessmentReport;
    ArrayList<Integer> studentIDs;

    public CourseManProg() {
        connectDB();
        createGUI();
    }

    public void connectDB() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/a2_1901040156/database.sqlite3");
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Serious problem: cannot connect to DB!");
            System.exit(1);
        }
    }

    private void createGUI() {
        gui = new JFrame("Desktop Application Development");
        gui.setSize(500, 500);
        gui.addWindowListener(this);

        JMenuBar menubar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(this);

        menubar.add(fileMenu);

        JMenu studentMenu = new JMenu("Student");

        JMenuItem addStudent = new JMenuItem("New Student");
        studentMenu.add(addStudent);
        addStudent.addActionListener(this);
        JMenuItem listStudent = new JMenuItem("List Student");
        studentMenu.add(listStudent);
        listStudent.addActionListener(this);
        menubar.add(studentMenu);

        JMenu moduleMenu = new JMenu("Module");

        JMenuItem addModule = new JMenuItem("New Module");
        moduleMenu.add(addModule);
        addModule.addActionListener(this);
        JMenuItem listSModule = new JMenuItem("List Module");
        moduleMenu.add(listSModule);
        listSModule.addActionListener(this);

        menubar.add(moduleMenu);

        JMenu enrollmentMenu = new JMenu("Enrollment");

        JMenuItem newEnrollment = new JMenuItem("New Enrollment");
        enrollmentMenu.add(newEnrollment);
        newEnrollment.addActionListener(this);
        JMenuItem initialReport = new JMenuItem("Initial Report");
        enrollmentMenu.add(initialReport);
        initialReport.addActionListener(this);
        JMenuItem assessmentReport = new JMenuItem("Assessment Report");
        enrollmentMenu.add(assessmentReport);
        assessmentReport.addActionListener(this);

        menubar.add(enrollmentMenu);

        gui.setJMenuBar(menubar);

        // north panel
        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(Color.YELLOW);
        JLabel lblTitle = new JLabel("Desktop Application Development");
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 15f));
        pnlTop.add(lblTitle);
        gui.add(pnlTop, BorderLayout.NORTH);

        // center panel
        JPanel pnlMiddle = new JPanel();
        pnlMiddle.setLayout(new BorderLayout());
        gui.add(pnlMiddle);

        String[] headers = {"Name", "Dob", "Email", "Address"};
        Object[][] data = null;
        studentIDs = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");
            ArrayList<Object[]> students = new ArrayList<>();
            while (rs.next()) {
                studentIDs.add(rs.getInt("Student_ID"));
                students.add(new Object[]{rs.getString("Student_name"), rs.getString("Dob"), rs.getString("Email"), rs.getString("Address"), false});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot fetch data from DB", "Error", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableModel tm = new DefaultTableModel(data, headers);
        tblStudents = new JTable(tm) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 4) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(column);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        tblStudents.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblStudents.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblStudents.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblStudents.getColumnModel().getColumn(3).setPreferredWidth(200);
        JScrollPane scrStudents = new JScrollPane(tblStudents);
        pnlMiddle.add(scrStudents);
    }

    public void display() {
        gui.setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        shutDown();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("New Student")) {
            if (addStudent == null) addStudent = new AddStudent(gui, this);
            addStudent.display();
        }
        if (command.equals("List Student")) {
            for (int i = 0; i < tblStudents.getRowCount(); i++) {
                tblStudents.setValueAt(true, i, 2);
            }
        }
        if (command.equals("New Module")) {
            if (addModule == null) addModule = new AddModule(gui, this);
            addModule.display();
        }
        if (command.equals("New Enrollment")) {
            if (newEnrollment == null) newEnrollment = new NewEnrollment(gui, this);
            newEnrollment.display();
        } else if (command.equals("Exit")) {
            shutDown();
        }
    }

    private void shutDown() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("DB connection was not closed properly!");
        }
        System.exit(0);
    }

    public void addStudent(String name, String dob, String email, String address) {
        try {
            String studentId = generateStudentId();
            String query = "INSERT INTO STUDENT(Student_name,DoB,Student_Email,Student_Address, Student_ID) VALUES(?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, dob);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setString(5, studentId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return; // terminate method
        }
        DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();
        model.addRow(new Object[]{name, dob, email, address, false});
    }

    private String generateStudentId() {
        int currTime = Calendar.getInstance().get(Calendar.YEAR);
        String query = "SELECT COUNT(Student_ID) AS total from STUDENT";
        String id = null;
        try {
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                int total = result.getInt("total");
                id = "S" + currTime + total;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot count student", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public void addModule(String name, int semester, int credits) {
        try {
            String code = generateModuleCode(semester);
            String query = "INSERT INTO MODULE (code, name_module, semester, credits) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, code);
            statement.setString(2, name);
            statement.setInt(3, semester);
            statement.setInt(4, credits);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return; // terminate method
        }
    }

    private String generateModuleCode(int semester) {
        String query = "SELECT COUNT(code) AS total from MODULE";
        String code = null;
        try {
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                int total = result.getInt("total");
                code = "M" + (semester * 100 + total);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot count student", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return code;
    }

    public static void main(String[] args) {
        CourseManProg app = new CourseManProg();
        app.display();
    }
}
