# Lab06-AREP

### Descripción

El proyecto consiste en un prototipo de una aplicación de registro de logs distribuida y balanceada utilizando Round Robin. La arquitectura está diseñada para manejar un alto volumen de registros de logs, distribuyendo la carga de manera equitativa entre múltiples instancias de RoundRobinMongoDB (o LogService). Todo el entorno está desplegado utilizando Docker para facilitar la portabilidad y la escalabilidad.

### Arquitectura del Prototipo

La arquitectura del prototipo consta de los siguientes componentes:

- **APP-LB-RoundRobin:** La aplicación principal que se encarga de recibir las solicitudes de registro de logs. Utiliza el algoritmo de Round Robin para balancear la carga entre múltiples instancias de RoundRobinMongoDB.
  
- **RoundRobinMongoDB (o LogService):** Tres instancias de RoundRobinMongoDB (o LogService), que actúan como servicios de almacenamiento de logs. Estas instancias están diseñadas para manejar las solicitudes de registro de logs y almacenar los datos de manera distribuida.

- **MongoDB:** Una instancia de MongoDB utilizada como base de datos para almacenar los logs. Esta base de datos se utiliza para almacenar los registros de logs de manera persistente.

  ![image](https://github.com/Tianrojas/Lab06-AREP/assets/62759668/5a808eaf-da3e-4c79-ad10-759df0dfbacf)


### Estructura del Repositorio

El repositorio consta de dos proyectos:

1. **APP-LB-RoundRobin:** Este proyecto contiene el código fuente de la aplicación principal, que se encarga de recibir las solicitudes de registro de logs y distribuir la carga entre las instancias de RoundRobinMongoDB utilizando el algoritmo de Round Robin.

2. **RoundRobinMongoDB:** Este proyecto contiene el código fuente de las instancias de RoundRobinMongoDB (o LogService), que son servicios encargados de manejar las solicitudes de registro de logs y almacenar los datos en la base de datos MongoDB.

### Componentes Principales

Los componentes principales del proyecto son:

- **APP-LB-RoundRobin:** La aplicación principal que se encarga de recibir las solicitudes de registro de logs y distribuir la carga entre las instancias de RoundRobinMongoDB utilizando el algoritmo de Round Robin.

- **RoundRobinMongoDB (o LogService):** Tres instancias de RoundRobinMongoDB (o LogService), que actúan como servicios de almacenamiento de logs. Estas instancias están diseñadas para manejar las solicitudes de registro de logs y almacenar los datos de manera distribuida.

- **MongoDB:** Una instancia de MongoDB utilizada como base de datos para almacenar los logs de manera persistente.

### Flujo de Trabajo

El flujo de trabajo del prototipo es el siguiente:

1. La aplicación principal (APP-LB-RoundRobin) recibe una solicitud de registro de logs.
2. Utilizando el algoritmo de Round Robin, la aplicación distribuye la carga entre las instancias de RoundRobinMongoDB.
3. Cada instancia de RoundRobinMongoDB maneja la solicitud de registro de logs y almacena los datos en la base de datos MongoDB.
4. Los logs se almacenan de manera persistente en la base de datos MongoDB para su posterior consulta y análisis.

### Ejemplo de Uso

  

### Instrucciones de Ejecución

Para ejecutar el prototipo, sigue los siguientes pasos:

1. Clona el repositorio en tu máquina local desde git o las imagenes desde Doker.
2. Asegúrate de tener Docker instalado en tu sistema.
3. Navega a la carpeta del proyecto en tu terminal.
4. Compila las imagenes de cada proyecto, para esto debes ejecutar `docker build --tag tianrojas/logroundrobin .` y `docker build --tag tianrojas/roundrobinbd .`
5. Ejecuta desde la raiz el comando `docker-compose up` para iniciar todos los servicios.
6. Una vez que todos los servicios estén en funcionamiento, la aplicación estará lista para recibir solicitudes de registro de logs.

¡Con estas instrucciones podrás ejecutar el prototipo y comenzar a registrar logs de manera distribuida y balanceada!
