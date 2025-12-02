Gestión de Nóminas 

Este proyecto, Gestión de Nóminas, es una aplicación web completa desarrollada con Spring Boot 3. El objetivo es crear una herramienta robusta para la gestión de empleados y el cálculo de sus salarios, demostrando una arquitectura limpia que separa la interfaz de usuario de una API RESTful. 

 
 
 

  
Tabla de Contenidos 

     Características 
     Stack Tecnológico 
     Estructura del Proyecto 
     Funcionalidades Implementadas 
     Flujo de Trabajo y Puntos Clave de Arquitectura 
     Problemas Comunes Encontrados y Soluciones 
     Cómo Ejecutar y Probar la Aplicación 
     Próximos Pasos y Mejoras Posibles 
     

Características 

El proyecto ofrece dos interfaces de acceso para una máxima flexibilidad: 
Interfaz Web Tradicional (MVC + Thymeleaf) 

     Gestión Completa de Empleados: Permite listar, buscar, editar y ver los detalles de los empleados a través de una interfaz web amigable.
     Búsqueda Avanzada: Implementa un formulario para buscar empleados por DNI, nombre, sexo, categoría o años de antigüedad.
     Visualización de Nóminas: Incluye vistas para consultar el salario de un empleado y ver un listado completo de todas las nóminas.
     Carga Automática de Datos: Al arrancar por primera vez, la aplicación se pobla con datos de prueba para facilitar el uso inmediato.
     

API RESTful 

Una API completa y documentada para que otras aplicaciones puedan consumir los datos. 

     Operaciones CRUD: Expone los endpoints GET, POST, PUT, DELETE para la gestión de empleados.
     DTOs (Data Transfer Objects): Para la API, no se exponen las entidades de la base de datos directamente. Se utilizan DTOs para tener un control total sobre los datos que se envían y reciben, además de añadir validaciones.
     Documentación Interactiva: Integra Swagger UI para que se pueda explorar y probar la API directamente desde el navegador.
     

Stack Tecnológico 

El proyecto se ha construido utilizando las siguientes tecnologías: 

     Java 17
     Spring Boot 3.2.5
     Spring MVC (para la interfaz web)
     Thymeleaf (motor de plantillas)
     Spring Data JPA (para la persistencia)
     Hibernate (implementación JPA)
     MySQL (base de datos)
     Maven (gestión de dependencias)
     Lombok (para escribir menos código repetitivo)
     Spring Boot Validation (para la validación en la API)
     SpringDoc OpenAPI (Swagger) (para la documentación de la API)
     

Estructura del Proyecto 

El código está organizado siguiendo las buenas prácticas de Spring Boot, separando las responsabilidades en paquetes: 
 
 
 
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
 
 
 
Funcionalidades Implementadas 
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
     

Flujo de Trabajo y Puntos Clave de Arquitectura 

La arquitectura del proyecto se basa en varias decisiones clave para mantener el código limpio y mantenible: 

     

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
         
     

Problemas Comunes Encontrados y Soluciones 

Durante el desarrollo, se han resuelto varios problemas comunes: 

     

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
         
     

Cómo Ejecutar y Probar la Aplicación 

    Configurar la Base de Datos: Asegurarse de que MySQL esté en ejecución y que las credenciales en src/main/resources/application.properties sean correctas. 
    Ejecutar la Aplicación: Desde la raíz del proyecto, ejecutar el comando:
    bash
     
     

 
1
mvn spring-boot:run
 
 
  
Acceder a la Interfaz Web: Abrir un navegador en http://localhost:8081. 
Probar la API REST:

     Interfaz Swagger UI: Acceder a http://localhost:8081/swagger-ui/index.html para explorar y probar la API de forma interactiva.
     Con curl: También se pueden usar comandos curl para probar los endpoints desde la terminal.
     

 
