#Imagen Modelo
FROM eclipse-temurin:21.0.3_9-jdk

#Definir directorio raiz de nuestro contenedor
WORKDIR /root

#copiar y pegar archivos dentro del contenedor
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#Descargar las dependencias
RUN ./mvnw dependency:go-offline

#Copiar el codigo fuente dentro del contenedor
COPY ./src /root/src

#Constuir nuestra aplicacion
RUN ./mvnw clean install -DskipTest

#Levantar nuestra aplicacion
ENTRYPOINT ["java","-jar", "/root/target/SpringDocker-0.0.1 -SNAPSHOT.jar"]

#Informar el puerto en el que se levantaara la aplicaion
EXPOSE 8080