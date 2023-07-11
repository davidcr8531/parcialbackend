package com.ues.edu.sv.parcial.repository;
import com.ues.edu.sv.parcial.model.Entidad1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Entidad1Repository extends JpaRepository<Entidad1,Integer> {
    //@Query("from Entidad1 e where (LOWER(p.nombrePaciente) like %:filtro% or LOWER(p.apellidoPaciente) like %:filtro%)")
   // List<Entidad1> buscarPaciente(@Param("filtro") String filtro);
}
