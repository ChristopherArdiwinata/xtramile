# 🚀 Getting Started

## Overview

This application consists of two components:

- **Frontend (patient-app):** Built with Angular.
- **Backend (demo):** Powered by Java Spring Boot.

## Prerequisites

Before you start, ensure you have the following installed:

- [Node.js](https://nodejs.org/) and npm (for frontend)
- [Angular CLI](https://angular.io/cli)
- [Java 17+](https://adoptopenjdk.net/) (for backend)
- [Maven](https://maven.apache.org/)
- [Docker & Docker Compose](https://www.docker.com/)

## Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone <repository-url>
cd <repository-folder>
```

### 2️⃣ Start the Frontend

```bash
cd patient-app
npm install
ng serve
```

> The frontend should now be running at `http://localhost:4200/`

### 3️⃣ Start the Backend

```bash
cd demo
mvn clean package
docker-compose up
```

> The backend should now be running and accessible as per the configured API endpoints.

## 📌 Notes

- Ensure your backend is running before accessing the frontend.
- If any port conflicts occur, adjust the configurations in `application.properties` (backend) or `angular.json` (frontend).

## 🛠 Troubleshooting

If you encounter issues, consider:

- Checking logs: `docker logs <container-id>`
- Ensuring dependencies are installed properly.
- Reviewing the project documentation.

Happy coding! 🚀

