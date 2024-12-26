# Voll Med

**Voll Med** es una aplicación diseñada para la gestión de consultas médicas, enfocada en automatizar tareas administrativas y facilitar la interacción entre pacientes, médicos y el personal administrativo. Incluye funcionalidades para la gestión de pacientes, médicos y consultas, garantizando seguridad y validaciones específicas.

---

## 🚀 Características principales

1. **Gestión de pacientes:**
   - Registro, actualización y baja lógica de pacientes.
   - Listado paginado y búsqueda de pacientes activos.

2. **Gestión de médicos:**
   - Registro, actualización y baja lógica de médicos.
   - Validación de disponibilidad según especialidad y horarios.
   - Listado paginado de médicos activos.

3. **Gestión de consultas:**
   - Programación de consultas con validaciones avanzadas.
   - Cancelación de consultas con motivos definidos.
   - Validación de horarios, disponibilidad de médicos y pacientes.

4. **Autenticación y seguridad:**
   - Sistema de autenticación mediante JWT (JSON Web Tokens).
   - Implementación de roles y validación de usuarios.

5. **Documentación interactiva:**
   - Swagger UI para explorar y probar la API.

---

## 🛠️ Tecnologías utilizadas

- **Java 17**: Lenguaje principal.
- **Spring Boot**: Framework para desarrollo backend.
  - Spring Data JPA para la persistencia de datos.
  - Spring Security para autenticación y autorización.
- **Hibernate**: ORM para interacción con la base de datos.
- **PostgreSQL**: Base de datos relacional.
- **Swagger**: Generación de documentación interactiva.
- **Maven**: Gestión de dependencias.
- **Lombok**: Reducción de código repetitivo.

---

## 📂 Estructura del proyecto

```
/src
├── main
│   ├── java
│   │   └── med.voll.api
│   │       ├── controller         # Controladores REST
│   │       ├── domain             # Lógica de negocio y entidades
│   │       │   ├── consulta       # Gestión de consultas
│   │       │   ├── medico         # Gestión de médicos
│   │       │   ├── paciente       # Gestión de pacientes
│   │       │   ├── direccion      # Gestión de direcciones
│   │       │   └── usuarios       # Gestión de usuarios y autenticación
│   │       ├── infra              # Configuraciones de infraestructura
│   │       │   ├── security       # Seguridad y JWT
│   │       │   └── exceptions     # Manejo de excepciones
│   └── resources
│       ├── application.properties # Configuración de la aplicación
│       └── data.sql               # Datos iniciales
└── test
    └── java
        └── med.voll.api           # Pruebas unitarias y de integración
```

---

## 📖 Endpoints principales

### **Autenticación**
- **POST** `/login`: Generación de JWT para autenticación.

### **Pacientes**
- **GET** `/pacientes`: Lista todos los pacientes activos (paginado).
- **POST** `/pacientes`: Registra un nuevo paciente.
- **PUT** `/pacientes`: Actualiza la información de un paciente.
- **DELETE** `/pacientes/{id}`: Da de baja a un paciente.

### **Médicos**
- **GET** `/medicos`: Lista todos los médicos activos (paginado).
- **POST** `/medicos`: Registra un nuevo médico.
- **PUT** `/medicos`: Actualiza la información de un médico.
- **PATCH** `/medicos/{id}`: Da de baja a un médico.

### **Consultas**
- **GET** `/consultas`: Lista todas las consultas (paginado).
- **POST** `/consultas`: Programa una nueva consulta.
- **DELETE** `/consultas`: Cancela una consulta existente.

---

## ⚙️ Configuración e instalación

### Prerrequisitos

1. **Java 17** o superior.
2. **Maven**.
3. **PostgreSQL**.

### Pasos para ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/voll-med.git
   ```

2. Configura la base de datos en el archivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/vollmed
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

5. Accede a Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 🧪 Pruebas

### **Pruebas de integración y unitarias**

1. **ConsultaControllerTest**:
   - Verifica el comportamiento de los endpoints relacionados con consultas.
   - Casos probados:
     - Solicitud sin datos devuelve código 400.
     - Solicitud válida devuelve código 200 y los detalles correctos.

2. **MedicoRepositoryTest**:
   - Prueba la lógica de selección de médicos disponibles.
   - Casos probados:
     - Retorna `null` cuando no hay médicos disponibles.
     - Retorna un médico disponible correctamente.

Ejecuta todas las pruebas con el siguiente comando:
```bash
mvn test
```

---

## 🤝 Contribuciones

¡Contribuciones son bienvenidas! Si deseas colaborar, abre un issue o un pull request en el repositorio.

---

## 📜 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más información.
