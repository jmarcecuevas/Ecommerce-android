# Ecommerce-android

Es una aplicación en la que podrás visualizar un listado de productos junto a su nombre, precio, descuento, precio de lista, etc.
Además contarás con una sección de detalle con los datos anteriormente nombrados pero también una galería de imágenes y 3 reviews asociadas al producto.

Por último, cuenta con una pantalla donde podrás ver todos los reviews del producto, cantidad de opiniones hechas por los usuarios y cuantos de ellos le dieron una estrella, dos, tres, cuatro o cinco.

# Detalles técnicos

Esta aplicación fue desarrollada 100% en Kotlin aplicando muchos de los patrones y arquitectura sugeridas por Google en su última I/O como por ejemplo LiveData, ViewModel, Navigation components, etc.
- Kotlin
  - Coroutines
  - Lambda expressions
  - Extension functions
  - Null-safety
  - Smart casts
  - String templates
  - Android extensions
  - Companion objects
  - Data classes
- Clean-Code
- MVVM
- SOLID
- Kodein para dependency injection
- Retrofit
- ConstraintLayout
- Android architecture components
  - LiveData
  - ViewModel
- Navigation components
- AndroidX
- Glide
- Lottie (para animaciones)
- Calligraphy
- Font, FontVariable: clases creadas para el manejo de fuentes, con la ayuda de extension functions y calligraphy.
- GSON para serialization y deserialization de JSON
- DTO pattern
- Singleton pattern
- Material Design
- Anko
- LifeCycle

# Decisiones

Soy una persona que está constantemente intentando crecer y aprender las nuevas tendencias, herramientas, tecnologías y metodologías que van surgiendo con respecto al desarrollo.

Por eso es que desde hace un tiempo opté por inclinarme hacia el uso de Kotlin como lenguaje principal para mis desarrollos, si bien llevo poco tiempo con este lenguaje, me he podido adaptar rápidamente y estoy mas que contento con ello.

Como trato de mantenerme siempre al día también decidí probar Android architecture components y navigation components. En este proyecto traté de plasmar un poco todo esto de usar las últimas tecnologías siempre con la mirada en el producto y su impacto en el usuario.

Me quedó pendiente hacer testing unitario, que lo hubiera hecho utilizando JUnit, Mockito y los Observer para detectar cambios en LiveData.

