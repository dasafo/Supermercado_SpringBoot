package com.dasafodata.SupermercadoPruebaTecnica.dto;

/*
Los DTOS nos sirve para transferir datos o combinar datos de diferentes clases
para mostrarlos sobre un objeto en particular
basicamente sirve para decir lo que mostrará nuestra request cuando en este
caso pidamos info de la detalle de la venta.
Lo que no se mapea son las etiquetas JPA(@Id, @GenerteValue,...),
ya que los DTO solo son para transferir datos y no van a la bbdd
*/

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private String nombreProd;
    private Integer cantProd;
    private Double precio;
    private Double subtotal; //añadimos una varible extra a este DTO
}
