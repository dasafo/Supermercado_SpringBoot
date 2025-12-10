package com.dasafodata.SupermercadoPruebaTecnica.model;

import com.dasafodata.SupermercadoPruebaTecnica.dto.DetalleVentaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/*
Esta tabla intermedia tendra info de cada linea del ticket de la compra
 */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class DetalleVenta {

    //1- Id unico interno de esta entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //2- ¿A que ticket pertence esta linea?
    //muchos detalles pueden estar asociados a una venta
    @ManyToOne (fetch = FetchType.LAZY) //Lazy para q solo traiga el ID
    @JoinColumn(name = "ventaId")
    private Venta venta;
    //para decirle con que columna se unira, esta columna ventaId sera nuestra FK de esta tabla
    //Crea una columna ventaId(nuestra FK) que conectara con el Id de Venta(sabe cual es por el @Id que ahi alli)
    //lo mismo para lo de debajo de aqui

    //3- ¿Que producto es?
    //Muchos detalles pueden estar asociados al mismo producto
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId")
    private Producto producto;

    //4- ¿Cuantos se llevo?
    private Integer cantProd;

    //5- ¿Que precio tenia en ese momento de la venta?
    private Double precio;

// Tendremos para la relacion con Venta(igual para Producto):

// 1 objeto en Venta:
//  (Total: 3.20€)

// 2 objetos en DetalleVenta:
// Detalle 1: { Producto: Leche, Cantidad: 2, Precio: 1.20 }
// Detalle 2: { Producto: Pan, Cantidad: 1, Precio: 0.80 }
}
