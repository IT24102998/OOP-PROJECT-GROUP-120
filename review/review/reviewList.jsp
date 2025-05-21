<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Review Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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

<!-- Side Navigation Bar -->
<div class="sidebar">
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/users">Users</a>
    <a href="${pageContext.request.contextPath}/photographers">Photographers</a>
    <a href="${pageContext.request.contextPath}/events">Events</a>
    <a href="${pageContext.request.contextPath}/bookings">Bookings</a>
    <a href="${pageContext.request.contextPath}/reviews" class="active">Reviews</a>
    <a href="${pageContext.request.contextPath}/Payment">Payment</a>
    <a href="${pageContext.request.contextPath}/logout" class="text-danger">Logout</a>
</div>

<!--Page Content -->
<div class="content">
    <h2 class="mb-4">Review Management</h2>
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Photographer ID</th>
            <th>User ID</th>
            <th>Rating</th>
            <th>Comment</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="review" items="${reviews}">
            <tr>
                <td>${review.id}</td>
                <td>${review.photographerId}</td>
                <td>${review.userId}</td>
                <td>${review.rating}</td>
                <td>${review.comment}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/reviews/delete/${review.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure you want to delete this review?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h3 class="mt-5">Add Review</h3>
    <form action="${pageContext.request.contextPath}/reviews/add" method="post">
        <div class="mb-3">
            <label class="form-label">ID</label>
            <input type="number" name="id" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Photographer ID</label>
            <input type="number" name="photographerId" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">User ID</label>
            <input type="number" name="userId" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Rating</label>
            <input type="number" step="0.1" name="rating" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Comment</label>
            <input type="text" name="comment" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">Add</button>
    </form>
</div>

</body>
</html>
