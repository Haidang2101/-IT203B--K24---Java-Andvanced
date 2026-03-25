package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/bank_db";
    private final static String USER = "root";
    private final static String PASSWORD = "123456";
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName(DRIVER);
            con= DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public static void closeConnection( ){
        Connection con=null;
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {}
        }
    }
}
