import { useForm } from 'react-hook-form';
import { TextField, Button, Container, Typography, Box } from '@mui/material';
import axios from 'axios';
import '../../styles/form.css';

type FormData = {
  name: string;
  email: string;
};

const RegisterCustomer = () => {
  const { register, handleSubmit, formState: { errors }, reset } = useForm<FormData>();

  const onSubmit = async (data: FormData) => {
    const formattedData = {
      name: data.name,
      email: data.email
    };

    try {
      const response = await axios.post('http://localhost:8080/api/v1/backend-java-springboot/customers', formattedData);
      reset();
      alert('Cliente registrado exitosamente');
      console.log('Cliente registrado:', response.data);
    } catch (error) {
      console.error('Error al registrar el cliente', error);
      alert('Hubo un error al registrar el cliente');
    }
  };

  return (
    <Container maxWidth="sm">
      <Box mt={5}>
        <Typography variant="h4" gutterBottom>
          Registro de Cliente
        </Typography>
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField
            label="Nombre"
            fullWidth
            margin="normal"
            {...register('name', { required: 'Este campo es requerido' })}
            error={!!errors.name}
            helperText={errors.name?.message}
          />
          <TextField
            label="Correo Electrónico"
            fullWidth
            margin="normal"
            {...register('email', { required: 'Este campo es requerido', pattern: { value: /\S+@\S+\.\S+/, message: 'Formato de correo inválido' } })}
            error={!!errors.email}
            helperText={errors.email?.message}
          />
          <Button type="submit" variant="contained" color="primary">
            Registrar Cliente
          </Button>
        </form>
      </Box>
    </Container>
  );
};

export default RegisterCustomer;
