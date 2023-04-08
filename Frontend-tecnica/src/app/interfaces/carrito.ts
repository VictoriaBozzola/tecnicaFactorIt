export interface Carrito {
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

export interface Producto {
    id: number;
    nombre: string;
    precio: number;
}