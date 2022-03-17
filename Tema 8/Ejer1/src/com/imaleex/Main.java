package com.imaleex;

import com.imaleex.Exceptions.DbException;
import com.imaleex.Utils.Db;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Db.setDbParams("root", null, "prueba", "localhost");
            Db db = Db.getInstance();
            Connection conn = db.getConnection();
        } catch (DbException e) {
            System.out.println(" Problemas con la base de datos " + e.getMessage());
        }
    }
}
