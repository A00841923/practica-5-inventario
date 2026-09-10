package mx.tec.inventario.ui.components

import java.text.NumberFormat
import java.util.Locale

/**
 * Dar formato de dinero es trabajo de la UI, no del dominio: el dominio guarda
 * un número, y cómo se ve depende de dónde esté el usuario.
 */
fun Double.comoPesos(): String =
    NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX")).format(this)
