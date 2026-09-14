package mx.tec.inventario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import mx.tec.inventario.ui.navigation.InventarioApp
import mx.tec.inventario.ui.theme.InventarioTheme

// Regina Aguilar García A00841923

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InventarioTheme { InventarioApp() } }
    }
}
