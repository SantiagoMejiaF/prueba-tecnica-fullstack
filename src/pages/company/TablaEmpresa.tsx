import { useState, useEffect } from "react";
import { Container, Table, TableHead, TableBody, TableCell, TableRow, IconButton, Typography, Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";
import { fetchCompanies, deleteCompany, updateCompany } from "../../services/Empresa";
import './TablaEmpresa.css';

type Company = {
  name: string;
  nit: string;
  address: string;
  phone: string;
};

const EmpresaTable = () => {
  const [companies, setCompanies] = useState<Company[]>([]);
  const [open, setOpen] = useState(false);
  const [selectedCompany, setSelectedCompany] = useState<Company | null>(null);

  const [formData, setFormData] = useState({
    name: "",
    address: "",
    phone: ""
  });

  const loadCompanies = async () => {
    try {
      const data = await fetchCompanies();
      setCompanies(data);
    } catch (error) {
      console.error('Error al cargar las empresas', error);
    }
  };

  const handleDeleteClick = async (nit: string) => {
    try {
      await deleteCompany(nit);
      setCompanies(companies.filter(company => company.nit !== nit));
    } catch (error) {
      console.error('Error al eliminar la empresa', error);
    }
  };


  const handleEditClick = (company: Company) => {
    setSelectedCompany(company);
    setFormData({
      name: company.name,
      address: company.address,
      phone: company.phone,
    });
    setOpen(true);
  };


  const handleClose = () => {
    setOpen(false);
    setSelectedCompany(null);
  };


  const handleFormSubmit = async () => {
    if (selectedCompany) {
      try {
        await updateCompany(selectedCompany.nit, formData);
        loadCompanies();
        setOpen(false);
      } catch (error) {
        console.error("Error al actualizar la empresa", error);
      }
    }
  };

  useEffect(() => {
    loadCompanies();
  }, []);

  return (
    <Container maxWidth="lg">
      <Typography variant="h4" gutterBottom>
        Listado de Empresas
      </Typography>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Nombre</TableCell>
            <TableCell>NIT</TableCell>
            <TableCell>Dirección</TableCell>
            <TableCell>Teléfono</TableCell>
            <TableCell>Acciones</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {companies.map(company => (
            <TableRow key={company.nit}>
              <TableCell>{company.name}</TableCell>
              <TableCell>{company.nit}</TableCell>
              <TableCell>{company.address}</TableCell>
              <TableCell>{company.phone}</TableCell>
              <TableCell>
                <IconButton color="primary" onClick={() => handleEditClick(company)}>
                  <Edit />
                </IconButton>
                <IconButton color="error" onClick={() => handleDeleteClick(company.nit)}>
                  <Delete />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Editar Empresa</DialogTitle>
        <DialogContent>
          <TextField
            label="Nombre"
            fullWidth
            margin="normal"
            value={formData.name}
            onChange={(e) => setFormData({ ...formData, name: e.target.value })}
          />
          <TextField
            label="Dirección"
            fullWidth
            margin="normal"
            value={formData.address}
            onChange={(e) => setFormData({ ...formData, address: e.target.value })}
          />
          <TextField
            label="Teléfono"
            fullWidth
            margin="normal"
            value={formData.phone}
            onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
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

export default EmpresaTable;
