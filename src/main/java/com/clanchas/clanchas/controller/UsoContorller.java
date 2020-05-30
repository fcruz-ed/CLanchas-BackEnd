package com.clanchas.clanchas.controller;

import com.clanchas.clanchas.model.Uso;
import com.clanchas.clanchas.service.UsoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/uso")
@CrossOrigin(value = "http://localhost:8080", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UsoContorller {

    private final Log log = LogFactory.getLog(getClass());

    private final UsoService service;

    @Autowired
    public UsoContorller(UsoService usoService) {
        this.service = usoService;
    }

    @GetMapping
    public ResponseEntity<List<Uso>> findAll() {
        log.info("Getting all Usos...");
        return new ResponseEntity<>(service.findAll() ,HttpStatus.OK);
    }

    @GetMapping("/de-renta-activa")
    public ResponseEntity<List<Uso>> findUsosDeRentaActiva() {
        log.info("Getting all Usos where renta is Activa...");
        return new ResponseEntity<>(service.findUsosDeRentaActiva(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        log.info("Getting Uso with id: " + id);
        Uso uso = service.findById(id);
        if (uso == null) {
            return new ResponseEntity<>("El Uso con id: "+id+" no ha sido encontrado.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(uso, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Uso uso) {
        log.info("Creating new Uso...");
        Uso usoSave = service.save(uso);
        if (usoSave == null) {
            return new ResponseEntity<>("No se ha podido crear el nuevo Uso, revise los datos mandados.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usoSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid Uso uso) {
        log.info("Updating Uso with id: " + id);
        Uso usoToUpdate = service.findById(id);
        if (uso == null) {
            return new ResponseEntity<>("El Uso con id: "+id+" no ha sido encontrado.", HttpStatus.NOT_FOUND);
        }
        usoToUpdate.setRenta_id(uso.getRenta_id());
        usoToUpdate.setTiempo(uso.getTiempo());
        usoToUpdate.setPrecio(uso.getPrecio());
        return new ResponseEntity<>(service.update(usoToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        log.info("Deleting Uso with id: " + id);
        Uso uso = service.findById(id);
        if (uso == null) {
            return new ResponseEntity<>("El Uso con id: "+id+" no ha sido encontrado.", HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>("Se ha eliminado el Uso con id: " + id, HttpStatus.OK);
    }

}
