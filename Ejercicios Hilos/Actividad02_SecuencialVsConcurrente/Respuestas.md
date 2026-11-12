# Actividad 2: Ejecución secuencial vs. concurrente

## Archivos

| Archivo | Para qué sirve |
|---|---|
| `Tarea.java` | Un `Runnable` que imprime en qué hilo se ejecuta y simula 1 s de trabajo con `Thread.sleep(1000)`. |
| `EjecucionSecuencial.java` | Ejecuta 5 tareas una detrás de otra en el hilo `main` (llamando a `run()` directamente). |
| `EjecucionConcurrente.java` | Lanza 5 hilos con `start()` y espera a que acaben con `join()`. |
| `ContarNumeros.java` | Pregunta 4: suma los números del 1 al 1000 de forma secuencial y con 4 hilos, y compara los tiempos. |

Para compilar y ejecutar: `javac *.java`, luego `java EjecucionSecuencial`, `java EjecucionConcurrente` o `java ContarNumeros`.

---

## Pregunta 1
**¿Qué diferencia de tiempo observaste? ¿Por qué la versión concurrente tarda mucho menos?**

Resultados obtenidos:

- Secuencial: **5025 ms**
- Concurrente: **1024 ms**

La versión concurrente es unas **5 veces más rápida**.

En la versión **secuencial**, las 5 tareas se ejecutan una detrás de otra en el mismo hilo (`main`). Cada tarea tiene que esperar a que termine la anterior, así que los tiempos se suman: 5 × 1000 ms = 5000 ms.

En la versión **concurrente**, cada tarea se ejecuta en su propio hilo y las 5 empiezan casi a la vez. Mientras un hilo está dormido (`sleep`), los demás también están avanzando en su espera, así que las esperas **se solapan** en lugar de sumarse. El tiempo total es el de la tarea más larga (≈1000 ms) más un pequeño coste por crear los hilos y gestionarlos (los ~24 ms de diferencia).

Esto funciona especialmente bien porque las tareas pasan casi todo el tiempo **esperando** (como cuando se espera a la red o al disco), y no usando la CPU. Por eso la mejora se notaría incluso en un ordenador con un solo núcleo.

## Pregunta 2
**¿Qué demuestran los nombres de hilo impresos (`main` frente a `Thread-X`)?**

En la versión secuencial todas las tareas imprimen `main`: todo el trabajo lo hace **un único hilo**, el hilo principal que la JVM crea al arrancar el programa.

En la versión concurrente aparecen `Thread-0` a `Thread-4`: cada `start()` ha creado una **nueva unidad de ejecución** dentro del **mismo proceso Java**.

Esto demuestra que:

- Un proceso (la JVM ejecutando el programa) puede contener **varios hilos**, que son las unidades de ejecución que planifica el sistema operativo.
- Todos esos hilos **comparten el mismo proceso** y, por tanto, su memoria y recursos (por ejemplo, todos escriben en la misma consola, `System.out`).
- Llamar a `run()` directamente **no crea un hilo**, solo ejecuta el método en el hilo actual. Para crear un hilo nuevo hay que llamar a `start()`.

## Pregunta 3
**Al ejecutar `EjecucionConcurrente` varias veces, ¿sale siempre el mismo orden? ¿Por qué?**

**No.** En dos ejecuciones seguidas se obtuvo:

```
Ejecución 1:                        Ejecución 2:
Tarea 3 iniciada en Thread-2        Tarea 3 iniciada en Thread-2
Tarea 4 iniciada en Thread-3        Tarea 2 iniciada en Thread-1
Tarea 5 iniciada en Thread-4        Tarea 5 iniciada en Thread-4
Tarea 1 iniciada en Thread-0        Tarea 4 iniciada en Thread-3
Tarea 2 iniciada en Thread-1        Tarea 1 iniciada en Thread-0
```

Aunque los hilos se lanzan en orden (0, 1, 2, 3, 4), no empiezan ni terminan en ese orden. Esto se debe a que **el planificador del sistema operativo** decide qué hilo se ejecuta en cada momento y en qué núcleo, según la carga del sistema, los otros procesos que estén en marcha, cuántos núcleos hay libres, etc. Llamar a `start()` solo deja el hilo **listo** para ejecutarse, no garantiza cuándo se ejecutará.

A esto se le llama **no determinismo** de la ejecución concurrente: el resultado (aquí, el orden de los mensajes) puede cambiar de una ejecución a otra. Si el orden importara, habría que usar mecanismos de sincronización (`join()`, `synchronized`, semáforos…).

## Pregunta 4
**Versión secuencial y concurrente que cuenta los números del 1 al 1000. Compara los tiempos.**

El programa está en `ContarNumeros.java`. Suma los números del 1 al 1000:

- **Secuencial:** con un bucle `for` en el hilo `main`.
- **Concurrente:** divide el rango en 4 tramos (1–250, 251–500, 501–750, 751–1000). Cada hilo suma su tramo y guarda el resultado en su propia posición de un array, así no hay condición de carrera. Al final, `main` hace `join()` y suma los 4 resultados parciales.

Resultados (3 ejecuciones):

| Ejecución | Secuencial | Concurrente (4 hilos) | Resultado |
|---|---|---|---|
| 1 | 9 µs | 8238 µs | Concurrente ~825 veces más lenta |
| 2 | 11 µs | 8363 µs | Concurrente ~730 veces más lenta |
| 3 | 11 µs | 7781 µs | Concurrente ~680 veces más lenta |

Las dos versiones dan el mismo resultado (**500500**), pero aquí **la versión concurrente es mucho más lenta**, al revés que en la pregunta 1.

**¿Por qué?** Sumar 1000 números es un trabajo minúsculo: la CPU lo hace en unos pocos microsegundos. En cambio, crear un hilo, arrancarlo, que el sistema operativo lo planifique, esperar a que termine con `join()` y juntar los resultados cuesta del orden de **milisegundos**. El coste de gestionar los hilos (*overhead*) es mucho mayor que el trabajo que se reparte, así que dividir la tarea no compensa.

**Conclusión:** la concurrencia no siempre hace un programa más rápido. Compensa cuando:

- Las tareas pasan mucho tiempo **esperando** (red, disco, `sleep`), como en la pregunta 1, o
- El trabajo de cálculo es **lo bastante grande** (por ejemplo, sumar cientos de millones de números) como para que el tiempo ahorrado al repartirlo entre varios núcleos supere el coste de crear y coordinar los hilos.

Para tareas pequeñas y rápidas, la versión secuencial es más simple y más eficiente.

> Nota: los tiempos exactos varían según el ordenador y la ejecución, pero la conclusión es la misma.
