package com.esserver.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection = null;
    private Connection conn;

    private DBConnection(){
        Properties props = new Properties();

        try {
            FileInputStream in = new FileInputStream("path/to/database.properties");
            props.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance(){
        if(dbConnection ==  null){
            return new DBConnection();
        } else return dbConnection;
    }

    public Connection getConn() {
        return conn;
    }
}
