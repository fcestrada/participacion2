# API REST - Gestión de Estados  - Ejercicio 2

## Integrantes
- **Christian Castro Hernández**
- **José Fernando Castañeda Estrada**
- **Pedro Alonso Gutiérrez Agramón**
- **Rodrigo Martínez Zambrano**

## Descripción del Proyecto
Este proyecto implementa una API REST para gestionar información de alumnos (actividad en clase) y estados (ejercicio en equipos). La API está construida utilizando Spring Boot y proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la entidad Estado, implementando el mapeo hacia un DTO, y disponibilizando una consulta de estados con paginación.

## Modelo de Datos
La entidad principal del modelo es `Estado` con los siguientes atributos:

```java
public class Estado {
    private int idEstado;
    private String estado;
    private String abreviatura;
}
```

## Controlador
El controlador principal es `EstadoDTOController` y está mapeado a `/api/v2/estados`:

```java
@RestController
@RequestMapping(path = "/api/v2/estados",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoDTOController {
    // Implementación de los métodos para cada endpoint
}
```

## Ejecución del Proyecto
Para ejecutar el proyecto, asegúrate de tener instalado Java y Maven. Luego, sigue estos pasos:

1. Clona el repositorio
2. Navega al directorio del proyecto
3. Ejecuta `mvn spring-boot:run`
4. La API estará disponible en `http://localhost:8080/api/v2/estados
