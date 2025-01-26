# README - Aplicación de Gestor de Pokémon Capturados

## Introducción

La aplicación "Gestor de Pokémon Capturados" está diseñada para que los usuarios puedan llevar un registro de los Pokémon que han capturado. Los usuarios pueden explorar la Pokédex, capturar Pokémon con un toque y gestionar su lista personal. Además, ofrece opciones de personalización, cambio de idioma y un sistema seguro de inicio de sesión.

## Características principales

### Autenticación

- Autenticación mediante Firebase Authentication.
- Inicio de sesión y registro de usuarios mediante correo electrónico.
- Inicio de sesión rápido usando Google.

### Pokédex

- Lista interactiva con 150 Pokémon obtenida de la API oficial de Pokémon.
- Diferenciación visual entre Pokémon capturados y no capturados.
- Captura de Pokémon con un solo toque en la Pokédex.
- Actualización dinámica de los Pokémon capturados en la Pokédex.

### Lista de Pokémon Capturados

- Muestra información detallada de los Pokémon capturados: nombre, índice, foto, tipo(s), peso y altura.
- Visualización mediante RecyclerView con CardView para cada Pokémon.
- Opción para eliminar Pokémon capturados si está habilitada en los ajustes.

### Ajustes

- Cambio de idioma entre español e inglés.
- Activación/desactivación de la opción para eliminar Pokémon capturados.
- Sección "Acerca de" con información del desarrollador y versión de la aplicación.
- Cierre de sesión de manera segura.

## Tecnologías utilizadas

- **Firebase**: Autenticación y almacenamiento en Firestore.
- **Retrofit**: Consumo de la API de Pokémon.
- **RecyclerView y CardView**: Presentación de listas de Pokémon en las pestañas de Capturados y Pokédex.
- **SharedPreferences**: Almacenamiento de ajustes en el dispositivo.
- **Fragments con NavHostFragment**: Navegación fluida entre las pestañas principales.
- **Material Design**: Diseño visual inspirado en el universo de Pokémon.

## Instrucciones de uso

### Requisitos previos

1. Android Studio instalado en tu computadora.
2. Una cuenta de Firebase configurada con un proyecto para la aplicación.
3. Dependencias especificadas en el archivo `build.gradle`.

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/AdriBelSer/tareaPMDMUd3.git
   ```
2. Abre el proyecto en Android Studio.
3. Configura Firebase:
   - Descarga el archivo `google-services.json` desde Firebase y colócalo en la carpeta `app`.
   - Asegúrate de habilitar Firebase Authentication y Firestore Database en la consola de Firebase.
4. Instala las dependencias necesarias ejecutando una sincronización de Gradle en Android Studio.
5. Ejecuta la aplicación en un emulador o dispositivo físico.

## Conclusiones del desarrollador

Durante el desarrollo de esta aplicación, he reforzado la implementación de Recyclerview y he asentado conceptos de la unidad anterior, he encontrado dificultades como por ejemplo la gestión de datos procedentes de la API, así como su guardado en bases de datos no relacionales como Firebase. El aprendizaje principal adquirido en esta tarea incluye la configuración inicial de Firebase y la gestión del consumo de la API de Pokémon con Retrofit. A través de este proyecto, profundicé en la organización de código y la gestión de datos entre la aplicación y servicios externos. 

## Capturas de pantalla
<img src="https://github.com/user-attachments/assets/f37fdbbf-d6c7-4071-a3d4-f5e116fc1a3a" alt="Screenshot 1" width="400">
<img src="https://github.com/user-attachments/assets/2e91a550-f1c7-4fca-8e01-088ec666696f" alt="Screenshot 2" width="400">
<img src="https://github.com/user-attachments/assets/8576d32f-e95e-4b4f-8db6-7ca7a2e79c2c" alt="Screenshot 3" width="400">
<img src="https://github.com/user-attachments/assets/731aca4c-0d14-4dd0-a70d-e3c18ff5e6cb" alt="Screenshot 4" width="400">
<img src="https://github.com/user-attachments/assets/d088d207-c337-4d93-b083-d6587cc2f0cb" alt="Screenshot 5" width="400">
<img src="https://github.com/user-attachments/assets/d2165d44-aa79-4917-bd46-7c0e419b66ac" alt="Screenshot 6" width="400">
<img src="https://github.com/user-attachments/assets/e5772edd-99e9-4d75-af43-3bb4bdc1d30b" alt="Screenshot 7" width="400">
<img src="https://github.com/user-attachments/assets/f51c2a1e-7c25-42ee-a31f-f02d4beadac9" alt="Screenshot 8" width="400">

