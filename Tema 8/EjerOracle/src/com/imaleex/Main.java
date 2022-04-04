package com.imaleex;

import com.imaleex.Exceptions.DbException;
import com.imaleex.Utils.Db;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        setupDb();

        dbTest1();
        dbTest2();
        dbTest3("REY");


    }


    private static void dbTest1(){
        System.out.println("Iniciando prueba 1 de la base de datos");
        try {
            Db db = Db.getInstance(2);
            Connection conn = db.getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call datos_emp32_scott(?)}");
            callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rs = (ResultSet) callableStatement.getObject(1);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }


    private static void dbTest2(){
        System.out.println("Iniciando prueba 2 de la base de datos");
        try {
            Db db = Db.getInstance(2);
            Connection conn = db.getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call datos_emp32_scott_v2(?,?)}");
            callableStatement.setInt(1, 2500);
            callableStatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rs = (ResultSet) callableStatement.getObject(2);
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dbTest3(String name){
        System.out.println("Iniciando prueba 3 de la base de datos");
        try {
        Db db = Db.getInstance(2);
        Connection con = db.getConnection();
        String plantilla = "SELECT * FROM EMPLE where APELLIDO = ?";

            PreparedStatement ps = con.prepareStatement(plantilla, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

        } catch (SQLException | DbException sqlException) {
            sqlException.printStackTrace();
        }
    }


    private static void setupDb(){
        try {
            Db.setDbParams("daw32", "daw32", "orcl", "172.20.225.114");
            Db db = Db.getInstance(2);
            System.out.println("Conectado  con exito a la base de datos "+ ((db.getConnection().getCatalog() != null) ? db.getConnection().getCatalog() : ""));
        } catch (DbException e) {
            System.out.println(" Problemas con la base de datos " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
