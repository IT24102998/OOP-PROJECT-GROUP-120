<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            display: flex;
        }
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed;
            background-color: #000;
            padding-top: 20px;
        }
        .sidebar a {
            color: #fff;
            padding: 12px 20px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover,
        .sidebar a.active {
            background-color: #444;
        }
        .content {
            margin-left: 250px;
            padding: 20px;
            width: 100%;
        }
    </style>
</head>
<body>

<!-- ✅ Side Navigation Bar -->
<div class="sidebar">
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/users" class="active">Users</a>
    <a href="${pageContext.request.contextPath}/photographers">Photographers</a>
    <a href="${pageContext.request.contextPath}/events">Events</a>
    <a href="${pageContext.request.contextPath}/bookings">Bookings</a>
    <a href="${pageContext.request.contextPath}/reviews">Reviews</a>
    <a href="${pageContext.request.contextPath}/Payment">Payment</a>
    <a href="${pageContext.request.contextPath}/logout" class="text-danger">Logout</a>
</div>

<!-- ✅ Content Area -->
<div class="content">
    <h2>User Management</h2>

    <!-- Responsive Table -->
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>
                        <!-- Edit Button -->
                        <a href="${pageContext.request.contextPath}/users/edit/${user.id}"
                           class="btn btn-warning">Edit</a>
                        <!-- Delete Button -->
                        <a href="${pageContext.request.contextPath}/users/delete/${user.id}"
                           class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <h3>Add User</h3>
    <form action="${pageContext.request.contextPath}/users/add" method="post" onsubmit="return validateEmail(document.getElementById('email'))">
        <div class="row mb-3">
            <div class="col-md-6">
                <label for="id" class="form-label">ID</label>
                <input type="number" name="id" id="id" class="form-control" placeholder="Enter ID" required>
            </div>
            <div class="col-md-6">
                <label for="name" class="form-label">Name</label>
                <input type="text" name="name" id="name" class="form-control" placeholder="Enter Name" required>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" id="email" class="form-control" placeholder="Enter Email" required oninput="validateEmail(this)">
                <div id="emailFeedback" class="invalid-feedback">Please enter a valid email (e.g., user@example.com)</div>
            </div>
            <div class="col-md-6">
                <label for="role" class="form-label">Role</label>
                <input type="text" name="role" id="role" class="form-control" placeholder="Enter Role" required>
            </div>
        </div>
        <button type="submit" class="btn btn-success">Add</button>
    </form>
</div>

<script>
    function validateEmail(input) {
        const pattern = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
        const feedback = document.getElementById('emailFeedback');
        if (!pattern.test(input.value)) {
            input.classList.add('is-invalid');
            feedback.style.display = 'block';
            return false;
        } else {
            input.classList.remove('is-invalid');
            feedback.style.display = 'none';
            return true;
        }
    }
</script>

</body>
</html>
