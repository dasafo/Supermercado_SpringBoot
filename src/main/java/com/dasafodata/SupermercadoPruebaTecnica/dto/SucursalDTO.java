package com.dasafodata.SupermercadoPruebaTecnica.dto;

/*
Los DTOS nos sirve para transferir datos o combinar datos de diferentes clases
para mostrarlos sobre un objeto en particular
basicamente sirve para decir lo que mostrará nuestra request cuando en este
caso pidamos info de la sucursal.
Lo que no se mapea son las etiquetas JPA(@Id, @GenerteValue,...),
ya que los DTO solo son para transferir datos y no van a la bbdd
*/

import com.dasafodata.SupermercadoPruebaTecnica.repository.ProductoRepository;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // Para decirle que lo usamos en Mapper
public class SucursalDTO {

    private Long id;
    private String nombre;
    private String direccion;
}
