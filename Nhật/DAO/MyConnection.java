package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

public class MyConnection {
    public static Connection getConnection(){
        Connection c = null;
        try{
            String url = "jdbc:mySQL://localhost:3306/tour";
            String username = "root";
            String password = "123456";

            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return c;
    }

    public static void closeConnection(Connection c){
        try {
            if(c != null)
                c.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
