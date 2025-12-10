package com.dasafodata.SupermercadoPruebaTecnica.repository;

import com.dasafodata.SupermercadoPruebaTecnica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository <Producto, Long> {

    //Metodo personalizado para buscar producto por nombre usando Optional(puede que devuelva valor o no)
    Optional<Producto> findByNombre(String nombre);
    //En JPA automaticamente cuando llamamos a un metedod 'findBy...' el valor que tomara es el nombre del campo,
    //en este caso Nombre, ya sabe asi que el parametro que recibira sera nombre
}
// traemos todos los metodos que hay en la interfaz JpaRepository (CRUD y mas) y lo asociamos al modelo
// Producto cuyo ID ha sido definido de tipo Long
