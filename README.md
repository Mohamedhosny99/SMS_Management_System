# Web Application for SMS Management with Twilio

## Description

This Java-based web application enables user and SMS management with integrated Twilio support. It features role-based access control, allowing administrators and regular users to interact with the system through distinct capabilities.

## Features

### 👤 User Management
- User registration and login
- Session handling and logout
- Role-based access (admin vs. customer)

### 📲 Twilio Integration
- Send SMS messages
- Phone number verification via Twilio
- Users manage their own Twilio credentials (SID, Auth Token, Sender ID)

### 📜 SMS Management
- View SMS history with filtering options
- Update and delete messages

### 🛠️ Admin Functionalities
- Admin dashboard
- View and manage all users
- View messages sent by any user

## Technologies Used

- **Backend:** Java, Java Servlets
- **Frontend:** JSP, HTML, CSS, JavaScript
- **Database:** PostgreSQL (update if different)
- **SMS Service:** Twilio API
- **Web Server:** Apache Tomcat (or any Servlet container)

## Setup and Installation

### 1. Prerequisites
- Java Development Kit (JDK 8+)
- Apache Tomcat or equivalent Servlet container
- PostgreSQL (or your configured DBMS)
- Twilio Account (SID, Auth Token, Sender Number)
- IDE (IntelliJ IDEA, Eclipse, etc.)

### 2. Database Setup
- Create a database (e.g., `webapp_db`)
- Set up tables: `users`, `customer`, `sms`, `sms_belongs` (based on your entity relationships)
- Configure DB connection in `DBConnection.java`

### 3. Twilio Configuration
- Users can provide Twilio credentials via the UI
- Add Twilio Java SDK to your project (`pom.xml` or `build.gradle`)

### 4. Project Deployment
1. Import the project into your IDE
2. Configure your Tomcat server
3. Deploy the WAR file or run from the IDE
4. Access the app at `http://localhost:8080/your-app-name`
