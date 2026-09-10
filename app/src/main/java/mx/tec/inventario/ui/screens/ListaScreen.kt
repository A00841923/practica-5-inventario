package mx.tec.inventario.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.tec.inventario.domain.Producto
import mx.tec.inventario.ui.components.CargandoView
import mx.tec.inventario.ui.components.ProductoCard
import mx.tec.inventario.ui.components.VacioView
import mx.tec.inventario.ui.theme.InventarioTheme

/**
 * La pantalla es tonta: recibe una lista y la pinta. No sabe que existe Room,
 * ni el ViewModel, ni de dónde salieron los datos — por eso su @Preview
 * funciona sin base de datos.
 *
 * `productos == null` significa "la consulta todavía no vuelve"; una lista
 * vacía significa "no hay nada". Son dos pantallas distintas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(
    productos: List<Producto>?,
    onProductoClick: (Int) -> Unit,
    onNuevoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text("Inventario") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNuevoClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar producto")
            }
        }
    ) { padding ->
        when {
            productos == null -> CargandoView(Modifier.padding(padding))

            productos.isEmpty() -> VacioView(
                mensaje = "Tu inventario está vacío.\nToca + para agregar el primer producto.",
                modifier = Modifier.padding(padding)
            )

            else -> LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productos, key = { it.id }) { producto ->
                    ProductoCard(
                        producto = producto,
                        onClick = { onProductoClick(producto.id) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListaPreview() {
    val demo = listOf(
        Producto(1, "Café de olla 1 kg", 189.0, 12),
        Producto(2, "Miel de agave 500 ml", 95.5, 4),
        Producto(3, "Chocolate de mesa", 64.0, 0)
    )
    InventarioTheme {
        ListaScreen(productos = demo, onProductoClick = {}, onNuevoClick = {})
    }
}

@Preview(showBackground = true, name = "Vacío")
@Composable
private fun ListaVaciaPreview() {
    InventarioTheme {
        ListaScreen(productos = emptyList(), onProductoClick = {}, onNuevoClick = {})
    }
}
