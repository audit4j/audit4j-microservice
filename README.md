# audit4j-microservice

Audit4j microservice is developed to fix following limitations in [Audit4j Core](https://github.com/audit4j/audit4j-core).

1. Audit4j core is not supported to embed in applications developed other than Java.
2. Audit4j core is not suitable for component based enterprise applications (Ex: SOA) which required to store Audit logs in a central location. 


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

## Client Api
We have developed client api consists of two types of transports

### RPC transport


### Web Socket transport


## Configurations

Configurations are under conf directory


