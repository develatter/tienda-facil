// Importa la URL base del backend, si es necesario.
const API_URL = "http://localhost:9000/api/customers";

export const saveClient = async (clientData) => {
  try {
    const response = await fetch(`${API_URL}/save`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(clientData),
    });

    if (!response.ok) {
      throw new Error('Error al guardar el cliente');
    }

    return await response.json();
  } catch (error) {
    console.error("Error:", error);
    throw error;
  }
};

// Añadir la función getAllClients para obtener todos los clientes.
export const getAllClients = async () => {
  try {
    const response = await fetch(`${API_URL}/all`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error('Error al obtener los clientes');
    }

    return await response.json(); // Devuelve los clientes
  } catch (error) {
    console.error("Error:", error);
    throw error;
  }
};








