package com.ues.edu.sv.parcial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="entidad3")
public class Entidad3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad3")
    private int id;

    @Column(name="dato",nullable=false,length=100)
    private String dato;

    @ManyToMany
    @JoinTable(
            name = "entidaddetalle",
            joinColumns = @JoinColumn(name = "identidad3", referencedColumnName = "id_entidad3"),
            inverseJoinColumns = @JoinColumn(name = "identidad4", referencedColumnName = "id_entidad4")
    )
    private Set<Entidad4> entidad4 = new HashSet<>();
}