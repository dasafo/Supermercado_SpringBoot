package com.dasafodata.SupermercadoPruebaTecnica.mapper;

import com.dasafodata.SupermercadoPruebaTecnica.dto.DetalleVentaDTO;
import com.dasafodata.SupermercadoPruebaTecnica.dto.ProductoDTO;
import com.dasafodata.SupermercadoPruebaTecnica.dto.SucursalDTO;
import com.dasafodata.SupermercadoPruebaTecnica.dto.VentaDTO;
import com.dasafodata.SupermercadoPruebaTecnica.model.Producto;
import com.dasafodata.SupermercadoPruebaTecnica.model.Sucursal;
import com.dasafodata.SupermercadoPruebaTecnica.model.Venta;

import java.util.stream.Collectors;

//Creamos esta clase para que transforme elementos de tpo normal a DTO
// asi lo podremos usar en el Service cuando necesitemos obtener cosas de tipo DTO
public class Mapper {

    // Mapeo de Producto a ProductoDTO
    // hacemos que sea statico para poder llamarlo luego sin crear instancias
    // (Mapper::toDTO)
    public static ProductoDTO toDTO(Producto p) {
        if (p == null)
            return null;
        /*
         * El método .builder() es parte del Patrón de Diseño Builder. Es una forma muy
         * elegante y legible de construir objetos, especialmente cuando tienen muchos
         * campos.
         */

        //Ahora usando el esquema profesional Builder asociamos las etiquetas (id, nombre, categoria,...)
        //que son los nombres de los campos del modelo DTO(aqui de ProductoDTO) los valores que hay
        //entre parentesis(p.getId())
        //Es como decirle, lo que tienes en Productos equivale a esto que hay en ProdcutosDTO
        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    // Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta v) {
        if (v == null)
            return null;

        //var porque no sabemos que tipo de varible entraria(int,string,...)
        // ahora accederemos a Producto y Detalle atraves de Venta(get.Detalle) gacias a la
        //conexion con detalle
        var detalle = v.getDetalle().stream()
                .map(det -> DetalleVentaDTO.builder()
                        .id(det.getProducto().getId()) //id de producto
                        .nombreProd(det.getProducto().getNombre()) //nombre del producto
                        .cantProd(det.getCantProd()) //de DetallesVenta
                        .precio(det.getPrecio()) //de DetallesVenta
                        .subtotal(det.getPrecio() * det.getCantProd())
                        .build())
                .collect(Collectors.toList()); //recolectamos todo y lo metemos en formato List

        // Ahora recolectamos toda la info de la lista de detalle, lo mapea en formato DetalleVentaDTO
        // cogiendo cada uno de los subtotales que hay en la lista que llega de detalle(arriba). Si hay 30
        // detalles va mirando una sublista de cada uno y luego hace la suma de todos los valores de subtotal
        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum); //empezamos en cero y vamos sumando

        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .idSucursal(v.getSucursal() != null ? v.getSucursal().getId() : null)
                .estado(v.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }

    // Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s) {
        if (s == null)
            return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }
}
