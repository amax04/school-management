// DOM Elements
const studentForm = document.getElementById('studentForm');
const studentTable = document.getElementById('studentTable').querySelector('tbody');
const modal = document.getElementById("studentModal");
const openModalButton = document.getElementById("openModalButton");
const closeModalButton = document.getElementById("closeModalButton");
const errorMessagesDiv = document.getElementById("errorMessages");

// API Base URL
// const apiBaseUrl = 'http://localhost:8080/students';
const apiBaseUrl = 'https://school-management-production-2040.up.railway.app/students';
/** Fetch and Display Students **/
async function fetchStudents() {
    try {
        const response = await fetch(apiBaseUrl);
        const students = await response.json();
        studentTable.innerHTML = ''; // Clear existing rows
        students.forEach(student => addStudentRow(student));
    } catch (error) {
        console.error("Error fetching students:", error);
        alert("Failed to load students.");
    }
}

/** Add Student Row to Table **/
function addStudentRow(student) {
    const row = document.createElement('tr');
    const photoSrc = student.photoPath || createPlaceholder(student.name);

    row.innerHTML = `
        <td><img src="${photoSrc}" alt="Photo" class="student-photo"></td>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.email}</td>
        <td>${student.grade}</td>
        <td>${student.address}</td>
        <td>${student.rollNo}</td>
        <td>${student.fatherName}</td>
        <td>${student.motherName}</td>
        <td>${student.aadhaarNo}</td>
        <td>${student.phoneNo}</td>
        <td>${student.altPhoneNo}</td>
        <td>${student.gender}</td>
        <td>${student.section}</td>
        <td>${student.sessionId}</td>
        <td class="actions">
            <button onclick="openEditModal(${student.id})">Edit</button>
            <button onclick="deleteStudent(${student.id})">Delete</button>
        </td>
    `;
    studentTable.appendChild(row);
}

/** Generate Placeholder Image **/
function createPlaceholder(name) {
    const firstLetter = name[0].toUpperCase();
    const color = stringToColor(firstLetter); // Unique color for each letter
    const canvas = document.createElement('canvas');
    canvas.width = 50;
    canvas.height = 50;

    const ctx = canvas.getContext('2d');
    // Draw background
    ctx.fillStyle = color;
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // Draw text (first letter)
    ctx.fillStyle = '#FFFFFF'; // White text
    ctx.font = '24px Arial';
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillText(firstLetter, canvas.width / 2, canvas.height / 2);

    return canvas.toDataURL(); // Return as data URL
}

/** Generate a Unique Color Based on Input String **/
function stringToColor(str) {
    let hash = 0;
    for (let i = 0; i < str.length; i++) {
        hash = str.charCodeAt(i) + ((hash << 5) - hash);
    }
    // Ensure the hash is positive
    hash = Math.abs(hash);

    const color = `#${((hash >> 24) & 0xFF).toString(16).padStart(2, '0')}${((hash >> 16) & 0xFF).toString(16).padStart(2, '0')}${((hash >> 8) & 0xFF).toString(16).padStart(2, '0')}`;
    return color.slice(0, 7); // Ensure it's a valid hex color
}

/** Add or Edit Student **/
studentForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const formData = new FormData(studentForm);

    // Check if it's edit mode or add mode
    const mode = studentForm.dataset.mode;
    if (mode === "edit") {
        const id = studentForm.dataset.id;
        handleEditStudent(id, formData);
    } else {
        addNewStudent(formData);
    }
});

/** Add New Student **/
async function addNewStudent(formData) {
    try {
        const response = await fetch(apiBaseUrl, {
            method: 'POST',
            body: formData,
        });

        if (response.ok) {
            const result = await response.json();
            alert("Student added successfully!");
            console.log("Saved Student:", result);
            closeModal();
            fetchStudents();
        } else {
            const error = await response.json();
            alert("Error adding student: " + (error.message || "Unknown error"));
        }
    } catch (error) {
        console.error("Error adding student:", error);
        alert("Failed to add student.");
    }
}

/** Edit Student **/
async function handleEditStudent(id, formData) {
    try {
        const response = await fetch(`${apiBaseUrl}/${id}`, {
            method: 'PUT',
            body: formData,
        });

        if (response.ok) {
            const result = await response.json();
            alert("Student updated successfully!");
            console.log("Updated Student:", result);
            closeModal();
            fetchStudents();
        } else {
            const error = await response.json();
            alert("Error updating student: " + (error.message || "Unknown error"));
        }
    } catch (error) {
        console.error("Error updating student:", error);
        alert("Failed to update student.");
    }
}

/** Delete a Student **/
async function deleteStudent(id) {
    if (!confirm("Are you sure you want to delete this student?")) return;

    try {
        const response = await fetch(`${apiBaseUrl}/${id}`, { method: 'DELETE' });
        if (response.ok) {
            alert("Student deleted successfully!");
            fetchStudents();
        } else {
            console.error("Error deleting student:", await response.text());
        }
    } catch (error) {
        console.error("Error deleting student:", error);
        alert("Failed to delete student.");
    }
}

/** Open Edit Modal **/
async function openEditModal(id) {
    try {
        const response = await fetch(`${apiBaseUrl}/${id}`);
        if (!response.ok) throw new Error("Student not found.");

        const student = await response.json();
        populateModalForEditing(student);

        modal.style.display = "block";
        errorMessagesDiv.innerHTML = ""; // Clear previous errors
    } catch (error) {
        console.error("Error fetching student for editing:", error);
        alert("Failed to open edit modal.");
    }
}

/** Populate Modal for Editing **/
function populateModalForEditing(student) {
    document.getElementById("name").value = student.name;
    document.getElementById("email").value = student.email;
    document.getElementById("grade").value = student.grade;
    document.getElementById("address").value = student.address;
    document.getElementById("rollNo").value = student.rollNo;
    document.getElementById("phoneNo").value = student.phoneNo;
    document.getElementById("fatherName").value = student.fatherName;
    document.getElementById("motherName").value = student.motherName;
    document.getElementById("aadhaarNo").value = student.aadhaarNo;
    document.getElementById("altPhoneNo").value = student.altPhoneNo;
    document.getElementById("gender").value = student.gender;
    document.getElementById("section").value = student.section;
    document.getElementById("sessionId").value = student.sessionId;

    studentForm.dataset.mode = "edit";
    studentForm.dataset.id = student.id;
}

/** Close Modal **/
function closeModal() {
    modal.style.display = "none";
    studentForm.reset();
    studentForm.dataset.mode = "add"; // Reset to add mode
}

openModalButton.addEventListener('click', () => {
    modal.style.display = "block";
});

closeModalButton.addEventListener('click', closeModal);

// Fetch students when the page loads
window.onload = fetchStudents;
