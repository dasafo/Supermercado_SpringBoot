package com.dasafodata.SupermercadoPruebaTecnica.model;

import com.dasafodata.SupermercadoPruebaTecnica.dto.DetalleVentaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String estado;
    private Double total;

    //como Sucursal-Venta es un 1-N
    //muchas ventas estan asociadas a una sucursal
    @ManyToOne
    private Sucursal sucursal;

    //Mapeamos de vuelta tambien a detalleVenta
    //cuando borremos una venta queremos tambien q se borren sus detalles
    @OneToMany(mappedBy = "venta", //le decimos que la FK esta DetalleVenta y que busque ahi venta
            cascade = CascadeType.ALL,
            orphanRemoval = true, // si un dato hijo ha sido elimiado, matalo aqui tambien
            fetch = FetchType.EAGER) // cuando cargues una Venta, trae todos sus detalles tambien(no Lazy)
    private List<DetalleVenta> detalle = new ArrayList<>();
    //esta es la lista donde viviran los hijos en memoria
    //le ponemos venta que es el atributo de la clase en detalles venta donde lo hemos relacionado ahi
}
