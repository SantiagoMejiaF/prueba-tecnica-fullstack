export interface Empresa {
    nit: string;
    nombre: string;
    direccion: string;
    telefono: string;
  }
  
  export interface Producto {
    codigo: string;
    nombre: string;
    caracteristicas: string;
    precio: string;
    empresa: string;
  }
  
  export interface Usuario {
    email: string;
    password: string;
  }
  