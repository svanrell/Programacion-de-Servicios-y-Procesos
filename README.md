# Programación de Servicios y Procesos (PSP)

Repositorio que contiene las prácticas, actividades y proyectos de la asignatura de **Programación de Servicios y Procesos**.

---

## Estructura del Repositorio

### [Ejercicios básicos de Java](Ejercicios%20básicos%20de%20Java)
Repaso inicial de Programación Orientada a Objetos (POO), estructuras de control y herencia en Java:

- **[Ejercicio 1: Calculadora de Stock y Descuentos](Ejercicios%20básicos%20de%20Java/Ejercicio1.java)**
  - Gestión de inventario con cálculo de importes totales.
  - Uso de constantes (`final`), variables estáticas y aplicación de descuentos e IVA.

- **[Ejercicio 2: Calificación de Notas](Ejercicios%20básicos%20de%20Java/Ejercicio2.java)**
  - Validación de notas numéricas en rango de 0 a 10.
  - Estructura condicional `if-else` para clasificar en *Suspenso*, *Aprobado*, *Notable* o *Excelente*.

- **[Ejercicio 3: Tablas de Multiplicar y Suma](Ejercicios%20básicos%20de%20Java/Ejercicio3.java)**
  - Generación de tablas de multiplicar mediante bucles `for`.
  - Cálculo de la suma acumulada de números pares del 1 al 20 usando bucles `while`.

- **[Ejercicio 4: Modelado de Clase Coche](Ejercicios%20básicos%20de%20Java/Ejercicio4.java)**
  - Definición de clase base con encapsulamiento (`private`), constructores, getters/setters y método `toString()`.

- **[Ejercicio 5: Simulación de Combate RPG](Ejercicios%20básicos%20de%20Java/Ejercicio5.java)**
  - Clase `Personaje` con atributos de vida, daño y nivel.
  - Sistema de turnos de combate, recepción de daño y determinación del vencedor.

- **[Ejercicio 6: Jerarquía de Empleados](Ejercicios%20básicos%20de%20Java/Ejercicio6.java)** / **[Versión Modular](Ejercicios%20básicos%20de%20Java/Ejercicio06_SistemaEmpleados)**
  - Herencia con superclase `Empleado` y subclase `Vendedor`.
  - Sobrescritura de métodos (`@Override`) y uso de `super` para sumar comisiones al salario base.
  - Disponible tanto en archivo único como estructurado en ficheros independientes (`Empleado.java`, `Vendedor.java`, `Main.java`).

- **[Ejercicio 7: Jerarquía de Vehículos y Carrera](Ejercicios%20básicos%20de%20Java/Ejercicio7.java)**
  - Superclase `Vehiculo` con atributos (`marca`, `peso`, `combustible`, `kmRecorridos`) y método `mover()` basado en fórmulas de consumo.
  - Subclases `Coche` (con `numeroPuertas`) y `Moto` (con `tieneCarenado`).
  - Clase `Carrera` que simula una competición por turnos entre dos vehículos indicando la posición en cabeza y el combustible consumido.

- **[Ejercicio 8: Gestión de Streaming y Suscripciones](Ejercicios%20básicos%20de%20Java/Ejercicio8.java)**
  - Clase `Persona` para gestión de usuarios, saldo disponible, gasto acumulado y estado de suscripción.
  - Clase abstracta `Contenido` con fechas (`LocalDate`), cálculo de días con `ChronoUnit` y método `reproducir()`.
  - Subclases `Pelicula` y `Serie` con cuotas base mensuales, control de vigencia y sobrescritura de métodos (`@Override`).
  - Polimorfismo mediante catálogo de contenidos (`List<Contenido>`) y recomendaciones según la duración.

---

### [Ejercicios Procesos](Ejercicios%20Procesos)
Prácticas sobre creación, gestión, monitorización y comunicación entre procesos:

- **[Actividad 1: Sistema de Notas y Reportes](Ejercicios%20Procesos/Actividad01_SistemaNotasReportes)**
  - Comunicación entre dos procesos Java independientes conectados mediante tuberías (`|`) y redirecciones de entrada/salida.
  - Proceso productor (`GeneradorNotas.java`): genera registros con notas aleatorias hacia `System.out` y mensajes de estado en `System.err`.
  - Proceso consumidor (`FiltroReporte.java`): lee de `System.in`, filtra las notas aprobadas hacia `aprobados.txt` y emite el reporte por `stderr` (`debug.log`).

- **[Ejercicio: Monitor de Actividad](Ejercicios%20Procesos/Ejercicio_ProcesosMonitorActividad)**
  - Cuestionario y análisis práctico de la gestión del sistema operativo: diferencias clave entre programas, procesos e hilos.
  - Estudio del comportamiento de procesos padre e hijos (ej. Google Chrome / VS Code y sus helpers/renderers) y el ciclo de vida de los procesos en ejecución.

---

### [Ejercicios Hilos](Ejercicios%20Hilos)
Prácticas de programación concurrente, gestión y sincronización de hilos en Java:

- **[Actividad 2: Ejecución Secuencial vs. Concurrente](Ejercicios%20Hilos/Actividad02_SecuencialVsConcurrente)**
  - Comparación de tiempos de ejecución entre flujos secuenciales y concurrentes mediante `Thread` y `Runnable`.
  - Solapamiento de tiempos de espera (`Thread.sleep`), demostración de planificación no determinista y cálculo paralelo de sumas (`ContarNumeros.java`).
  - Incluye memoria explicativa con respuestas a preguntas teóricas (`Respuestas.md`).

- **[Ejercicio 4: Sistema de Reservas y Sincronización de Hilos](Ejercicios%20Hilos/Ejercicio4/reserva.java)**
  - Simulación de reserva concurrente de plazas de avión con 200 hilos agentes (`AgenteVentas`) para 100 asientos disponibles.
  - Sincronización de secciones críticas mediante bloque `synchronized` sobre un cerrojo (`Object`).
  - Control de concurrencia y acceso seguro al recurso compartido (`SistemaReservas`).
  - Sincronización y espera de finalización de todos los hilos con `join()`.

- **[Actividad 5: Servicio de Descargas](Ejercicios%20Hilos/Actividad05_ServicioDescargas)**
  - Simulación de un servidor de descargas con control de concurrencia mediante semáforos (`Semaphore(2)`) para limitar las descargas simultáneas.
  - Asignación concurrente de hilos trabajadores (`DescargaArchivo.java`) gestionados por el hilo despachador principal (`ServicioDescargas.java`).
  - Métodos sincronizados (`synchronized`) para el consumo atómico de la bolsa de tokens y el recuento global de descargas.

---

## Requisitos y Ejecución

### Requisitos
- **Java JDK 17 o superior** instalado.

### Compilación y Ejecución

#### 1. Ejercicios básicos de Java
```bash
cd "Ejercicios básicos de Java"
javac Ejercicio7.java
java Ejercicio7

cd Ejercicio06_SistemaEmpleados
javac *.java
java Main
```

#### 2. Ejercicios de Procesos
```bash
cd "Ejercicios Procesos/Actividad01_SistemaNotasReportes"
javac GeneradorNotas.java FiltroReporte.java
{ java GeneradorNotas 20 | java FiltroReporte; } > aprobados.txt 2> debug.log
```

#### 3. Ejercicios de Hilos
```bash
cd "Ejercicios Hilos/Actividad02_SecuencialVsConcurrente"
javac *.java
java EjecucionSecuencial
java EjecucionConcurrente
java ContarNumeros

cd "Ejercicios Hilos"
javac Ejercicio4/reserva.java
java Ejercicio4.reserva

cd "Ejercicios Hilos/Actividad05_ServicioDescargas"
javac *.java
java ServicioDescargas
```
