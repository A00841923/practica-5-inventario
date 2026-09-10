package mx.tec.inventario.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.tec.inventario.domain.ProductoError
import mx.tec.inventario.domain.ProductoValidator
import mx.tec.inventario.ui.state.FormularioUiState
import mx.tec.inventario.ui.theme.InventarioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    uiState: FormularioUiState,
    esEdicion: Boolean,
    onNombreChange: (String) -> Unit,
    onPrecioChange: (String) -> Unit,
    onCantidadChange: (String) -> Unit,
    onGuardar: () -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(if (esEdicion) "Editar producto" else "Nuevo producto") },
                navigationIcon = {
                    IconButton(onClick = onCancelar) {
                        Icon(Icons.Default.Close, contentDescription = "Cancelar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = onNombreChange,
                label = { Text("Nombre") },
                singleLine = true,
                isError = uiState.nombreError != null,
                supportingText = { uiState.nombreError?.let { Text(it.mensaje()) } },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.precio,
                onValueChange = onPrecioChange,
                label = { Text("Precio") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = uiState.precioError != null,
                supportingText = { uiState.precioError?.let { Text(it.mensaje()) } },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.cantidad,
                onValueChange = onCantidadChange,
                label = { Text("Cantidad") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = uiState.cantidadError != null,
                supportingText = { uiState.cantidadError?.let { Text(it.mensaje()) } },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = onGuardar,
                enabled = uiState.puedeGuardar,
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (uiState.guardando) "Guardando…" else "Guardar") }
        }
    }
}

// Traducir el error del dominio a español es trabajo de la UI, no del dominio.
private fun ProductoError.mensaje(): String = when (this) {
    ProductoError.NombreVacio -> "Escribe un nombre"
    ProductoError.NombreMuyLargo -> "Máximo ${ProductoValidator.NOMBRE_MAX} caracteres"
    ProductoError.PrecioInvalido -> "Un precio mayor que cero, con punto decimal"
    ProductoError.CantidadInvalida -> "Un número entero, cero o más"
}

@Preview(showBackground = true)
@Composable
private fun FormularioPreview() {
    InventarioTheme {
        FormularioScreen(
            uiState = FormularioUiState(
                nombre = "Café de olla 1 kg",
                precio = "189.0",
                cantidad = "12"
            ),
            esEdicion = false,
            onNombreChange = {}, onPrecioChange = {}, onCantidadChange = {},
            onGuardar = {}, onCancelar = {}
        )
    }
}
