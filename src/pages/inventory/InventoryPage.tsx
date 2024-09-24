import React, { useState, useEffect } from 'react';
import { Container, Table, TableHead, TableBody, TableCell, TableRow, TableContainer, Paper, Button, Typography, TextField, Dialog, DialogActions, DialogContent, DialogTitle, TablePagination, InputAdornment } from '@mui/material';
import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable';
import axios from 'axios';
import SearchIcon from '@mui/icons-material/Search';
import './InventoryPage.css';

type Product = {
  code: number;
  name: string;
  features: string;
  price: number;
  company: {
    nit: string;
    name: string;
    address: string;
    phone: string;
  };
  categories: Array<{
    id: number;
    name: string;
  }>;
};

const InventarioPage: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);
  const [email, setEmail] = useState<string>('');
  const [open, setOpen] = useState<boolean>(false);
  const [page, setPage] = useState<number>(0);
  const [rowsPerPage, setRowsPerPage] = useState<number>(5);
  const [searchTerm, setSearchTerm] = useState<string>('');

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/backend-java-springboot/products/list');
        setProducts(response.data);
        setFilteredProducts(response.data);
      } catch (error) {
        console.error('Error al cargar productos', error);
      }
    };

    fetchProducts();
  }, []);

  const generatePDF = () => {
    const doc = new jsPDF();
    doc.text('Inventario de Productos', 14, 22);

    autoTable(doc, {
      head: [['Código', 'Nombre', 'Precio', 'Características', 'Empresa', 'Categorías']],
      body: products.map((product) => [
        product.code,
        product.name,
        product.price,
        product.features,
        product.company.name,
        product.categories.map((cat) => cat.name).join(', '),
      ]),
    });

    doc.save('inventario_productos.pdf');
  };

  const sendEmail = async () => {
    const pdfAttachment = generatePDF();

    try {
      await axios.post('http://localhost:3001/send-email', {
        email,
        attachment: pdfAttachment,
      });

      alert('Correo enviado con éxito');
      handleClose();
    } catch (error) {
      console.error('Error al enviar el correo', error);
      alert('Hubo un error al enviar el correo.');
    }
  };

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const term = event.target.value;
    setSearchTerm(term);
    const filtered = products.filter((product) =>
      product.name.toLowerCase().includes(term.toLowerCase()) ||
      product.company.name.toLowerCase().includes(term.toLowerCase())
    );
    setFilteredProducts(filtered);
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
        Inventario de Productos
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

      <TableContainer component={Paper} className="table-container">
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Código</TableCell>
              <TableCell>Nombre</TableCell>
              <TableCell>Precio</TableCell>
              <TableCell>Características</TableCell>
              <TableCell>Empresa</TableCell>
              <TableCell>Categorías</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredProducts.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((product) => (
              <TableRow key={product.code}>
                <TableCell>{product.code}</TableCell>
                <TableCell>{product.name}</TableCell>
                <TableCell>{product.price}</TableCell>
                <TableCell>{product.features}</TableCell>
                <TableCell>{product.company.name}</TableCell>
                <TableCell>{product.categories.map((cat) => cat.name).join(', ')}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <TablePagination
          component="div"
          count={filteredProducts.length}
          page={page}
          onPageChange={handleChangePage}
          rowsPerPage={rowsPerPage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </TableContainer>

      <div className="button-group">
        <Button onClick={handleOpen} variant="contained" color="primary">
          Enviar Inventario por Correo
        </Button>
        <Button onClick={generatePDF} variant="contained" color="secondary">
          Descargar Inventario en PDF
        </Button>
      </div>

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Enviar Inventario por Correo</DialogTitle>
        <DialogContent>
          <TextField
            label="Correo Electrónico"
            fullWidth
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            margin="normal"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="secondary">
            Cancelar
          </Button>
          <Button onClick={sendEmail} color="primary">
            Enviar
          </Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
};

export default InventarioPage;
