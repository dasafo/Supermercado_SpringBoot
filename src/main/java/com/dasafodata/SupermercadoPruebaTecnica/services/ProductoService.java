package com.dasafodata.SupermercadoPruebaTecnica.services;

import com.dasafodata.SupermercadoPruebaTecnica.dto.ProductoDTO;
import com.dasafodata.SupermercadoPruebaTecnica.exception.NotFoundException;
import com.dasafodata.SupermercadoPruebaTecnica.mapper.Mapper;

import com.dasafodata.SupermercadoPruebaTecnica.model.Producto;
import com.dasafodata.SupermercadoPruebaTecnica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

//REcordar que el Service es el intermediario entre el controller y nuestro repository(bbdd)
@Service
public class ProductoService implements IProductoService {

    //Creamos esta variable para poder acceder a los metodos JPA definidos en la Interfaz ProductoRepository
    //ya que queremos acceder a la bbdd
    @Autowired // inyeccion de dependencias
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }
    /* findAll() nos obliga que sea de tipo producto, pero queremos traer la info del DTO por ello:
     *
     * Usa .stream() porque necesita transformar la lista de datos.
     *
     * El problema es este:
     * repo.findAll() te devuelve una lista de Entidades (List<Producto>), todos los
     * productos.
     * Pero el metodo traerProductos está obligado a devolver una lista de DTOs
     * (List<ProductoDTO>). No puedes devolver la lista tal cual porque son tipos
     * diferentes.
     *
     * El proceso paso a paso:
     * .stream(): Convierte la lista sólida en una "cinta transportadora" de
     * elementos que van pasando uno a uno.
     * .map(Mapper::toDTO): Es una estación de trabajo en esa cinta. A cada Producto
     * que pasa, le aplica el metodo toDTO y lo convierte en un ProductoDTO. En
     * Mapper llama a la clase Mapper que se encagara de indicarle como hacer esto.
     * .toList(): Al final de la cinta, recoge todos los nuevos ProductoDTO y los
     * mete en una nueva lista.
     */
    //Cada producto que esta en la bbdd en forma de PRoducto lo pasamos a ProductoDTO

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        //Lo que llega del Cliente en formato DTO ahora lo pasamos a formato Producto porque
        //asi sera guadado en la BBDD, aqui usamos Builder() porque estamos creando un objeto nuevo
        //en memoria, como vemos en actualizarPRodcuto() ahi no hara falta, porque solo modificamos(uso de setters)
        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre()) //el productoDto viene de la variable de entrada de esta funcion crearProducto()
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();
        //lo guardamos primero en la BBDD en formato normal Producto, y luego lo pasamos a DTO
        //usando la clase Mapper y metodo toDTO() para que el cliente lo pueda ver si quiere
        return Mapper.toDTO(repo.save(prod));
    }
    //Recibimos un ProductoDTO y lo pasamos a Pdoructo(al reves que arriba en crearProducto()


    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        // vamos a buscar si existe ese producto en la bbdd
        Producto prod = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        //aqui no usamos builder porque solo queremos modificar en memoria usando setters, no como arriba q es nuevo
        prod.setNombre(productoDto.getNombre());
        prod.setCategoria(productoDto.getCategoria());
        prod.setCantidad(productoDto.getCantidad());
        prod.setPrecio(productoDto.getPrecio());

        //aqui hacemos lo mismo que paso en el metodo anteior, solo destacar q aqui si que se acabara creando un objeto
        // nuevo en memoria de tipo DTO(por la confi. en toDTO()), pero estos son de usar y tirar asi que da igual
        //lo q importa son los objetos en memoria de las entidades en la BBDD(los modelos)
        return Mapper.toDTO(repo.save(prod));
    }


    @Override
    public void eliminarProducto(Long id) {
        if (!repo.existsById(id)) {

            throw new NotFoundException("Producto no encontrado para eliminarlo");
        }

        repo.deleteById(id);
    }
}
