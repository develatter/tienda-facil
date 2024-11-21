import React, { useEffect, useState } from 'react';
import { Box, Typography } from '@mui/material';
import axios from 'axios';

function ReportView() {
  const [report, setReport] = useState([]);

  useEffect(() => {
    const fetchReport = async () => {
      try {
        const response = await axios.get('http://localhost:9000/api/reports');
        setReport(response.data);
      } catch (error) {
        console.error('Error al obtener los reportes:', error);
      }
    };
    fetchReport();
  }, []);

  return (
    <Box>
      <Typography variant="h6">Reportes</Typography>
      {/* Aquí se puede mostrar el reporte de manera adecuada */}
      <pre>{JSON.stringify(report, null, 2)}</pre>
    </Box>
  );
}

export default ReportView;









