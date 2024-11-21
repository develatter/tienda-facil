import React from 'react';
import ReactDOM from 'react-dom/client';  // Cambiar de 'react-dom' a 'react-dom/client'
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// Crear el root para React 18 y versiones superiores
const root = ReactDOM.createRoot(document.getElementById('root'));

// Usar el método .render() para renderizar la aplicación
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// reportWebVitals es opcional, pero ayuda a medir el rendimiento
reportWebVitals();



