package com.forge.project.jdbc.dao;

import com.forge.project.jdbc.ConnectionManager;
import com.forge.project.jdbc.dto.Pelicula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PeliculaDAO {

    private Connection connection;

    public PeliculaDAO() throws SQLException {
        connection = ConnectionManager.obtenerConexion();
    }

    public List<Pelicula> obtenerPeliculaPorNombre(String nombre) throws SQLException {
        String sql = "select nombre, año, calificacion from pelicula where nombre like ?";
        return obtenerResultados(sql, nombre);
    }

    public List<Pelicula> obtenerPeliculasPorActor(String nombreActor) throws SQLException {
        String sql = "select distinct nombre, año, calificacion " +
                "from personaje, pelicula " +
                "where año=p_año and nombre=p_nombre and a_nombre=?";
        return obtenerResultados(sql, nombreActor);
    }

    public List<Pelicula> obtenerPeliculaPorActorLike(String actorLike) throws SQLException {
        String sql = "select distinct nombre, año, calificacion " +
                "from personaje, pelicula " +
                "where año=p_año and nombre=p_nombre and a_nombre like ?";
        return obtenerResultados(sql, "%"+actorLike+"%");
    }

    public List<Pelicula> obtenerPeliculasPorPersonaje(String personaje) throws SQLException {
        String sql = "select distinct nombre, año, calificacion " +
                "from personaje, pelicula " +
                "where año=p_año and nombre=p_nombre and personaje like ?";
        return obtenerResultados(sql, personaje);
    }

    private List<Pelicula> obtenerResultados(String sql, String parametro) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, parametro);
        ResultSet rs = ps.executeQuery();
        List<Pelicula> peliculas = new LinkedList<>();
        while (rs.next()){
            Pelicula p = new Pelicula(
                    rs.getString("nombre"),
                    rs.getInt("año"),
                    rs.getDouble("calificacion")
            );
            peliculas.add(p);
        }
        return peliculas;
    }

    public Map<String, Integer> contarActoresPorPelicula() throws SQLException {
        String sql = "select p_nombre, COUNT(distinct a_nombre) as cuenta " +
                     "from personaje " +
                     "group by p_nombre";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Map<String, Integer> resultado = new HashMap<>();
        while (rs.next()){
            resultado.put(
                    rs.getString("p_nombre"),
                    rs.getInt("cuenta")
            );
        }
        return resultado;
    }

    public void insertarPelicula(Pelicula p) throws SQLException {
        String sql = "insert into pelicula(nombre, año, calificacion) " +
                "values (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setInt(2, p.getAnho());
        ps.setDouble(3, p.getCalificacion());
        ps.executeUpdate();
    }

    public void borrarPelicula(Pelicula p) throws SQLException {
        String sql = "delete from pelicula where " +
                "nombre=? and año=? and calificacion=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setInt(2, p.getAnho());
        ps.setDouble(3, p.getCalificacion());
        ps.executeUpdate();
    }

    public void editarPelicula(String nombre, int anho, Pelicula p) throws SQLException {
        String sql = "update pelicula " +
                "set nombre=?, año=?, calificacion=? " +
                "where nombre=? and año=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setInt(2, p.getAnho());
        ps.setDouble(3, p.getCalificacion());
        ps.setString(4, nombre);
        ps.setInt(5, anho);
        ps.executeUpdate();
    }
}
