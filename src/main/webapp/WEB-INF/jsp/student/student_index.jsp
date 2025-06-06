<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="includes/navbar.jsp" %><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management</title>
    <link rel="stylesheet" href="/student_frontend/style.css">
    <style>
        .btn{
            background-color: #0c94ff;
        }
    </style>
</head>
<body>
<main>
    <!-- Add New Student Button -->
  <%--  <button id="openModalButton" class="btn">Add New Student</button>--%>

    <!-- Modal for Adding Student -->
    <div id="studentModal" class="modal">
        <div class="modal-content">
            <span id="closeModalButton" class="close-btn">&times;</span>
            <h2>Add Student</h2>

            <!-- Validation Errors -->
            <div id="errorMessages" style="color: red; margin-bottom: 10px;"></div>

            <!-- Student Form -->
            <form id="studentForm" method="post" enctype="multipart/form-data" >
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required><br>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required><br>

                <label for="grade">Grade:</label>
                <input type="text" id="grade" name="grade" required><br>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address"><br>

                <label for="rollNo">Roll No:</label>
                <input type="text" id="rollNo" name="rollNo"><br>

                <label for="fatherName">Father's Name:</label>
                <input type="text" id="fatherName" name="fatherName"><br>

                <label for="motherName">Mother's Name:</label>
                <input type="text" id="motherName" name="motherName"><br>

                <label for="aadhaarNo">Aadhaar No:</label>
                <input type="text" id="aadhaarNo" name="aadhaarNo"><br>

                <label for="phoneNo">Phone No:</label>
                <input type="text" id="phoneNo" name="phoneNo"><br>

                <label for="altPhoneNo">Alternate Phone No:</label>
                <input type="text" id="altPhoneNo" name="altPhoneNo"><br>

                <label for="gender">Gender:</label>
                <select id="gender" name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select><br><br>

                <label for="section">Section:</label>
                <select id="section" name="section">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                </select><br><br>

                <label for="sessionId">Session ID:</label>
                <input type="text" id="sessionId" name="sessionId"><br>

                <!-- Add Photo Upload Field -->
                <input type="file" name="image" id="image" accept="image/*" />

                <button type="submit">Add Student</button>
            </form>
        </div>
    </div>

    <!-- Modal for Editing Student -->
    <div id="editStudentModal" class="modal">
        <div class="modal-content">
            <span id="closeEditModalButton" class="close-btn">&times;</span>
            <h2>Edit Student</h2>

            <!-- Validation Errors -->
            <div id="editErrorMessages" style="color: red; margin-bottom: 10px;"></div>

            <!-- Student Form -->
            <form id="editStudentForm" enctype="multipart/form-data">
                <input type="hidden" id="editStudentId" name="id">

                <label for="editName">Name:</label>
                <input type="text" id="editName" name="name" required><br>

                <label for="editEmail">Email:</label>
                <input type="email" id="editEmail" name="email" required><br>

                <label for="editGrade">Grade:</label>
                <input type="text" id="editGrade" name="grade" required><br>

                <label for="editAddress">Address:</label>
                <input type="text" id="editAddress" name="address"><br>

                <label for="editRollNo">Roll No:</label>
                <input type="text" id="editRollNo" name="rollNo"><br>

                <label for="editFatherName">Father's Name:</label>
                <input type="text" id="editFatherName" name="fatherName"><br>

                <label for="editMotherName">Mother's Name:</label>
                <input type="text" id="editMotherName" name="motherName"><br>

                <label for="editAadhaarNo">Aadhaar No:</label>
                <input type="text" id="editAadhaarNo" name="aadhaarNo"><br>

                <label for="editPhoneNo">Phone No:</label>
                <input type="text" id="editPhoneNo" name="phoneNo"><br>

                <label for="editAltPhoneNo">Alternate Phone No:</label>
                <input type="text" id="editAltPhoneNo" name="altPhoneNo"><br>

                <label for="editGender">Gender:</label>
                <select id="editGender" name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select><br><br>

                <label for="editSection">Section:</label>
                <select id="editSection" name="section">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                </select><br><br>

                <label for="editSessionId">Session ID:</label>
                <input type="text" id="editSessionId" name="sessionId"><br>

                <input type="file" name="image" id="photo" accept="image/*" />



                <button type="submit">Update Student</button>
            </form>
        </div>
    </div>

    <!-- Student List Section -->
    <section class="table-section">
        <h2>Student List</h2>
        <table id="studentTable">
            <thead>
            <tr>
                <th>Photo</th>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Grade</th>
                <th>Address</th>
                <th>Roll No</th>
                <th>Father's Name</th>
                <th>Mother's Name</th>
                <th>Aadhaar No</th>
                <th>Phone No</th>
                <th>Alternate Phone No</th>
                <th>Gender</th>
                <th>Section</th>
                <th>Session ID</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!-- Dynamic rows will be added here -->
            </tbody>
        </table>
    </section>
</main>

<script src="/student_frontend/script.js"></script>
</body>
</html>
