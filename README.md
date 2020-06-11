# Control Lanchas
Sistema BackEnd tipo REST API para el control de Lanchas

## Diagrama ER
La base de datos tiene un Diagrama ER de la siguiente manera:

![Diagrama ER](https://github.com/SorrowVehemente/clanchas/extra/db_lanchas_diagrama.png "Diagrama ER")

La base de datos se detalla más [aquí](https://github.com/SorrowVehemente/clanchas-db "Repositorio para la base de datos del proyecto CLanchas").
## Dev
### Ejecutar el proyecto
+ Ejecutar <code>./mvnw spring-boot:run</code>.

+ Si se tiene Maven instalado, en consola el comando en consola
<code>mvn spring-boot:run</code>

+ Si se tiene IntelliJ IDEA simplemente basta con presionar el comando
<code>shift + F10</code> para ejecutar el proyecto.

### Configuración del servidor
La configuración general para el servidor se encuentra en: <code>resources/application.properties</code>.

### Configuración base de datos
En el archivo <code>resources/application.properties</code>, vienen las configuraciones de la base de datos.

Es importante recalcar que se está usando la versión 8 de <code>MySQL</code>, por ende, la URL de conexión lleva ciertos parámetros para su correcto funcionamiento.

También se tiene que tener en cuenta que la zona horaria configurada <code>America/Mazatlan</code> se puede cambiar por la zona horaria deseada.
<code>America/Mexico_City</code> por ejemplo sería la zona horaria para Aguascalientes. Para ver más zonas horarias consultar: [Zonas horarias](https://www.zeitverschiebung.net/es/ "Página para ver diferentes zonas horarias")
## Prod
### Empaquetar servidor
Para hacer un build de la aplicación ó empaquetarla en un solo archivo <code>.jar</code> están las siguientes opciones.

+ Si se usa el IDE IntelliJ IDEA, al abrir el proyecto, en la parte derecha hay una opción de nombre <code>Maven</code> al abrirlo
y dentro del proyecto <code>clanchas</code>, se abre el <code>Lifecycle</code> y dentro se ejecuta el comando <code>package</code>

+ Si se tiene <code>maven</code> instalado, debe ejecutar el comando en consola:
<code>mvn install</code> en el directorio del proyecto.

+ Si no se tiene, se puede dirigir a la raíz del proyecto y ejecutar el comando en consola:
<code>./mvn install</code>

### Jar
El <code>.jar</code> generado y empaquetado al hacer el <code>build</code> se encuentra en <code>target/clanchas-X.X.X-SNAPSHOT.jar</code> donde <code>X.X.X</code> es la versión de la aplicaión  

## Relacionado
### Database
La base de datos se encuentra en: [CLanchas DB](https://github.com/SorrowVehemente/clanchas-db "Repositorio para la base de datos del proyecto CLanchas").

### Front End
La parte para el FrontEnd está en otro proyecto: [CLanchas Cliente](https://github.com/SorrowVehemente/clanchas-cliente "Repositorio del Proyecto FrontEnd para el Control de Lanchas")
