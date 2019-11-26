package org.senac.controle_financiamento.controller;

import org.senac.controle_financiamento.domain.Area;
import org.senac.controle_financiamento.domain.Projeto;
import org.senac.controle_financiamento.repository.AreaRepository;
import org.senac.controle_financiamento.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private AreaRepository areaRepository;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid Projeto projeto) {
        ResponseEntity<?> responseEntity = validarProjeto(projeto);

        if (responseEntity == null) {
            responseEntity = ResponseEntity.ok(projetoRepository.save(projeto));
        }

        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid Projeto projeto) {
        Projeto projetoPersistido = projetoRepository.findOneByNome(projeto.getNome());

        if (projetoPersistido == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format(" O Projeto %s não esta cadastrado!", projeto.getNome()));
        } else {
            ResponseEntity<?> responseEntity = validarProjeto(projetoPersistido);

            if (responseEntity == null) {
                responseEntity = ResponseEntity.ok(projetoRepository.save(projetoPersistido));
            }

            return responseEntity;
        }
    }

    private ResponseEntity<?> validarProjeto(Projeto projeto) {
        Area area = areaRepository.findByNomeIgnoreCase(projeto.getArea().getNome());
        if (area == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format(" A Área %s não esta cadastrada!", projeto.getArea().getNome()));
        } else {
            projeto.setArea(area);
            if (projeto.getOrcamento().compareTo(area.getOrcamentoMaximo()) == 1 &&
                    projeto.getOrcamento().compareTo(area.getOrcamentoMinimo()) == -1) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("O Orçamento do projeto precisa estar dentro dos limites estabelecidos pela área.");
            }
        }
        return null;
    }
}
