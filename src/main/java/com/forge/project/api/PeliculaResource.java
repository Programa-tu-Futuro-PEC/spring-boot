package com.forge.project.api;

import com.forge.project.jdbc.dao.PeliculaDAO;
import com.forge.project.jdbc.dto.Pelicula;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PeliculaResource {

    @RequestMapping(method = RequestMethod.GET, value = "/pelicula/{nombre}")
    public List<Pelicula> getPeliculasByNombreLike(@PathVariable("nombre") String nombre)
            throws SQLException {
        List<Pelicula> peliculas = new PeliculaDAO()
                .obtenerPeliculaPorNombre("%"+nombre+"%");
        return peliculas;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pelicula/actor/{nombre}")
    public List<Pelicula> getPeliculasByNombreActorLike(@PathVariable("nombre") String nombre)
            throws SQLException {
        List<Pelicula> peliculas = new PeliculaDAO()
                .obtenerPeliculaPorActorLike("%"+nombre+"%");
        return peliculas;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pelicula/personaje/{nombre}")
    public List<Pelicula> getPeliculasByNombrePersonajeLike(@PathVariable("nombre") String nombre)
            throws SQLException {
        List<Pelicula> peliculas = new PeliculaDAO()
                .obtenerPeliculasPorPersonaje("%"+nombre+"%");
        return peliculas;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pelicula/")
    public Pelicula createPelicula(@RequestBody Pelicula p) throws SQLException {
        new PeliculaDAO().insertarPelicula(p);
        return p;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pelicula/")
    public void borrarPelicula(@RequestBody Pelicula p) throws SQLException {
        new PeliculaDAO().borrarPelicula(p);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/pelicula/{nombre}/{anho}")
    public void editarPelicula(@PathVariable("nombre") String nombre,
                               @PathVariable("anho") int anho,
                               @RequestBody Pelicula p) throws SQLException {
        new PeliculaDAO().editarPelicula(nombre, anho, p);
    }


}
