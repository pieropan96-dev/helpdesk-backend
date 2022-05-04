package com.pieropan.helpdesk.controller;

import com.pieropan.helpdesk.dominio.Chamado;
import com.pieropan.helpdesk.dominio.dtos.ChamadoDto;
import com.pieropan.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(chamado));
    }

    @GetMapping()
    public ResponseEntity<List<ChamadoDto>> findAll() {
        List<Chamado> chamados = chamadoService.findAll();
        List<ChamadoDto> chamadoDtos = chamados.stream().map(obj -> new ChamadoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(chamadoDtos);
    }
}