# 🚚 FleetFlow API

A RESTful API for managing and optimizing delivery operations including clients, vehicles, drivers, and shipments with full lifecycle tracking, security, and CI/CD automation.

---

## 📌 Project Overview

FleetFlow is a backend system designed for a logistics company to manage deliveries efficiently.  
It allows the creation and assignment of deliveries, tracking their status, and managing all related resources (clients, vehicles, drivers, users).

The system evolves across multiple phases:
- REST API development (Spring Boot)
- Database versioning (Flyway)
- DTO + Validation layer
- Dockerized deployment
- CI/CD with GitHub Actions
- Security with JWT + Spring Security
- Role-based access control

---

## ⚙️ Tech Stack

- Java 17 / 21
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- MapStruct
- Lombok
- MySQL
- Flyway
- Swagger / OpenAPI
- Docker
- GitHub Actions (CI/CD)
- JUnit 5

---

## 🧩 Main Entities

### 👤 Client
- id
- name
- email
- city
- phone

### 🚛 Vehicle
- id
- plate number (unique)
- type (Truck, Van, etc.)
- capacity
- status (AVAILABLE, IN_DELIVERY, MAINTENANCE)

### 👨‍✈️ Driver
- id
- name
- phone
- license type
- availability

### 📦 Delivery
- id
- deliveryDate
- originAddress
- destinationAddress
- status (PENDING, IN_PROGRESS, DELIVERED)
- assigned driver
- assigned vehicle
- client

### 🔐 User
- id
- username
- email
- password
- role (ADMIN, MANAGER, DRIVER)

---

## 🚀 Features

### 👥 Client Management
- Create / Update / Delete / List clients

### 🚛 Vehicle Management
- Create / Update / Delete vehicles
- Filter available vehicles
- Derived queries:
    - `findByStatus()`
    - `findByCapacityGreaterThan()`

### 👨‍✈️ Driver Management
- CRUD drivers
- List available drivers
- Derived query:
    - `findByAvailableTrue()`

### 📦 Delivery Management
- Create delivery
- Assign driver + vehicle
- Update status
- Filter by status
- Filter by client
- Custom queries:
    - deliveries between dates
    - deliveries by destination city

---

## 🔐 Security (JWT + Roles)

### Roles
- **ADMIN** → full access
- **MANAGER** → clients + deliveries management
- **DRIVER** → view & update own deliveries

### Features
- User registration & login
- JWT generation & validation
- Role-based authorization
- Endpoint protection with Spring Security

---

## 📄 DTO + Validation

Entities are NOT exposed directly.

### Validation Rules:
- `@NotBlank` → text fields
- `@NotNull` → required fields
- `@Email` → email validation
- `@Positive` → capacity values

### Error Handling
Global exception handler using:
```java
@RestControllerAdvice