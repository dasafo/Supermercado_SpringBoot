package com.dasafodata.SupermercadoPruebaTecnica.controller;

import com.dasafodata.SupermercadoPruebaTecnica.dto.VentaDTO;
import com.dasafodata.SupermercadoPruebaTecnica.services.ISucursalService;
import com.dasafodata.SupermercadoPruebaTecnica.services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Para indicar que es un Controller(Endpoint)
@RequestMapping("/api/ventas") // aqui va la URL de este endpoint
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas() {
        return ResponseEntity.ok(ventaService.traerVentas());

    }

    /**
     * Crea una venta usando directamente VentaDTO en la request(opcion simple, sin
     * REquest separado)
     * Se espera que el DTO traiga la info
     */

    @PostMapping
    public ResponseEntity<VentaDTO> create(@RequestBody VentaDTO dto) {

        VentaDTO created = ventaService.crearVenta(dto);

        return ResponseEntity.created(URI.create("/api/ventas/" + created.getId())).body(created);
    }

    // aqui no usaremos ResponseEntity como en los otros para ver otra variabte,
    // para ver si devuelve un DTO
    // normal y corriente
    @PutMapping("/{id}")
    public VentaDTO actualizar(@PathVariable Long id, @RequestBody VentaDTO dto) {
        // actualiza fecha, estado, idSucursal, total y reemplaza detalee
        return ventaService.actualizarVenta(id, dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
