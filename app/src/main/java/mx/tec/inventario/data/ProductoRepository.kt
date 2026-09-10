package mx.tec.inventario.data

import mx.tec.inventario.domain.Producto

/**
 * Los productos, guardados en una lista en memoria.
 *
 * Es un `object` porque no necesita nada para existir: se construye solo, y por
 * eso hay uno y el mismo para toda la app.
 *
 * Y ahí está el problema: la memoria se va con el proceso. Agrega un producto,
 * cierra la app desde el selector de aplicaciones, vuelve a abrirla — no está.
 */
object ProductoRepository {

    private val productos = mutableListOf(
        Producto(1, "Café de olla 1 kg", 189.00, 12),
        Producto(2, "Miel de agave 500 ml", 95.50, 4),
        Producto(3, "Chocolate de mesa", 64.00, 0)
    )

    private var siguienteId = 4

    fun obtenerTodos(): List<Producto> = productos.sortedBy { it.nombre.lowercase() }

    fun obtenerPorId(id: Int): Producto? = productos.firstOrNull { it.id == id }

    fun agregar(producto: Producto) {
        productos += producto.copy(id = siguienteId)
        siguienteId++
    }

    fun actualizar(producto: Producto) {
        val posicion = productos.indexOfFirst { it.id == producto.id }
        if (posicion >= 0) productos[posicion] = producto
    }

    fun borrar(producto: Producto) {
        productos.removeAll { it.id == producto.id }
    }
}
