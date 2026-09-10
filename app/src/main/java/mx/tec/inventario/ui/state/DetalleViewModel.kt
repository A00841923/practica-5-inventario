package mx.tec.inventario.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import mx.tec.inventario.data.ProductoRepository
import mx.tec.inventario.domain.Producto
import mx.tec.inventario.ui.navigation.Route

/**
 * Un producto y las dos acciones que se hacen sobre él.
 *
 * El id no se lo pasa nadie a mano: `SavedStateHandle` trae los argumentos de
 * navegación, y de paso sobrevive a que el sistema mate el proceso.
 */
class DetalleViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val productoId: Int =
        checkNotNull(savedStateHandle.get<Int>(Route.ARG_PRODUCTO_ID))

    var producto by mutableStateOf<Producto?>(null)
        private set

    init { recargar() }

    fun recargar() {
        producto = ProductoRepository.obtenerPorId(productoId)
    }

    fun venderUno() {
        val actual = producto ?: return
        if (actual.agotado) return
        ProductoRepository.actualizar(actual.copy(cantidad = actual.cantidad - 1))
        recargar()
    }

    fun borrar(alTerminar: () -> Unit) {
        val actual = producto ?: return
        ProductoRepository.borrar(actual)
        alTerminar()
    }
}
