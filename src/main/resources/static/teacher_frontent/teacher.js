function fetchTeachers() {
    const apiUrl = "https://school-management-production-2040.up.railway.app/teachers";
    const tableBody = document.getElementById("teacher-table-body");
    const loader = document.getElementById("loader");

    loader.style.display = "block";
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            loader.style.display = "none";
            tableBody.innerHTML = "";
            data.forEach(teacher => {
                const photoUrl = teacher.photoUrl && teacher.photoUrl.trim() !== "" ? teacher.photoUrl : "https://via.placeholder.com/50"; // Default fallback image
                const row = `<tr>
                    <td>${teacher.id}</td>
                    <td>${teacher.name}</td>
                    <td>${teacher.email}</td>
                    <td>${teacher.qualification}</td>
                    <td>${teacher.experience ? teacher.experience + " years" : "N/A"}</td>
                    <td>
                        ${teacher.photoUrl ?
                            `<img src="${teacher.photoUrl}" width="50" height="50" class="rounded-circle">` :
                            `<div class="avatar">${teacher.name.charAt(0).toUpperCase()}</div>`
                        }
                    </td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            loader.style.display = "none";
            console.error("Error fetching teachers:", error);
        });
}
