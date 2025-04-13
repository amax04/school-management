<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Attendance History</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #2c3e50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .back-btn {
            display: block;
            width: 120px;
            margin: 20px auto;
            padding: 10px;
            text-align: center;
            background: #3498db;
            color: white;
            border-radius: 8px;
            text-decoration: none;
        }
    </style>
</head>
<body>

<h2>Past Attendance History</h2>

<c:if test="${empty history}">
    <p style="text-align:center; color:gray;">No attendance records found.</p>
</c:if>

<c:if test="${not empty history}">
    <table>
        <thead>
        <tr>
            <th>Date</th>
            <th>Grade</th>
            <th>Section</th>
            <th>Student ID</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${history}">
            <c:forEach var="studentAttendance" items="${record.attendancelist}">
                <tr>
                    <td>${record.date}</td>
                    <td>${record.grade}</td>
                    <td>${record.section}</td>
                    <td>${studentAttendance.studentId}</td>
                    <td>${studentAttendance.status}</td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<a href="/teacher/student-attendance/view" class="back-btn">← Go Back</a>

</body>
</html>
