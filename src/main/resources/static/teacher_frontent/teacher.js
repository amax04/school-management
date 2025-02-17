function fetchTeachers() {
    const apiUrl = "https://school-management-production-2040.up.railway.app/teachers";
    const tableBody = document.getElementById("teacher-table-body");
    const loader = document.getElementById("loader");

    loader.style.display = "block";

    // Check for cached data
    const cachedData = localStorage.getItem("teacherData");
    if (cachedData) {
        displayTeachers(JSON.parse(cachedData));
        loader.style.display = "none";
    } else {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                localStorage.setItem("teacherData", JSON.stringify(data)); // Cache the data
                displayTeachers(data);
                loader.style.display = "none";
            })
            .catch(error => {
                console.error("Error fetching teachers:", error);
                loader.style.display = "none";
                tableBody.innerHTML = `<tr><td colspan="4" class="text-center text-danger">Failed to load data. ${error.message}</td></tr>`;
            });
    }
}

function displayTeachers(data) {
    const tableBody = document.getElementById("teacher-table-body");
    tableBody.innerHTML = "";

    if (!Array.isArray(data) || data.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="4" class="text-center">No data found</td></tr>`;
        return;
    }

    let fragment = document.createDocumentFragment(); // Create a document fragment

    data.forEach(teacher => {
        let photoHtml = teacher.photoUrl
            ? `<img src="${teacher.photoUrl}" width="50" height="50" class="rounded-circle" loading="lazy">`
            : `<div class="avatar">
                  ${teacher.name ? teacher.name.charAt(0).toUpperCase() : "T"}
              </div>`;

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${photoHtml}</td>
            <td>${teacher.id}</td>
            <td>${teacher.name}</td>
            <td>
                 <button class="btn btn-outline-primary btn-sm border-0" onclick="toggleDetails(${teacher.id})">
                     <i class="fa-solid fa-caret-down"></i>
                 </button>
            </td>
        `;
        fragment.appendChild(row);

        // Create the details row
        const detailsRow = document.createElement("tr");
        detailsRow.id = `details-${teacher.id}`;
        detailsRow.style.display = "none";
        detailsRow.innerHTML = `
            <td colspan="4">
                <table class="table table-bordered mt-2">
                    <tbody>
                        <tr><th>Email</th><td>${teacher.email || "N/A"}</td></tr>
                        <tr><th>Qualification</th><td>${teacher.qualification || "N/A"}</td></tr>
                        <tr><th>Experience</th><td>${teacher.experience ? teacher.experience + " years" : "N/A"}</td></tr>
                        <tr><th>Address</th><td>${teacher.address || "N/A"}</td></tr>
                        <tr><th>DOB</th><td>${teacher.dob || "N/A"}</td></tr>
                        <tr><th>Aadhar No</th><td>${teacher.aadhaarNo || "N/A"}</td></tr>
                        <tr><th>Phone</th><td>${teacher.phone || "N/A"}</td></tr>
                        <tr><th>altPhoneNo</th><td>${teacher.altPhoneNo || "N/A"}</td></tr>
                        <tr><th>Gender</th><td>${teacher.gender || "N/A"}</td></tr>
                        <tr><th>DOJ</th><td>${teacher.doj || "N/A"}</td></tr>
                        <tr><th>Status</th><td>${teacher.isActive ? "Active" : "Inactive"}</td></tr>
                    </tbody>
                </table>
                <div class="d-flex justify-content-end">
                    <button class="btn btn-warning btn-sm me-2" onclick="editTeacher(${teacher.id})">Edit</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteTeacher(${teacher.id})">Delete</button>
                </div>
            </td>
        `;
        fragment.appendChild(detailsRow);
    });

    tableBody.appendChild(fragment); // Append the fragment to the table body
}

// Toggle Details Function
function toggleDetails(teacherId) {
    const detailsRow = document.getElementById(`details-${teacherId}`);
    detailsRow.style.display = detailsRow.style.display === "none" ? "table-row" : "none";
}

// Dummy Add, Edit, Delete Functions
function addTeacher() {
    alert("Redirecting to Add Teacher Page...");
}

function editTeacher(teacherId) {
    alert("Edit Teacher with ID: " + teacherId);
}

function deleteTeacher(teacherId) {
    if (confirm("Are you sure you want to delete this teacher?")) {
        alert("Deleting Teacher ID: " + teacherId);
    }
}

// Load Teachers on Page Load
document.addEventListener("DOMContentLoaded", fetchTeachers);
