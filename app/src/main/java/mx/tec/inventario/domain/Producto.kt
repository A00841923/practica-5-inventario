package mx.tec.inventario.domain

/**
 * Un producto del inventario.
 *
 * Kotlin puro: no sabe que existe Android, ni Room, ni la base de datos.
 * Si mañana los datos vinieran de un archivo o de una API, esta clase no
 * cambiaría ni una línea.
 */
data class Producto(
    val id: Int = 0,
    val nombre: String,
    val precio: Double,
    val cantidad: Int
) {
    val agotado: Boolean get() = cantidad == 0

    val valorEnInventario: Double get() = precio * cantidad
}
