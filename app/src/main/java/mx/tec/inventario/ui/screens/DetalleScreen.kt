package mx.tec.inventario.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.tec.inventario.domain.Producto
import mx.tec.inventario.ui.components.comoPesos
import mx.tec.inventario.ui.theme.InventarioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    producto: Producto,
    onVenderUno: () -> Unit,
    onEditar: () -> Unit,
    onBorrar: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estado de pantalla puro: si el diálogo está abierto no le importa a nadie
    // más, así que no sube al ViewModel.
    var confirmando by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(producto.nombre) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    IconButton(onClick = { confirmando = true }) {
                        Icon(Icons.Default.Delete, contentDescription = "Borrar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Renglon("Precio", producto.precio.comoPesos())
                    Renglon("En existencia", "${producto.cantidad}")
                    Renglon("Valor total", producto.valorEnInventario.comoPesos())
                }
            }

            Button(
                onClick = onVenderUno,
                enabled = !producto.agotado,
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (producto.agotado) "Sin existencias" else "Vender uno") }

            OutlinedButton(onClick = onEditar, modifier = Modifier.fillMaxWidth()) {
                Text("Editar")
            }
        }
    }

    if (confirmando) {
        AlertDialog(
            onDismissRequest = { confirmando = false },
            title = { Text("¿Borrar ${producto.nombre}?") },
            text = { Text("Esto lo quita de la base de datos. No se puede deshacer.") },
            confirmButton = {
                TextButton(onClick = {
                    confirmando = false
                    onBorrar()
                }) { Text("Borrar") }
            },
            dismissButton = {
                TextButton(onClick = { confirmando = false }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
private fun Renglon(etiqueta: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(etiqueta, style = MaterialTheme.typography.bodyMedium)
        Text(valor, style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun DetallePreview() {
    InventarioTheme {
        DetalleScreen(
            producto = Producto(1, "Café de olla 1 kg", 189.0, 12),
            onVenderUno = {}, onEditar = {}, onBorrar = {}, onBack = {}
        )
    }
}
