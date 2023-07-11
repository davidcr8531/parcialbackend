package com.ues.edu.sv.parcial.controller;
import com.ues.edu.sv.parcial.model.Entidad1;
import com.ues.edu.sv.parcial.repository.Entidad1Repository;
import com.ues.edu.sv.parcial.service.IEntidad1Service;
import com.ues.edu.sv.parcial.servicioimpl.ReportesServiceEXCELImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/entidad1")
@CrossOrigin(origins = "*")
public class Entidad1Controller {

    private final ReportesServiceEXCELImpl reportesServiceEXCELImpl;
    private final IEntidad1Service entidad1Service;
    private final Entidad1Repository entidad1Repository;

    public Entidad1Controller(ReportesServiceEXCELImpl reportesServiceEXCELImpl, IEntidad1Service entidad1Service1, Entidad1Repository entidad1Repository) {
        this.reportesServiceEXCELImpl = reportesServiceEXCELImpl;
        this.entidad1Service = entidad1Service1;
        this.entidad1Repository = entidad1Repository;
    }

    @GetMapping
    public ResponseEntity<Page<Entidad1>> listarEntidad1s(Pageable pageable) {
        return ResponseEntity.ok(entidad1Repository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Entidad1> guardarEntidad1(@Valid @RequestBody Entidad1 entidad1) {
        Entidad1 entidad1Guardado = entidad1Repository.save(entidad1);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(entidad1Guardado.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(entidad1Guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entidad1> actualizarEntidad1(@Valid @RequestBody Entidad1 entidad1, @PathVariable int id) {
        Optional<Entidad1> entidad1Optional = entidad1Repository.findById(id);
        if (entidad1Optional.isPresent()) {
            entidad1.setId(entidad1Optional.get().getId());
            entidad1Repository.save(entidad1);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entidad1> eliminarEntidad1(@PathVariable Integer id) {
        Optional<Entidad1> entidad1Optional = entidad1Repository.findById(id);
        if (entidad1Optional.isPresent()) {
            entidad1Repository.delete(entidad1Optional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entidad1> obtenerEntidad1PorId(@PathVariable Integer id) {
        Optional<Entidad1> entidad1Optional = entidad1Repository.findById(id);
        if (entidad1Optional.isPresent()) {
            return ResponseEntity.ok(entidad1Optional.get());
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping(value = "/pdf")
    public void listConsultaMedicasPorEspecialidadPdf(ModelAndView model, HttpServletResponse response) throws IOException {
        this.entidad1Service.generarReportePorEntidad1(response);
    }

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pacientes.xls";
        response.setHeader(headerKey, headerValue);
        reportesServiceEXCELImpl.generateExcel(response);
        response.flushBuffer();
    }
}
