package com.dasafodata.SupermercadoPruebaTecnica.services;

import com.dasafodata.SupermercadoPruebaTecnica.dto.VentaDTO;

import java.util.List;

public interface IVentaService {

    // READ
    List<VentaDTO> traerVentas();
    // CREATE
    VentaDTO crearVenta(VentaDTO ventaDto);
    // UPDATE
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDto);
    // DELETE
    void eliminarVenta(Long id);
}
