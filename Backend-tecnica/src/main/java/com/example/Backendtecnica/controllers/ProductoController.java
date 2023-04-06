package com.example.Backendtecnica.controllers;

import com.example.Backendtecnica.entities.Carrito;
import com.example.Backendtecnica.entities.Producto;
import com.example.Backendtecnica.entities.UsuarioGenerate;
import com.example.Backendtecnica.repository.CarritoRepository;
import com.example.Backendtecnica.repository.FechaRepository;
import com.example.Backendtecnica.repository.ProductoRespository;
import com.example.Backendtecnica.repository.UsuariosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductoController {

    private final Logger log = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoRespository productoRespository;

    @Autowired
    private FechaRepository fechaRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public ProductoController(ProductoRespository productoRespository, FechaRepository fechaRepository, CarritoRepository carritoRepository, UsuariosRepository usuariosRepository) {
        this.productoRespository = productoRespository;
        this.fechaRepository = fechaRepository;
        this.carritoRepository = carritoRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @PostMapping("/crearProducto")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto){
        productoRespository.save(producto);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/api/productos")
    public ResponseEntity<List<Producto>> listaProductos(){
        try {
            List<Producto> todosLosProductos = productoRespository.findAll();
            return ResponseEntity.ok(todosLosProductos);

        }catch (Exception e){
            log.warn("Error: " + e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/api/productos/{id}")
    public Producto productoPorId(@PathVariable Long id){
        Optional<Producto> productoSeleccionado = productoRespository.findById(id);
        if(productoSeleccionado.isPresent()){
            return productoSeleccionado.get();
        }
        return null;
    }

    @GetMapping("/consultarTipoCarrito")
    public ResponseEntity<Carrito.Tipo> tipoCarrito(@RequestParam boolean vip){
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaPromocion = fechaRepository.findAll().get(0).getFechaEspecial();
       try{
           if( fechaHoy == fechaPromocion){
               return ResponseEntity.ok(Carrito.Tipo.PROMOCION);
           } else if(vip){
               return ResponseEntity.ok(Carrito.Tipo.VIP);
           } else {
               return ResponseEntity.ok(Carrito.Tipo.COMUN);
           }
       } catch (Exception e){
           log.warn("Error: " + e);
           return ResponseEntity.unprocessableEntity().build();
       }
    }

    @PostMapping("/registrarCarrito")
    public ResponseEntity registrarCarrito(@RequestBody Carrito carrito) {
        carrito.setFechaCompra(LocalDate.now());
        try {
            UsuarioGenerate usuario = usuariosRepository.findById(Long.valueOf(carrito.getIdUsuario())).get();
            if(usuario.isVip()){
                List<Carrito> listadoComprasMes = carritoRepository.findCompras(carrito.getIdUsuario(), carrito.getFechaCompra());
                if(listadoComprasMes.size() == 0){
                    usuariosRepository.modificarTipoUsuario(usuario.getId(), false);
                }
            } else {
                List<Carrito> listadoComprasVip = carritoRepository.findComprasVip(carrito.getIdUsuario(), carrito.getFechaCompra());
                if(listadoComprasVip.size() > 0) {
                    usuariosRepository.modificarTipoUsuario(usuario.getId(), true);
                }
            }

            Carrito save = carritoRepository.save(carrito);

            return ResponseEntity.ok(save);

        } catch(Exception e){

            log.warn("Error: " + e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }



}
