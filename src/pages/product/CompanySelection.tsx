import  { useState, useEffect } from "react";
import { Container, Table, TableHead, TableBody, TableCell, TableRow, Button, Typography } from "@mui/material";
import { fetchCompanies } from "../../services/Product";
import ProductForm from "./ProductForm";

type Company = {
  name: string;
  nit: string;
};

const CompanySelection = () => {
  const [companies, setCompanies] = useState<Company[]>([]);
  const [selectedCompany, setSelectedCompany] = useState<string | null>(null);
  const [openProductForm, setOpenProductForm] = useState(false);

  useEffect(() => {
    const loadCompanies = async () => {
      const data = await fetchCompanies();
      setCompanies(data);
    };
    loadCompanies();
  }, []);

  const handleSelectCompany = (nit: string) => {
    setSelectedCompany(nit);
    setOpenProductForm(true);
  };

  return (
    <Container maxWidth="lg">
      <Typography variant="h4" gutterBottom>
        Registrar Producto
      </Typography>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Nombre</TableCell>
            <TableCell>NIT</TableCell>
            <TableCell>Acciones</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {companies.map((company) => (
            <TableRow key={company.nit}>
              <TableCell>{company.name}</TableCell>
              <TableCell>{company.nit}</TableCell>
              <TableCell>
                <Button color="primary" onClick={() => handleSelectCompany(company.nit)}>
                  Registrar Producto
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      {selectedCompany && (
        <ProductForm
          open={openProductForm}
          onClose={() => setOpenProductForm(false)}
          selectedCompanyNit={selectedCompany}
        />
      )}
    </Container>
  );
};

export default CompanySelection;
