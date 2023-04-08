export class Carrito {
    idUsuario: number;
    tipo: Tipo;
    productos: Producto[];
    total: number;
    fechaCompra?: Date;
}

export enum Tipo {
    COMUN = 'COMUN',
    VIP = 'VIP',
    PROMOCION = 'PROMOCION'
}

export class Producto {
    id: number;
    nombre: string;
    precio: number;
}