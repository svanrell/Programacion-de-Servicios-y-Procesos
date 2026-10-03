# Programación de Servicios y Procesos (PSP)

Repositorio que contiene las prácticas, actividades y proyectos de la asignatura de **Programación de Servicios y Procesos**.

---

## 📁 Estructura del Repositorio

### 📂 [Ejercicios básicos de Java](Ejercicios%20básicos%20de%20Java)
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

- **[Ejercicio 6: Jerarquía de Empleados](Ejercicios%20básicos%20de%20Java/Ejercicio6.java)**
  - Herencia con superclase `Empleado` y subclase `Vendedor`.
  - Sobrescritura de métodos (`@Override`) y uso de `super` para sumar comisiones al salario base.

- **[Ejercicio 7: Jerarquía de Vehículos y Carrera](Ejercicios%20básicos%20de%20Java/Ejercicio7.java)**
  - Superclase `Vehiculo` con atributos (`marca`, `peso`, `combustible`, `kmRecorridos`) y método `mover()` basado en fórmulas de consumo.
  - Subclases `Coche` (con `numeroPuertas`) y `Moto` (con `tieneCarenado`).
  - Clase `Carrera` que simula una competición por turnos entre dos vehículos indicando la posición en cabeza y el combustible consumido.

---

## 🚀 Requisitos y Ejecución

### Requisitos
- **Java JDK 17 o superior** instalado.

### Compilación y Ejecución
Para compilar y ejecutar cualquiera de los ejercicios desde la terminal:

```bash
# Navegar a la carpeta de ejercicios
cd "Ejercicios básicos de Java"

# Compilar y ejecutar (ejemplo con Ejercicio 7)
javac Ejercicio7.java
java Ejercicio7
```
