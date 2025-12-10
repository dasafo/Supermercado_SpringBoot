# 🛒 Supermercado Prueba Técnica

Bienvenido al repositorio del proyecto **Supermercado Prueba Técnica**. Este proyecto es una API RESTful desarrollada con **Spring Boot** para la gestión de ventas, productos y sucursales de un supermercado.

## 🚀 Tecnologías Utilizadas

Este proyecto utiliza las siguientes tecnologías y herramientas:

* **Java 17**: Lenguaje de programación principal.
* **Spring Boot 3.5.7**: Framework para el desarrollo de la aplicación.
* **Spring Data JPA**: Para la persistencia de datos y manejo de repositorios.
* **MySQL / H2**: Bases de datos soportadas (H2 para desarrollo/pruebas, MySQL para producción).
* **Lombok**: Para reducir el código repetitivo (getters, setters, constructores, etc.).
* **Maven**: Gestor de dependencias y construcción del proyecto.

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura en capas clásica:

* **Model**: Entidades del dominio (`Venta`, `DetalleVenta`, `Producto`, `Sucursal`).
* **Repository**: Interfaces de acceso a datos.
* **Service**: Lógica de negocio.
* **Controller**: Endpoints de la API REST.

## 🛠️ Instalación y Ejecución

1. **Clonar el repositorio:**

    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd SupermercadoPruebaTecnica
    ```

2. **Compilar y ejecutar:**

    Puedes ejecutar la aplicación utilizando Maven:

    ```bash
    ./mvnw spring-boot:run
    ```

3. **Acceso:**
    La aplicación se iniciará por defecto en el puerto `8080`.

## 📦 Entidades Principales

* **Producto**: Representa los artículos disponibles en el supermercado.
* **Sucursal**: Tiendas físicas del supermercado.
* **Venta**: Cabecera de una transacción de venta.
* **DetalleVenta**: Renglones o items individuales dentro de una venta.

---
*Desarrollado como parte de una prueba técnica.*
