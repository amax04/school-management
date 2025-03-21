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
    <%-- Sweet Alert Message   --%>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

        /* Navbar Styling */
        nav {
        background-color: #007bff;
        padding: 20px;
        text-align: center;
        margin-bottom: 15px;
        }

        nav a {
        color: white;
        margin: 15px;
        text-decoration: none;
        font-size: 18px;
        font-weight: bold;
        }

        nav a:hover {
        text-decoration: underline;
        color: #ffeb3b;
        }
    </style>
</head>
<body>

<div class="container my-4">
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
                        <a href="${pageContext.request.contextPath}/teachers/edit/${teacher.id}" class="text-success">
                            <i class="fas fa-edit"></i>
                        </a>

                        <a href="javascript:void(0);" class="text-danger" onclick="confirmDelete(${teacher.id})">
                            <i class="fas fa-trash-alt"></i>
                        </a>
                    </td>
                    <td>
                        <i class="fas fa-chevron-down expand-icon" onclick="toggleDetails(this)"></i>
                    </td>
                </tr>
                <tr class="details-row" style="display: none;">
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

    function confirmDelete(id) {
        Swal.fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "/teachers/delete/" + id;  // ✅ Must match controller mapping
            }
        });
    }

    function toggleDetails(icon) {
        let detailsRow = icon.closest("tr").nextElementSibling;
        detailsRow.style.display = (detailsRow.style.display === "none" || detailsRow.style.display === "") ? "table-row" : "none";
        icon.classList.toggle("fa-chevron-up");
        icon.classList.toggle("fa-chevron-down");
    }
</script>

</body>
</html>
