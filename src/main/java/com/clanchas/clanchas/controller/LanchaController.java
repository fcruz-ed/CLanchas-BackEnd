package com.clanchas.clanchas.controller;

import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.service.LanchaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lancha")
@CrossOrigin(value = "http://localhost:8080", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class LanchaController {

    private final Log log = LogFactory.getLog(getClass());

    private final LanchaService lanchaService;

    @Autowired
    public LanchaController(LanchaService lanchaService) {
        this.lanchaService = lanchaService;
    }

    @GetMapping
    public ResponseEntity<List<Lancha>> findAll() {
        log.info("Getting all Lanchas.");
        return new ResponseEntity<>(lanchaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        log.info("Getting lancha with id: " + id);
        Lancha lancha = lanchaService.findById(id);
        if(lancha == null) {
            return new ResponseEntity<>("Lancha con id: "+id+" no encontrada.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lancha, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Lancha lancha) {
        log.info("Saving new lancha...");
        Lancha lanchaSave = lanchaService.save(lancha);
        if(lanchaSave == null) {
            return new ResponseEntity<>("La lancha no se ha podido crear, no puede haber dos lanchas con 'numero': "
                    +lancha.getNumero()+", revise los datos mandados.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lanchaSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLancha(@PathVariable("id") Long id, @RequestBody @Valid Lancha lancha) {
        log.info("Updating lancha with id: "+id);
        Lancha lanchaToUpdate = lanchaService.findById(id);
        if(lanchaToUpdate == null) {
            return new ResponseEntity<>("Lancha con id: "+id+" no encontrada.", HttpStatus.NOT_FOUND);
        }
        lanchaToUpdate.setNumero(lancha.getNumero());
        lanchaToUpdate.setNombre(lancha.getNombre());
        lanchaToUpdate.setEstado(lancha.getEstado());
        return new ResponseEntity<>(lanchaService.update(lanchaToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        log.info("Deleting lancha with id: "+id);
        Lancha lancha = lanchaService.findById(id);
        if (lancha == null) {
            return new ResponseEntity<>("Lancha con id: "+id+" no encontrada.", HttpStatus.NOT_FOUND);
        }
        lanchaService.delete(id);
        return new ResponseEntity<>("Lancha con id: "+id+" ha sido eliminada.", HttpStatus.OK);
    }

    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
        log.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }
}
