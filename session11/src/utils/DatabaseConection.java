package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConection {
        private final static String DRIVER= "com.mysql.cj.jdbc.Driver";
        private final static String URL = "jdbc:mysql://localhost:3306/medicalAppointmentDB";
        private final static String USERNAME = "root";
        private final static String PASSWORD = "12345678";

        public static Connection openConnection() throws ClassNotFoundException {
                Connection connection;
                try{
                        Class.forName(DRIVER);
                        connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
                }catch(ClassNotFoundException | SQLException e){
                        throw new RuntimeException(e);
                }
                return connection;

        }



}
