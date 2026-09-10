# Práctica 5 — Inventario

Código de arranque de la Práctica 5 de TC2007B.

Es una app de inventario **terminada**: lista, detalle y formulario, con
navegación y validación. Puedes agregar, editar, vender y borrar productos.

Le falta una sola cosa, y es el tema de la práctica: los datos viven en una
lista en memoria. Agrega un producto, cierra la app desde el selector de
aplicaciones y vuelve a abrirla — no está.

## Cómo empezar

1. Clona el repositorio y ábrelo en Android Studio.
2. Espera a que Gradle sincronice y corre la app: debes ver tres productos.
3. Comprueba el bug de arriba antes de leer nada más.
4. Sigue la guía: https://startdroid.com/practicas/inventario.html

## Cómo trabajar

Haz un commit en cada checkpoint de la guía:

    git add -A ; git commit -m "checkpoint a1"

Si algo se rompe sin remedio, `git restore .` te regresa al último checkpoint bueno.

Los experimentos que rompen el código a propósito van en una rama:

    git switch -c experimento-d3     # antes de romper nada
    git switch main                  # el código bueno vuelve solo

## Uso de IA

Todo commit con código generado por IA debe declararlo con un trailer
`Co-Authored-By`. Ver la política completa en la guía.

## Entrega

Ver la rúbrica en la guía.
