package com.dasafodata.SupermercadoPruebaTecnica.services;

import com.dasafodata.SupermercadoPruebaTecnica.dto.SucursalDTO;
import com.dasafodata.SupermercadoPruebaTecnica.model.Sucursal;

import java.util.List;

public interface ISucursalService {

    // READ
    List<SucursalDTO> traerSucursales();
    // CREATE
    SucursalDTO crearSucursal(SucursalDTO sucursalDto);
    // UPDATE
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);
    // DELETE
    void eliminarSucursal(Long id);
}

