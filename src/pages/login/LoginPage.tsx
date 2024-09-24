import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  MDBBtn,
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBInput
} from 'mdb-react-ui-kit';

function LoginPage() {
  const [email, setEmail] = useState('admin@admin.com');
  const [password, setPassword] = useState('admin123');
  const navigate = useNavigate();

  const handleLogin = () => {
    if (email === 'admin@admin.com' && password === 'admin123') {
      navigate('/empresa');
    } else {
      alert('Credenciales incorrectas');
    }
  };

  return (
    <MDBContainer fluid className="gradient-form">

      <MDBRow className="d-flex justify-content-center align-items-center h-100">
        <MDBCol col='6' className="card-custom">
          <div className="d-flex flex-column ms-5">
            <div className="text-center">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
                style={{ width: '185px' }} alt="logo" />
              <h4 className="mt-1 mb-5 pb-1">Sistema de Gestión Empresarial</h4>
            </div>

            <p>Por favor, inicia sesión en tu cuenta para gestionar empresas y productos.</p>

            <MDBInput wrapperClass='mb-4' label='Correo electrónico' id='form1' type='email'
              value={email} onChange={(e) => setEmail(e.target.value)} />
            <MDBInput wrapperClass='mb-4' label='Contraseña' id='form2' type='password'
              value={password} onChange={(e) => setPassword(e.target.value)} />

            <div className="text-center pt-1 mb-5 pb-1">
              <MDBBtn className="mb-4 w-100 gradient-custom-2" onClick={handleLogin}>Iniciar Sesión</MDBBtn>
              <a className="text-muted" href="#!">¿Olvidaste tu contraseña?</a>
            </div>

            <div className="d-flex flex-row align-items-center justify-content-center pb-4 mb-4">
              <p className="mb-0">¿No tienes una cuenta?</p>
              <MDBBtn outline className='mx-2' color='danger'>
                REGÍSTRATE
              </MDBBtn>
            </div>

          </div>
        </MDBCol>

        <MDBCol col='6' className="mdb-col-full-height gradient-custom-2">
          <div className="d-flex flex-column justify-content-center h-100">
            <div className="text-white px-3 py-4 p-md-5 mx-md-4">
              <h4 className="mb-4">Más que una empresa</h4>
              <p className="small mb-0">
                Nuestro sistema permite a las empresas gestionar su información de manera eficiente y segura, brindando acceso a la administración de empresas, productos, inventario y más, facilitando el control de datos en tiempo real.
              </p>
            </div>
          </div>
        </MDBCol>

      </MDBRow>

    </MDBContainer>
  );
}

export default LoginPage;
