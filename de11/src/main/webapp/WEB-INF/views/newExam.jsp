<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Exam - Exam Attendant Application</title>
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
            max-width: 600px;
            width: 100%;
            padding: 40px;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
            font-size: 2em;
        }

        .subtitle {
            color: #666;
            margin-bottom: 30px;
            font-size: 0.95em;
        }

        .form-group {
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 600;
            font-size: 1em;
        }

        .required {
            color: #ff6b6b;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            transition: all 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        textarea:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        input[type="text"].error,
        input[type="number"].error,
        textarea.error {
            border-color: #ff6b6b;
            background: #fff5f5;
        }

        textarea {
            resize: vertical;
            min-height: 120px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .error-message {
            color: #ff6b6b;
            font-size: 0.9em;
            margin-top: 5px;
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .error-message::before {
            content: "⚠️";
        }

        .success-message {
            background: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .form-actions {
            display: flex;
            gap: 15px;
            margin-top: 30px;
            flex-wrap: wrap;
        }

        .btn {
            flex: 1;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            text-decoration: none;
            transition: all 0.3s ease;
            font-weight: 600;
            text-align: center;
            min-width: 150px;
        }

        .btn-submit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }

        .btn-cancel {
            background: #f0f0f0;
            color: #333;
            border: 2px solid #ddd;
        }

        .btn-cancel:hover {
            background: #e0e0e0;
            border-color: #999;
        }

        .info-box {
            background: #e7f3ff;
            border-left: 4px solid #0066cc;
            padding: 15px;
            border-radius: 3px;
            margin-bottom: 20px;
            color: #0066cc;
            font-size: 0.95em;
            line-height: 1.5;
        }

        .info-box strong {
            display: block;
            margin-bottom: 8px;
        }

        @media (max-width: 600px) {
            .container {
                padding: 20px;
            }

            h1 {
                font-size: 1.5em;
            }

            .form-actions {
                flex-direction: column;
            }

            .btn {
                min-width: auto;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>➕ Create New Exam</h1>
        <p class="subtitle">Fill in the form below to create a new exam</p>

        <c:if test="${not empty error}">
            <div class="success-message" style="background: #f8d7da; border-color: #f5c6cb; color: #721c24;">
                ${error}
            </div>
        </c:if>

        <div class="info-box">
            <strong>ℹ️ Required Fields</strong>
            All textboxes are mandatory. Duration must be a positive number (in minutes).
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/new-exam" novalidate>
            <!-- Exam Name -->
            <div class="form-group">
                <label for="name">
                    Exam Name <span class="required">*</span>
                </label>
                <input type="text"
                       id="name"
                       name="name"
                       value="${param.name}"
                       placeholder="Enter exam name"
                       class="${not empty errors.name ? 'error' : ''}">
                <c:if test="${not empty errors.name}">
                    <div class="error-message">${errors.name}</div>
                </c:if>
            </div>

            <!-- Description -->
            <div class="form-group">
                <label for="description">
                    Description <span class="required">*</span>
                </label>
                <textarea id="description"
                          name="description"
                          placeholder="Enter exam description"
                          class="${not empty errors.description ? 'error' : ''}">${param.description}</textarea>
                <c:if test="${not empty errors.description}">
                    <div class="error-message">${errors.description}</div>
                </c:if>
            </div>

            <!-- Duration -->
            <div class="form-group">
                <label for="duration">
                    Duration (Minutes) <span class="required">*</span>
                </label>
                <input type="number"
                       id="duration"
                       name="duration"
                       value="${param.duration}"
                       placeholder="Enter duration in minutes"
                       min="1"
                       class="${not empty errors.duration ? 'error' : ''}">
                <c:if test="${not empty errors.duration}">
                    <div class="error-message">${errors.duration}</div>
                </c:if>
            </div>

            <!-- Form Actions -->
            <div class="form-actions">
                <button type="submit" class="btn btn-submit">✅ Submit</button>
                <a href="${pageContext.request.contextPath}/exams" class="btn btn-cancel">❌ Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
