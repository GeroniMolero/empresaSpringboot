Documentación del Proyecto: Gestión de Nóminas con Spring Boot 3 
1. Resumen del Proyecto 

Este proyecto, "Gestión de Nóminas", es una aplicación web desarrollada con Spring Boot 3. Su propósito es gestionar los datos de empleados y el cálculo de sus salarios. La aplicación destaca por ofrecer dos interfaces de acceso bien diferenciadas: 

    Una interfaz de usuario web tradicional (MVC con Thymeleaf) para la gestión manual a través de un navegador. 
    Una API RESTful completa para que otras aplicaciones (frontends modernos, aplicaciones móviles, etc.) puedan consumir los datos de forma programática. 

2. Stack Tecnológico 

El proyecto se ha construido utilizando las siguientes tecnologías y librerías: 

     Java 17: Versión LTS del lenguaje de programación.
     Spring Boot 3.2.5: Framework principal que simplifica la configuración y el desarrollo.
     Maven: Herramienta de gestión de dependencias y ciclo de vida del proyecto.
     Spring MVC: Para la creación de la interfaz de usuario web.
     Thymeleaf: Motor de plantillas para renderizar las vistas HTML.
     Spring Data JPA: Para la persistencia y el acceso a datos.
     Hibernate: Implementación de JPA encargada de la comunicación con la base de datos.
     MySQL: Sistema de base de datos relacional.
     Lombok: Librería para reducir el código repetitivo (getters, setters, constructores, etc.).
     Spring Boot Validation: Para la validación de los datos de entrada en la API REST.
     SpringDoc OpenAPI (Swagger): Para la generación automática de la documentación de la API REST.
     

3. Estructura del Proyecto 

El código está organizado siguiendo las buenas prácticas de Spring Boot, separando las responsabilidades en paquetes: 
 
 
 
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
empresaSpringboot/
└── src/
    └── main/
        ├── java/
        │   └── com/springboot/empresa/
        │       ├── config/          # Clases de configuración (OpenAPI, DataLoader)
        │       ├── controller/      # Manejan las peticiones HTTP (Web y REST)
        │       ├── dto/             # Data Transfer Objects para la API
        │       ├── exception/       # Manejo de excepciones
        │       ├── model/           # Entidades de la base de datos (JPA)
        │       ├── repository/      # Interfaces de acceso a datos (Spring Data JPA)
        │       ├── service/         # Lógica de negocio
        │       └── EmpresaApplication.java # Clase principal de la aplicación
        └── resources/
            ├── static/          # Recursos estáticos (CSS, JS, imágenes)
            ├── templates/       # Plantillas Thymeleaf para las vistas web
            └── application.properties # Archivo de configuración principal
 
 
 
4. Funcionalidades Implementadas 
4.1. Interfaz Web (MVC) 

     Página de Inicio (index.html): Punto de entrada con enlaces a las secciones principales.
     Listado de Empleados (/empleados): Muestra una tabla con todos los empleados y sus acciones.
     Búsqueda de Empleados (/empleados/buscar): Formulario para buscar empleados por DNI, nombre, sexo, categoría o años.
     Edición de Empleados (/empleados/editar/{dni}): Formulario para modificar los datos de un empleado existente.
     Visualización de Salarios: Vistas para consultar el salario de un empleado y ver un listado de todas las nóminas.
     

4.2. API REST 

La API expone los recursos de empleados a través del endpoint /api/empleados y sigue las operaciones CRUD estándar: 

     GET /api/empleados: Lista todos los empleados.
     GET /api/empleados/{dni}: Busca un empleado por su DNI.
     POST /api/empleados: Crea un nuevo empleado (con validación de datos).
     PUT /api/empleados/{dni}: Actualiza un empleado existente.
     DELETE /api/empleados/{dni}: Borra un empleado y su nómina asociada.
     

5. Flujo de Trabajo y Puntos Clave de Arquitectura 

    Separación de Controladores: 
         EmpleadosController (@Controller): Gestiona las peticiones web que devuelven vistas HTML.
         EmpleadoRestController (@RestController): Gestiona las peticiones de la API que devuelven datos JSON.
         NominasController (@RestController): Gestiona las operaciones específicas de nóminas para la API.
          

    Uso de DTOs (Data Transfer Objects): 
         Se utilizan para la API REST (EmpleadoDTO, EmpleadoResponseDTO) para desacoplar la capa de persistencia (entidades JPA) de la capa de exposición (API). Esto permite un control total sobre los datos que se envían y reciben, y añade validaciones de forma segura.
          

    Lógica de Negocio Centralizada: 
         El cálculo del sueldo reside en la clase Nomina (sueldoCalculado).
         Los servicios (EmpleadoService, NominaService) orquestan las operaciones, como actualizar un empleado y recalcular su nómina en una misma transacción.
          

    Inicialización de Datos: 
         Se utiliza un CommandLineRunner (DataLoader.java) que se ejecuta al arrancar la aplicación. Este verifica si la base de datos está vacía y, en ese caso, carga un conjunto de datos de prueba, utilizando la lógica de negocio de los servicios para garantizar la coherencia.
          

6. Problemas Comunes Encontrados y Soluciones 

Durante el desarrollo, se resolvieron varios problemas comunes: 

     

    java.net.BindException: Address already in use: El puerto 8080 ya estaba en uso por otro servicio. 
         Solución: Cambiar el puerto en application.properties a server.port=8081.
         
     

    HTTP 400 Bad Request en la búsqueda: El formulario HTML enviaba los parámetros campo y valor, pero el controlador esperaba uno solo llamado criterio. 
         Solución: Adaptar el controlador y el servicio para manejar los dos parámetros y aplicar la lógica de búsqueda según el campo seleccionado.
         
     

    HTTP 404 Not Found al editar desde resultados de búsqueda: El formulario usaba método POST para enviar el DNI, pero el controlador esperaba un GET con el DNI en la URL (/editar/{dni}). 
         Solución: Cambiar el <form> por un simple enlace <a> con la URL correcta generada por Thymeleaf.
         
     

    The import jakarta.validation cannot be resolved: Faltaba la dependencia de validación. 
         Solución: Añadir spring-boot-starter-validation al pom.xml.
         
     

    La configuración de Swagger no se aplicaba: Las propiedades en application.properties no eran leídas por la librería SpringDoc. 
         Solución: Crear una clase de configuración Java (OpenApiConfig.java) con un método @Bean que define el objeto OpenAPI personalizado. Este método es más robusto y explícito.
         
     

7. Cómo Ejecutar y Probar la Aplicación 

    Configurar la Base de Datos: Asegúrate de que MySQL está en ejecución y que las credenciales en src/main/resources/application.properties son correctas. 
    Ejecutar la Aplicación: Desde la raíz del proyecto, ejecutar el comando:
    bash
     
     

     
    1
    mvn spring-boot:run
     
     
      
    Acceder a la Interfaz Web: Abrir un navegador en http://localhost:8081. 
    Probar la API REST:
         Interfaz Swagger UI: Acceder a http://localhost:8081/swagger-ui/index.html para explorar y probar la API de forma interactiva.
         Con curl: Usar comandos curl para probar los endpoints desde la terminal (ver ejemplos en la conversación anterior).
          

8. Próximos Pasos y Mejoras Posibles 

El proyecto está completo y funcional, pero podría mejorarse con: 

     Seguridad: Implementar Spring Security para proteger los endpoints y gestionar la autenticación y autorización de usuarios.
     Paginación: Añadir paginación a los listados de empleados para manejar grandes volúmenes de datos de forma eficiente.
     Pruebas Automatizadas: Desarrollar una suite de pruebas unitarias y de integración (con JUnit, Mockito y TestContainers) para garantizar la calidad del código.
     Dockerización: Crear un Dockerfile para desplegar la aplicación en un contenedor, facilitando su distribución y escalabilidad.
     Manejo de Errores Mejorado: Crear excepciones personalizadas y un @ControllerAdvice más avanzado para manejar errores de forma más granular y amigable.
     
