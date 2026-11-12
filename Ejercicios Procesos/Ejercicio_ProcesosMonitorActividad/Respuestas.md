# Programas, procesos e hilos – Monitor de Actividad

## Práctica realizada

1. He abierto el **Monitor de Actividad** de macOS (pestaña *CPU*, con las columnas *Hilos* y *PID* visibles).
2. He iniciado **Google Chrome**. En la lista aparecen el proceso principal `Google Chrome` y varios procesos auxiliares: `Google Chrome Helper (GPU)`, `Google Chrome Helper` (red, almacenamiento…) y `Google Chrome Helper (Renderer)`.
3. Al abrir varias pestañas, el número de procesos `Google Chrome Helper (Renderer)` aumenta: cada pestaña (o cada sitio web) suele tener su propio proceso de renderizado. También sube el número total de hilos, ya que cada proceso nuevo trae sus propios hilos.
4. He abierto dos ventanas de **VS Code**. En la lista aparecen varios procesos `Code Helper` adicionales y aumenta el número de procesos y de hilos.

---

## Cuestionario de comprensión

### Pregunta 1
**¿Qué era la aplicación en sí misma? ¿Qué entidad se creó en el Administrador de Tareas?**

Antes de iniciarlo, el navegador es un **programa**: un archivo ejecutable guardado en disco (`/Applications/Google Chrome.app`). Es una entidad **pasiva**, solo contiene instrucciones y datos.

Al ejecutarlo, el sistema operativo crea un **proceso**, que es lo que aparece en el Monitor de Actividad. Un proceso es un programa **en ejecución**: es una entidad **activa** y tiene su propio PID, su espacio de memoria, sus recursos asignados (archivos abiertos, conexiones…) y un estado que va cambiando (en ejecución, listo, bloqueado). En el caso de Chrome no se crea un solo proceso, sino un proceso principal que a su vez lanza varios procesos hijo.

### Pregunta 2
**Si cerraste todas las pestañas pero dejaste la ventana principal abierta, ¿qué pasó con el proceso?**

Los procesos de renderizado de las pestañas cerradas **terminan**: desaparecen de la lista y se libera su memoria. El **proceso principal** del navegador (y algunos auxiliares, como el de GPU o el de red) **siguen en ejecución**, porque la aplicación sigue abierta. El número de procesos y de hilos baja, pero la aplicación no termina hasta que se cierra del todo (en macOS, con `Cmd + Q`).

### Pregunta 3
**Con dos instancias de la misma aplicación, ¿cuántos programas y cuántos procesos hay? ¿Son independientes?**

- **Programas: uno solo.** Las dos ventanas proceden del mismo ejecutable guardado en disco.
- **Procesos: al menos dos**, uno por instancia. Cada uno tiene su propio PID, su propio espacio de memoria y su propio estado. (En aplicaciones como VS Code o Chrome, cada instancia además genera varios procesos auxiliares.)

**Son independientes**: cada proceso tiene su propia memoria y el sistema operativo los planifica por separado. Si uno falla o se bloquea, el otro puede seguir funcionando, y lo que pasa en uno (variables, datos abiertos) no afecta directamente al otro. Si necesitan comunicarse, deben hacerlo mediante mecanismos de comunicación entre procesos (IPC), como tuberías o sockets.

> Matiz: algunas aplicaciones, para ahorrar recursos, gestionan las ventanas nuevas desde el mismo proceso principal en lugar de crear una instancia totalmente nueva. Aun así, el principio es el mismo: un único programa puede dar lugar a varios procesos.

### Pregunta 4
**En el navegador, ¿qué ventaja tiene que un proceso tenga múltiples hilos? Ejemplo práctico.**

Los hilos de un mismo proceso **comparten la memoria y los recursos** del proceso, pero se ejecutan de forma concurrente. Esto aporta varias ventajas:

- **Capacidad de respuesta:** un hilo se encarga de la interfaz (dibujar, responder a clics y al teclado) mientras otros hilos hacen tareas lentas, como descargar archivos, decodificar imágenes o vídeo, ejecutar JavaScript o acceder al disco. Así la ventana no se queda "congelada" mientras se espera a la red.
- **Aprovechamiento de varios núcleos:** con varios hilos, el trabajo se reparte entre los núcleos de la CPU (paralelismo real).
- **Menor coste que crear procesos:** crear un hilo y cambiar entre hilos es más ligero que hacerlo con procesos, y como comparten memoria se comunican más fácilmente (aunque necesitan sincronización para evitar condiciones de carrera).

**Ejemplo práctico:** mientras descargas un archivo grande, puedes seguir navegando, haciendo scroll y escribiendo en la barra de direcciones. La descarga se hace en un hilo distinto del que atiende la interfaz.

**Ejemplo de una pestaña que se congela:** si una web ejecuta un JavaScript con un bucle infinito, esa pestaña deja de responder, pero el resto del navegador sigue funcionando y aparece el aviso de "La página no responde", que permite cerrarla. En los navegadores modernos este aislamiento se consigue sobre todo porque **cada pestaña es un proceso distinto**, y dentro de cada proceso varios hilos reparten el trabajo. Combinando las dos técnicas, un fallo en una parte no tumba toda la aplicación y la interfaz principal sigue respondiendo.
