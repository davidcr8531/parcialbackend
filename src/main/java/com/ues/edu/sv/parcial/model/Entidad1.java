package com.ues.edu.sv.parcial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="entidad1")
public class Entidad1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad1")
    private int id;

    @Column(name="dato",nullable=false,length=100)
    private String dato;

    @ManyToOne
    @JoinColumn(name="id_entidad2",nullable=false,
            foreignKey=@ForeignKey(name="FK-entidad2"))
    private Entidad2 entidad2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Entidad2 getEntidad2() {
        return entidad2;
    }

    public void setEntidad2(Entidad2 entidad2) {
        this.entidad2 = entidad2;
    }
}
