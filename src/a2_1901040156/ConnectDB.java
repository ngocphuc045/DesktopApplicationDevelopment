package a2_1901040156;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    static Connection connectDB() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:src/a2_1901040156/database.sqlite3";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to database!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
