package a2_1901040156;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddStudent extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private JFrame parentGUI;
    private CourseManProg courseManProg;
    private JTextField txtName;
    private JTextField txtDoB;
    private JTextField txtAddress;
    private JTextField txtEmail;

    public AddStudent(JFrame parentGUI, CourseManProg courseManProg) {
        this.parentGUI = parentGUI;
        this.courseManProg = courseManProg;
        createGUI();
    }

    private void createGUI() {
        gui = new JFrame("Add Student");
        gui.addWindowListener(this);

        // center panel
        JPanel panCenter = new JPanel(new GridLayout(4, 2, 5, 10));
        panCenter.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        panCenter.add(new JLabel("Name"));
        txtName = new JTextField(15);
        panCenter.add(txtName);
        panCenter.add(new JLabel("DoB"));
        txtDoB = new JTextField(15);
        panCenter.add(txtDoB);
        panCenter.add(new JLabel("Address"));
        txtAddress = new JTextField(15);
        panCenter.add(txtAddress);
        panCenter.add(new JLabel("Email"));
        txtEmail = new JTextField(15);
        panCenter.add(txtEmail);

        gui.add(panCenter);

        // bottom
        JPanel panBottom = new JPanel();

        JButton btnAddStudent = new JButton("Add Student");
        btnAddStudent.addActionListener(this);
        panBottom.add(btnAddStudent);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        panBottom.add(btnCancel);

        panBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        gui.add(panBottom, BorderLayout.SOUTH);
        gui.pack();

        int x = (int) parentGUI.getLocation().getX() + 100;
        int y = (int) parentGUI.getLocation().getY() + 100;
        gui.setLocation(x, y);
    }

    public void display() {
        gui.setVisible(true);
        System.out.println("Add Student GUI displayed...");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        disposeGUI();
    }

    private void disposeGUI() {
        txtName.setText("");
        txtDoB.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        gui.dispose();
        System.out.println("Add Student GUI disposed...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
         if (command.equals("Cancel")) {
            disposeGUI();
        } else if (command.equals("Add Student")) {
             System.out.println("Add Successfully");
             courseManProg.addStudent(txtName.getText(), txtDoB.getText(), txtEmail.getText(), txtAddress.getText());
             disposeGUI();
         }
    }

}