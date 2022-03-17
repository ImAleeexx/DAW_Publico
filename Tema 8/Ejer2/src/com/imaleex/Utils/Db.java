package com.imaleex.Utils;

import com.imaleex.Exceptions.DbException;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Db {
    // Static var used for storing the single instance allowed for this class
    private static Db instance = null;
    private static String dbUser, dbPassword, dbName, dbHost = null;

    public Connection getConnection() {
        return connection;
    }

    //Instance vars
    private Connection connection = null;

    // Private constructor to prevent instances
    private Db(String dbUser, String dbPassword, String dbName, String dbHost) throws SQLException, ClassNotFoundException {
        this.connection = startConnection(dbUser,dbPassword,dbName,dbHost);
    }

    private Connection startConnection(String dbUser, String dbPassword, String dbName, String dbHost) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://"+dbHost +":3306/"+dbName;
        return DriverManager.getConnection (url , dbUser, dbPassword);
    }


    public static void setDbParams(String dbUser, String dbPassword, String dbName, String dbHost){
        Db.dbHost = dbHost;
        Db.dbPassword = dbPassword;
        Db.dbName = dbName;
        Db.dbUser = dbUser;
    }


    private  boolean dbTestConnection(){
        try {
            if (!this.connection.isClosed()){
                return true;
            } else{
                return false;
            }
        } catch (SQLException e) {

            e.printStackTrace();
            return  false;
        }

    }

    // Static method that returns real unique instance of db class
    public static Db getInstance() throws DbException {
        try{
            if (instance == null )
                if ((dbUser != null ||  dbName != null || dbHost != null))
                    instance = new Db(dbUser, dbPassword, dbName, dbHost);
                else
                    throw new DbException("The db parameters are not correctly setted");

            return instance;
        } catch (SQLException sqlException){
            throw new DbException("The db connector could not connect with the database, the Db object has not been created");
        } catch (ClassNotFoundException classNotFoundException){
            throw new DbException("The db connector class could not be loaded, check the project modules and jre folder");
        }
    }

}
