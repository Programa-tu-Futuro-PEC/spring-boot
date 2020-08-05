package com.forge.project.api;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaisResource {

    static List<String> paises = new ArrayList<>();
    static Map<String, Integer> prueba = new HashMap<>();
    static {
        paises.add("Chile"); paises.add("Colombia");
        paises.add("Bolivia"); paises.add("Ecuador");
        prueba.put("uno", 1); prueba.put("dos", 2);
        prueba.put("tres", 3); prueba.put("cuatro", 4);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pais/")
    public List<String> getPaises(){
        return paises;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pais/")
    public void addPais(@RequestBody String s){
        paises.add(s);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pais/{id}")
    public void deletePais(@PathVariable("id") int i){
        paises.remove(i);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pais/{id}")
    public String getPais(@PathVariable("id") int i){
        return paises.get(i);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/numero/")
    public Map<String, Integer> getNumeros(){
        return prueba;
    }

}
