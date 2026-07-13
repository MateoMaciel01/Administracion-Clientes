# 👥 Sistema de Administración de Clientes

Aplicación web desarrollada en **Java** utilizando **Java EE, JSP, Servlets y MySQL Workbench** que permite administrar clientes mediante una interfaz web intuitiva. 
El sistema incorpora autenticación de usuarios, gestión de sesiones, validación de datos y persistencia de la información en una base de datos MySQL, 
iguiendo una arquitectura en capas para mantener una correcta separación de responsabilidades.

---

## 🚀 Funcionalidades

### 🔐 Inicio de sesión

- Autenticación de usuarios registrados.
- Validación de credenciales.
- Gestión de sesiones para controlar el acceso al sistema.
- Visualización del usuario autenticado durante toda la navegación.

### 👤 Administración de clientes

El sistema permite registrar nuevos clientes realizando validaciones sobre cada uno de los datos ingresados.

Se administran los siguientes datos:

- DNI.
- CUIL.
- Nombre y apellido.
- Sexo.
- Fecha de nacimiento.
- Dirección.
- Nacionalidad.
- Localidad.
- Provincia.
- Correo electrónico.
- Teléfono.

### 📋 Consulta de clientes

El sistema ofrece un listado dinámico que permite:

- Visualizar todos los clientes registrados.
- Buscar clientes en tiempo real.
- Ordenar la información por columnas.
- Navegar mediante paginación utilizando **DataTables**.

---

## 🛠️ Tecnologías utilizadas

- Java
- Java EE
- JSP (JavaServer Pages)
- Servlets
- JDBC
- MySQL
- MySQL Workbench
- Apache Tomcat 8
- HTML5
- CSS3
- DataTables
- Eclipse IDE

---

## 🏛️ Arquitectura

El proyecto fue desarrollado siguiendo una **arquitectura en capas**, separando la presentación, la lógica de negocio y el acceso a los datos.

```text
Interfaz (JSP)
      │
      ▼
Controladores (Servlets)
      │
      ▼
Lógica de negocio
      │
      ▼
DAO / JDBC
      │
      ▼
MySQL
```

---

## ✅ Validaciones implementadas

Durante el registro de clientes el sistema valida automáticamente:

- DNI numérico entre 6 y 8 dígitos.
- CUIL numérico de 11 dígitos.
- Nombre y apellido únicamente con letras.
- Fecha de nacimiento válida (no permite fechas futuras).
- Correo electrónico con formato válido y único.
- Teléfono numérico de 10 dígitos.
- Verificación de campos obligatorios antes de almacenar la información.

---


## ✨ Características

- Arquitectura en capas.
- Autenticación mediante sesiones.
- Validaciones del lado del servidor.
- Persistencia de datos utilizando MySQL y JDBC.
- Tabla dinámica con búsqueda, ordenamiento y paginación.
- Interfaz web simple e intuitiva.
- Código organizado para facilitar su mantenimiento y escalabilidad.

---

## ⚙️ Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/MateoMaciel01/Administracion-Clientes.git
```

### 2. Configurar la base de datos

- Crear la base de datos `sistema_clientes`.
- Ejecutar el script SQL incluido en el repositorio utilizando **MySQL Workbench**.
- Configurar los datos de conexión según el entorno local.

### 3. Ejecutar la aplicación

- Importar el proyecto en Eclipse IDE.
- Configurar Apache Tomcat 8.
- Ejecutar la aplicación desde el servidor.

### Usuario de prueba

| Usuario | Contraseña |
|----------|------------|
| admin | pass |

---

## 📌 Estado del proyecto

✅ Proyecto finalizado.

---

## 👨‍💻 Autor

**Mateo Maciel**
