# Fantasy_CasaSteiger

Juego de fantasía oscura (terror sobrenatural) hecho con Java Swing. para tarea de DI 2CFGSDAMP. 
Llegas a la mansión Hollow veintiocho años después de la desaparición de la familia, siguiendo el reportaje del periodista Elías Marrow. Reúne las pruebas, descubre qué ocurrió y decide cómo termina la historia.

## Historia

En el año 2000, la familia Hollow desaparece de su mansión. La policía no encuentra nada, y el periodista Elías Marrow publica un reportaje y no vuelve a escribir. Tú, un investigador paranormal, encuentras ese reportaje y te adentras en la casa para descubrir por qué Elías se esfumó.

## Cómo se juega

1. **Menú:** escribe el nombre del investigador y pulsa *Nueva investigación*.
2. **Expediente:** lee el reportaje y tus objetivos.
3. **Investigación:** visita el dormitorio, la biblioteca y el sótano. Cada lugar esconde una prueba:
   - Dormitorio: el diario de la madre.
   - Biblioteca: el libro ritual.
   - Sótano: el símbolo de invocación.
4. **Ritual final:** decide qué haces:
   - *Cerrar el portal*: con las tres pruebas, es el final bueno. Sin ellas, termina mal.
   - *Completar el ritual*: final malo, la invocación.
   - *Intentar escapar*: final alternativo, la huida.

## Requisitos

- Java 17 o superior (probado con JDK 24).
- NetBeans con soporte Maven.
- Conexión a internet opcional: la imagen del sótano se descarga de internet. Sin conexión se usa la imagen que incluye el proyecto.

## Ejecutar

1. En NetBeans: *File → Open Project* y selecciona la carpeta del proyecto.
2. Pulsa *Run Project*. La clase principal es `com.mycompany.casa_steiger.Casa_steiger`.

## Estructura

```
src/main/java/com/mycompany/casa_steiger/
  Casa_steiger.java       Punto de entrada
  MenuFrame.java          Pantalla 1: menú principal
  ExpedienteFrame.java    Pantalla 2: reportaje y objetivos
  InvestigacionFrame.java Pantalla 3: lugares y pruebas
  FinalFrame.java         Pantalla 4: ritual final
  Recursos.java           Carga de imágenes
  Util.java               Utilidades: imágenes, paneles y diálogos
src/main/resources/imagenes/   Fondos, iconos e imágenes del juego
```

## Conceptos de Swing utilizados

- Varios `JFrame` con `dispose()` para cambiar de pantalla.
- Paso de parámetros entre ventanas (nombre del investigador y pruebas).
- `JDialog` simple, creado directamente (sin heredar de él).
- Imágenes del proyecto cargadas con `getResource()`.
- Imagen descargada de internet con `SwingWorker`, para no bloquear la ventana.
- Ventanas de tamaño fijo (800 × 600) y `FlowLayout`.

## Créditos e imágenes

Algunas imágenes del proyecto proceden de otras páginas web y se incluyen con fines educativos.

Los iconos de interrogante y los fondos de la biblioteca y el sótano son adaptaciones de imágenes originales.
