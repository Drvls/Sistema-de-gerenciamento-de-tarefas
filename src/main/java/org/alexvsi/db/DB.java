package org.alexvsi.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    public static Connection getRootConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/?useSSL=false";
        Properties props = new Properties();
        try(FileInputStream fs = new FileInputStream("db.properties")){
            props.load(fs);
        }
        catch (IOException e){
            throw new DbException(e.getMessage());
        }
        return DriverManager.getConnection(url, props);
    }

    public static Connection getConnection(){
        Connection connection;
        try{
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            connection = DriverManager.getConnection(url, props);
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return connection;
    }

    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }
}
