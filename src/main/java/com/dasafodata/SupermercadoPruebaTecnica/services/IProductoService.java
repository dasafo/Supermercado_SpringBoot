package com.dasafodata.SupermercadoPruebaTecnica.services;

import com.dasafodata.SupermercadoPruebaTecnica.dto.ProductoDTO;
import com.dasafodata.SupermercadoPruebaTecnica.dto.VentaDTO;

import java.util.List;

public interface IProductoService {


    // READ
    List<ProductoDTO> traerProductos();
    // CREATE
    ProductoDTO crearProducto(ProductoDTO productoDto);
    // UPDATE
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);
    // DELETE
    void eliminarProducto(Long id);
}
