<html lang="en" xmlns:th="http://www.thymeleaf.org">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Teacher</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-1">
    <div class="row justify-content-center">
        <div class="col-md-15 col-lg-13"> <!-- Increased width -->
            <div class="card shadow-sm rounded p-4">
                <h2 class="text-center text-dark mb-2">Add Teacher</h2>

                <form action="/teachers/teacher/add" method="POST" enctype="multipart/form-data" th:object="${teacher}">

                    <!-- ERROR MESSAGES -->
                    <div class="text-danger" th:if="${#fields.hasErrors('*')}">
                        <ul th:each="error : ${#fields.errors('*')}">
                            <li th:text="${error}"></li>
                        </ul>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="name" class="form-label">Name</label>
                                <input type="text" class="form-control" id="name" name="name">
                                <div class="text-danger" th:if="${#fields.hasErrors('name')}" th:text="${#fields.errors('name')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email">
                                <div class="text-danger" th:if="${#fields.hasErrors('email')}" th:text="${#fields.errors('email')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="qualification" class="form-label">Qualification</label>
                                <input type="text" class="form-control" id="qualification" name="qualification">
                                <div class="text-danger" th:if="${#fields.hasErrors('qualification')}" th:text="${#fields.errors('qualification')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="specialization" class="form-label">Specialization</label>
                                <input type="text" class="form-control" id="specialization" name="specialization">
                                <div class="text-danger" th:if="${#fields.hasErrors('specialization')}" th:text="${#fields.errors('specialization')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="experience" class="form-label">Experience</label>
                                <input type="text" class="form-control" id="experience" name="experience">
                                <div class="text-danger" th:if="${#fields.hasErrors('experience')}" th:text="${#fields.errors('experience')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" id="address" name="address">
                                <div class="text-danger" th:if="${#fields.hasErrors('address')}" th:text="${#fields.errors('address')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="dob" class="form-label">Date of Birth</label>
                                <input type="date" class="form-control" id="dob" name="dob">
                                <div class="text-danger" th:if="${#fields.hasErrors('dob')}" th:text="${#fields.errors('dob')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="phone" class="form-label">Phone No</label>
                                <input type="text" class="form-control" id="phone" name="phone">
                                <div class="text-danger" th:if="${#fields.hasErrors('phone')}" th:text="${#fields.errors('phone')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="altPhoneNo" class="form-label">Another Phone No</label>
                                <input type="text" class="form-control" id="altPhoneNo" name="altPhoneNo">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="gender" class="form-label">Gender</label>
                                <select id="gender" class="form-select" name="gender">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Other">Other</option>
                                </select>
                                <div class="text-danger" th:if="${#fields.hasErrors('gender')}" th:text="${#fields.errors('gender')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="doj" class="form-label">Date of Joining</label>
                                <input type="date" class="form-control" id="doj" name="doj">
                                <div class="text-danger" th:if="${#fields.hasErrors('doj')}" th:text="${#fields.errors('doj')}"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="photoUrl" class="form-label">Photo Url</label>
                                <input type="file" class="form-control" id="photoUrl" name="photoUrl" accept="image/*">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-2">
                                <label for="isActive" class="form-label">Active Status</label>
                                <select class="form-select" id="isActive" name="isActive">
                                    <option value="true">Active</option>
                                    <option value="false">Inactive</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="text-center mt-2">
                        <button type="submit" class="btn btn-success px-5 py-3">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
