package a2_1901040156;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddModule extends WindowAdapter implements ActionListener {
    private JFrame moduleFrame;
    private JFrame parentGUI;
    private JComboBox comboBox;
    private CourseManProg courseManProg;
    private JTextField txtName;
    private JTextField txtSemester;
    private JTextField txtCredit;

    public AddModule(JFrame parentGUI, CourseManProg courseManProg) {
        this.parentGUI = parentGUI;
        this.courseManProg = courseManProg;
        createGUI();
    }

    private void createGUI() {
        moduleFrame = new JFrame("Add Module");
        moduleFrame.addWindowListener(this);

        // center panel
        JPanel panCenter = new JPanel(new GridLayout(4, 2, 5, 10));
        panCenter.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        panCenter.add(new JLabel("Name"));
        txtName = new JTextField(15);
        panCenter.add(txtName);

        panCenter.add(new JLabel("Semester"));
        txtSemester = new JTextField(15);
        panCenter.add(txtSemester);
        String module[] = {"Compulsory module", "Elective module"};
        comboBox = new JComboBox<>(module);
        comboBox.setBounds(100, 50,150,20);
        panCenter.add(new JLabel("Module"));
        panCenter.add(comboBox);

        panCenter.add(new JLabel("Credit"));
        txtCredit = new JTextField(15);
        panCenter.add(txtCredit);

        moduleFrame.add(panCenter);

        // bottom
        JPanel pnlBottom = new JPanel();

        JButton btnModule = new JButton("Add Module");
        btnModule.addActionListener(this);
        pnlBottom.add(btnModule);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        pnlBottom.add(btnCancel);

        pnlBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        moduleFrame.add(pnlBottom, BorderLayout.SOUTH);
        moduleFrame.pack();

        int x = (int) parentGUI.getLocation().getX() + 100;
        int y = (int) parentGUI.getLocation().getY() + 100;
        moduleFrame.setLocation(x, y);
    }

    public void display() {
        moduleFrame.setVisible(true);
        System.out.println("Add Module GUI displayed...");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        disposeGUI();
    }

    private void disposeGUI() {
        txtName.setText("");
        txtSemester.setText("");
        txtCredit.setText("");
        moduleFrame.dispose();
        System.out.println("Add Module GUI disposed...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Cancel")) {
            disposeGUI();
        } else if (command.equals("Add Module")) {
            courseManProg.addModule(txtName.getText(), Integer.parseInt(txtSemester.getText()), Integer.parseInt(txtCredit.getText()));
            System.out.println("Add Successfully");
            disposeGUI();
        }
    }
}