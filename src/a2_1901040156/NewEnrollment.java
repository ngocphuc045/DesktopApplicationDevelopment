package a2_1901040156;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewEnrollment extends WindowAdapter implements ActionListener {
    private JFrame enrollmentFrame;
    private JPanel pnlMiddle;
    private JComboBox boxStudent;
    private JComboBox boxModule;
    private JTextField txtInternalMark;
    private JTextField txtExamMark;
    private JPanel pnlBottom;
    private JButton btnCancel;
    private JFrame parentGUI;
    private CourseManProg courseManProg;
    public NewEnrollment(JFrame parentGUI, CourseManProg courseManProg) {
        this.parentGUI = parentGUI;
        this.courseManProg = courseManProg;
        createGUI();
    }

    private void createGUI(){
        enrollmentFrame = new JFrame("New Enrollment");
        pnlMiddle = new JPanel(new GridLayout(4, 2,5,10));
        pnlMiddle.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        String student[] = {"Pham Minh Duc", "Pham Duc Minh"};
        boxStudent = new JComboBox<>(student);
        boxStudent.setBounds(100, 50,150,20);
        pnlMiddle.add(new JLabel("Student"));
        pnlMiddle.add(boxStudent);

        String model[] = {"JSD", "SPM", "IPR", "ATI"};
        boxModule = new JComboBox<>(model);
        boxModule.setBounds(100, 50,150,20);
        pnlMiddle.add(new JLabel("Model"));
        pnlMiddle.add(boxModule);

        pnlMiddle.add(new JLabel("Internal Mark"));
        txtInternalMark = new JTextField(3);
        pnlMiddle.add(txtInternalMark);

        pnlMiddle.add(new JLabel("Exam Mark"));
        txtExamMark = new JTextField(3);
        pnlMiddle.add(txtExamMark);

        enrollmentFrame.add(pnlMiddle);

        //button
        pnlBottom = new JPanel();

        JButton btnAddStudent = new JButton("Add Enrollment");
        btnAddStudent.addActionListener(this);
        pnlBottom.add(btnAddStudent);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        pnlBottom.add(btnCancel);

        pnlBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        enrollmentFrame.add(pnlBottom, BorderLayout.SOUTH);
        enrollmentFrame.pack();

        int x = (int) parentGUI.getLocation().getX() + 100;
        int y = (int) parentGUI.getLocation().getY() + 100;
        enrollmentFrame.setLocation(x, y);

    }

    public void display(){
        enrollmentFrame.setVisible(true);
        System.out.println("New Enrollment GUI displayed...");
    }
    @Override
    public void windowClosing(WindowEvent e) {
        disposeGUI();
    }

    private void disposeGUI() {
        enrollmentFrame.dispose();
        System.out.println("New Enrollment GUI disposed...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Cancel")) {
            disposeGUI();
        }
    }
}
