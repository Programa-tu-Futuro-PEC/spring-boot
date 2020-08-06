package com.forge.project.api;

import com.forge.project.jdbc.dao.ActorDAO;
import com.forge.project.jdbc.dao.PeliculaDAO;
import com.forge.project.jdbc.dto.Actor;
import com.forge.project.jdbc.dto.Pelicula;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActorResource {

    @RequestMapping(method = RequestMethod.POST, value = "/actor/")
    public Actor createPelicula(@RequestBody Actor p) throws SQLException {
        new ActorDAO().insertarActor(p);
        return p;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/actor/{nombre}")
    public void editarPelicula(@PathVariable("nombre") String nombre,
                               @RequestBody Actor p) throws SQLException {
        new ActorDAO().editarActor(nombre, p);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/actor/{nombre}")
    public List<Actor> getPeliculasByNombreLike(@PathVariable("nombre") String nombre)
            throws SQLException {
        List<Actor> peliculas = new ActorDAO()
                .obtenerActorPorNombreLike("%"+nombre+"%");
        return peliculas;
    }

}
