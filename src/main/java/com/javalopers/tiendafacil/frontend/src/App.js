import React, { useState } from 'react';
import { CssBaseline, ThemeProvider, AppBar, Toolbar, Typography, Container, Box, Button, Paper } from '@mui/material';

import { createTheme } from '@mui/material/styles';
import ClienteForm from './Componentes/ClientesForm';
import ClienteList from './Componentes/ClientesList';
import OrderForm from './Order/OrderForm';
import OrderList from './Order/OrderList';
import ReportesView from './Reportes/ReportesView';

const theme = createTheme({
  palette: {
    primary: {
      main: '#ff6f61', // color principal para tienda
    },
    secondary: {
      main: '#fff',
    },
  },
});

function App() {
  const [view, setView] = useState('clientes'); // 'clientes', 'pedidos', 'reportes'

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AppBar position="sticky">
        <Toolbar>
          <Typography variant="h6">Tienda Fácil</Typography>
        </Toolbar>
      </AppBar>
      <Container maxWidth="lg" sx={{ marginTop: 4 }}>
        <Box display="flex" justifyContent="space-between" mb={4}>
          <Button
            variant="contained"
            color="primary"
            onClick={() => setView('clientes')}
            sx={{ width: '30%', padding: 2 }}
          >
            Clientes
          </Button>
          <Button
            variant="contained"
            color="primary"
            onClick={() => setView('pedidos')}
            sx={{ width: '30%', padding: 2 }}
          >
            Pedidos
          </Button>
          <Button
            variant="contained"
            color="primary"
            onClick={() => setView('reportes')}
            sx={{ width: '30%', padding: 2 }}
          >
            Reportes
          </Button>
        </Box>

        <Paper elevation={3} sx={{ padding: 3 }}>
          {view === 'clientes' && (
            <>
              <ClienteForm />
              <ClienteList />
            </>
          )}
          {view === 'pedidos' && (
            <>
              <OrderForm />
              <OrderList />
            </>
          )}
          {view === 'reportes' && <ReportesView />}
        </Paper>
      </Container>
    </ThemeProvider>
  );
}

export default App;











