package mx.tec.inventario.ui.navigation

object Route {
    const val LISTA = "lista"
    const val NUEVO = "nuevo"
    const val DETALLE = "detalle/{productoId}"
    const val EDITAR = "editar/{productoId}"

    const val ARG_PRODUCTO_ID = "productoId"

    fun detalle(id: Int) = "detalle/$id"
    fun editar(id: Int) = "editar/$id"
}
