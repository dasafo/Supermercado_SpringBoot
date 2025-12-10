package com.dasafodata.SupermercadoPruebaTecnica.repository;

import com.dasafodata.SupermercadoPruebaTecnica.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository <Venta,Long> {
}
// traemos todos los metodos que hay en la interfaz JpaRepository (CRUD y mas) y lo asociamos al modelo
// Ventas cuyo ID ha sido definido de tipo Long

//En Repository no hace falta hacer tambien para la tabla intermedia DetalleVenta ya que en VEnta.java
// ya hicimos la otra conexion a esa tabla intermedia(dibireccionalidad de las entidades y el JPA
// es inteligente y ya sobreentiende la tabla intermedia esto lo hicimos añadiendo el mapeo
// en OneToMany el atributo que tenemos (venta) asociado en el modelaje de la tabla intermedia DetallesVenta