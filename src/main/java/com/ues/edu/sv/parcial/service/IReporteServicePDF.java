package com.ues.edu.sv.parcial.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IReporteServicePDF<T>{
    void generarReporte(InputStream stream, HttpServletResponse response, List<T> data) throws IOException;
}
