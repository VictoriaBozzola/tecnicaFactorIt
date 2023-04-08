package com.example.Backendtecnica.controllers;

import com.example.Backendtecnica.entities.Carrito;
import com.example.Backendtecnica.entities.FechaEspecial;
import com.example.Backendtecnica.entities.Producto;
import com.example.Backendtecnica.entities.UsuarioGenerate;
import com.example.Backendtecnica.repository.CarritoRepository;
import com.example.Backendtecnica.repository.FechaRepository;
import com.example.Backendtecnica.repository.ProductoRespository;
import com.example.Backendtecnica.repository.UsuariosRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    @ApiOperation("Crear nuevos productos a parte de los mock")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto){
        productoRespository.save(producto);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/productos")
    @ApiOperation("Trae todos los productos de la DB")
    public ResponseEntity<List<Producto>> listaProductos(){
        try {
            List<Producto> todosLosProductos = productoRespository.findAll();
            return ResponseEntity.ok(todosLosProductos);

        }catch (Exception e){
            log.warn("Error: " + e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/fechaEspecial")
    @ApiOperation("Consulta la fecha especial de promociones")
    public ResponseEntity<FechaEspecial> fechaEspecial(){
        try {
            FechaEspecial fechaPromocion = fechaRepository.findAll().get(0);
            return ResponseEntity.ok(fechaPromocion);

        } catch (Exception e){
            log.warn("Error: " + e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/consultarTipoCarrito")
    @ApiOperation("Consulta cual es el tipo de carrito que se va a crear")
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
    @ApiOperation("Registra el carrito cuandos se finaliza la compra")
    public ResponseEntity<String> registrarCarrito(@RequestBody Carrito carrito) {
        carrito.setFechaCompra(LocalDate.now());
        try {
            UsuarioGenerate usuario = usuariosRepository.findById(Long.valueOf(carrito.getIdUsuario())).get();
            boolean estadoVip = usuario.isVip();
            if(usuario.isVip()){
                List<Carrito> listadoComprasMes = carritoRepository.findCompras(carrito.getIdUsuario(), carrito.getFechaCompra());
                if(listadoComprasMes.size() == 0){
                    usuario.setVip(false);
                    usuariosRepository.save(usuario);
                    estadoVip = false;
                }
            } else {
                List<Carrito> listadoComprasVip = carritoRepository.findComprasVip(carrito.getIdUsuario(), carrito.getFechaCompra());
                if(listadoComprasVip.size() > 0) {
                    usuario.setVip(true);
                    usuariosRepository.save(usuario);
                    estadoVip = true;
                }
            }

            Carrito save = carritoRepository.save(carrito);
            log.info("El carrito se guardo correctamente, estado vip de usuario: " + estadoVip);
            return ResponseEntity.ok().build();

        } catch(Exception e){

            log.warn("Error: " + e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }



}
