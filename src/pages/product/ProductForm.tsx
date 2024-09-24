import React, { useState, useEffect } from "react";
import {
  Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField, FormControl, InputLabel, Select, MenuItem, Checkbox, ListItemText, OutlinedInput,
} from "@mui/material";
import { fetchCategories, createProduct } from "../../services/Product";

type Category = {
  id: number;
  name: string;
};

type ProductFormProps = {
  open: boolean;
  onClose: () => void;
  selectedCompanyNit: string;
};

const ProductForm: React.FC<ProductFormProps> = ({ open, onClose, selectedCompanyNit }) => {
  const [categories, setCategories] = useState<Category[]>([]);
  const [selectedCategories, setSelectedCategories] = useState<number[]>([]);
  const [productData, setProductData] = useState({
    code: "",
    name: "",
    features: "",
    price: "",
  });

  useEffect(() => {
    const loadCategories = async () => {
      const data = await fetchCategories();
      setCategories(data);
    };
    loadCategories();
  }, []);

  const handleCategoryChange = (event: any) => {
    const {
      target: { value },
    } = event;
    setSelectedCategories(typeof value === "string" ? value.split(",") : value);
  };

  const handleFormSubmit = async () => {
    const product = {
      ...productData,
      companyNit: selectedCompanyNit,
      categoryIds: selectedCategories,
    };
    try {
      await createProduct(product);
      alert("Producto registrado con éxito");
      onClose();
    } catch (error) {
      console.error("Error al crear el producto", error);
    }
  };

  return (
    <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
      <DialogTitle>Registrar Producto</DialogTitle>
      <DialogContent>
        <TextField
          label="Código"
          fullWidth
          margin="normal"
          value={productData.code}
          onChange={(e) => setProductData({ ...productData, code: e.target.value })}
        />
        <TextField
          label="Nombre del Producto"
          fullWidth
          margin="normal"
          value={productData.name}
          onChange={(e) => setProductData({ ...productData, name: e.target.value })}
        />
        <TextField
          label="Características"
          fullWidth
          margin="normal"
          value={productData.features}
          onChange={(e) => setProductData({ ...productData, features: e.target.value })}
        />
        <TextField
          label="Precio"
          fullWidth
          margin="normal"
          value={productData.price}
          onChange={(e) => setProductData({ ...productData, price: e.target.value })}
        />

        <FormControl fullWidth margin="normal">
          <InputLabel id="select-categories-label">Categorías</InputLabel>
          <Select
            labelId="select-categories-label"
            multiple
            value={selectedCategories}
            onChange={handleCategoryChange}
            input={<OutlinedInput label="Categorías" />}
            renderValue={(selected) =>
              categories
                .filter((category) => selected.includes(category.id))
                .map((category) => category.name)
                .join(", ")
            }
          >
            {categories.map((category) => (
              <MenuItem key={category.id} value={category.id}>
                <Checkbox checked={selectedCategories.includes(category.id)} />
                <ListItemText primary={category.name} />
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Cancelar</Button>
        <Button onClick={handleFormSubmit}>Guardar</Button>
      </DialogActions>
    </Dialog>
  );
};

export default ProductForm;
