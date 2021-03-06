package com.imaleex.Utils;

import com.imaleex.Exceptions.DbException;

import java.sql.*;

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

    // Private constructor to prevent instances (singleton)
    private Db(String dbUser, String dbPassword, String dbName, String dbHost, int dbType) throws SQLException, ClassNotFoundException, DbException {
        switch (dbType){
            case 1:
                this.connection = startMysqlConnection(dbUser, dbPassword, dbName, dbHost);
                break;
            case 2:
                this.connection = startOracleConnection(dbUser, dbPassword, dbName, dbHost);
                break;
            default:
                throw new DbException("The db type is not supported");

        }
    }

    private Connection startMysqlConnection(String dbUser, String dbPassword, String dbName, String dbHost) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://"+dbHost +":3306/"+dbName;
        return DriverManager.getConnection (url , dbUser, dbPassword);
    }

    private Connection startOracleConnection(String dbUser, String dbPassword, String dbName, String dbHost) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@"+dbHost +":1521:"+dbName;
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
    public static Db getInstance(int dbType) throws DbException {
        try{
            if (instance == null )
                if ((dbUser != null ||  dbName != null || dbHost != null))
                    instance = new Db(dbUser, dbPassword, dbName, dbHost, dbType);
                else
                    throw new DbException("The db parameters are not correctly setted");

            return instance;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            throw new DbException("The db connector could not connect with the database, the Db object has not been created");
        } catch (ClassNotFoundException classNotFoundException){
            throw new DbException("The db connector class could not be loaded, check the project modules and jre folder");
        }
    }

    public static int getRowCount(ResultSet resultSet) {
        if (resultSet == null){
            return 0;
        }
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }

        return 0;
    }

}
