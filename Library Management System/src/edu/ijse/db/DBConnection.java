package edu.ijse.db;

import edu.ijse.dto.Admin;

import java.util.ArrayList;

public class DBConnection {
    public static ArrayList<Admin> userTable = new ArrayList<>();

    static {
        userTable.add(new Admin(
                1,
                "a",
                "1"
        ));
    }

}
