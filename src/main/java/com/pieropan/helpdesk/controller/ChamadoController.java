package com.pieropan.helpdesk.controller;

import com.pieropan.helpdesk.dominio.Chamado;
import com.pieropan.helpdesk.dominio.Cliente;
import com.pieropan.helpdesk.dominio.dtos.ChamadoDto;
import com.pieropan.helpdesk.dominio.dtos.ClienteDto;
import com.pieropan.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto chamadoDto) {
        Chamado chamado = chamadoService.create(chamadoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDto chamadoDto) {
        Chamado chamado = chamadoService.update(id, chamadoDto);
        return ResponseEntity.ok().body(new ChamadoDto(chamado));
    }
}