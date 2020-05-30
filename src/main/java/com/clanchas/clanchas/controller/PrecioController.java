package com.clanchas.clanchas.controller;

import com.clanchas.clanchas.model.Precio;
import com.clanchas.clanchas.service.PrecioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/precios")
@CrossOrigin(value = "http://localhost:8080", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PrecioController {

    private final Log log = LogFactory.getLog(getClass());

    private final PrecioService service;

    @Autowired
    public PrecioController(PrecioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Precio>> findAll() {
        log.info("Getting all Precios...");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/activo")
    public ResponseEntity<List<Precio>> findPreciosActivos() {
        log.info("Getting all Precios Activos...");
        return new ResponseEntity<>(service.findPreciosActivos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        log.info("Getting Precio with id: " + id + "...");
        Precio precio = service.findById(id);
        if(precio == null) {
            return new ResponseEntity<>("Precio con id: " + id + " no encontrado.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(precio, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Precio precio) {
        log.info("Saving new Precio..."+precio);
        Precio precioSave = service.save(precio);
        if(precioSave == null) {
            return new ResponseEntity<>("No se ha podido crear el Precio, revise los datos mandados.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(precioSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid Precio precio) {
        log.info("Updating Precio with id: " + id + "...");
        Precio precioToUpdate = service.findById(id);
        if(precio == null) {
            return new ResponseEntity<>("Precio con id: " + id + " no encontrado.", HttpStatus.NOT_FOUND);
        }
        precioToUpdate.setTiempo(precio.getTiempo());
        precioToUpdate.setPrecio(precio.getPrecio());
        precioToUpdate.setEstado(precio.getEstado());
        return new ResponseEntity<>(service.update(precioToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        log.info("Deleting Precio with id: " + id);
        Precio precio = service.findById(id);
        if(precio == null) {
            return new ResponseEntity<>("Precio con id: " + id + " no encontrado.", HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>("Precio con id: "+id+" ha sido eliminado.", HttpStatus.OK);
    }

}
