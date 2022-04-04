package com.imaleex.Models.DbModel;

import com.imaleex.Exceptions.DbException;
import com.imaleex.Models.Evento;
import com.imaleex.Utils.Db;

import java.sql.*;

import static com.imaleex.Utils.Db.getRowCount;

/**
 * @author Alex Cortes
 */
public class EventoDAO {


    public static void insertEvento(Evento e) throws DbException {
        Db db = Db.getInstance();
        Connection con = db.getConnection();
        String plantilla = "INSERT INTO eventos (name, location, max_assistants, start_time, end_time) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(plantilla, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getName());
            ps.setString(2, e.getLocation());
            ps.setInt(3, e.getMaxAssistants());
            ps.setTimestamp(4, Timestamp.valueOf(e.getStartTime()));
            ps.setTimestamp(5, Timestamp.valueOf(e.getEndTime()));
            int n = ps.executeUpdate();
            if (n != 1)
                throw new DbException("El número de filas actualizadas no es uno");

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    e.setId(generatedKeys.getInt(1));
                } else {
                    throw new DbException("El ID del evento no es valido");
                }
            }
        } catch (SQLException sqlException) {
            throw new DbException(sqlException.getMessage());
        }

    }


    public static void cancelarEvento(Evento e) throws DbException {
        Db db = Db.getInstance();
        Connection con = db.getConnection();
        String plantilla = "UPDATE eventos SET active = 0 where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1, e.getId());
            int n = ps.executeUpdate();
            if (n != 1)
                throw new DbException("El número de filas actualizadas no es uno");
            e.setActive(false);
        } catch (SQLException sqlException) {
            throw new DbException(sqlException.getMessage());
        }
    }

    public static Evento  queryEvento


    public static void editEvento(Evento e) throws DbException {
        Db db = Db.getInstance();
        Connection con = db.getConnection();
        String plantilla = "UPDATE eventos SET name = ?, location = ?, max_assistants = ? , start_time = ?, end_time = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1, e.getName());
            ps.setString(2, e.getLocation());
            ps.setInt(3, e.getMaxAssistants());
            ps.setTimestamp(4, Timestamp.valueOf(e.getStartTime()));
            ps.setTimestamp(5, Timestamp.valueOf(e.getEndTime()));
            ps.setInt(6, e.getId());
            int n = ps.executeUpdate();
            if (n != 1)
                throw new DbException("El número de filas actualizadas no es uno");
        } catch (SQLException sqlException) {
            throw new DbException(sqlException.getMessage());
        }

    }


    public static Evento buscarEvento(String name) throws DbException {
        Db db = Db.getInstance();
        Connection con = db.getConnection();
        String plantilla = "SELECT * FROM eventos where name = ?";
        try {
            PreparedStatement ps = con.prepareStatement(plantilla, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, name);
            return loadEvento(ps);

        } catch (SQLException sqlException) {
            throw new DbException(sqlException.getMessage());
        }
    }


    public static Evento buscarEvento(int id) throws DbException {
        Db db = Db.getInstance();
        Connection con = db.getConnection();
        String plantilla = "SELECT * FROM eventos where id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(plantilla, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            return loadEvento(ps);
        } catch (SQLException sqlException) {
            throw new DbException(sqlException.getMessage());
        }


    }

    private static Evento loadEvento(PreparedStatement ps) throws SQLException, DbException {
        ResultSet res = ps.executeQuery();
        if (getRowCount(res) > 1) {
            throw new DbException("Ya existe un evento con ese nombre");
        }
        if (res.next()) {
            return new Evento(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("location"),
                    res.getInt("max_assistants"),
                    res.getTimestamp("start_time").toLocalDateTime(),
                    res.getTimestamp("end_time").toLocalDateTime(),
                    res.getBoolean("active")
            );
        } else
            throw new DbException("Evento no encontrado");
    }


}
