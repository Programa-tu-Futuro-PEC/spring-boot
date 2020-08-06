package com.forge.project.jdbc.dao;

import com.forge.project.jdbc.ConnectionManager;
import com.forge.project.jdbc.dto.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ActorDAO {
    private Connection connection;

    public ActorDAO() throws SQLException {
        this.connection = ConnectionManager.obtenerConexion();
    }

    public Actor obtenerActorPorNombre(String nombre) throws SQLException {
        String sql = "select nombre, genero from actor where nombre=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            Actor a = new Actor(rs.getString("nombre"), rs.getString("genero"));
            return a;
        }
        return new Actor("", " ");
    }


    public List<Actor> obtenerActorPorNombreLike(String nombre) throws SQLException {
        String sql = "select nombre, genero from actor where nombre like ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, nombre);
        List<Actor> resultado = new LinkedList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Actor a = new Actor(rs.getString("nombre"), rs.getString("genero"));
            resultado.add(a);
        }
        return resultado;
    }

    public void insertarActor(Actor A) throws SQLException {
        String sql = "insert into actor(nombre, genero) values (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, A.getNombre());
        ps.setString(2, ""+A.getGenero());
        ps.executeUpdate();
    }

    public void editarActor(String nombre, Actor p) throws SQLException {
        String sql = "update actor " +
                "set nombre=?, genero=? " +
                "where nombre=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getGenero()+"");
        ps.setString(3, nombre);
        ps.executeUpdate();
    }
}
