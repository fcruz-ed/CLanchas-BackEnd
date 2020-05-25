package com.clanchas.clanchas.controller;

import com.clanchas.clanchas.model.Renta;
import com.clanchas.clanchas.service.RentaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/renta")
@CrossOrigin(value = "http://localhost:8080", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class RentaController {

    private final Log log = LogFactory.getLog(getClass());

    private final RentaService rentaService;

    @Autowired
    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    @GetMapping
    public ResponseEntity<List<Renta>> findAll() {
        log.info("Getting all Rentas...");
        return new ResponseEntity<>(rentaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/activo")
    public ResponseEntity<List<Renta>> findRentasActivas() {
        log.info("Getting all Rentas Activas...");
        return new ResponseEntity<>(rentaService.findRentasActivas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        log.info("Getting Renta with id: " + id);
        Renta renta = rentaService.findById(id);
        if(renta == null) {
            return new ResponseEntity<>("No se ha encontrado la Renta con el id: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(renta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Renta renta) {
        log.info("Creating new Renta...");
        Renta rentaSave = rentaService.save(renta);
        if(renta == null) {
            return new ResponseEntity<>("No se ha podido crear la nueva renta...\nCompruebe los datos mandados.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rentaSave, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid Renta renta) {
        log.info("Updating Renta with id: " + id);
        Renta rentaToUpdate = rentaService.findById(id);
        if(rentaToUpdate == null) {
            return new ResponseEntity<>("No se ha encontrado la Renta con el id: " + id, HttpStatus.NOT_FOUND);
        }
        rentaToUpdate.setLancha_id(renta.getLancha_id());
        rentaToUpdate.setEn_uso(renta.isEn_uso());
        rentaToUpdate.setC_adultos(renta.getC_adultos());
        rentaToUpdate.setC_jovenes(renta.getC_jovenes());
        rentaToUpdate.setObservaciones(renta.getObservaciones());
        return new ResponseEntity<>(rentaService.update(rentaToUpdate), HttpStatus.OK);
    }

    @PutMapping("/update-uso/{id},{en_uso}")
    public ResponseEntity<Object> updateUso(@PathVariable("id") Long id, @PathVariable("en_uso") boolean uso) {
        Renta renta = rentaService.findById(id);
        if(renta == null) {
            return new ResponseEntity<>("No se ha encontrado la Renta con el id: " + id, HttpStatus.NOT_FOUND);
        }
        renta.setEn_uso(uso);
        renta = rentaService.updateUso(id, uso);
        return new ResponseEntity<>(renta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        log.info("Deleting Renta with id: " + id);
        Renta renta = rentaService.findById(id);
        if(renta == null) {
            return new ResponseEntity<>("No se ha encontrado la renta con el id: " + id, HttpStatus.NOT_FOUND);
        }
        rentaService.delete(id);
        return new ResponseEntity<>("Renta con id: " + id + " ha sido eliminada.", HttpStatus.OK);
    }

    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
        log.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }
}
