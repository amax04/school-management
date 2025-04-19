<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add / Edit Student</title>
    <script defer src="/student_frontend/addOrEditStudent.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            width: 80%;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }
        .form-group {
            display: flex;
            flex-direction: column;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        input, select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        button {
            grid-column: span 2;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 id="formTitle">Add Student</h2>
    <form id="studentForm">
        <input type="hidden" id="studentId" name="studentId">

        <div class="form-group">
            <label for="name">Student Name</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="grade">Grade</label>
            <input type="text" id="grade" name="grade" required>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" id="address" name="address">
        </div>
        <div class="form-group">
            <label for="rollNo">Roll No</label>
            <input type="number" id="rollNo" name="rollNo">
        </div>
        <div class="form-group">
            <label for="fatherName">Father's Name</label>
            <input type="text" id="fatherName" name="fatherName">
        </div>
        <div class="form-group">
            <label for="motherName">Mother's Name</label>
            <input type="text" id="motherName" name="motherName">
        </div>
        <div class="form-group">
            <label for="aadhaarNo">Aadhaar No</label>
            <input type="text" id="aadhaarNo" name="aadhaarNo">
        </div>
        <div class="form-group">
            <label for="phoneNo">Phone No</label>
            <input type="text" id="phoneNo" name="phoneNo">
        </div>
        <div class="form-group">
            <label for="altPhoneNo">Alternate Phone No</label>
            <input type="text" id="altPhoneNo" name="altPhoneNo">
        </div>
        <div class="form-group">
            <label for="gender">Gender</label>
            <select id="gender" name="gender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
        </div>
        <div class="form-group">
            <label for="section">Section</label>
            <input type="text" id="section" name="section">
        </div>
        <div class="form-group">
            <label for="sessionId">Session ID</label>
            <input type="text" id="sessionId" name="sessionId">
        </div>
        <div class="form-group">
            <label for="image">Upload Photo</label>
            <%--<input type="file" id="photo" name="photo">--%>
            <input type="file" name="image" id="image" accept="image/*" />
        </div>

        <button type="submit">Save Student</button>
    </form>
</div>
</body>
</html>
