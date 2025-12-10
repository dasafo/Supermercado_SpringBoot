package com.dasafodata.SupermercadoPruebaTecnica.controller;

import com.dasafodata.SupermercadoPruebaTecnica.dto.ProductoDTO;
import com.dasafodata.SupermercadoPruebaTecnica.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Para indicar que es un Controller(Endpoint)
@RequestMapping("/api/productos") // aqui va la URL de este endpoint
public class ProductoController {

    // Primero comenzamos haciendo una inyeccion de dependencias a nuestro services,
    // aqui de Prodcutos
    @Autowired
    private IProductoService productoService;

    // Creamos nuestro primer metodo con el cual vamos a traer todos nuestros
    // productos
    // Este metodo respondera a las peticiones HTTP GET
    // ResponseEntity es una clase que nos permite modificar la respuesta de nuestra
    // API
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> traerProductos() {

        // Llamamos a la capa de servicios para que vaya a la bbdd y traer la lista
        // de productos y con OK la envuelve en una respuesta con estado HTTP 200 OK y
        // la envia de vuelta a quien hizo la peticion/cliente
        return ResponseEntity.ok(productoService.traerProductos());
    }

    //Ahora para peiciones HTTP POST
    //este metodo entra la respuesta del body
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO dto) {

        // Recibimos este producto DTO, lo mandamos al Service, que se cree y despues que responda
        ProductoDTO creado = productoService.crearProducto(dto);

        return ResponseEntity.created(URI.create("/api/productos" + creado.getId())).body(creado);
    }

    //HTTP PUT (para modificaciones, este caso el id)
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO>  actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO dto) {

        return ResponseEntity.ok(productoService.actualizarProducto(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }

}
