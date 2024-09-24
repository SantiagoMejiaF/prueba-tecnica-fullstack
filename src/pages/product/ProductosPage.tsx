import React, { useState } from 'react';

const ProductosPage: React.FC = () => {
  const [producto, setProducto] = useState({
    codigo: '',
    nombre: '',
    caracteristicas: '',
    precio: '',
    empresa: '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setProducto({ ...producto, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Llamada al servicio API para guardar el producto
  };

  return (
    <div className="container">
      <h2>Registro de Productos</h2>
      <form className="form" onSubmit={handleSubmit}>
        <input name="codigo" placeholder="Código" onChange={handleChange} required />
        <input name="nombre" placeholder="Nombre del producto" onChange={handleChange} required />
        <input name="caracteristicas" placeholder="Características" onChange={handleChange} required />
        <input name="precio" placeholder="Precio" onChange={handleChange} required />
        <input name="empresa" placeholder="Empresa" onChange={handleChange} required />
        <button type="submit" className="btn">Registrar Producto</button>
      </form>
    </div>
  );
};

export default ProductosPage;
