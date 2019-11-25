package org.senac.controle_financiamento.controller;

import org.senac.controle_financiamento.domain.Area;
import org.senac.controle_financiamento.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaRepository repository;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid Area area) {
        if (repository.findByNome(area.getNome()) == null) {
            return ResponseEntity.ok(repository.save(area));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format(" A Área %s já foi cadastrada", area.getNome()));
        }
    }
}
