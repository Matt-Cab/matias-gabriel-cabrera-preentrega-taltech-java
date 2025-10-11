# Proyecto de preentrega para el curso de Backend con Java de Talento Tech

Este proyecto es una aplicación de consola en Java que simula un sistema básico para la gestión de productos y la creación de pedidos. Permite a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre productos y registrar nuevos pedidos, actualizando automáticamente el stock.

---

## Características

* **Gestión de Productos (CRUD):**
    * Crear nuevos productos con nombre, precio y stock.
    * Mostrar el listado completo de productos.
    * Buscar productos por nombre.
    * Editar el nombre, precio o stock de un producto existente.
    * Eliminar productos del inventario.
* **Gestión de Pedidos:**
    * Crear un nuevo pedido seleccionando productos del inventario y especificando cantidades.
    * Manejo de **stock:** El stock se reduce al crear un pedido y se verifica que haya suficiente.
    * Mostrar el listado de pedidos registrados con sus detalles y total.
* **Utilidades:**
    * Manejo de excepciones para datos inválidos (precio $\le 0$, stock $< 0$) y stock insuficiente.
    * Datos de productos iniciales para pruebas.
    * Menú de opciones interactivo.

---

## Estructura del Código

El proyecto está compuesto por las siguientes clases principales:

| Clase | Descripción |
| :--- | :--- |
| **`Main`** | Contiene el método principal (`main`) y toda la lógica de la aplicación (menú, gestión de opciones, CRUD de productos, creación y visualización de pedidos). |
| **`Product`** | Representa un producto con sus atributos (`name`, `price`, `stock`). Incluye métodos para modificar sus atributos y para reducir el stock, lanzando `InsufficientStockException` si es necesario. |
| **`Order`** | Representa un pedido, compuesto por una lista de objetos `OrderItem`. Se encarga de agregar productos al pedido, reducir su stock correspondiente y calcular el total. |
| **`OrderItem`** | Representa un artículo dentro de un pedido, enlazando un `Product` con la cantidad solicitada (`quantity`) y calculando el subtotal. |
| **`InsufficientStockException`** | Clase de excepción personalizada utilizada para manejar situaciones donde la cantidad solicitada de un producto excede el stock disponible. |

---

## Requisitos

* **Java Development Kit (JDK) 17** o superior.

---

## Cómo Ejecutar

1.  Tener un entorno Java configurado.
2.  Compila las clases Java:
    ```bash
    javac Main.java Product.java Order.java OrderItem.java InsufficientStockException.java
    ```
3.  Ejecuta la clase principal:
    ```bash
    java Main
    ```

La aplicación se iniciará mostrando el **Menú de Opciones** en la consola.

---

## 📝 Uso

Al iniciar la aplicación, se mostrará el menú principal (observa el cambio del símbolo `#` a `*`):


```
----- MENÚ DE OPCIONES -----
Ingrese un número correspondiente a la opción deseada:

0: Salir.
1: Crear producto.
2: Mostrar productos.
3: Buscar producto por nombre.
4: Editar producto.
5: Borrar producto.
6: Crear pedido.
7: Mostrar pedidos.
```

Ingresar el número correspondiente a la acción que se desea realizar y seguir las indicaciones en pantalla.
* Al crear un pedido (opción **6**), la aplicación te guiará para seleccionar productos del listado y la cantidad, verificando el stock en cada paso.