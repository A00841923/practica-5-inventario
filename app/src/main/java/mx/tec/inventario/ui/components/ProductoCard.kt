package mx.tec.inventario.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.tec.inventario.domain.Producto
import mx.tec.inventario.ui.theme.InventarioTheme

@Composable
fun ProductoCard(
    producto: Producto,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(end = 12.dp)) {
                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = producto.precio.comoPesos(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = if (producto.agotado) "Agotado" else "${producto.cantidad} en stock",
                style = MaterialTheme.typography.labelLarge,
                color = if (producto.agotado) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductoCardPreview() {
    InventarioTheme {
        ProductoCard(
            producto = Producto(1, "Café de olla 1 kg", 189.0, 12),
            onClick = {}
        )
    }
}
