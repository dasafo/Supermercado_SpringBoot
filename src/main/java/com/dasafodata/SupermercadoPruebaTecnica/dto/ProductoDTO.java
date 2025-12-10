package com.dasafodata.SupermercadoPruebaTecnica.dto;

/*
Los DTOS nos sirve para transferir datos o combinar datos de diferentes clases
para mostrarlos sobre un objeto en particular
basicamente sirve para decir lo que mostrará nuestra request cuando en este
caso pidamos info de la detalle del producto.
Lo que no se mapea son las etiquetas JPA(@Id, @GenerteValue,...),
ya que los DTO solo son para transferir datos y no van a la bbdd
*/
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //PAra cuando lo usemos en Mapper
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;
}
