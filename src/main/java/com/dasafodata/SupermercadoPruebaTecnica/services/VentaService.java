package com.dasafodata.SupermercadoPruebaTecnica.services;

import com.dasafodata.SupermercadoPruebaTecnica.dto.DetalleVentaDTO;
import com.dasafodata.SupermercadoPruebaTecnica.dto.VentaDTO;
import com.dasafodata.SupermercadoPruebaTecnica.mapper.Mapper;
import com.dasafodata.SupermercadoPruebaTecnica.model.DetalleVenta;
import com.dasafodata.SupermercadoPruebaTecnica.model.Producto;
import com.dasafodata.SupermercadoPruebaTecnica.model.Sucursal;
import com.dasafodata.SupermercadoPruebaTecnica.model.Venta;
import com.dasafodata.SupermercadoPruebaTecnica.repository.ProductoRepository;
import com.dasafodata.SupermercadoPruebaTecnica.repository.SucursalRepository;
import com.dasafodata.SupermercadoPruebaTecnica.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    // Necesitamos acceder a la bbdd de todas las entidades para este caso de venta
    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    // en traerVentas() podiamos hacerlo tambien igual que hicimos en
    // traerSucursalres() con build
    // es otra forma de hacerlo mas primitiva, se recomienda hacerlo como se hizo en
    // Sucursales
    @Override
    public List<VentaDTO> traerVentas() {

        // traemos todas las ventas de tipo Venta de la BBDD en fomra de lista
        List<Venta> ventas = ventaRepo.findAll();
        // Creamos una lista auxiliar de tipo VentaDTO
        List<VentaDTO> ventasDto = new ArrayList<>();

        VentaDTO dto; // podriamos crear esto dentro del for de abajo, pero sino se estaria
        // creando una instancia a cada vuelta del bucle

        // Recorremos la lista ventas de arriba
        for (Venta v : ventas) {
            dto = Mapper.toDTO(v); // Convertimos cada Venta v en DTO con toDTO
            ventasDto.add(dto); // añadimos cada venta tipo DTO a la lista ventasDto para mostrar al cliente
        }

        return ventasDto; // devuelve la lista de tipo VentasDTO
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        // Para crear una venta debemos tener una venta, una sucursal y un detalle.
        // PAra venta tenemos la fecha, el estado y el total.
        // Para sucursal tenemos el id.
        // Para detalle tenemos el id y el producto.

        if (ventaDto == null)
            throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null)
            throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un porducto");

        // Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new RuntimeException("Sucursal no encontrada");
        }
        // Crear la venta
        // aqui recibiremos ya el id, venta y estado de VentaDTO
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc); // le pasamos la sucursal que hemos encontrado arriba
        vent.setTotal(ventaDto.getTotal());

        // la lista de detalles(aqui estan los productos)
        // del VentaDTO que recibimos(ventaDto) vamos a recibir una lista de detalles
        // tipo DTO
        // Vamos a tener que recorrer esa lista de Detalles, convertir cada detalleDTO
        // en un
        // Detalle y asignarlo luego a la lista para luego poder traerlo al Service

        List<DetalleVenta> detalles = new ArrayList<>();

        Double totalCalculado = 0.0;

        // REcorremos cada linea del ticjet que envio el cliente
        for (DetalleVentaDTO detDTO : ventaDto.getDetalle()) {
            // Buscar producto por id(tu detDTO usa id como id de producto)
            // Como en en DetalleVentaDTO no inlcuimos como atributo el Id del producto(si
            // el Id del detalle)
            // hemos tenido que crear en ProductoRepository un metodo(findByNombre()) que
            // busque el id buscando el producto(nombreProd, que
            // si esta en DetalleVentaDTO, para ello suponemos que no puede haber productos
            // con el mismo nombre dos veces)
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null)
                throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());

            // Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            // finalmente agregamos a nuestra lista creada antes 'detalles' todo esto que
            // hemos hecho
            detalles.add(detalleVent);
            totalCalculado = totalCalculado + (detDTO.getPrecio() * detDTO.getCantProd());

        }

        // finalmente a nuestra venta vent le asignamos el detalle que hemos obtenido
        // del for(detalles)
        vent.setDetalle(detalles);

        // guardamos en la BBDD
        vent = ventaRepo.save(vent);

        // Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida; // para que muestre en formato DTO la info de la venta creada

    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        // Buscamos si la venta existe para actualizarla
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null)
            throw new RuntimeException("Venta no encontrada");

        // En caso de que exista actualizarla con los datos que vienen en el argumento
        // de este metodo (VentaDTO ventaDto)
        if (ventaDto.getFecha() != null) {
            v.setFecha(ventaDto.getFecha());
        }
        if (ventaDto.getEstado() != null) {
            v.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal() != null) {
            v.setTotal(ventaDto.getTotal());
        }
        if (ventaDto.getIdSucursal() != null) {
            Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null)
                throw new RuntimeException("Sucursal no encontrada");
            v.setSucursal(suc);
        }

        ventaRepo.save(v);

        // ahora queremos ver lo modificado(hay que pasarlo a formato DTO)
        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {

        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null)
            throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);
    }
}
