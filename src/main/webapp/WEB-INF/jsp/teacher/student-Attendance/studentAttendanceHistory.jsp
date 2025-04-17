<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- Added for fn:length -->
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
        .debug {
            text-align: center;
            color: #555;
            font-size: 14px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<h2>Past Attendance History</h2>

<!-- Display teacherId (debugging purposes or if needed on the page) -->
<p class="debug">Teacher ID from Session: <%= session.getAttribute("teacherId") %></p>

<!-- Display history size for debugging -->
<p class="debug">History size: ${fn:length(history)}</p>

<%
    Long teacherIdLong = (Long) session.getAttribute("teacherId");
    String teacherId = teacherIdLong != null ? String.valueOf(teacherIdLong) : "";
    String contextPath = request.getContextPath();
%>
<form method="get" action="<%= contextPath %>/teacher/student-attendance/student-attendance-history" style="text-align:center; margin-bottom: 20px;">
    <input type="hidden" name="teacherId" value="<%= teacherId %>" />
    Grade: <input type="text" name="grade" placeholder="e.g. 10" value="<%= request.getParameter("grade") != null ? request.getParameter("grade") : "" %>" />
    Section: <input type="text" name="section" placeholder="e.g. A" value="<%= request.getParameter("section") != null ? request.getParameter("section") : "" %>" />
    Date: <input type="date" name="date" value="<%= request.getParameter("date") != null ? request.getParameter("date") : "" %>" />
    <input type="submit" value="Filter" style="padding: 5px 10px; margin-left: 10px;" />
</form>

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