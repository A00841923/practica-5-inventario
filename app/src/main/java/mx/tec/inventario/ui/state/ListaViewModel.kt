package mx.tec.inventario.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import mx.tec.inventario.data.ProductoRepository
import mx.tec.inventario.domain.Producto

/**
 * La lista del inventario.
 *
 * `recargar()` hay que llamarlo a mano cada vez que algo pudo haber cambiado:
 * al entrar a la pantalla, y al volver de agregar o borrar. Si se te olvida en
 * un solo lugar, la pantalla miente.
 */
class ListaViewModel : ViewModel() {

    var productos by mutableStateOf<List<Producto>?>(null)
        private set

    fun recargar() {
        productos = ProductoRepository.obtenerTodos()
    }
}
