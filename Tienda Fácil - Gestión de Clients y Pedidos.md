# Tienda Fácil - Gestión de Clients y Pedidos

<!--

Archivo README.md incial para el proyecto Tienda Fácil.
Este archivo ofrece una visión general del proyeto y sus requisitos.
Actualiza este archivo conforme avanza el desarrollo para reflejar cambios y adiciones.

-->

## Descripción del Proyecto

<!--
Proporciona una descripción breve pero clara del proyecto para ayudar
a otos colaboradores a entender el objeto principal de este sistema.

<--

Este proyecto tiene como objetivo implementar un sistema de gestión para **Tienda Fácil** que permite registar y
gestionar clientes y pedidos.

Este sistema busca facilitar a los empleados el acceso a información de clientes y pedidos activos y pendientes.

### General

- **Equipos de trabajo**: Un lider de equipo es responsable de la coordinación.
- **Gestión de tareas**: Uso de un tablero de tareas en **Trello** para el seguimiento y orgabización.
- **Contro de versiones**: Repsoitorio en **GitHub** con ramas independientes para cada desarrollador
- **Acceso coordinadores**: Chantal y Jorge deben de tener acceso al trablero y repositorio de gitHub.

### Tecnologías recomendadas

<!
Detallar las tecnologías utilizadas garatntizar que todos los desarrolladores
y futuros colaboradores entiendan los requisitos técnicos.
-->

- **Backend**: Java con Spring Boot y base de datos MySql o PostgreSQL.
- **Frontend**: Angular o React.
- **Documentación**: Markdown (para el archivo `README.md´.) y **Swagger** (para la documentación de la API).

## Objetivos del Proyecto

1. **Gestión de Clientes**:Registrar, actualizar y cosnultar la información de clientes.
2. **Gestión de pedidos**: Registra, actualizar, consultar y eliminar pedidos vinculados a clientes.
3. **Reportes**: Generacón de repsorte de clientes activos y pedidos pendientes.

## Especificaciones Técnicas

### Backend

- **Tecnología**: Java con Spring Boot.
- **Base de datos**: MySql y PostgreSQL.
- **API REST** con enpoints diseñados para la gestión de clientes, pedidos y gereración reportes.

### Frontend

- **Framework**: Angular o React, según las habilidades del grupo.
- **Componentes Principales**:
  - **Registro de clientes**: Formulario para agragar nuevos clientes.
  - **Lista de clientes**: Vista con opciones de edición y eliminación de clientes.
  - **Registro de pedido**: Visualización y gestión de pedidos con opciones de devoluciones.
  - **Resportes**: Vista para clientes activos y pedidos por clientes.

## Funcionalidades Adicionales

- **Paginación**: En la lista de clientes y pedidos.
- **Búsqueda y filtros** para clientes y pedidos.
- **Validación de formularios** en todas las secciones de entradas de datos .

## Roles del Equipo

1. **Líder de Proyecto**: coordina, supervisa el progreso y revisa código.
2. **Backend Developer(s)**: Desarrolla la API REST Y maneja las base de datos.
3. **Frontend Developer(s)**: Crean la interfaz de usuario y asegura una experiencia fluida.
4. **QA Tester** (opcional): Realizan pruebas funcionales e integración (puede ser un rol rotativo).

## fase del Proyecto

### Fase 1: Planificacion y Configuración

- **Reunión de inicio**: Asiganr roles y definir expectativas.
- **Configuracion de Trello**: Organizar tareas en columnas de flujo de trabajo ("To Do", "In Review", "Done").
- **Configuración de Github**: Crear ramas de desarrollo y establecer reglas de distribución.
- **Documentacón inicila**: Crear este archivo README.md con la descripción del proyecto.

### Fase 2: desarrollo del Backend

- **Modelo y Controladores del Clientes**: Crear el modelo y endpoints de clientes.
- **Modelo y Controladores de pedidos**: Crear el modelo y endpoints de pedidos.
- **Reportes**: Implementar endpoints para reportes de clientes activos y pedidos pendientes.
- **Pruebas**: Realizar pruebas de la API y base de datos (unitarias e integración).

### Fase 3: Desarrollo del Frontend

- **Interfaz de Cliente**: Crear componentes de registro y lista de clientes.
- **Interfaz de Pedido**: Crear componentes de registro y lista de pedidos.
- **Vista de Reportes**: Implementar visualización de reportes.
- **Integración API**: Conectar los componentes del frontend con los endpoints del backend.

## Herramientas Recomendadas

1. **Gestión de Tareas y Comunicación**

   - **trello**: Para el flujo de trabajo.
   - **Discord**: Comunicación en tiempo real.
   - **Google Meet / Zoom**: Reuniones semanales.

2. **Control de Versiones** -**GitHub**: Ramas independientes, pull Requets (PR) para integración.

3. **Backend**

   - **java con spring Boot**: Para la implementación de la API.
   - **Base de Datos**: MySQL Y PostgreSQL.
   - **Postman**: Pruebas de la API REST.

4. **Frontend**

   - **Angular o React**: Según la preferencia del equipo.

5. **Documentación**
   - **Swagger**: Documentación de la API.
   - **Markdown**: README.md para el repositorio del proyecto.

## Instalción y Configuración Inicial

1. **Clonar el repositorio**:

```bash

     git clone https://github.com/usuario/repositorio.git
     cd repositorio.........
```
