package com.ues.edu.sv.parcial.service;

import com.ues.edu.sv.parcial.model.Entidad1;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

public interface IEntidad1Service extends ICRUD<Entidad1>{
    void generarReportePorEntidad1(HttpServletResponse response) throws IOException;
}
