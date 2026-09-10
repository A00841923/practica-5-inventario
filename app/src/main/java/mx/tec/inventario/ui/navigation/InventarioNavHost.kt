package mx.tec.inventario.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mx.tec.inventario.ui.components.CargandoView
import mx.tec.inventario.ui.screens.DetalleScreen
import mx.tec.inventario.ui.screens.FormularioScreen
import mx.tec.inventario.ui.screens.ListaScreen
import mx.tec.inventario.ui.state.DetalleViewModel
import mx.tec.inventario.ui.state.FormularioViewModel
import mx.tec.inventario.ui.state.ListaViewModel

@Composable
fun InventarioApp() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Route.LISTA) {

        composable(Route.LISTA) {
            val viewModel: ListaViewModel = viewModel()

            // Hay que volver a preguntar cada vez que se entra: la lista pudo
            // haber cambiado desde otra pantalla.
            LaunchedEffect(Unit) { viewModel.recargar() }

            ListaScreen(
                productos = viewModel.productos,
                onProductoClick = { id -> nav.navigate(Route.detalle(id)) },
                onNuevoClick = { nav.navigate(Route.NUEVO) }
            )
        }

        composable(
            route = Route.DETALLE,
            arguments = listOf(navArgument(Route.ARG_PRODUCTO_ID) { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt(Route.ARG_PRODUCTO_ID) ?: return@composable
            val viewModel: DetalleViewModel = viewModel()

            // Y aquí otra vez, porque el usuario pudo venir de editarlo.
            LaunchedEffect(Unit) { viewModel.recargar() }

            val actual = viewModel.producto
            if (actual == null) {
                CargandoView()
            } else {
                DetalleScreen(
                    producto = actual,
                    onVenderUno = { viewModel.venderUno() },
                    onEditar = { nav.navigate(Route.editar(id)) },
                    onBorrar = { viewModel.borrar { nav.popBackStack(Route.LISTA, false) } },
                    onBack = { nav.popBackStack() }
                )
            }
        }

        composable(Route.NUEVO) {
            val viewModel: FormularioViewModel = viewModel()

            FormularioScreen(
                uiState = viewModel.uiState,
                esEdicion = viewModel.esEdicion,
                onNombreChange = viewModel::onNombreChange,
                onPrecioChange = viewModel::onPrecioChange,
                onCantidadChange = viewModel::onCantidadChange,
                onGuardar = { viewModel.guardar { nav.popBackStack() } },
                onCancelar = { nav.popBackStack() }
            )
        }

        composable(
            route = Route.EDITAR,
            arguments = listOf(navArgument(Route.ARG_PRODUCTO_ID) { type = NavType.IntType })
        ) {
            val viewModel: FormularioViewModel = viewModel()

            FormularioScreen(
                uiState = viewModel.uiState,
                esEdicion = viewModel.esEdicion,
                onNombreChange = viewModel::onNombreChange,
                onPrecioChange = viewModel::onPrecioChange,
                onCantidadChange = viewModel::onCantidadChange,
                onGuardar = { viewModel.guardar { nav.popBackStack() } },
                onCancelar = { nav.popBackStack() }
            )
        }
    }
}
