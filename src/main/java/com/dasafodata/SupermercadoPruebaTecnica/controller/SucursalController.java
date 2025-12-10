package com.dasafodata.SupermercadoPruebaTecnica.controller;

import com.dasafodata.SupermercadoPruebaTecnica.dto.SucursalDTO;
import com.dasafodata.SupermercadoPruebaTecnica.services.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController //Para indicar que es un Controller(Endpoint)
@RequestMapping("/api/sucursales") //aqui va la URL de este endpoint
public class SucursalController {

    //inyeccion de dependencias a nuestra interfaz del service
    @Autowired
    private ISucursalService sucursalService;

    //devolvemos en formato suscursalDTO, con status HTTP de OK
    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursales() {

        return ResponseEntity.ok(sucursalService.traerSucursales());
    }

    //Para crear una nueva sucursal
    //recibimos en el body de nuestra request la infor de la nueva sucursal en formato DTO
    //
    @PostMapping
    public ResponseEntity<SucursalDTO> create(@RequestBody SucursalDTO dto) {

        //llamamos al services y creamos la sucursal
        SucursalDTO created = sucursalService.crearSucursal(dto);
        //devolvemos la info de la respuesta
        return ResponseEntity.created(URI.create("/api/sucursales/" + created.getId())).body(created);
    }

    //Para hacer la actualizacionk
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> update(@PathVariable Long id, @RequestBody SucursalDTO dto) {

        return  ResponseEntity.ok(sucursalService.actualizarSucursal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        sucursalService.eliminarSucursal(id);

        //como nos hemos limitado a borrar le pedimos que devuelva una respuesta sin contenido
        return ResponseEntity.noContent().build();
    }


}
