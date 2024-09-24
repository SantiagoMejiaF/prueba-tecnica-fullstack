import { useForm } from 'react-hook-form';
import { TextField, Button, Container, Typography, Box } from '@mui/material';
import axios from 'axios';
import '../../styles/form.css';

type FormData = {
  nit: string;
  name: string;
  address: string;
  phone: string;
};

const EmpresaPage = () => {
  const { register, handleSubmit, formState: { errors } } = useForm<FormData>();

  const onSubmit = async (data: FormData) => {
    const formattedData = {
      nit: data.nit,
      name: data.name,
      address: data.address,
      phone: data.phone
    };

    try {
      const response = await axios.post('http://localhost:8080/api/v1/backend-java-springboot/companies', formattedData);
      console.log('Empresa creada:', response.data);
      alert('Empresa registrada exitosamente');
    } catch (error) {
      console.error('Error al registrar la empresa', error);
      alert('Hubo un error al registrar la empresa');
    }
  };

  return (
    <Container maxWidth="sm">
      <Box mt={5}>
        <Typography variant="h4" gutterBottom>
          Registro de Empresa
        </Typography>
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField
            label="NIT"
            fullWidth
            margin="normal"
            {...register('nit', { required: 'Este campo es requerido' })}
            error={!!errors.nit}
            helperText={errors.nit?.message}
          />
          <TextField
            label="Nombre de la Empresa"
            fullWidth
            margin="normal"
            {...register('name', { required: 'Este campo es requerido' })}
            error={!!errors.name}
            helperText={errors.name?.message}
          />
          <TextField
            label="Dirección"
            fullWidth
            margin="normal"
            {...register('address', { required: 'Este campo es requerido' })}
            error={!!errors.address}
            helperText={errors.address?.message}
          />
          <TextField
            label="Teléfono"
            fullWidth
            margin="normal"
            {...register('phone', { required: 'Este campo es requerido' })}
            error={!!errors.phone}
            helperText={errors.phone?.message}
          />
          <Button type="submit" variant="contained" color="primary">
            Registrar Empresa
          </Button>
        </form>
      </Box>
    </Container>
  );
};

export default EmpresaPage;
