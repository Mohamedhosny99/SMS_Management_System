# **Docker Setup for SMS Management System**

```bash
docker build -t your_web_app_image .
docker network create webapp-net
docker run --name postgres-container --network webapp-net -e POSTGRES_USER=postgres_username -e POSTGRES_PASSWORD=your_password -e POSTGRES_DB=web_app -p 5432:5432 -d postgres
docker run --name my_web_app --network webapp-net -p 8080:8080 your_web_app_image
Change the URL in your JDBC file
jdbc:postgresql://the-name-of-your-service:5432/web_app
Run your web application:
http://localhost:8080/your-web-app-context
