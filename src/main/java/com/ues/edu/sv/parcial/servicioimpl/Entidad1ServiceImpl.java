package com.ues.edu.sv.parcial.servicioimpl;

import com.ues.edu.sv.parcial.model.Entidad1;
import com.ues.edu.sv.parcial.repository.Entidad1Repository;
import com.ues.edu.sv.parcial.service.IEntidad1Service;
import com.ues.edu.sv.parcial.service.IReporteServicePDF;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class Entidad1ServiceImpl implements IEntidad1Service {
    private final Entidad1Repository servicioEntidad1;
    private final IReporteServicePDF servicioReporte;

    @Override
    public Entidad1 registrar(Entidad1 obj) {
        return this.servicioEntidad1.save(obj);
    }

    @Override
    public Entidad1 modificar(Entidad1 obj) {
        return this.servicioEntidad1.save(obj);
    }

    @Override
    public List<Entidad1> listar() {
        List<Entidad1> listEntidad1s = this.servicioEntidad1.findAll();
        return listEntidad1s;
    }

    @Override
    public Entidad1 leerPorId(Integer id) {
        return this.servicioEntidad1.findById(id).get();
    }

    @Override
    public boolean eliminar(Entidad1 obj) {
        try {
            this.servicioEntidad1.delete(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //@Override
    //public List<Entidad1> buscarEntidad1(String filtro) {
    //    return this.servicioEntidad1.buscarEntidad1(filtro);
    //}

    @Override
    public void generarReportePorEntidad1(HttpServletResponse response) throws IOException{
        final InputStream stream = this.getClass().getResourceAsStream("/reportes/reporte1.jrxml");
        this.servicioReporte.generarReporte(stream,response,listar());
    }
}
