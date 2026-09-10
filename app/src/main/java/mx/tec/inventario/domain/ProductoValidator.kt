package mx.tec.inventario.domain

/** Lo que puede estar mal en un producto capturado a mano. */
enum class ProductoError {
    NombreVacio,
    NombreMuyLargo,
    PrecioInvalido,
    CantidadInvalida
}

/**
 * Las reglas de qué es un producto válido. Viven en el dominio, no en la
 * pantalla ni en la base de datos: son verdad siempre, venga el dato de donde
 * venga.
 *
 * Reciben texto porque eso es lo que teclea el usuario. Traducir "123.45" a
 * un Double es parte de validar, no algo que ocurra antes.
 */
object ProductoValidator {

    const val NOMBRE_MAX = 40

    fun validarNombre(nombre: String): ProductoError? = when {
        nombre.isBlank() -> ProductoError.NombreVacio
        nombre.trim().length > NOMBRE_MAX -> ProductoError.NombreMuyLargo
        else -> null
    }

    fun validarPrecio(precio: String): ProductoError? {
        val valor = precio.toDoubleOrNull()
        return if (valor == null || valor <= 0.0) ProductoError.PrecioInvalido else null
    }

    fun validarCantidad(cantidad: String): ProductoError? {
        val valor = cantidad.toIntOrNull()
        return if (valor == null || valor < 0) ProductoError.CantidadInvalida else null
    }

    fun esValido(nombre: String, precio: String, cantidad: String): Boolean =
        validarNombre(nombre) == null &&
            validarPrecio(precio) == null &&
            validarCantidad(cantidad) == null
}
