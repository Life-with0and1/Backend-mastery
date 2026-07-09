# Backend Mastery 🚀

This repository contains my backend development projects built using Spring Boot and modern backend technologies.

## 📂 Projects

### 1. Kafka User Registration Notification System

A microservices-based project where user registration triggers an asynchronous email notification using Apache Kafka.

#### Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Apache Kafka
- Docker
- Spring Mail

#### Architecture

```
Client
   │
   ▼
User Service
   │
   ├── Save User to MySQL
   │
   └── Publish UserRegisteredEvent
              │
              ▼
          Apache Kafka
              │
              ▼
     Notification Service
              │
              ▼
      Send Welcome Email
```

#### Features

- User Registration API
- Password Encryption (BCrypt)
- Validation
- Global Exception Handling
- Kafka Producer
- Kafka Consumer
- Email Notification
- Asynchronous Communication

---

More backend projects will be added as I continue learning.
