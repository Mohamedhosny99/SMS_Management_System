<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send SMS</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(rgba(106, 17, 203, 0.8), rgba(37, 117, 252, 0.8)); /* Gradient overlay on background image */
            background-size: cover;
            background-position: center;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            color: #333;
        }

        /* Header Styles */
        header {
            background-color: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(10px);
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }

        header h1 {
            font-size: 24px;
            font-weight: 600;
            color: #fff;
        }

        header nav {
            display: flex;
            gap: 20px;
        }

        header nav a {
            color: #fff;
            text-decoration: none;
            font-size: 16px;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        header nav a:hover {
            color: #6a11cb;
        }

        /* Main Content Styles */
        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .card {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            animation: fadeIn 1s ease-in-out;
            width: 100%;
            max-width: 500px;
            text-align: center;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            margin-bottom: 20px;
            color: #6a11cb;
            font-size: 28px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-size: 14px;
            font-weight: 500;
        }

        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            background-color: rgba(255, 255, 255, 0.9);
            transition: border-color 0.3s ease;
        }

        .form-group input:focus,
        .form-group textarea:focus {
            border-color: #6a11cb;
            outline: none;
        }

        .form-group textarea {
            resize: vertical;
            min-height: 100px;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background-color: #6a11cb;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #2575fc;
        }

        /* Footer Styles */
        footer {
            width: 100%;
            padding: 15px 0;
            background-color: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(10px);
            text-align: center;
            font-size: 14px;
            color: #fff;
            border-top: 1px solid rgba(255, 255, 255, 0.1);
        }

        footer a {
            color: #fff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        footer a:hover {
            color: #6a11cb;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            header {
                flex-direction: column;
                gap: 10px;
            }

            header nav {
                gap: 10px;
            }

            .card {
                padding: 20px;
            }

            h2 {
                font-size: 24px;
            }
        }
    </style>

</head>
<body>
<%
    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

<!-- Header -->
<header>
    <h1>Send SMS</h1>
    <nav>
        <a href="home.jsp">Home</a>
        <a href="smsHistory.jsp">SMS History</a>
        <a href="login.jsp">Logout</a>
    </nav>
</header>

<!-- Main Content -->
<div class="container">
    <div class="card">
        <h2>Send SMS</h2>
        <form id="sendSMSForm" action="/SendSmsServlet" method="POST">
            <div class="form-group">
                <label>To</label>
                <input type="text" name="toNumber" placeholder="Enter phone number" required>
            </div>
            <div class="form-group">
                <label>Message</label>
                <textarea name="message" placeholder="Enter your message" rows="4" required></textarea>
            </div>
            <button type="submit" class="btn">Send SMS</button>
        </form>
    </div>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2025 Twilio sms Client. All rights reserved. |
        <a href="#">Privacy Policy</a> |
        <a href="#">Terms of Service</a>
    </p>
</footer>

<!-- Alert Script -->
<% if (success != null) { %>
<script>
    alert("SMS sent successfully!");
</script>
<% } else if (error != null) { %>
<script>
    alert("<%= error %>");
</script>
<% } %>

</body>
</html>
