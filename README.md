# Tienda Fácil - Gestión de Clientes y Pedidos

## Índice

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Requisitos de la Aplicación](#requisitos-de-la-aplicación)
3. [Equipo de Trabajo](#equipo-de-trabajo)
4. [Especificaciones Técnicas](#especificaciones-técnicas)
5. [Documentación de la Base de Datos](#documentación-de-la-base-de-datos)

---

## Descripción del Proyecto

**Tienda Fácil** es un proyecto colaborativo originado en el Discord de Bytes Colaborativos con el objetivo 
de diseñar e implementar un sistema de gestión que permita el registro y gestión de clientes y pedidos.

Este sistema busca facilitar a los empleados el acceso a información de clientes y pedidos activos y pendientes,
registrar nuevos pedidos para clientes y el resto de acciones necesarias para administrar un comercio online.

## Requisitos de la Aplicación

1. **Gestión de Clientes**: Registrar, actualizar y consultar la información de clientes.
2. **Gestión de Pedidos**: Registrar, actualizar, consultar y eliminar pedidos vinculados a clientes.
3. **Generación de Reportes**: Elaboración de informes de clientes activos y pedidos pendientes.
4. **Paginación** en la lista de clientes y pedidos.
5. **Búsqueda y filtros** para clientes y pedidos.
6. **Validación de formularios** en todas las secciones de entradas de datos.


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

### Frontend (WIP)

- **Framework**: Angular o React, según las habilidades del grupo.
- **Componentes Principales**:
  - **Registro de clientes**: Formulario para agragar nuevos clientes.
  - **Lista de clientes**: Vista con opciones de edición y eliminación de clientes.
  - **Registro de pedido**: Visualización y gestión de pedidos con opciones de devoluciones.
  - **Resportes**: Vista para clientes activos y pedidos por clientes.

### Herramientas Adicionales
- **Gestión de Tareas y Comunicación**
  - **Trello**: Para el flujo de trabajo.
  - **Discord**: Comunicación en tiempo real y Reuniones Semanales.

- **Control de Versiones** 
  - **GitHub**

- **Documentación**:
  - **Writerside** para la elaboración del README.md y resto de archivos de documentación del proyecto.
  - **Diagrams** para los diagramas de flujo y E/R

## Documentación de la Base de Datos

### Diagrama E/R
<img src="diagrama-er.png" alt="Diagrama E/R de la BBDD de Tienda Fácil">

### Diccionario de Datos

---

#### **Tabla: `customers` - Información de clientes**

| **Nombre de la columna** | **Tipo de dato**          | **Restricciones**          | **Descripción**                                    |
|:--------------------------|:-------------------------:|:--------------------------:|:--------------------------------------------------|
| `customer_id`            | INTEGER, AUTO_INCREMENT  | PK                        | Identificador único del cliente.                 |
| `first_name`             | VARCHAR(100)            | NOT NULL                  | Nombre del cliente.                              |
| `last_name`              | VARCHAR(300)            | NOT NULL                  | Apellido del cliente.                            |
| `address`                | VARCHAR(100)            | NOT NULL                  | Dirección del cliente.                           |
| `mail`                   | VARCHAR(100)            | NOT NULL                  | Correo electrónico del cliente.                 |
| `reg_date`               | DATETIME                | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Fecha de registro.                  |
| `active`                 | BOOLEAN                 | NOT NULL                  | Indica si el cliente está activo.               |

---

#### **Tabla: `order_status` - Define estado del pedido**

| **Nombre de la columna** | **Tipo de dato**          | **Restricciones**          | **Descripción**                                    |
|:--------------------------|:-------------------------:|:--------------------------:|:--------------------------------------------------|
| `status_id`              | INTEGER, AUTO_INCREMENT  | PK                        | Identificador único del estado.                  |
| `status`                 | VARCHAR(10)             | NOT NULL, UNIQUE          | Descripción del estado (e.g., Pendiente, Enviado, Entregado). |

---

#### **Tabla: `products` - Información de productos disponibles**

| **Nombre de la columna** | **Tipo de dato**          | **Restricciones**          | **Descripción**                                    |
|:--------------------------|:-------------------------:|:--------------------------:|:--------------------------------------------------|
| `product_id`             | INTEGER, AUTO_INCREMENT  | PK                        | Identificador único del producto.                |
| `product_name`           | VARCHAR(100)            | NOT NULL                  | Nombre del producto.                             |
| `product_description`    | TEXT                    | NULL                      | Descripción del producto.                        |
| `unit_price`             | DECIMAL(10, 2)          | NOT NULL                  | Precio unitario del producto.                    |
| `current_stock`          | INTEGER                 | NOT NULL, DEFAULT 0       | Cantidad en stock.                               |

---

#### **Tabla: `orders` - Registra pedidos de clientes**

| **Nombre de la columna** | **Tipo de dato**          | **Restricciones**          | **Descripción**                                    |
|:--------------------------|:-------------------------:|:--------------------------:|:--------------------------------------------------|
| `order_id`               | INTEGER, AUTO_INCREMENT  | PK                        | Identificador único del pedido.                  |
| `customer_id`            | INTEGER                 | NOT NULL, FK              | Referencia al cliente (`customers.customer_id`). |
| `order_date`             | DATETIME                | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Fecha del pedido.                       |
| `delivery_date`          | DATE                    | NOT NULL                  | Fecha de entrega.                                |
| `status_id`              | INTEGER                 | NOT NULL, DEFAULT 1, FK   | Referencia al estado del pedido (`order_status.status_id`). |

---

#### **Tabla: `order_details` - Detalle de productos incluidos en el pedido**

| **Nombre de la columna** | **Tipo de dato**          | **Restricciones**          | **Descripción**                                    |
|:--------------------------|:-------------------------:|:--------------------------:|:--------------------------------------------------|
| `details_id`             | INTEGER, AUTO_INCREMENT  | PK                        | Identificador único del detalle.                 |
| `order_id`               | INTEGER                 | NOT NULL, FK              | Referencia al pedido (`orders.order_id`).        |
| `product_id`             | INTEGER                 | NOT NULL, FK              | Referencia al producto (`products.product_id`).  |
| `product_amount`         | INTEGER                 | NOT NULL                  | Cantidad del producto.                           |

#### **Reglas de eliminación**
| **Regla**                              | **Descripción**                                                                 |
|:---------------------------------------|:--------------------------------------------------------------------------------|
| `ON DELETE CASCADE`                    | Los detalles se eliminan automáticamente si se elimina el pedido asociado.     |

---
### Paso a Tabla

- Tabla customers: Información de clientes
  - Columnas:
    - customer_id (INTEGER, AUTO_INREMENT, PK): identificador único del cliente.
    - first_name (VARCHAR(100), NOT NULL): Nombre del cliente.
    - last_name (VARCHAR(300), NOT NULL): Apellido del cliente.
    - address (VARCHAR(100), NOT NULL): Dirección del cliente.
    - mail (VARCHAR(100), NOT NULL): Correo electrónico.
    - reg_date (DATETIME, NOT NULL, DEFAULT CURRENT_TIMESTAMP): Fecha de registro.
    - active (BOOLEAN, NOT NULL): Indica si el cliente está activo.
- Tabla order_status: Define estado del pedido
  - Columnas:
    - status_id (INTEGER, AUTO_INCREMENT, PK): Identificador único del estado.
    - status (VARCHAR(10), NOT NULL, UNIQUE): Descripción del estado (p. ej., Pendiente, Enviado, Entregado).
- Tabla products: Información de productos disponibles
  - Columnas:
    - product_id (INTEGER, AUTO_INCREMENT, PK): Identificador único del producto.
    - product_name (VARCHAR(100), NOT NULL): Nombre del producto.
    - product_description (TEXT): Descripción del producto.
    - unit_price (DECIMAL(10, 2), NOT NULL): Precio unitario del producto.
    - current_stock (INTEGER, NOT NULL, DEFAULT 0): Cantidad en stock.
- Tabla orders: Registra pedidos de clientes
  - Columnas:
    - order_id (INTEGER, AUTO_INCREMENT, PK): Identificador único del pedido.
    - customer_id (INTEGER, NOT NULL, FK): Referencia al cliente (customers.customer_id).
    - order_date (DATETIME, NOT NULL, DEFAULT CURRENT_TIMESTAMP): Fecha del pedido.
    - delivery_date (DATE, NOT NULL): Fecha de entrega.
    - status_id (INTEGER, NOT NULL, DEFAULT 1, FK): Referencia al estado del pedido (order_status.status_id).
- Tabla order_details: Detalle de productos incluídos en el pedido
  - Columnas:
    - details_id (INTEGER, AUTO_INCREMENT, PK): Identificador único del detalle.
    - order_id (INTEGER, NOT NULL, FK): Referencia al pedido (orders.order_id).
    - product_id (INTEGER, NOT NULL, FK): Referencia al producto (products.product_id).
    - product_amount (INTEGER, NOT NULL): Cantidad del producto.
  - Reglas de eliminación:
    - Los detalles se eliminan automáticamente si se elimina el pedido asociado (ON DELETE CASCADE).

---

### **Relaciones entre Tablas**

| **Relación**                          | **Origen**           | **Destino**             | **Tipo de Relación** | **Descripción**                                                               |
|:--------------------------------------|:---------------------|:------------------------|:--------------------:|:------------------------------------------------------------------------------|
| `orders.customer_id`                  | `orders`            | `customers.customer_id` | FK                  | Un pedido pertenece a un cliente. Un cliente puede tener múltiples pedidos.   |
| `orders.status_id`                    | `orders`            | `order_status.status_id`| FK                  | Un pedido tiene un estado.                                                    |
| `order_details.order_id`              | `order_details`     | `orders.order_id`       | FK, ON DELETE CASCADE| Cada detalle pertenece a un pedido. Un pedido puede tener múltiples detalles. |
| `order_details.product_id`            | `order_details`     | `products.product_id`   | FK                  | Cada detalle se asocia con un producto.                                       |

---

#### **Explicación de las Relaciones**

##### **1. orders ➡️ customers**
- **Relación:** Cada pedido registrado en la tabla `orders` está asociado con un cliente específico en la tabla `customers`.
- **Columna de referencia:** `orders.customer_id` es una clave foránea (FK) que apunta a `customers.customer_id`.
- **Restricciones:**
  - No se permite que un pedido exista sin un cliente válido.
  - Al eliminar un cliente, los pedidos asociados no se eliminan automáticamente (no hay `ON DELETE CASCADE` en esta relación).

---

##### **2. orders ➡️ order_status**
- **Relación:** Cada pedido tiene un estado asociado, registrado en la tabla `order_status`.
- **Columna de referencia:** `orders.status_id` es una clave foránea (FK) que apunta a `order_status.status_id`.
- **Restricciones:**
  - No se permite que un pedido exista sin un estado válido.
  - Al eliminar un estado, los pedidos asociados no se eliminan automáticamente (no hay `ON DELETE CASCADE` en esta relación).

---

##### **3. order_details ➡️ orders**
- **Relación:** Cada detalle registrado en la tabla `order_details` pertenece a un pedido en la tabla `orders`.
- **Columna de referencia:** `order_details.order_id` es una clave foránea (FK) que apunta a `orders.order_id`.
- **Restricciones:**
  - Los detalles del pedido se eliminan automáticamente si se elimina el pedido asociado, gracias a `ON DELETE CASCADE`.

---

##### **4. order_details ➡️ products**
- **Relación:** Cada detalle registrado en la tabla `order_details` está asociado con un producto en la tabla `products`.
- **Columna de referencia:** `order_details.product_id` es una clave foránea (FK) que apunta a `products.product_id`.
- **Restricciones:**
  - No se permite que un detalle exista sin un producto válido.
  - No se eliminan automáticamente los detalles si se elimina un producto (no hay `ON DELETE CASCADE` en esta relación).

---

#### **Notas Técnicas**
- **Claves Foráneas (FK):** Estas relaciones están implementadas con restricciones de clave foránea (`FOREIGN KEY`) para garantizar la integridad referencial.
- **ON DELETE CASCADE:** La eliminación en cascada está configurada únicamente en la relación `order_details ➡️ orders`, permitiendo eliminar automáticamente los detalles de un pedido si el pedido asociado es eliminado.
- **Integridad:** Las relaciones aseguran que los datos entre tablas estén sincronizados y que no haya valores huérfanos en las tablas dependientes.

---

<!--

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

-->