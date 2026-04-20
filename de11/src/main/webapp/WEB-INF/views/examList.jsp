<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View All Exams - Exam Attendant Application</title>
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
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            padding: 30px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            flex-wrap: wrap;
            gap: 15px;
        }

        h1 {
            color: #333;
            font-size: 2em;
        }

        .action-buttons {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
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
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .btn-secondary {
            background: #f0f0f0;
            color: #333;
            border: 2px solid #667eea;
        }

        .btn-secondary:hover {
            background: #667eea;
            color: white;
        }

        .btn-danger {
            background: #ff6b6b;
            color: white;
            padding: 8px 15px;
            font-size: 0.9em;
        }

        .btn-danger:hover {
            background: #ff5252;
            transform: translateY(-2px);
        }

        .table-container {
            overflow-x: auto;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        thead {
            background: #f8f9fa;
            border-bottom: 2px solid #667eea;
        }

        th {
            padding: 15px;
            text-align: left;
            color: #333;
            font-weight: 600;
        }

        td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
        }

        tbody tr:hover {
            background: #f8f9fa;
        }

        .no-exams {
            text-align: center;
            padding: 40px;
            color: #999;
            font-size: 1.1em;
        }

        .exam-name {
            color: #667eea;
            font-weight: 600;
        }

        .exam-duration {
            background: #e7f3ff;
            padding: 5px 10px;
            border-radius: 3px;
            color: #0066cc;
            font-weight: 500;
        }

        .exam-description {
            color: #666;
            max-width: 300px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .delete-btn {
            background: #ff6b6b;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 3px;
            cursor: pointer;
            font-size: 0.9em;
            transition: all 0.3s ease;
        }

        .delete-btn:hover {
            background: #ff5252;
        }

        .timestamp {
            color: #999;
            font-size: 0.9em;
        }

        .empty-message {
            background: #f0f0f0;
            padding: 20px;
            border-radius: 5px;
            text-align: center;
            color: #666;
        }

        @media (max-width: 768px) {
            .header {
                flex-direction: column;
                align-items: flex-start;
            }

            .action-buttons {
                width: 100%;
            }

            .btn {
                flex: 1;
                text-align: center;
            }

            table {
                font-size: 0.9em;
            }

            th, td {
                padding: 8px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📋 All Exams</h1>
            <div class="action-buttons">
                <a href="${pageContext.request.contextPath}/new-exam" class="btn btn-primary">➕ New Exam</a>
                <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">🏠 Back Home</a>
            </div>
        </div>

        <c:if test="${empty exams}">
            <div class="empty-message">
                <p>No exams found. <a href="${pageContext.request.contextPath}/new-exam">Create your first exam</a></p>
            </div>
        </c:if>

        <c:if test="${not empty exams}">
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Duration (minutes)</th>
                            <th>Created At</th>
                            <th>Updated At</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="exam" items="${exams}">
                            <tr>
                                <td>${exam.id}</td>
                                <td class="exam-name">${exam.name}</td>
                                <td class="exam-description">${exam.description}</td>
                                <td><span class="exam-duration">${exam.duration} min</span></td>
                                <td class="timestamp">${exam.formattedCreatedAt}</td>
                                <td class="timestamp">${exam.formattedUpdatedAt}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/delete-exam?id=${exam.id}" 
                                       class="delete-btn"
                                       onclick="return confirm('Are you sure you want to delete this exam?');">
                                        🗑️ Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</body>
</html>
