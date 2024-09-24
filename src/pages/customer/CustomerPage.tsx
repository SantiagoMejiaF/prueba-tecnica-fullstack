import React, { useState, useEffect } from 'react';
import { Container, Table, TableHead, TableBody, TableCell, TableRow, TableContainer, Paper, Button, Typography, TextField, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, TablePagination, InputAdornment } from '@mui/material';
import { Edit, Delete, Search as SearchIcon } from '@mui/icons-material';
import axios from 'axios';
import './CustomerPage.css';

type Customer = {
  id: number;
  name: string;
  email: string;
};

const CustomerPage: React.FC = () => {
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [filteredCustomers, setFilteredCustomers] = useState<Customer[]>([]);
  const [selectedCustomer, setSelectedCustomer] = useState<Customer | null>(null);
  const [open, setOpen] = useState<boolean>(false);
  const [searchTerm, setSearchTerm] = useState<string>('');
  const [page, setPage] = useState<number>(0);
  const [rowsPerPage, setRowsPerPage] = useState<number>(5);

  const [formData, setFormData] = useState({
    name: '',
    email: ''
  });

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/backend-java-springboot/customers');
        setCustomers(response.data);
        setFilteredCustomers(response.data);
      } catch (error) {
        console.error('Error al cargar los clientes', error);
      }
    };
    fetchCustomers();
  }, []);

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const term = event.target.value;
    setSearchTerm(term);
    const filtered = customers.filter((customer) =>
      customer.name.toLowerCase().includes(term.toLowerCase()) ||
      customer.email.toLowerCase().includes(term.toLowerCase())
    );
    setFilteredCustomers(filtered);
  };

  const handleEditClick = (customer: Customer) => {
    setSelectedCustomer(customer);
    setFormData({ name: customer.name, email: customer.email });
    setOpen(true);
  };

  const handleDeleteClick = async (id: number) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/backend-java-springboot/customers/${id}`);
      setCustomers(customers.filter((customer) => customer.id !== id));
      setFilteredCustomers(filteredCustomers.filter((customer) => customer.id !== id));
    } catch (error) {
      console.error('Error al eliminar cliente', error);
    }
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedCustomer(null);
  };

  const handleFormSubmit = async () => {
    if (selectedCustomer) {
      try {
        await axios.put(`http://localhost:8080/api/v1/backend-java-springboot/customers/${selectedCustomer.id}`, formData);
        setCustomers(customers.map((customer) => (customer.id === selectedCustomer.id ? { ...customer, ...formData } : customer)));
        setFilteredCustomers(filteredCustomers.map((customer) => (customer.id === selectedCustomer.id ? { ...customer, ...formData } : customer)));
        setOpen(false);
      } catch (error) {
        console.error('Error al actualizar cliente', error);
      }
    }
  };

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <Container maxWidth="lg">
      <Typography variant="h4" gutterBottom align="center">
        Listado de Clientes
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
              <TableCell>Nombre</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredCustomers.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((customer) => (
              <TableRow key={customer.id}>
                <TableCell>{customer.name}</TableCell>
                <TableCell>{customer.email}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEditClick(customer)}>
                    <Edit />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDeleteClick(customer.id)}>
                    <Delete />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <TablePagination
          component="div"
          count={filteredCustomers.length}
          page={page}
          onPageChange={handleChangePage}
          rowsPerPage={rowsPerPage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </TableContainer>

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Editar Cliente</DialogTitle>
        <DialogContent>
          <TextField
            label="Nombre"
            fullWidth
            margin="normal"
            value={formData.name}
            onChange={(e) => setFormData({ ...formData, name: e.target.value })}
          />
          <TextField
            label="Email"
            fullWidth
            margin="normal"
            value={formData.email}
            onChange={(e) => setFormData({ ...formData, email: e.target.value })}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="secondary">
            Cancelar
          </Button>
          <Button onClick={handleFormSubmit} color="primary">
            Guardar
          </Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
};

export default CustomerPage;
