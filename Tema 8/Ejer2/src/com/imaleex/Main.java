package com.imaleex;

import com.imaleex.Exceptions.DbException;
import com.imaleex.Models.DbModel.EventoDAO;
import com.imaleex.Models.Evento;
import com.imaleex.Utils.Db;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        setupDb();

        Evento e = new Evento(
                "Prueba",
                "Aula 33",
                10,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(new Long("1647260461000")), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(new Long("1647265561000")), ZoneId.systemDefault())
                );

        try {
            EventoDAO.insertEvento(e);
            System.out.println(EventoDAO.buscarEvento(e.getId()).getName());
            e.setLocation("Aula 34");
            EventoDAO.editEvento(e);
            System.out.println(EventoDAO.buscarEvento(e.getId()).getLocation());
            EventoDAO.cancelarEvento(e);
            System.out.println(EventoDAO.buscarEvento(e.getId()).isActive());
            System.out.println(EventoDAO.buscarEvento(e.getName()).getLocation());
        } catch (DbException ex) {
            ex.printStackTrace();
        }
    }


    private static void setupDb(){
        try {
            Db.setDbParams("root", null, "tema8", "localhost");
            Db db = Db.getInstance();
            System.out.println("Conectado  con exito a la base de datos "+ db.getConnection().getCatalog());
        } catch (DbException e) {
            System.out.println(" Problemas con la base de datos " + e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
