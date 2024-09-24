import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link, useLocation } from 'react-router-dom';
import EmpresaPage from './pages/company/EmpresaPage';
import InventarioPage from './pages/inventory/InventoryPage';
import LoginPage from './pages/login/LoginPage';
import EmpresaTable from './pages/company/TablaEmpresa';
import CompanySelection from './pages/product/CompanySelection';
import CustomerPage from './pages/customer/CustomerPage';
import RegisterCustomer from './pages/customer/RegisterCustomer';
import OrderTable from './pages/order/OrderTable';
import RegisterOrderPage from './pages/order/RegisterOrderPage'; 
import './styles/Navbar.css';

const App: React.FC = () => {
  const location = useLocation();

  return (
    <div className="App">
      {location.pathname !== "/" && (
        <div className="sidebar">
          <ul>
            <li><Link to="/">Login</Link></li>
            <li><Link to="/empresa">Registrar Empresa</Link></li>
            <li><Link to="/empresas">Empresas</Link></li>
            <li><Link to="/inventario">Inventario</Link></li>
            <li><Link to="/register-product">Registrar Producto</Link></li>
            <li><Link to="/clientes">Clientes</Link></li>
            <li><Link to="/clientes/registrar">Registrar Cliente</Link></li>
            <li><Link to="/orders">Pedidos</Link></li>
            <li><Link to="/register-order">Registrar Pedido</Link></li>
          </ul>
        </div>
      )}

      <div className="main-content">
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/empresa" element={<EmpresaPage />} />
          <Route path="/empresas" element={<EmpresaTable />} />
          <Route path="/inventario" element={<InventarioPage />} />
          <Route path="/register-product" element={<CompanySelection />} />
          <Route path="/clientes" element={<CustomerPage />} />
          <Route path="/clientes/registrar" element={<RegisterCustomer />} />
          <Route path="/orders" element={<OrderTable />} />
          <Route path="/register-order" element={<RegisterOrderPage />} />
        </Routes>
      </div>
    </div>
  );
};

export default App;
