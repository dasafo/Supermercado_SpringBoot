package com.dasafodata.SupermercadoPruebaTecnica.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;


    /*
    Aqui no ponemos un @OneTMany hacia DetalleVenta como hicimos en Venta, ya que un Producto
    puede tener miles o decenas de miles de asociaciones en DetalleVenta. Si tuvieras una
    lista List<DetalleVenta> ventas dentro de Producto cada vez que cargaras el objeto "Leche"
    (aunque sea para ver su precio), Hibernate podría intentar traerse (o preparar en memoria)
una lista con millones de registros históricos de ventas.
     */
}
