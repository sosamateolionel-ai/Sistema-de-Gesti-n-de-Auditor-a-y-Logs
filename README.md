# Sistema de Gestión de Auditoría y Logs

## Descripción

Este proyecto implementa un sistema de gestión de registros de auditoría (*logs*) utilizando Java y los principios de la Programación Orientada a Objetos.

El sistema permite enviar registros a distintos destinos: un archivo local, una base de datos y un servicio en la nube. Cada destino implementa sus propias reglas de almacenamiento y validación, mientras que un gestor central administra el envío de los registros.

## Funcionamiento

El programa permite realizar dos tipos de envío:

* **Difusión masiva:** envía un mismo registro a todos los destinos registrados.
* **Envío selectivo:** envía un registro únicamente al destino indicado por su nombre.

Cada destino aplica sus propias reglas. El archivo local comprueba que exista una ruta configurada, la base de datos valida los niveles de registro y el servicio en la nube solo acepta registros de nivel `ERROR` y `CRITICAL`.

## Interfaz y clases principales

### 1. `DestinoLog`

Interfaz que define el contrato común para todos los destinos de registro.

**Métodos:**

* `guardarRegistro()`: procesa un registro con su nivel y mensaje.
* `getNombre()`: devuelve el nombre del destino.

### 2. `LogArchivoLocal`

Implementa el almacenamiento simulado de registros en un archivo local.

**Atributos:**

* `rutaArchivo`: ruta del archivo donde se guardarían los registros.
* `lineasEscritas`: contador de líneas procesadas correctamente por el método.

**Funcionamiento:**

* Verifica que la ruta no sea nula ni esté vacía.
* Incrementa el contador cuando la ruta es válida.
* Muestra en consola la línea que se añadiría al archivo, junto con el nivel y el mensaje.

### 3. `LogBaseDatos`

Implementa el registro de eventos mediante una sentencia SQL simulada.

**Atributos:**

* `tabla`: nombre de la tabla de destino.
* `nivelesValidos`: lista de niveles permitidos (`INFO`, `WARN`, `ERROR` y `CRITICAL`).

**Funcionamiento:**

* Comprueba que el nivel recibido esté en la lista de niveles válidos.
* Rechaza los registros con niveles desconocidos.
* Si el nivel es válido, muestra una sentencia `INSERT` en consola.

### 4. `LogServicioNube`

Simula el envío de registros a un servicio remoto.

**Atributo:**

* `endpoint`: dirección del servicio que recibiría los registros.

**Funcionamiento:**

* Acepta únicamente los niveles `ERROR` y `CRITICAL`.
* Omite los registros de otros niveles.
* Muestra una petición HTTP `POST` simulada con los datos del registro en formato JSON.

### 5. `GestorAuditoria`

Administra los destinos de registro mediante una lista de tipo `DestinoLog`.

**Métodos principales:**

* `agregarDestino()`: incorpora un destino y muestra su nombre.
* `registrar()`: envía un registro a todos los destinos registrados.
* `registrarEn()`: busca un destino por nombre y envía el registro únicamente a ese destino.

Si no hay destinos registrados, `registrar()` informa la situación. Si se solicita un destino inexistente mediante `registrarEn()`, muestra un mensaje de error.

### 6. `Main`

Contiene el método principal del programa.

Realiza las siguientes pruebas:

1. Registra los destinos de archivo local, base de datos y nube.
2. Envía un registro `INFO`, que la nube omite.
3. Envía un registro `CRITICAL`, que procesan los tres destinos.
4. Envía un registro `DEBUG2`, que la base de datos rechaza.
5. Envía un registro `ERROR` únicamente a la base de datos.
6. Intenta enviar un registro a un destino inexistente llamado `syslog`.

## Ejemplo de funcionamiento

| Prueba                             | Resultado esperado                                                     |
| ---------------------------------- | ---------------------------------------------------------------------- |
| Registro `INFO`                    | Archivo y base de datos lo procesan; la nube lo omite.                 |
| Registro `CRITICAL`                | Los tres destinos procesan el registro.                                |
| Registro `DEBUG2`                  | El archivo lo procesa, la base de datos lo rechaza y la nube lo omite. |
| Envío selectivo a la base de datos | Solo la base de datos recibe el registro `ERROR`.                      |
| Envío a `syslog`                   | El gestor informa que el destino no está registrado.                   |

## Conceptos de programación utilizados

* **Interfaces:** `DestinoLog` define un contrato común para los distintos destinos.
* **Polimorfismo:** el gestor utiliza objetos de diferentes clases mediante la misma interfaz.
* **Encapsulamiento:** los atributos de las clases concretas son privados.
* **Colecciones:** se utiliza `List` y `ArrayList` para administrar los destinos.
* **Modularidad:** cada clase tiene una responsabilidad específica.
* **Validación de datos:** cada destino aplica sus propias reglas antes de procesar un registro.
* **Búsqueda en colecciones:** `registrarEn()` recorre los destinos para encontrar el solicitado.
* **Simulación de protocolos:** se representan operaciones de archivo, SQL y HTTP mediante mensajes en consola.

## Tecnologías utilizadas

* Java
* Programación Orientada a Objetos
* IntelliJ IDEA

## Ejecución

<img width="1332" height="731" alt="image" src="https://github.com/user-attachments/assets/96510921-25b5-4f9c-b0ce-4e739cce2b7b" />

## Objetivo del ejercicio

Practicar interfaces, polimorfismo y modularidad mediante un sistema de auditoría extensible que permite incorporar nuevos destinos de registro sin modificar la lógica principal del gestor.

**Aclaración:** el proyecto simula las operaciones de escritura en archivos, inserción en bases de datos y envío a la nube. No realiza operaciones reales sobre esos sistemas.
