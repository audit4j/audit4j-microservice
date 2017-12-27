# audit4j-microservice
Language independent and centralized auditing server as a microservice.

## Build

Execute below two maven goals.

```mvn clean package```

```mvn assembly:single```

## Run the microservice

1. Copy and extract `audit4j-microservice-1.0.0-distribution.tar.gz` under `target` directory
2. Go to bin directory
3. execute start.bat

Dashboard: http://localhost:8080

### Default Ports
  * 8080 - Dashboard and restful api
  * 9091 - WebSocket transport
  * 9092 - RPC transport

## Configurations

Configurations are under conf directory


