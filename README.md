# Spring Cloud Microservices Demo

This repository contains a small, clean microservices demo built with **Spring Boot** and **Spring Cloud**.  
It showcases core patterns used in cloud-native architectures:

-Service Discovery with **Eureka Server**
-API Gateway with **Spring Cloud Gateway**
-Inter-service communication using **OpenFeign**
-Service registration and discovery with **Eureka Clients**


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Architecture Overview

The system consists of four services:

1. **discovery-server**  
   - Eureka Server (Service Registry)
   - Central place where all services register and discover each other  
   - Port: `8761`

2. **product-service**  
   - Simple REST API that exposes a list of products  
   - Registers itself to the Eureka Server as `product-service`  
   - Port: `8081`

3. **order-service**  
   - Exposes order data  
   - Calls `product-service` using **OpenFeign** via service name (through Eureka)  
   - Registers itself as `order-service`  
   - Port: `8082`

4. **api-gateway**  
   - API Gateway built with **Spring Cloud Gateway**  
   - Routes external requests to internal services via Eureka  
   - Port: `8080`

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

##  Tech Stack

- **Java**: 21  
- **Spring Boot**: 3.5.8  
- **Spring Cloud**: 2025.0.0 (Release Train)  
- **Spring Cloud Netflix Eureka** (Server + Client)  
- **Spring Cloud Gateway**  
- **Spring Cloud OpenFeign**  
- **Maven**


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

##  Project Structure

Approximate structure:

spring-cloud-microservices-demo/
├── discovery-server/
│   └── discovery-server/        # Eureka Server module
├── product-service/
│   └── product-service/         # Product Service module
├── order-service/
│   └── order-service/           # Order Service module
├── api-gateway/
│   └── api-gateway/             # API Gateway module
└── README.md

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

How to Run the System

1. Prerequisites
-JDK 21 installed
-Maven installed (or use the Maven wrapper mvnw)
-IDE like IntelliJ IDEA (optional but recommended)


2. Start the Services in Order
**Open a terminal in each service's folder (or use your IDE).

1) Start Eureka Server
   cd discovery-server/discovery-server
   mvn spring-boot:run

   Eureka Dashboard: http://localhost:8761


2) Start Product Service
   -Registers itself with Eureka as product-service
   -Local endpoint: http://localhost:8081/products

3) Start Order Service
   -Registers itself as order-service
   -Uses OpenFeign to call product-service via Eureka
   -Local endpoint: http://localhost:8082/orders


4) Start API Gateway
   API Gateway will:
    -Register as api-gateway
    -Use Discovery Locator to route requests to services based on their service IDs
   Gateway Port: 8080





Exposed Endpoints
** Via Services Directly

Eureka Dashboard
GET http://localhost:8761

Product Service
GET http://localhost:8081/products
Returns a static list of products, for example:

[
  { "id": 1, "name": "Laptop", "price": 1200.0 },
  { "id": 2, "name": "Smartphone", "price": 800.0 },
  { "id": 3, "name": "Headphones", "price": 150.0 }
]


Order Service
GET http://localhost:8082/orders
Returns example orders that internally call product-service using Feign.



** Via API Gateway

Using Spring Cloud Gateway + Eureka, you can access services via the gateway:

Default pattern with Discovery Locator:
http://localhost:8080/{service-id}/{path}

Examples:
-GET http://localhost:8080/product-service/products
-GET http://localhost:8080/order-service/orders

The gateway looks up the service in Eureka and forwards the request.


------------------------------------------------------------------------------------------------

How Service Discovery Works Here

-Each microservice has:
spring:
  application:
    name: product-service   # or order-service, api-gateway

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/


-They register themselves with the Eureka Server at startup.
-Other services (like order-service) and the api-gateway resolve targets by service name, not hard-coded host/port.


Example Feign Client in order-service:
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDto> getProducts();
}


No need to know http://localhost:8081 directly — Eureka handles it.

-------------------------------------------------------------------------------------------

Testing the System

After starting all services:
1)Go to Eureka Dashboard: http://localhost:8761

You should see:
-PRODUCT-SERVICE
-ORDER-SERVICE
-API-GATEWAY

2)Call product-service via the gateway:
curl http://localhost:8080/product-service/products

3)Call order-service via the gateway:
curl http://localhost:8080/order-service/orders

You should get JSON responses without directly talking to 8081 or 8082.

----------------------------------------------------------------------------------------


Future Improvements (Ideas)

-Add a database (e.g., PostgreSQL) to product-service and order-service
-Add centralized configuration using Spring Cloud Config
-Add resilience patterns (Circuit Breaker, Retry) using Resilience4j
-Add unit tests & integration tests


----------------------------------------------------------------------------------------

Author

-Name: Aisha
-Project Type: Educational / Demo – Spring Cloud Microservices
-Tech Focus: Microservices, Spring Boot, Spring Cloud, Service Discovery, API Gateway, Feign
