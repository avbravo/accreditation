#Instalacion
Esta configurado para que ecuche en el puerto 9002




# accreditation
sistema de accreditaciones



#Execute docker compose
docker-compose up -d

java -jar -Xmx512m target/accreditation.jar 



Para ejecutar uberjar
java -jar payara-micro-6.2023.1.jar --deploy accreditation.war --outputUberJar accreditation.jar


#Ejecutar el war


java -jar /home/avbravo/software/payara/payara-micro-6.2023.1.jar  --deploy /home/avbravo/NetBeansProjects/u/utp/accreditation-stack/master/accreditation/target/accreditation.war --nocluster --logo --port 8080


#Crear  el Uberjar
java -jar   /home/avbravo/software/payara/payara-micro-6.2023.1.jar --deploy /home/avbravo/NetBeansProjects/u/utp/accreditation-stack/master/accreditation/target/accreditation.war --outputUberJar /home/avbravo/Descargas/accreditation.jar 



# Ejecutar JAR

 java -jar -Xmx512m accreditation.jar --nocluster --logo --port 8080 >>log.txt


## Mediante maven

Crear el war
```shell

mvn clean verify

cd target

````


Ejecutar 

```shell

mvn clean verify payara-micro:start

cd target

````

## Crear el uber jar

De esta manera el url solo tendria el ip , util para microservicios

```shell

mvn clean verify payara-micro:bundle

cd target

java -jar accreditation-bundle.jar
````