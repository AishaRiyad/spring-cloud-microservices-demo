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
