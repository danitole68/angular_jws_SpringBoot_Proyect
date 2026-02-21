# Angular JWS SpringBoot Project

Una aplicación web completa para la gestión de libros, películas y discos con autenticación segura basada en JWT.

## Descripción General

Este proyecto es una aplicación web que facilita la gestión de una colección multimedia personal. Los usuarios pueden iniciar sesión de forma segura en el sistema para acceder a funcionalidades que les permiten añadir, actualizar, eliminar y consultar su colección de libros, películas y discos. La aplicación ha sido diseñada para proporcionar una experiencia de usuario intuitiva y responsiva.

## Características Principales

- **Autenticación de Usuarios**: Sistema de login seguro con tokens JWT
- **Gestión de Libros**: Crear, leer, actualizar y eliminar registros de libros
- **Gestión de Películas**: Organizar y administrar tu colección de películas
- **Gestión de Discos**: Mantener un inventario de tus discos musicales
- **Interfaz Responsiva**: Diseño adaptable para dispositivos de escritorio y móviles
- **Seguridad**: Implementación de medidas de seguridad robustas en el backend

## Stack Tecnológico

### Backend
- **Java**: Lenguaje de programación principal
- **Spring Boot**: Framework para desarrollar aplicaciones empresariales
- **Spring Security**: Autenticación y autorización
- **JWT (JSON Web Tokens)**: Para la gestión segura de sesiones
- **JPA/Hibernate**: Mapeo objeto-relacional
- **Base de Datos**: (Especificar la base de datos utilizada)

### Frontend
- **Angular**: Framework para construir aplicaciones web dinámicas
- **TypeScript**: Lenguaje tipado para mayor confiabilidad
- **HTML5 y CSS3**: Estructuración y estilos web modernos

## Estructura del Proyecto

```
angular_jws_SpringBoot_Proyect/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/prueba/PruebaSpring/
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── BookController.java
│   │   │       │   ├── FilmController.java
│   │   │       │   └── DiscController.java
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── security/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── angular/
│   └── frontend/ (En desarrollo)
├── pom.xml
└── README.md
```

## Requisitos Previos

- Java 11 o superior
- Maven 3.6 o superior
- Node.js 14+ y npm (para el frontend Angular)
- Git

## Instrucciones de Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/danitole68/angular_jws_SpringBoot_Proyect.git
cd angular_jws_SpringBoot_Proyect
```

### 2. Configurar el Backend

```bash
# Navegar a la carpeta del proyecto Spring Boot
cd .

# Instalar dependencias
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

### 3. Configurar el Frontend Angular

```bash
# Navegar a la carpeta de Angular
cd angular/frontend

# Instalar dependencias
npm install

# Ejecutar el servidor de desarrollo
ng serve
```

El frontend estará disponible en `http://localhost:4200`

## Uso

1. **Acceder a la Aplicación**: Abre tu navegador y ve a `http://localhost:4200`
2. **Iniciar Sesión**: Ingresa tus credenciales de usuario
3. **Gestionar tu Colección**: 
   - Haz clic en "Libros", "Películas" o "Discos"
   - Utiliza el botón "Agregar" para crear nuevos registros
   - Edita o elimina registros según sea necesario

## Problemas Conocidos

### Frontend Angular

Actualmente, el directorio `angular/frontend` no ha sido agregado completamente al repositorio. Esto se debe a limitaciones con el control de versiones y la configuración del proyecto. Se están trabajando en soluciones alternativas para integrar correctamente el frontend.

Puedes ver en el historial de git que hay cambios pendientes relacionados con el directorio de Angular que no han sido commiteados exitosamente.

<img width="1378" height="510" alt="image" src="https://github.com/user-attachments/assets/1b69fec8-a2e5-4621-a1b5-5e7013341c6f" />


## Configuración de Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto (si es necesario) con las siguientes variables:

```
DATABASE_URL=jdbc:mysql://localhost:3306/nombreDB
DATABASE_USER=root
DATABASE_PASSWORD=contraseña
JWT_SECRET=tu_secret_key_aqui
```

## Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Haz un fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.

## Autor

**danitole68**

## Soporte

Si encuentras algún problema o tienes sugerencias, por favor abre un issue en el repositorio de GitHub.

---

**Última actualización**: 2026-02-21 19:04:43
