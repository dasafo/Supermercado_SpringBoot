package com.dasafodata.SupermercadoPruebaTecnica.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


//para que agregue automaticamente usando lombok un constructor vacio y no vacio y los setters y los getters
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
}
