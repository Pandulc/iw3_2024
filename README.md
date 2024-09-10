# Proyecto IW3 - 2024

## Descripción
Este proyecto es un servidor desarrollado con Spring Boot que maneja proveedores, productos y categorías. Está diseñado para ser una solución backend robusta y escalable para la gestión de estos elementos.

## Tecnologías Utilizadas
- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para el desarrollo de aplicaciones Java.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **Docker**: Para la contenedorización de la base de datos MySQL.

## Funcionalidades
- **Proveedores**: Gestión completa de proveedores, incluyendo creación, actualización, eliminación y listado.
- **Productos**: Gestión de productos con funcionalidades similares a las de proveedores.
- **Categorías**: Manejo de categorías para organizar los productos.

## Requisitos Previos
- **Java 17** o superior
- **Maven 3.6.3** o superior
- **Docker** (opcional, para la base de datos MySQL)

## Configuración del Proyecto
1. Clonar el repositorio:
   ```sh
   git clone <URL_DEL_REPOSITORIO>
   cd <NOMBRE_DEL_PROYECTO>
### Configurar la base de datos:  
Asegúrate de tener un contenedor de MySQL en ejecución.

Configura las propiedades de la base de datos en application-mysqldev.properties o application.yml.

### Construir el proyecto:  
```sh
mvn clean package -Dspring.profiles.active=mysqldev -Dbuild=jar
```
### Ejecutar el proyecto:  
```sh
cd target/
java -jar -Dspring.profiles.active=mysqldev <NOMBRE_DEL_JAR>
```
## Endpoints Disponibles
URL base: http://localhost:8080/api/v1
- Productos
  - GET /products
  - GET /products/{id}
  - GET /products/by_name/{product}
  - POST /products
  - PUT /products/{id}
  - DELETE /products/{id}
- Categorias
  - GET /products/categories
  - GET /products/categories/{id}
  - GET /products/categories/by_name/{category}
  - POST /products/categories
  - PUT /products/categories/{id}
  - DELETE /products/categories/{id}
- Proveedores
  - GET /suppliers
  - GET /suppliers/{id}
  - GET /suppliers/by_name/{provider}
  - POST /suppliers
  - PUT /suppliers/{id}
  - DELETE /suppliers/{id}