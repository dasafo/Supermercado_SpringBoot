package com.dasafodata.SupermercadoPruebaTecnica.dto;

/*
Los DTOS nos sirve para transferir datos o combinar datos de diferentes clases
para mostrarlos sobre un objeto en particular
basicamente sirve para decir lo que mostrará nuestra request cuando en este
caso pidamos info de la venta.
Lo que no se mapea son las etiquetas JPA(@Id, @GenerteValue,...),
ya que los DTO solo son para transferir datos y no van a la bbdd

En este DTO veremos combinacion de otras clases que estan asociados a la venta
*/

import com.dasafodata.SupermercadoPruebaTecnica.repository.ProductoRepository;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //Para decirle que lo usaremos en Mapper
public class VentaDTO {

    // datos de la venta
    private Long id;
    private LocalDate fecha;
    private String estado;

    // datos de la sucursal
    private Long idSucursal;

    // lista de detalles
    private List<DetalleVentaDTO> detalle;

    // total de la venta
    private Double total;
}
