# Proyecto de preentrega para el curso de Backend con Java de Talento Tech

---

## Descripción

Este proyecto es una aplicación de consola desarrollada en **Java** que permite gestionar productos y pedidos de manera sencilla.  
Incluye funcionalidades CRUD (Crear, Leer, Actualizar y Eliminar) para productos, así como la creación y visualización de pedidos con control de stock.

El código fue refactorizado para utilizar un identificador único (`UUID`) en cada producto, mejorando la integridad y la manipulación de datos.

---

## Objetivos del proyecto

- Aplicar los principios de **Programación Orientada a Objetos (POO)**.
- Practicar el uso de **clases, objetos, listas y excepciones personalizadas**.
- Implementar un **menú interactivo** para gestionar datos desde consola.
- Manejar **identificadores únicos (UUID)** para mejorar la gestión de productos.
- Validar datos de entrada y controlar errores mediante excepciones.

---

## Estructura del proyecto

```
src/
├── Main.java
├── Product.java
├── Order.java
├── OrderItem.java
└── InsufficientStockException.java
```


---

## Clases principales

### `Product`
Representa un producto disponible en el inventario.  
Cada producto tiene:
- `id`: identificador único generado automáticamente (`UUID`)
- `name`: nombre del producto
- `price`: precio unitario
- `stock`: cantidad disponible en inventario

Incluye validaciones y métodos para modificar sus atributos y reducir stock.

### `Order`
Representa un pedido realizado.  
Cada pedido tiene:
- `id`: numérico incremental
- `items`: lista de productos con cantidad
- `total`: monto total del pedido

Controla que no se creen pedidos vacíos y actualiza el stock de los productos asociados.

### `OrderItem`
Asocia un producto con una cantidad dentro de un pedido y calcula su subtotal.

### `InsufficientStockException`
Excepción personalizada que se lanza cuando se intenta agregar al pedido una cantidad mayor al stock disponible.

### `Main`
Contiene el menú principal y toda la lógica de interacción con el usuario:
- Crear, listar, buscar, editar y eliminar productos.
- Crear pedidos y ver pedidos existentes.
- Validaciones de entrada y control de errores.

---

## Menú principal

Al ejecutar el programa, se muestra el siguiente menú interactivo:

```
----- MENÚ DE OPCIONES -----
0: Salir.
1: Crear producto.
2: Mostrar productos.
3: Buscar producto por nombre.
4: Editar producto (por ID).
5: Borrar producto (por ID).
6: Crear pedido.
7: Mostrar pedidos.
```


---

## Ejemplo de uso

### Mostrar productos

```
ID: 3f2c7a12 | Producto: Monitor ASUS TUF 27'' 165Hz | Precio: $299.99 | Stock: 9
ID: 7a5b8f90 | Producto: Teclado Mecánico HyperX Alloy Origins | Precio: $119.99 | Stock: 15
```

### Editar producto

```
Ingrese el ID del producto a editar: 3cc1b89a
¿Desea editar el siguiente producto?:
ID: 3cc1b89a | Producto: Mouse Logitech G Pro X Superlight | Precio: $149,99 | Stock: 20
Ingrese 1 = Sí / 0 = No: 1
Nuevo nombre (ENTER para mantener):
Nuevo precio (0 para mantener): -1
Nuevo stock (-1 para mantener): 8
Producto actualizado.
```


### Crear pedido

```
CREANDO NUEVO PEDIDO...
Ingrese el ID del producto: 7a5b8f90
Ingrese cantidad (disponible: 15): 2
Producto agregado al pedido.
¿Desea agregar otro producto? (s/n): n

Pedido creado exitosamente:
Pedido #1

    Teclado Mecánico HyperX Alloy Origins x2 = $239.98
    Total del pedido: $239.98
```


---

## Tecnologías utilizadas

- **Lenguaje:** Java 17+
- **Paradigma:** Programación Orientada a Objetos
- **Colecciones:** `ArrayList`
- **Control de flujo:** estructuras condicionales y bucles
- **Manejo de excepciones:** `try-catch`, excepción personalizada

---

## Ejecución

1. Clonar o descargar el proyecto.
2. Abrirlo en tu entorno de desarrollo preferido (por ejemplo, IntelliJ IDEA o VS Code con extensión de Java).
3. Compilar y ejecutar el archivo `Main.java`.
4. Interactuar con el menú desde la consola.

---

## Autor y créditos

Proyecto desarrollado como parte del **Curso de Backend con Java – Talento Tech** por *Matías Cabrera*.

---
