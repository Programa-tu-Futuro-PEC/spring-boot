package com.forge.project.api;

import com.forge.project.jdbc.dao.PeliculaDAO;
import com.forge.project.jdbc.dto.Pelicula;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
