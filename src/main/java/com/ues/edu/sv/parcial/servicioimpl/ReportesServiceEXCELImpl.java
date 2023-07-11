package com.ues.edu.sv.parcial.servicioimpl;

import com.ues.edu.sv.parcial.model.Entidad1;
import com.ues.edu.sv.parcial.repository.Entidad1Repository;
import com.ues.edu.sv.parcial.service.IReporteServiceEXCEL;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportesServiceEXCELImpl implements IReporteServiceEXCEL {
    @Autowired
    private Entidad1Repository entidad1Repo;

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Pacientes");
        Row filaTitulo = sheet.createRow(0);
        Cell celda = filaTitulo.createCell(1);
        celda.setCellValue("LISTADO GENERAL DE PACIENTES");
        Row filaData = sheet.createRow(2);
        int dataRowIndice = 1;
        String[] columnas = {"ID", "DATO1"};
        // AQUI ES DONDE VAN LOS ENCABEZADOS O HEADERS DEL EXCEL A GENERAR
        for (int i = 0; i < columnas.length; i++) {
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
        }

        int dataRowIndex = 4;
        List<Entidad1> entidad1 = entidad1Repo.findAll();
        for (Entidad1 datos : entidad1) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(datos.getId());
            dataRow.createCell(1).setCellValue(datos.getDato());
            dataRowIndex++;
        }
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}
