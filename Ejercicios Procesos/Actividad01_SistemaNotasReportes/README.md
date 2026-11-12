# Actividad 1: Sistema de notas y reportes

Hay dos procesos Java separados, conectados mediante una tubería (`|`) y con redirección de E/S.

| Archivo | Función |
|---|---|
| `GeneradorNotas.java` | **Productor.** Recibe N por argumento y escribe N líneas `NOTA:VALOR` en `System.out` (nota aleatoria de 0 a 10; `Aprobado` si es ≥ 5, `Suspendido` si es < 5). Escribe los mensajes de inicio y fin en `System.err`. |
| `FiltroReporte.java` | **Consumidor.** Lee de `System.in` línea a línea, escribe solo los `Aprobado` en `System.out` y al final escribe `REPORTE: Se encontraron X registros Aprobado.` en `System.err`. |
| `aprobados.txt` | Recibe la salida estándar final (la de `FiltroReporte`). |
| `debug.log` | Recibe la salida de error de los dos procesos. |

## Cómo ejecutarlo

Abre la terminal en esta carpeta:

```bash
# 1. Compilar
javac GeneradorNotas.java FiltroReporte.java

# 2. Ejecutar la cadena (ejemplo con 20 notas)
{ java GeneradorNotas 20 | java FiltroReporte; } > aprobados.txt 2> debug.log
```

Qué hace cada parte del comando:

- `java GeneradorNotas 20 | java FiltroReporte`: la **tubería** conecta la salida estándar del generador con la entrada estándar del filtro. El sistema operativo lanza los dos procesos a la vez y los comunica.
- `{ ... }`: agrupa los dos procesos para que las redirecciones se apliquen a ambos.
- `> aprobados.txt`: la salida estándar final (solo la del filtro, porque la del generador ya va por la tubería) se guarda en `aprobados.txt`.
- `2> debug.log`: la salida de error (`stderr`) de **los dos procesos** se guarda en `debug.log`.

Otra forma equivalente, redirigiendo cada proceso por separado (se usa `>>` para que ninguno borre lo que escribe el otro):

```bash
java GeneradorNotas 20 2>> debug.log | java FiltroReporte > aprobados.txt 2>> debug.log
```

> En Windows (CMD) no existe `{ }`. Usa la segunda forma: `java GeneradorNotas 20 2>> debug.log | java FiltroReporte > aprobados.txt 2>> debug.log`

## Resultado de una ejecución de prueba (15 notas)

`aprobados.txt`
```
6:Aprobado
5:Aprobado
7:Aprobado
9:Aprobado
10:Aprobado
7:Aprobado
5:Aprobado
9:Aprobado
7:Aprobado
```

`debug.log`
```
[GeneradorNotas] Inicio: se van a generar 15 registros.
[GeneradorNotas] Fin: se han generado 15 registros.
REPORTE: Se encontraron 9 registros Aprobado.
```

Como las notas son aleatorias, cada ejecución da un resultado distinto. Los `Suspendido` no aparecen en ningún archivo: el filtro los descarta, y por eso no se ven en la consola ni en `aprobados.txt`.
