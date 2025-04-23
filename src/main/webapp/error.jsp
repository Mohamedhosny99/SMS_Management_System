<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>SMS Sending Error</title>
  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(135deg, #ff4e50, #f9d423);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: #fff;
    }

    .error-container {
      text-align: center;
      background-color: rgba(0, 0, 0, 0.5);
      padding: 40px;
      border-radius: 12px;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
    }

    h1 {
      font-size: 48px;
      margin-bottom: 20px;
    }

    p {
      font-size: 20px;
      margin-bottom: 30px;
    }

    a {
      display: inline-block;
      padding: 12px 24px;
      background-color: #fff;
      color: #ff4e50;
      font-weight: bold;
      border-radius: 8px;
      text-decoration: none;
      transition: background-color 0.3s ease, color 0.3s ease;
    }

    a:hover {
      background-color: #ff4e50;
      color: #fff;
    }
  </style>
</head>
<body>
<div class="error-container">
  <h1>Oops!</h1>
  <p>Failed to send the message.</p>
  <a href="home.jsp">Home</a>
</div>
</body>
</html>
