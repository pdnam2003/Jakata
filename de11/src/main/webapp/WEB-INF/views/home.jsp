<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exam Attendant Application - Home</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            max-width: 800px;
            width: 100%;
            padding: 40px;
            text-align: center;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
            font-size: 2.5em;
        }

        .subtitle {
            color: #666;
            margin-bottom: 30px;
            font-size: 1.1em;
        }

        .exam-image {
            margin: 30px 0;
            max-width: 100%;
            height: auto;
        }

        .image-placeholder {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            width: 100%;
            height: 300px;
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 1.2em;
            margin: 30px 0;
            flex-direction: column;
        }

        .image-placeholder svg {
            width: 100px;
            height: 100px;
            margin-bottom: 20px;
        }

        .links-section {
            margin-top: 40px;
            display: flex;
            gap: 20px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .btn {
            display: inline-block;
            padding: 12px 30px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            font-size: 1.1em;
            cursor: pointer;
            text-decoration: none;
            transition: all 0.3s ease;
            font-weight: 600;
        }

        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }

        .btn-secondary {
            background: #f0f0f0;
            color: #333;
            border: 2px solid #667eea;
        }

        .btn-secondary:hover {
            background: #667eea;
            color: white;
            transform: translateY(-2px);
        }

        .description {
            color: #666;
            font-size: 1.05em;
            line-height: 1.6;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📚 Exam Attendant Application</h1>
        <p class="subtitle">Welcome to the Online Exam Management System</p>

        <div class="image-placeholder">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
            </svg>
            Sample Exam Question & Answer
        </div>

        <p class="description">
            This is the home page of the Exam Attendant Application. You can manage online exams by creating,
            taking, and grading them. Choose one of the options below to get started:
        </p>

        <div class="links-section">
            <a href="${pageContext.request.contextPath}/exams" class="btn btn-primary">
                📋 View Exams
            </a>
            <a href="${pageContext.request.contextPath}/new-exam" class="btn btn-secondary">
                ➕ New Exam
            </a>
        </div>
    </div>
</body>
</html>
