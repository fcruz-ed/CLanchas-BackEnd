package com.clanchas.clanchas.controller;

import com.clanchas.clanchas.model.Diario;
import com.clanchas.clanchas.service.DiarioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/diario")
@CrossOrigin(value = "*", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class DiarioContoller {

    private final Log log = LogFactory.getLog(getClass());

    private final DiarioService service;

    @Autowired
    public DiarioContoller(DiarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        log.info("Getting all Diarios...");
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        log.info("Getting Diario with id: " + id);
        Diario diario = service.findById(id);
        if (diario == null) {
            return new ResponseEntity<>("El Diario con el id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(diario);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Diario diario) {
        log.info("Saving new Diario...");
        Diario diarioSave = service.save(diario);
        if (diarioSave == null) {
            return new ResponseEntity<>("No se ha podido crear el Diario, revise los datos mandados.\nRecuerde que solo puede haber un Diario de un día.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(diarioSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid Diario diario) {
        log.info("Updating Diario with id: " + id);
        Diario diarioToUpdate = service.findById(id);
        if (diarioToUpdate == null) {
            return new ResponseEntity<>("No se ha podido crear el Diario, revise los datos mandados.", HttpStatus.BAD_REQUEST);
        }
        diarioToUpdate.setDia(diario.getDia());
        diarioToUpdate.setDescripcion(diario.getDescripcion());
        return new ResponseEntity<>(service.update(diarioToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        log.info("Deleting Diario with id: " + id);
        Diario diario = service.findById(id);
        if (diario == null) {
            return new ResponseEntity<>("El Diario con el id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return ResponseEntity.ok("Se ha eliminado el Diario con id: " + id);
    }

}
