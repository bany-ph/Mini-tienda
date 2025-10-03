package org.app.config;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConfig {
    private static Connection connection = null;
    private static Properties properties ;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        properties = new Properties();
        try(InputStream input = DataBaseConfig.class.getClassLoader().getResourceAsStream("config.properties")){
            if(input == null){
                throw new RuntimeException("File not found");
            }

            properties.load(input);
            driver = properties.getProperty("db.driver");
            // build url
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            Class.forName(driver); // load the driver in memory.
        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException("Error downloading properties");
        }
    }

    public static Connection getConnection() throws SQLException {
        try{
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Data Base connected → " + connection.toString());
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return connection;
    }
}
