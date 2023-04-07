# tecnicaFactorIt
Exámen tecnico para Factor It
Detalles:
JAVA 11 
Maven 
Spring Boot 
H2
Swagger: http://localhost:8080/swagger-ui/
Angular: ng serve

# Spring Boot
- Aplicación API REST con acceso a base de datos PostgreSQL
- El acceso se realizará desde postman o navegador.


Clases:

Producto:
    - id: num
    - nombre: string
    - precio: num

Carrito: (DB)
    - id: num
    - idUsuario: string 
    - tipo: COMUN/PROMOCION/VIP (enum)
    - productos: Producto[]
    - total: (numero total con el descuento aplicado)
    - fechaCompra: fecha

Fecha Especial (DB)
    - fecha 

Usuarios: (DB)
    envio login
    - usuario: string
    devuelvo login
    - idUsuario
    - vip: boolean
    - usuario: string

Logica:
    - Desde el Front 
        1) Loguear usuario
        2) Consultar productos en la DB para visualizar en pantalla
        3) Cada producto tendra un boton de agregarACarrito({objeto clase Producto})
            - Se guardará en un estado del Front el array de los productos agregados.
                - carrito= {
                    productos: Producto[]; 
                } 
        4) Tendremos un icono de carrito en el menu de arriba:
            - Al Seleccionarlo abre un menu al costado con:  
                a. tipo de carrito (carrito.tipo)
                b. productos seleccionados ( for carrito.productos)
                c. precio total  
                    c.1. Si tiene +4 productos (Todo tipo de carrito)
                        - el descuento del 25%
                    c.2 Si tiene +10 productos:
                        - comun: - $100
                        - fecha especial: - $300
                        - vip: no cuenta el producto mas barato y descuenta del total - $500
                d. Botón finalizar compra (Recien ahi envia los datos al back)
                    - Registra en la DB la compra

                b. La opcion de eliminar se filtra desde el front
                c. Como ya consulto el tipo de carrito en el punto a ya puedo armar la logica en el front.
                    - guardo ese total con el descuento aplicado en carrito.total

Requests BACK:
    - 1)  - POST loggin(usuario)
            - devuelve: 
            - idUsuario
            - vip
            - nombreUsuario
    - 2)  - GET consultarProductos()
            - devuelve:
                 - Producto[]
    4)  - a. GET (con params) consultarTipoCarrito(fechaActual, vip - sessionStorageFront){
                let fechaPromocion = GET Fecha Especial
                                        - devuelve fecha
                if(fechaPromocion === fechaActual) {
                    return enum PROMOCION
                } else if (vip) {
                    return enum VIP
                } else {
                    return enum COMUN
                }
        }

        - d. POST registrarCarrito({clase Carrito}){
            let buscarUsuario = //Buscar por carrito.idUsuario
            if (buscarUsuario.vip == true) // Si el usuario es Vip entonces 
                // Filtrar si en el mes no hizo compras modificar el vip a false
                let comprasMes = GET consultaComprasUsuario(carrito.idUsuario, fecha){
                    // filtrar si hizo compras este mes
                    filter > Por usuario
                                > (Dentro del usuario) Por mes

                    if(filter.lenght === 0) { // si no hizo compras va a modificar el estado vip a false
                         PUT editarTipoUsuario(idUsuario: carrito.idUsuario, vip: false) {
                        }
                    }
            else 
                let comprasMesVip = GET consultaComprasUsuario(carrito.idUsuario, fecha){
                    // filtrar para encontrar el array de compras mayores a $10.000
                    filter > Por usuario
                                > (Dentro del usuario) Por mes
                                    > por precio total (mayor a $10.000)
                    return array de Carrito[]
                    }

                    if(comprasMesVip.length > 0) {
                        PUT editarTipoUsuario({idUsuario: carrito.idUsuario, vip: true}) {
                        //Cambiar en la DB el tipo de usuario buscando el id y cambiando el vip
                        }
                    }

            return status ok 
            
        }

