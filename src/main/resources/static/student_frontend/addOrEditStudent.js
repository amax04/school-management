//const apiBaseUrl = 'http://localhost:8080/students';

const apiBaseUrl = 'https://school-management-production-2040.up.railway.app/students';
// Get student ID from URL
const urlParams = new URLSearchParams(window.location.search);
const studentId = urlParams.get("id");

// Fetch student details if updating
async function fetchStudentDetails() {
    if (!studentId) return; // No ID means it's an Add operation

    try {
        const response = await fetch(`${apiBaseUrl}/${studentId}`);
        if (!response.ok) throw new Error("Student not found.");
        const student = await response.json();

        // Populate form fields
        document.getElementById("formTitle").innerText = "Edit Student";
        document.getElementById("studentId").value = student.id;
        document.getElementById("gender").value = student.gender;
        document.getElementById("section").value = student.section;
        document.getElementById("sessionId").value = student.sessionId;
        document.getElementById("grade").value = student.grade;
        document.getElementById("address").value = student.address;
        document.getElementById("rollNo").value = student.rollNo;
        document.getElementById("phoneNo").value = student.phoneNo;
        document.getElementById("motherName").value = student.motherName;
        document.getElementById("aadhaarNo").value = student.aadhaarNo;
        document.getElementById("altPhoneNo").value = student.altPhoneNo;
        document.getElementById("name").value = student.name;
        document.getElementById("email").value = student.email;
        document.getElementById("fatherName").value = student.fatherName;
    } catch (error) {
        console.error("Error fetching student details:", error);
    }
}

// Handle form submission
document.getElementById('studentForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);

    const isUpdating = !!document.getElementById("studentId").value;
    const apiUrl = isUpdating ? `${apiBaseUrl}/${studentId}` : apiBaseUrl;
    const method = isUpdating ? "PUT" : "POST";

    try {
        const response = await fetch(apiUrl, {
            method: method,
            body: formData,
        });

        if (response.ok) {
            alert(isUpdating ? "Student updated successfully!" : "Student added successfully!");
            window.location.href = "/admin/studentshow"; // Redirect to main page
        } else {
            alert("Error saving student.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Failed to save student.");
    }
});

window.onload = fetchStudentDetails;
