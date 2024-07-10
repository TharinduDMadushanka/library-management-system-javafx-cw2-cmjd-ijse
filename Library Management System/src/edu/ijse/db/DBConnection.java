package edu.ijse.db;

import edu.ijse.dto.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Thariya920@");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public static ArrayList<Admin> userTable = new ArrayList<>();
    static {
        userTable.add(new Admin(
                "Tharindu",
                "1",
                "tharindu@gmail.com",
                "a"
        ));
    }

}
