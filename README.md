# E-commerce-CLI

Ejercicio práctico del curso de desarrollo de aplicaciones Full Stack con Java para el módulo 4

Enlace del repositorio de GitHub: https://github.com/gNOR-mu/ecommerce-cli-m4

# Instrucciones de ejecución

# Propósito

Desarrollar una aplicación de consola en Java con dos flujos (Administrador y Usuario) para gestionar
catálogo, carrito y compra, practicando POO, control de flujo, colecciones,
validaciones/excepciones y pruebas unitarias.

# Qué debe hacer la App

Qué debe hacer la app:
Menú principal

0. Salir
1. ADMIN
2. USUARIO

ADMIN (gestión de productos)

- Listar productos
- Buscar (nombre/categoría)
- Crear producto (id único, nombre, categoría, precio > 0)
- Editar producto (nombre/categoría/precio)
- Eliminar producto (con confirmación)

USUARIO (carrito, descuento y compra)

- Listar / Buscar productos
- Agregar al carrito (id, cantidad > 0)
- Quitar del carrito (id)
- Ver carrito (ítems, subtotales, TOTAL base)
- Ver descuentos activos (mostrar reglas y su condición)
- Confirmar compra

* Calcular TOTAL base y aplicar automáticamente todas las reglas de descuento que
  correspondan.
* Mostrar detalle de descuentos aplicados y TOTAL final.
* Crear la Orden en memoria y vaciar el carrito.

**Validaciones obligatorias:** id existente; cantidad entera > 0; no confirmar con carrito vacío.

**Cálculos:**

- subtotal = precio × cantidad; totalBase = Σ subtotales; totalFinal = totalBase – descuentosAplicados.

**Excepción personalizada:** por ejemplo, CantidadInvalidaException.

# Descuentos automáticos
   Definir al menos 2 reglas y evaluarlas sin intervención del usuario al confirmar, por ejemplo:

- **DescuentoPorMonto(min, porcentaje)** → si totalBase >= min, descuenta %.
- **DescuentoPorCategoria(categoria, porcentaje)** → si el carrito contiene esa categoría,
  descuenta % sobre totalBase.

El usuario debe poder listar los descuentos vigentes por consola.

# Requisitos técnicos mínimos
- Clases sugeridas: Producto, Carrito, Orden, Catalogo (gestión de productos),
  TiendaService (lógica), Consola (UI).
- Colecciones: List/Map; ordenamientos con Comparator (por nombre/precio).
- Tests (JUnit): ≥3 (totales de carrito; validación de cantidad; aplicación de descuentos).
- Datos en memoria.
- Git/GitHub: repo público ecommerce-cli-m4, mínimo 3 commits con mensajes descriptivos.

# Notas
- En windows, si se ejecuta el programa desde cmd es posible que no se interpreten correctamente los caracteres 
mostrando un texto similar a <<Men├║>>, se soluciona especificando UTF-8 con:

```BASH
  chcp 65001
```