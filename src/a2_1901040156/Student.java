package a2_1901040156;

import javax.swing.*;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Student {
    private JFrame gui;
    private AddStudent addGUI;
    private String id;
    private String name;
    private String dob;
    private String address;
    private String email;

    public Student(String id, String name, String dob, String address, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    private boolean validateName(String name) {
        return name.length() > 0;
    }

    private boolean validateDob(String dob) {
        return dob.length() > 0;
    }

    private boolean validateAddress(String address) {
        return address.length() > 0;
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        return emailPattern.matcher(email).matches();
    }

}