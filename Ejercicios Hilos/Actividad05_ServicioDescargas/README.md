# Actividad 5: Servicio de descargas

## Archivos

- `ServicioDescargas.java`: el servicio principal (contiene el `main`). Simula una solicitud cada 5 s, lanza un hilo por solicitud y se detiene tras 20 descargas o cuando se acaban los tokens.
- `DescargaArchivo.java`: el `Runnable` que atiende cada descarga. Consume entre 1 y 5 tokens, informa del archivo, de los tokens y del hilo (`Thread.getName()`), espera 3 s y suma la descarga al total del servidor.

## Cómo se cumple cada requisito

| Requisito | Implementación |
|---|---|
| Una solicitud cada 5 segundos | `Thread.sleep(5000)` en el bucle de `iniciar()`. |
| Un hilo nuevo por solicitud; el servicio sigue escuchando | `new Thread(new DescargaArchivo(...)).start()`. El hilo principal no espera a la descarga y sigue recibiendo solicitudes. |
| Máximo 2 descargas simultáneas | `Semaphore(2)`: `acquire()` antes de descargar y `release()` en un `finally`. Si ya hay 2 en curso, el tercer hilo se bloquea hasta que una termine. |
| 50 tokens iniciales; cada descarga consume entre 1 y 5 | `random.nextInt(1, 6)` y el método `synchronized consumirTokens()`. |
| Contador total de descargas compartido | `synchronized registrarDescarga()`, para evitar condiciones de carrera entre hilos. |
| Parada tras 20 descargas o sin tokens | El bucle comprueba `numSolicitud < 20` y que queden tokens. Si una solicitud necesita más tokens de los que quedan, se rechaza y el servicio se detiene. Al final, `join()` espera a que terminen las descargas en curso. |

## Ejecutar

```bash
javac *.java
java ServicioDescargas
```

Una ejecución completa dura alrededor de 1,5 minutos (hasta 20 solicitudes × 5 s). Como cada descarga gasta de media 3 tokens, lo normal es que se acaben los tokens antes de llegar a 20 descargas (unas 15-17).

## Ejemplo de salida (final)

```
Nueva solicitud de descarga #15
Descargando archivo_15.zip en Thread-14 (consume 5 tokens, quedan 3)
Descarga #15 completada. (total descargas: 15)

Nueva solicitud de descarga #16
Descargando archivo_16.zip en Thread-15 (consume 3 tokens, quedan 0)
Descarga #16 completada. (total descargas: 16)

----------------------------------------
Motivo: no quedan tokens suficientes.
Descargas realizadas: 16
Tokens restantes: 0
Servicio detenido
```

> Nota: con solicitudes cada 5 s y descargas de 3 s, cada descarga termina antes de que llegue la siguiente, así que el semáforo casi nunca tiene que bloquear a nadie. Para ver el semáforo en acción, baja `INTERVALO_SOLICITUDES_MS` a, por ejemplo, `1000`: verás que nunca hay más de 2 descargas a la vez.
