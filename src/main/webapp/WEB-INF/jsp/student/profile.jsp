<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Profile</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="/student_frontend/style.css">
</head>
<body class="bg-gray-100 min-h-screen flex">

<%@include file="student_sidebar.jsp"%>

<div class="flex-1 p-8">
    <div class="max-w-3xl mx-auto bg-white rounded-2xl shadow-md p-8">
        <h2 class="text-3xl font-semibold text-gray-800 mb-6">Student Profile</h2>

        <div class="space-y-4">
            <div>
                <label class="block text-sm text-gray-600">Name</label>
                <p class="text-lg text-gray-900 font-medium">${student.name}</p>
            </div>

            <div>
                <label class="block text-sm text-gray-600">Email</label>
                <p class="text-lg text-gray-900 font-medium">${student.email}</p>
            </div>

            <div>
                <label class="block text-sm text-gray-600">Mobile</label>
                <p class="text-lg text-gray-900 font-medium">${student.phoneNo}</p>
            </div>
            <!-- You can add more fields here -->
            <!-- Example:
                <div>
                    <label class="block text-sm text-gray-600">Mobile</label>
                    <p class="text-lg text-gray-900 font-medium">${student.phoneNo}</p>
                </div>
                -->

            <div class="mt-6">
                <a href="/student/dashboard" class="inline-block bg-blue-600 text-white px-4 py-2 rounded-lg shadow hover:bg-blue-700 transition">
                    Back to Dashboard
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
