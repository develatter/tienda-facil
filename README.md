# Tienda Fácil - Gestión de Clientes y Pedidos

## Índice
1. [Descripción General del Proyecto](#descripción-del-proyecto)
2. [Requisitos Funcionales](#requisitos-funcionales)
3. [Equipo de Trabajo](#equipo-de-trabajo)
4. [Especificaciones Técnicas](#especificaciones-técnicas)

## Descripción del Proyecto

**Tienda Fácil** es un proyecto colaborativo originado en el Discord de Bytes Colaborativos con el objetivo 
de diseñar e implementar un sistema de gestión que permita el registro y gestión de clientes y pedidos.

Este sistema busca facilitar a los empleados el acceso a información de clientes y pedidos activos y pendientes,
registrar nuevos pedidos para clientes y el resto de acciones necesarias para administrar un comercio online.

## Requisitos Funcionales

1. **Gestión de Clientes**: Registrar, actualizar y consultar la información de clientes.
2. **Gestión de Pedidos**: Registrar, actualizar, consultar y eliminar pedidos vinculados a clientes.
3. **Generación de Reportes**: Elaboración de informes de clientes activos y pedidos pendientes.

## Equipo de Trabajo

- **Líder de Equipo**: Alejandro López

    Coordinación y supervisión del proyecto, revisión de código y organización del trabajo de equipo. 

- **Backend Developer**: Didier Maldonado y Alejandro López

    Desarrollo de la API REST e implementación de las base de datos.

- **Frontend**: Martín Canguillén 
    
  Creación de la interfaz de usuario y diseño de una experiencia fluida.

- **Documentación y Testing**: Jacinto Castillo y Luis Martínez

    Elbaroación de la documentación del proyecto y realización de pruebas funcionales y de  integración.

## Especificaciones Técnicas

### Backend

- **Lenguaje**: Java 17
- **Framework**: Spring Boot
- **Base de datos**: MySQL
- **Gestor de Dependencias**: Maven
- **Dependencias**:
  - Spring Web
  - Spring Data JPA
  - MySQL Driver
  - Validation
  - Lombok
  - Spring Boot Dev Tools

### Frontend

- **Framework**: Angular o React, según las habilidades del grupo.
- **Componentes Principales**:
  - **Registro de clientes**: Formulario para agragar nuevos clientes.
  - **Lista de clientes**: Vista con opciones de edición y eliminación de clientes.
  - **Registro de pedido**: Visualización y gestión de pedidos con opciones de devoluciones.
  - **Resportes**: Vista para clientes activos y pedidos por clientes.

### Funcionalidades Adicionales

- **Paginación**: En la lista de clientes y pedidos.
- **Búsqueda y filtros** para clientes y pedidos.
- **Validación de formularios** en todas las secciones de entradas de datos .


### Herramientas Recomendadas

1. **Gestión de Tareas y Comunicación**

   - **Trello**: Para el flujo de trabajo.
   - **Discord**: Comunicación en tiempo real.
   - **Google Meet / Zoom**: Reuniones semanales.

2. **Control de Versiones** -**GitHub**: Ramas independientes, pull Requets (PR) para integración.

3. **Backend**

   - **Java con spring Boot**: Para la implementación de la API.
   - **Base de Datos**: MySQL.
   - **Postman**: Pruebas de la API REST.

4. **Frontend**

   - **React**: Según la preferencia del equipo.

5. **Documentación**
   - **Swagger**: Documentación de la API.
   - **Markdown**: README.md para el repositorio del proyecto.

<!--
### Requisitos del proyecto

- **Equipos de trabajo**: Un lider de equipo es responsable de la coordinación.
- **Gestión de tareas**: Uso de un tablero de tareas en **Trello** para el seguimiento y orgabización.
- **Contro de versiones**: Repsoitorio en **GitHub** con ramas independientes para cada desarrollador
- **Acceso coordinadores**: Chantal y Jorge deben de tener acceso al trablero y repositorio de gitHub.

### Tecnologías recomendadas

- **Backend**: Java con Spring Boot y base de datos MySql o PostgreSQL.
- **Frontend**: Angular o React.
- **Documentación**: Markdown (para el archivo `README.md´.) y **Swagger** (para la documentación de la API).

## Roadmap

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

## Instalción y Configuración Inicial

1. **Clonar el repositorio**:

```bash

     git clone https://github.com/usuario/repositorio.git
     cd repositorio.........
```
-->