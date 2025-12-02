Gestión de Nóminas 

Una aplicación web completa desarrollada con Spring Boot 3 para la gestión de empleados y el cálculo de sus nóminas. Este proyecto demuestra una arquitectura limpia, separando la interfaz de usuario web de una API RESTful robusta y documentada. 

 
 
 

  
Tabla de Contenidos 

     Características 
     Stack Tecnológico 
     Estructura del Proyecto 
     Configuración y Puesta en Marcha 
     Uso de la Aplicación 
     Arquitectura y Decisiones Clave 
     Aprendizajes y Retos 
     

Características 

La aplicación ofrece dos interfaces de acceso para una máxima flexibilidad: 
Interfaz Web Tradicional (MVC + Thymeleaf) 

     Gestión Completa de Empleados: Listar, buscar, editar y ver detalles de los empleados a través de una interfaz amigable.
     Búsqueda Avanzada: Permite buscar empleados por DNI, nombre, sexo, categoría o años de antigüedad.
     Visualización de Nóminas: Consulta el salario de un empleado o ve un listado completo de todas las nóminas.
     Carga Automática de Datos: Al arrancar por primera vez, la aplicación se pobla con datos de prueba para poder empezar a trabajar inmediatamente.
     

API RESTful 

Una API completa y documentada para el consumo por otras aplicaciones. 

     Operaciones CRUD: GET, POST, PUT, DELETE para la gestión de empleados.
     DTOs (Data Transfer Objects): Control total sobre los datos que se exponen y se reciben en la API.
     Validación de Entrada: Validación automática de los datos enviados a la API con mensajes de error claros.
     Documentación Interactiva: Integración con Swagger UI para explorar y probar la API desde el navegador.
     

Stack Tecnológico 

Este proyecto ha sido construido con las siguientes tecnologías: 

     Java 17
     Spring Boot 3.2.5
     Spring MVC (para la interfaz web)
     Thymeleaf (motor de plantillas)
     Spring Data JPA (para la persistencia)
     Hibernate (implementación JPA)
     MySQL (base de datos)
     Maven (gestión de dependencias)
     Lombok (para reducir código repetitivo)
     Spring Boot Validation (para la validación en la API)
     SpringDoc OpenAPI (Swagger) (para la documentación de la API)
     

Estructura del Proyecto 

El código sigue una estructura de paquetes limpia y escalable, separando las responsabilidades: 
 
 
 
1
2
3
4
5
6
7
8
src/main/java/com/springboot/empresa/
├── config/          # Clases de configuración (OpenAPI, DataLoader)
├── controller/      # Controladores MVC y REST
├── dto/             # Data Transfer Objects para la API
├── exception/       # Manejo global de excepciones
├── model/           # Entidades de la base de datos (JPA)
├── repository/      # Interfaces de Spring Data JPA
└── service/         # Lógica de negocio
 
 
 
Configuración y Puesta en Marcha 

Sigue estos pasos para poner la aplicación en marcha en tu entorno local. 
Prerrequisitos 

     JDK 17 o superior.
     Maven 3.8 o superior.
     MySQL 8.0 o superior.
     

1. Clonar el Repositorio 
bash
 
 
 
1
2
git clone https://github.com/tu-usuario/empresaSpringboot.git
cd empresaSpringboot
 
 
 
2. Configurar la Base de Datos 

    Crea una base de datos en MySQL:
    sql
     
     

 
1
CREATE DATABASE empresaSB;
 
 
  
Configura el acceso a la base de datos en el archivo src/main/resources/application.properties:
properties
 
 

     
    1
    2
    3
    4
    5
    6
    7
    spring.application.name=empresa
    spring.datasource.url=jdbc:mysql://localhost:3306/empresaSB
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    server.port=8081
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
     
     
      

3. Ejecutar la Aplicación 

Desde la raíz del proyecto, ejecuta el siguiente comando de Maven: 
bash
 
 
 
1
mvn spring-boot:run
 
 
 

La aplicación arrancará en http://localhost:8081. 
Uso de la Aplicación 
Interfaz Web 

Una vez la aplicación esté corriendo, abre tu navegador en http://localhost:8081. Desde ahí podrás acceder a todas las funcionalidades de gestión de empleados. 
API REST 

Puedes explorar y probar la API REST de dos maneras: 

    Swagger UI (Recomendado): Abre http://localhost:8081/swagger-ui/index.html en tu navegador. Tendrás una interfaz interactiva para probar todos los endpoints. 
    Con curl: También puedes usar la línea de comandos. Por ejemplo, para listar todos los empleados:
    bash
     
     

     
    1
    curl -X GET http://localhost:8081/api/empleados
     
     
      

Arquitectura y Decisiones Clave 

Durante el desarrollo, tomé varias decisiones de arquitectura para asegurar un código limpio y mantenible: 

     Separación de Controladores: He creado controladores distintos para la web (@Controller) y para la API (@RestController), cada uno con una responsabilidad clara.
     Uso de DTOs: En la API REST, no expongo las entidades de JPA directamente. En su lugar, uso DTOs para controlar la entrada y salida de datos y añadir una capa de validación robusta.
     Lógica Centralizada: La lógica de negocio (como el cálculo de nóminas) reside en la capa de Service. Esto permite que tanto la interfaz web como la API reutilicen la misma lógica, evitando la duplicación de código.
     Carga de Datos Inicial: Utilizo un CommandLineRunner para poblar la base de datos con datos de prueba solo si está vacía, lo que facilita el desarrollo y las pruebas.
     