<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="includes/navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Teacher Management</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    <style>
        .expand-icon {
            cursor: pointer;
            font-size: 1.3rem;
            color: #007bff;
        }
        .details-row {
            display: none;
        }
        .teacher-photo {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
            background-color: #007bff;
            color: white;
            font-size: 24px;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: center;
            text-transform: uppercase;
        }
    </style>
</head>
<body>

<div class="container my-4">
<%--    <h2 class="text-center my-4 text-primary">Teacher Management</h2>--%>

    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Photo</th>
                <th>Name</th>
                <th>Experience</th>
                <th>Phone</th>
                <th>Actions</th>
                <th>More</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="teacher" items="${teachers}">
                <tr>
                    <td>${teacher.id}</td>
                    <td class="text-center">
                        <img src="${teacher.photoUrl}" class="teacher-photo"
                             onerror="setPlaceholder(this, '${teacher.name}')">
                    </td>
                    <td>${teacher.name}</td>
                    <td>${teacher.experience} years</td>
                    <td>${teacher.phone}</td>
                    <td>
                        <i class="fas fa-edit text-success" onclick="editTeacher(${teacher.id})"></i>
                        <i class="fas fa-trash-alt text-danger" onclick="deleteTeacher(${teacher.id})"></i>
                    </td>
                    <td>
                        <i class="fas fa-chevron-down expand-icon" onclick="toggleDetails(this)"></i>
                    </td>
                </tr>
                <tr class="details-row">
                    <td colspan="7">
                        <table class="table table-bordered">
                            <tr>
                                <th>Email</th>
                                <td>${teacher.email}</td>
                                <th>Qualification</th>
                                <td>${teacher.qualification}</td>
                            </tr>
                            <tr>
                                <th>Specialization</th>
                                <td>${teacher.specialization}</td>
                                <th>Address</th>
                                <td>${teacher.address}</td>
                            </tr>
                            <tr>
                                <th>Aadhaar No</th>
                                <td>${teacher.aadhaarNo}</td>
                                <th>Gender</th>
                                <td>${teacher.gender}</td>
                            </tr>
                            <tr>
                                <th>Date of Birth</th>
                                <td>${teacher.dob}</td>
                                <th>Date of Joining</th>
                                <td>${teacher.doj}</td>
                            </tr>
                            <tr>
                                <th>Alt Phone</th>
                                <td>${teacher.altPhoneNo}</td>
                                <th>Active</th>
                                <td>${teacher.isActive ? 'Yes' : 'No'}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function setPlaceholder(imgElement, name) {
        let firstLetter = name.charAt(0).toUpperCase();
        let placeholder = document.createElement("div");
        placeholder.className = "teacher-photo";
        placeholder.textContent = firstLetter;
        imgElement.replaceWith(placeholder);
    }

    function editTeacher(id) {
        alert("Edit function called for Teacher ID: " + id);
    }

    function deleteTeacher(id) {
        if (confirm("Are you sure you want to delete this teacher?")) {
            alert("Delete function called for Teacher ID: " + id);
        }
    }

    function toggleDetails(icon) {
        let detailsRow = icon.closest("tr").nextElementSibling;
        if (detailsRow.style.display === "none" || detailsRow.style.display === "") {
            detailsRow.style.display = "table-row";
            icon.classList.replace("fa-chevron-down", "fa-chevron-up");
        } else {
            detailsRow.style.display = "none";
            icon.classList.replace("fa-chevron-up", "fa-chevron-down");
        }
    }
</script>

</body>
</html>
