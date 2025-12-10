package com.dasafodata.SupermercadoPruebaTecnica.repository;

import com.dasafodata.SupermercadoPruebaTecnica.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository <Sucursal, Long> { //long porque el id en Sucursal es long

}
// traemos todos los metodos que hay en la interfaz JpaRepository (CRUD y mas) y lo asociamos al modelo
// Sucursal cuyo ID ha sido definido de tipo Long
