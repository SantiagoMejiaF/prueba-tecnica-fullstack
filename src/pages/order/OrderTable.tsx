import React, { useState, useEffect } from 'react';
import { Container, Table, TableHead, TableBody, TableCell, TableRow, TableContainer, Paper, Button, Typography, IconButton, Dialog, DialogActions, DialogContent, DialogTitle, TablePagination, TextField, InputAdornment } from '@mui/material';
import { Edit, Delete } from '@mui/icons-material';
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';

type Order = {
  id: number;
  customer: {
    id: number;
    name: string;
    email: string;
  };
  products: Array<{
    code: number;
    name: string;
    price: number;
  }>;
  total: number;
};

const OrderTable: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [filteredOrders, setFilteredOrders] = useState<Order[]>([]);
  const [page, setPage] = useState<number>(0);
  const [rowsPerPage, setRowsPerPage] = useState<number>(5);
  const [searchTerm, setSearchTerm] = useState<string>('');
  const [open, setOpen] = useState<boolean>(false);
  const [selectedOrder, setSelectedOrder] = useState<Order | null>(null);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/backend-java-springboot/orders/list');
        setOrders(response.data);
        setFilteredOrders(response.data);
      } catch (error) {
        console.error('Error al cargar las órdenes', error);
      }
    };

    fetchOrders();
  }, []);

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const term = event.target.value;
    setSearchTerm(term);
    const filtered = orders.filter((order) =>
      order.customer.name.toLowerCase().includes(term.toLowerCase()) ||
      order.products.some((product) => product.name.toLowerCase().includes(term.toLowerCase()))
    );
    setFilteredOrders(filtered);
  };

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleDeleteClick = async (id: number) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/backend-java-springboot/orders/${id}`);
      setOrders(orders.filter(order => order.id !== id));
      setFilteredOrders(orders.filter(order => order.id !== id));
    } catch (error) {
      console.error('Error al eliminar la orden', error);
    }
  };

  const handleEditClick = (order: Order) => {
    setSelectedOrder(order);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedOrder(null);
  };

  const handleUpdateOrder = async () => {
    if (selectedOrder) {
      try {
        await axios.put(`http://localhost:8080/api/v1/backend-java-springboot/orders/${selectedOrder.id}`, selectedOrder);
        setOpen(false);
      } catch (error) {
        console.error('Error al actualizar la orden', error);
      }
    }
  };

  return (
    <Container maxWidth="lg">
      <Typography variant="h4" gutterBottom align="center">
        Órdenes
      </Typography>

      <TextField
        label="Buscar"
        variant="outlined"
        fullWidth
        margin="normal"
        value={searchTerm}
        onChange={handleSearch}
        InputProps={{
          startAdornment: (
            <InputAdornment position="start">
              <SearchIcon />
            </InputAdornment>
          ),
        }}
      />

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Cliente</TableCell>
              <TableCell>Productos</TableCell>
              <TableCell>Total</TableCell>
              <TableCell>Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredOrders.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((order) => (
              <TableRow key={order.id}>
                <TableCell>{order.id}</TableCell>
                <TableCell>{order.customer.name}</TableCell>
                <TableCell>{order.products.map(product => product.name).join(', ')}</TableCell>
                <TableCell>{order.total}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEditClick(order)}>
                    <Edit />
                  </IconButton>
                  <IconButton color="error" onClick={() => handleDeleteClick(order.id)}>
                    <Delete />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <TablePagination
          component="div"
          count={filteredOrders.length}
          page={page}
          onPageChange={handleChangePage}
          rowsPerPage={rowsPerPage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </TableContainer>

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Editar Orden</DialogTitle>
        <DialogContent>
          <TextField
            label="Total"
            fullWidth
            value={selectedOrder?.total || ''}
            onChange={(e) => setSelectedOrder({ ...selectedOrder!, total: parseInt(e.target.value) })}
            margin="normal"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancelar
          </Button>
          <Button onClick={handleUpdateOrder} color="primary">
            Guardar
          </Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
};

export default OrderTable;
