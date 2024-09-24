import React, { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { TextField, Button, Container, Typography, Box } from '@mui/material';
import axios from 'axios';
import '../../styles/form.css';

type FormData = {
  customerId: number;
  productIds: number[];
  total: number;
};

const RegisterOrderPage = () => {
  const { register, handleSubmit, formState: { errors }, reset } = useForm<FormData>();
  const [products, setProducts] = useState<any[]>([]);
  const [customers, setCustomers] = useState<any[]>([]);

  useEffect(() => {
    const fetchProductsAndCustomers = async () => {
      try {
        const productsResponse = await axios.get('http://localhost:8080/api/v1/backend-java-springboot/products/list');
        const customersResponse = await axios.get('http://localhost:8080/api/v1/backend-java-springboot/customers');
        setProducts(productsResponse.data);
        setCustomers(customersResponse.data);
      } catch (error) {
        console.error('Error al cargar productos o clientes', error);
      }
    };

    fetchProductsAndCustomers();
  }, []);

  const onSubmit = async (data: FormData) => {
    const formattedData = {
      customerId: data.customerId,
      productIds: data.productIds,
      total: data.total,
    };

    try {
      const response = await axios.post('http://localhost:8080/api/v1/backend-java-springboot/orders', formattedData);
      console.log('Orden creada:', response.data);
      alert('Orden registrada exitosamente');
      reset();  // Línea para limpiar el formulario
    } catch (error) {
      console.error('Error al registrar la orden', error);
      alert('Hubo un error al registrar la orden');
    }
  };

  return (
    <Container maxWidth="sm">
      <Box mt={5}>
        <Typography variant="h4" gutterBottom>
          Registro de Orden
        </Typography>
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField
            label="ID del Cliente"
            fullWidth
            margin="normal"
            {...register('customerId', { required: 'Este campo es requerido' })}
            error={!!errors.customerId}
            helperText={errors.customerId?.message}
          />

          <TextField
            label="ID de Productos (separados por comas)"
            fullWidth
            margin="normal"
            {...register('productIds', { required: 'Este campo es requerido' })}
            error={!!errors.productIds}
            helperText={errors.productIds?.message}
          />

          <TextField
            label="Total"
            fullWidth
            margin="normal"
            {...register('total', { required: 'Este campo es requerido' })}
            error={!!errors.total}
            helperText={errors.total?.message}
          />

          <Button type="submit" variant="contained" color="primary">
            Registrar Orden
          </Button>
        </form>
      </Box>
    </Container>
  );
};

export default RegisterOrderPage;
