import { Component, OnInit } from '@angular/core';
import { GeneralService } from 'src/app/config/general-service.service';
import { Carrito, Producto, Tipo } from 'src/app/interfaces/carrito';
import { Usuario } from 'src/app/interfaces/usuario';

@Component({
  selector: 'app-portal',
  templateUrl: './portal.component.html',
  styleUrls: ['./portal.component.css']
})
export class PortalComponent implements OnInit {

  listadoProductos: Producto[];
  carrito: Carrito;
  totalSinDescuento: number;
  fechaPromocion = new Date();
  datosUsuario: Usuario;
  descuento: boolean;
  okRegistro: boolean;
  
  constructor(private gralService: GeneralService) { }

  ngOnInit(): void {
    this.carrito = {
      idUsuario: -1,
      tipo: null,
      productos: [],
      total: 0,
      };
    this.totalSinDescuento = 0;
    this.descuento = false;
    this.okRegistro = false;
    this.getProductos();
    this.getFechaEspecial();
    this.datosUsuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.actualizaciónUsuario();
    this.tipoCarrito();
    this.carrito.idUsuario = this.datosUsuario.idUsuario;
  }

  actualizaciónUsuario(){
    this.gralService.login(this.datosUsuario.usuario).subscribe(data => {
      this.datosUsuario.vip = data.vip;
      sessionStorage.setItem('usuario', JSON.stringify(this.datosUsuario));
    })
  }

  getProductos(){
    this.gralService.getProductos().subscribe((p) => {
      this.listadoProductos = p;
    }, err => {
      console.log("Error al traer productos ", err);
    })
  }

  getFechaEspecial(){
    this.gralService.getFechaEspecial().subscribe(fecha => {
      this.fechaPromocion = new Date(fecha.fechaEspecial);
    }, err =>{
      console.log('Error fecha especial ', err);
    })
  }

  agregarACarrito(p: Producto){
    let producto = new Producto();
    producto.id =  Math.floor(Math.random() * 9999); // Generar un id alterno para poder eliminar del carrito
    producto.nombre = p.nombre;
    producto.precio = p.precio;
    this.carrito.productos.push(producto);
    this.totalSinDescuento += producto.precio;
    this.actualizaciónUsuario();
    this.tipoCarrito();
    this.descuentoAplicado();
    
  }

  eliminarDelCarrito(id: number){
    let index = this.carrito.productos.findIndex((e) => e.id == id);
    this.totalSinDescuento -= this.carrito.productos[index].precio;
    this.carrito.productos.splice(index, 1);
    this.actualizaciónUsuario();
    this.tipoCarrito();
    this.descuentoAplicado();
  }

  descuentoAplicado(){
    if(this.carrito.productos.length >= 4 && this.carrito.productos.length < 10){
      this.descuento = true;
      this.carrito.total = this.totalSinDescuento - (this.totalSinDescuento * 0.25);
    } else if(this.carrito.productos.length >= 10){
      this.descuento = true;
      switch(this.carrito.tipo){
        case Tipo.COMUN:
          this.carrito.total = this.totalSinDescuento - 100;
          break;
        case Tipo.VIP:
          // encontrar producto con precio mas bajo
          let producto = new Producto();
          producto.precio = this.carrito.total;
          for (let i = 0; i < this.carrito.productos.length; i++){
            if(this.carrito.productos[i].precio < producto.precio){ 
              producto.id = this.carrito.productos[i].id;
              producto.nombre = this.carrito.productos[i].nombre;
              producto.precio = this.carrito.productos[i].precio;
            }
          }
          this.carrito.total = this.totalSinDescuento - producto.precio - 500;
          break;
        case Tipo.PROMOCION:
          this.carrito.total = this.totalSinDescuento - 300;
          break;

      }
    } else {
      this.descuento = false;
      this.carrito.total = this.totalSinDescuento;
    }
  }

  tipoCarrito(){
    this.gralService.getTipoCarrito(this.datosUsuario.vip).subscribe(tipo => {
      this.carrito.tipo = tipo;
      console.log('Tipo carrito', this.carrito.tipo);
    }, err => {
      console.log('Error consultando tipo de carrito: ', err)
    })
  }

  registrarCarrito(){
    console.log("Carrito principal", this.carrito);
    this.gralService.registrarCarrito(this.carrito).subscribe(res => {
      console.log("El carrito se registro correctamente");
      this.okRegistro = true;
      setTimeout(()=> {
        this.ngOnInit();
      }, 1500)
    }, err => {
      console.log('Error al registrar carrito', err)
    })

  }

}
