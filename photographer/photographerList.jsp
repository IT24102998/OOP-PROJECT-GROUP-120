<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Photographer Management</title>
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

<!-- ✅ Sidebar Navigation -->
<div class="sidebar">
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/users">Users</a>
    <a href="${pageContext.request.contextPath}/photographers" class="active">Photographers</a>
    <a href="${pageContext.request.contextPath}/events">Events</a>
    <a href="${pageContext.request.contextPath}/bookings">Bookings</a>
    <a href="${pageContext.request.contextPath}/reviews">Reviews</a>
    <a href="${pageContext.request.contextPath}/Payment">Payment</a>
    <a href="${pageContext.request.contextPath}/logout" class="text-danger">Logout</a>
</div>

<!-- ✅ Content Area -->
<div class="content">
    <h2>Photographer Management</h2>

    <a href="${pageContext.request.contextPath}/photographers/sort" class="btn btn-primary mb-3">Sort by Rating</a>

    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Rating</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="photographer" items="${photographers}">
            <tr>
                <td>${photographer.id}</td>
                <td>${photographer.name}</td>
                <td>${photographer.email}</td>
                <td>${photographer.rating}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/photographers/delete/${photographer.id}" class="btn btn-danger"
                       onclick="return confirm('Are you sure you want to delete this photographer?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h3>Add Photographer</h3>
    <form action="${pageContext.request.contextPath}/photographers/add" method="post">
        <div class="row mb-3">
            <div class="col-md-6">
                <label>ID</label>
                <input type="number" name="id" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label>Name</label>
                <input type="text" name="name" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <label>Email</label>
                <input type="email" name="email" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label>Rating</label>
                <input type="number" step="0.1" name="rating" class="form-control" required>
            </div>
        </div>
        <button type="submit" class="btn btn-success">Add</button>
    </form>
</div>

</body>
</html>
