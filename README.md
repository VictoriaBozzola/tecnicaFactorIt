# tecnicaFactorIt
Exámen tecnico para Factor It
Detalles:
JAVA 11 
Maven 
Spring Boot 
H2
Swagger: http://localhost:8080/swagger-ui/
Angular: ng serve

## Spring Boot
- Aplicación API REST con acceso a base de datos PostgreSQL
- El acceso se realizará desde postman o navegador.


## Clases

Producto:
    - id: num
    - nombre: string
    - precio: num

Carrito: 
    - id: num
    - idUsuario: string 
    - tipo: COMUN/PROMOCION/VIP (enum)
    - productos: Producto[]
    - total: (numero total con el descuento aplicado)
    - fechaCompra: fecha

Fecha Especial:
    - fecha 

Usuarios:
    envio login
    - usuario: string
    devuelvo login
    - idUsuario
    - vip: boolean
    - usuario: string

## Logica

        1) Loguear usuario
            a. Usuario: Victoria (vip) o Maria (comun)
        2) Consultar productos en la DB para visualizar en pantalla
        3) Cada producto tendra un boton de agregarACarrito({objeto clase Producto})
            - Se guardará en un estado del Front el array de los productos agregados.
                - carrito= {
                    productos: Producto[]; 
                } 
        4) Tendremos el detalle de los productos agregados a un costado:
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
                    - Desde el front dice que registro carrito y lo limpia para generar uno nuevo

                b. La opcion de eliminar productos se filtra desde el front


