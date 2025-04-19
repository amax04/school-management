<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teacher Profile</title>

    <!-- Bootstrap & FontAwesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #f0f4f8, #d9e2ec);
            font-family: 'Segoe UI', sans-serif;
        }

        .profile-container {
            max-width: 900px;
            margin: 50px auto;
            background-color: white;
            border-radius: 16px;
            padding: 30px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }

        .profile-photo {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }

        .profile-title {
            font-size: 32px;
            font-weight: bold;
            color: #007bff;
        }

        .profile-subtitle {
            font-size: 18px;
            color: #6c757d;
        }

        .info-title {
            color: #343a40;
            font-weight: 600;
        }

        .info-value {
            color: #555;
        }

        .info-section {
            padding: 20px 0;
            border-top: 1px solid #dee2e6;
        }

        .btn-back {
            margin-top: 30px;
        }

        @media(max-width: 576px) {
            .profile-photo {
                width: 100px;
                height: 100px;
            }

            .profile-title {
                font-size: 24px;
            }
        }
    </style>
</head>
<body>

<div class="profile-container text-center">
    <img src="${teacher.photoUrl}" class="profile-photo mb-3"
         onerror="this.style.display='none'; document.getElementById('fallback').style.display='flex';" alt="Profile Photo">

    <div id="fallback" class="profile-photo mb-3 mx-auto" style="display: none; background-color: #007bff; color: white; font-size: 40px; align-items: center; justify-content: center; display: none;">
        <%= ((String) session.getAttribute("teacherName")).substring(0, 1).toUpperCase() %>
    </div>

    <h2 class="profile-title">${teacher.name}</h2>
    <p class="profile-subtitle">${teacher.specialization}</p>

    <div class="row info-section text-start">
        <div class="col-md-6 mb-3">
            <div class="info-title">Email</div>
            <div class="info-value">${teacher.email}</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Phone</div>
            <div class="info-value">${teacher.phone}</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Alt Phone</div>
            <div class="info-value">${teacher.altPhoneNo}</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Experience</div>
            <div class="info-value">${teacher.experience} years</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Qualification</div>
            <div class="info-value">${teacher.qualification}</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Date of Birth</div>
            <div class="info-value">${teacher.dob}</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Date of Joining</div>
            <div class="info-value">${teacher.doj}</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Address</div>
            <div class="info-value">${teacher.address}</div>
        </div>
        <div class="col-md-6 mb-3">
            <div class="info-title">Active</div>
            <div class="info-value">${teacher.isActive ? 'Yes' : 'No'}</div>
        </div>
    </div>

    <!-- Actions -->
    <div class="text-center my-4">
        <div>
            <a href="${pageContext.request.contextPath}/teachers/edit/${teacher.id}" class="btn btn-warning">
                Edit Profile
            </a>
        </div>

        <div>
            <a href="${pageContext.request.contextPath}/teachers/dashboard" class="btn btn-secondary rounded-pill btn-back">
                <i class="fas fa-arrow-left me-2"></i>Back to List
            </a>
        </div>
    </div>

</div>

</body>
</html>
