# EduPlus API: NGO Workshop & Attendee Management

**EduPlus API** is a high-performance, secure enterprise solution designed to streamline the management of educational workshops and attendee tracking for Non-Governmental Organizations (NGOs).

### 🎯 Who is it for?

* **NGO Coordinators**: To organize, schedule, and monitor educational sessions.
* **Volunteers/Administrators**: To manage attendee registrations and track presence.
* **Reporting Teams**: To generate data-driven insights through CSV exports.

### 💡 Why use it?

NGOs often struggle with fragmented data across spreadsheets. This API centralizes that data with:

* **Security**: Role-based access control using JWT (JSON Web Tokens).
* **Scalability**: Dockerized environment for easy deployment.
* **Reliability**: Automated data validation and structured database relationships.
* **Integration**: Fully documented OpenAPI (Swagger) for seamless frontend or mobile app connections.

---

### 🚀 Key Features

* **Auth System**: Secure Registration and Login with encrypted passwords.
* **Workshop Lifecycle**: Create, update, and filter "Active" sessions.
* **Attendee Management**: Full CRUD operations for managing participant details.
* **Attendance Tracking**: Register participants for specific workshops and update their status (Present, Absent, Canceled).
* **Data Portability**: Export workshop attendee lists to CSV for offline reporting.

---

### 🛠️ Tech Stack

* **Language**: Java 21
* **Framework**: Spring Boot 3.4.2
* **Security**: Spring Security & JWT
* **Database**: PostgreSQL
* **Documentation**: SpringDoc OpenAPI (Swagger UI)
* **Containerization**: Docker & Docker Compose

---

### 🏗️ How to Run the Project

#### 1. Prerequisites

* [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running.
* VS Code (recommended).

#### 2. Launching the Application

Navigate to the project root (where `docker-compose.yml` is located) in your terminal and run:

```powershell
docker-compose up --build

```

#### 3. Accessing the API

Once the logs show `Started EduplusApiApplication`, access the following:

* **Interactive Dashboard (Swagger UI)**:
`http://localhost:8080/swagger-ui/index.html`
* **API Documentation (JSON)**:
`http://localhost:8080/v3/api-docs`

---

### 🔑 Testing the API (Step-by-Step)

1. **Register**: Use the `/api/v1/auth/register` endpoint to create an account.
2. **Login**: Use `/api/v1/auth/login` to receive your JWT token.
3. **Authorize**: Click the green **Authorize** button at the top of the Swagger UI and paste your token.
4. **Create**: Start by creating a Workshop, then an Attendee, and finally link them via the Attendance controller.