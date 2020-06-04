package com.clanchas.clanchas.controller;

import com.clanchas.clanchas.model.Persona;
import com.clanchas.clanchas.service.PersonaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lista-personas")
@CrossOrigin(value = "*", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PersonaController {

    private final Log log = LogFactory.getLog(getClass());

    private final PersonaService service;

    @Autowired
    public PersonaController(PersonaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        log.info("Getting Lista Personas.");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        log.info("Getting persona with id: " + id);
        Persona persona = service.findById(id);
        if(persona == null) {
            return new ResponseEntity<>("Persona con id: "+id+" no encontrada.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Persona persona) {
        log.info("Saving new pesona...");
        Persona personaSave = service.save(persona);
        if(personaSave == null) {
            return new ResponseEntity<>("La persona no se ha podido crear", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePersona(@PathVariable("id") Long id, @RequestBody @Valid Persona persona) {
        log.info("Updating persona with id: "+id);
        Persona personaToUpdate = service.findById(id);
        if(personaToUpdate == null) {
            return new ResponseEntity<>("Persona con id: "+id+" no encontrada.", HttpStatus.NOT_FOUND);
        }
        personaToUpdate.setNombre(persona.getNombre());
        return new ResponseEntity<>(service.update(personaToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        log.info("Deleting persona with id: "+id);
        Persona persona = service.findById(id);
        if (persona == null) {
            return new ResponseEntity<>("Persona con id: "+id+" no encontrada.", HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>("La Persona con id: "+id+" ha sido eliminada.", HttpStatus.OK);
    }
}
