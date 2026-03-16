# Real-Time Chat System (Spring Boot + WebSocket + Redis)

A scalable **real-time chat backend** built with **Spring Boot**, **WebSocket**, **Redis Pub/Sub**, **PostgreSQL**, and **Elasticsearch**.

This project demonstrates how modern messaging platforms like **WhatsApp, Slack, or Discord** are designed at the backend level.

---

# Features

* User registration & authentication
* Real-time messaging using WebSockets
* Redis Pub/Sub for horizontal scaling
* Message persistence in PostgreSQL
* Message search using Elasticsearch
* Typing indicator
* Read receipts
* REST APIs for message management

---

# Tech Stack

Backend:

* Java 21
* Spring Boot
* Spring Security
* Spring WebSocket
* Spring Data JPA
* Spring Data Elasticsearch

Infrastructure:

* PostgreSQL
* Redis
* Elasticsearch
* Docker

---

# Architecture

```
Client
   │
   │ WebSocket / REST
   ▼
Spring Boot Chat Server
   │
   ├── PostgreSQL (store messages)
   ├── Redis Pub/Sub (real-time message distribution)
   └── Elasticsearch (search messages)
```

---

# Project Structure

```
src/main/java/com/chatapp
│
├── config
│   ├── SecurityConfig
│   ├── RedisConfig
│   └── WebSocketConfig
│
├── controller
│   ├── AuthController
│   ├── MessageController
│   └── SearchController
│
├── service
│   ├── AuthService
│   └── MessageService
│
├── repository
│   ├── MessageRepository
│   └── MessageSearchRepository
│
├── model
│   ├── Message
│   └── MessageDocument
│
└── redis
    ├── RedisPublisher
    └── RedisSubscriber
```

---

# Running with Docker

Start all required infrastructure:

```
docker-compose up -d
```

Services started:

| Service       | Port |
| ------------- | ---- |
| PostgreSQL    | 5432 |
| Redis         | 6379 |
| Elasticsearch | 9200 |

---

# Running the Application

```
mvn spring-boot:run
```

Application will start on:

```
http://localhost:8080
```

---

# API Documentation

Base URL:

```
http://localhost:8080
```

---

# 1 Register User

Endpoint

```
POST /auth/register
```

Curl

```
curl -X POST http://localhost:8080/auth/register \
-H "Content-Type: application/json" \
-d '{
"userName":"alice",
"password":"pass123"
}'
```

Example Response

```
{
"id":1,
"userName":"alice",
"isActive":true
}
```

---

# 2 Login User

Endpoint

```
POST /auth/login
```

Curl

```
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{
"userName":"alice",
"password":"pass123"
}'
```

Response

```
{
"token":"JWT_TOKEN_HERE"
}
```

---

# 3 Send Message

Endpoint

```
POST /messages
```

Curl

```
curl -X POST http://localhost:8080/messages \
-H "Content-Type: application/json" \
-d '{
"sender":"alice",
"receiver":"bob",
"content":"Hello Bob!"
}'
```

Example Response

```
{
"id":1,
"sender":"alice",
"receiver":"bob",
"content":"Hello Bob!",
"status":"SENT"
}
```

---

# 4 Get All Messages

Endpoint

```
GET /messages
```

Curl

```
curl http://localhost:8080/messages
```

Response

```
[
{
"id":1,
"sender":"alice",
"receiver":"bob",
"content":"Hello Bob!",
"status":"SENT"
}
]
```

---

# 5 Mark Message as Read

Endpoint

```
POST /messages/{id}/read
```

Example

```
curl -X POST http://localhost:8080/messages/1/read
```

Result

```
200 OK
```

Message status becomes:

```
READ
```

---

# 6 Search Messages (Elasticsearch)

Endpoint

```
GET /messages/search
```

Example

```
curl "http://localhost:8080/messages/search?query=hello"
```

Response

```
[
{
"id":"1",
"sender":"alice",
"content":"Hello Bob!"
}
]
```

---

# WebSocket Chat

Endpoint

```
ws://localhost:8080/chat
```

Send message destination

```
/app/chat.send
```

Subscribe to messages

```
/topic/messages
```

Example message payload

```
{
"sender":"alice",
"receiver":"bob",
"content":"Hello Bob!"
}
```

---

# Redis Pub/Sub

Redis is used to distribute messages across multiple chat servers.

Publisher

```
redisPublisher.publish("chat", message)
```

Subscriber

```
RedisSubscriber listens on channel "chat"
```

---

# Database Schema

Users

```
users
------
id
user_name
password
is_active
```

Messages

```
messages
--------
id
sender
receiver
content
status
timestamp
```

---

# Message Status Flow

```
SENT → DELIVERED → READ
```

---

# Testing Redis

Open Redis CLI:

```
docker exec -it chitt-chat-googly-redis-1 redis-cli
```

Publish test message:

```
PUBLISH chat "hello redis"
```

Server console will print:

```
Received message from Redis: hello redis
```

---

# Future Improvements

* Kafka for large-scale messaging
* Media file support (S3)
* Message encryption
* Rate limiting
* Group chat
* Push notifications

---
