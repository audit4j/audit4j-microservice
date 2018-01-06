# Overview

Audit4j microservice is developed to fix following limitations in [Audit4j Core](https://github.com/audit4j/audit4j-core).

1. Audit4j core is not supported to embed in applications developed other than Java.
2. Audit4j core is not suitable for component based enterprise applications (Ex: SOA) which required to store Audit logs in a central location. 

# Getting Started

## Prerequisites
You need the following installed and available in your $PATH:

* Java 8 (http://java.oracle.com)
* Apache maven 3.0.4 or greater (http://maven.apache.org/)

## Build

Execute below two maven goals.

```mvn clean package```

```mvn assembly:single```

## Run the microservice

1. Copy and extract `audit4j-microservice-1.0.0-distribution.tar.gz` under `target` directory
2. Go to bin directory
3. execute `start.bat`

Dashboard: http://localhost:8080

### Default Ports
  * 8080 - Dashboard and REST api
  * 9091 - WebSocket transport
  * 9092 - RPC transport

### Configurations
Configurations files are located in conf directory, [Audit4j core configurations](http://audit4j.org/documentation/#configuration) and Server configurations are served as seperately.

## Client Api
We have developed client api consists of two types of transports for various requirements.

### RPC transport
RPC transport is the fastest transport and the protocol is consists of binary and compressed messages. The transport is developed on top of Apache thrift which added out-of-the-box support for various [languages](https://thrift.apache.org/docs/Languages). 

#### Basic Configurations

conf/server.config.yml

```yml
!ServerConfiguration
transports: 
- !org.audit4j.microservice.transport.thrift.ThriftTransportServer
  serverPort = 9999 #server port set to 9999 Default 9092
  serverHost = localhost
  multiThreadded = true #Server to initialize as multithreadded server default is single threadded.

```
#### Genarating client stub

1. [Download and install](https://thrift.apache.org/docs/install/) Apache Thrift Compiler.
2. Copy Audit4j microservice [api](https://github.com/audit4j/audit4j-microservice/blob/master/api/api.thrift) to a desired location.
3. Generate client stub using below command.

```thrift --gen <language> api.thrift```

#### SSL Configurations

Securing Audit traffic is only recommended if the traffic is exposed as a public service. Using below steps, RPC communication can be secured via SSL. 

1. Creating key store for server
   ```keytool -genkeypair -alias certificatekey -keyalg RSA -validity 7 -keystore keystore.jks```
   
2. Export certificate
   ```keytool -export -alias certificatekey -keystore keystore.jks -rfc -file cert.cer```
   
3. Creating trust store for client
   ```keytool -import -alias certificatekey -file cert.cer -keystore truststore.jks```

4. Configure RPC transport

```yml
!ServerConfiguration
transports: 
- !org.audit4j.microservice.transport.thrift.ThriftTransportServer
  secureServer = true
  secureKeyStore = conf/trust/keystore.jks
  secureKeyPassword = 123456 #Given password while creating the keystore
```

### Web Socket transport


## Configurations

Configurations are under conf directory


