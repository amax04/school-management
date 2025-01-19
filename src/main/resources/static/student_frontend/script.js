// DOM Elements
const studentForm = document.getElementById('studentForm');
const studentTable = document.getElementById('studentTable').querySelector('tbody');
const modal = document.getElementById("studentModal");
const openModalButton = document.getElementById("openModalButton");
const closeModalButton = document.getElementById("closeModalButton");
const errorMessagesDiv = document.getElementById("errorMessages");

// API Base URL
const apiBaseUrl = 'http://school-management-production-2040.up.railway.app/students';

/** Fetch and Display Students **/
async function fetchStudents() {
    try {
        const response = await fetch(apiBaseUrl);
        const students = await response.json();

        studentTable.innerHTML = ''; // Clear existing rows
        students.forEach(student => addStudentRow(student));
    } catch (error) {
        console.error("Error fetching students:", error);
    }
}

/** Add Student Row to Table **/
function addStudentRow(student) {
    const row = document.createElement('tr');
    row.innerHTML = `
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

/** Add or Edit Student **/
studentForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const formData = new FormData(studentForm);
    const studentData = Object.fromEntries(formData);

    if (studentForm.dataset.mode === "edit") {
        // Edit Student
        const id = studentForm.dataset.id;
        handleEditStudent(id, studentData);
    } else {
        // Add New Student
        try {
            const response = await fetch(apiBaseUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(studentData),
            });

            if (response.ok) {
                alert("Student added successfully!");
                closeModal();
                fetchStudents();
            } else {
                const error = await response.json();
                displayErrors(errorMessagesDiv, error.errors || ["Error adding student."]);
            }
        } catch (error) {
            console.error("Error adding student:", error);
        }
    }
});

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

/** Handle Edit Student **/
async function handleEditStudent(id, studentData) {
    try {
        const response = await fetch(`${apiBaseUrl}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(studentData),
        });

        if (response.ok) {
            alert("Student updated successfully!");
            closeModal();
            fetchStudents();
        } else {
            const error = await response.json();
            displayErrors(errorMessagesDiv, error.errors || ["Error updating student."]);
        }
    } catch (error) {
        console.error("Error updating student:", error);
    }
}

/** Close Modal **/
function closeModal() {
    modal.style.display = "none";
    studentForm.reset();
    delete studentForm.dataset.mode;
    delete studentForm.dataset.id;
}

/** Open Modal for Adding a Student **/
openModalButton.onclick = () => {
    modal.style.display = "block";
    errorMessagesDiv.innerHTML = ""; // Clear previous errors
};

/** Close Modal When Clicked Outside **/
window.onclick = (event) => {
    if (event.target === modal) {
        closeModal();
    }
};

/** Close Modal with Close Button **/
closeModalButton.onclick = closeModal;

/** Display Validation Errors **/
function displayErrors(container, errors) {
    container.innerHTML = errors.map(error => `<p>${error}</p>`).join('');
}

// Load Students on Page Load
fetchStudents();
