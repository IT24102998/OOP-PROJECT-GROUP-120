<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navigation Bar (Black) -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">CaptureNow Admin</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/users">Users</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/photographers">Photographers</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/events">Events</a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/bookings">Bookings</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/reviews">Reviews</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Payment">Payment</a></li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!--Page Content -->
<div class="container mt-4">
    <h2>Booking Management</h2>
    <a href="${pageContext.request.contextPath}/bookings/process" class="btn btn-primary mb-3">Process Next Booking</a>

    <!-- Pending Bookings (Queue) Section -->
    <h3>Pending Bookings (Queue)</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Event ID</th>
            <th>Photographer ID</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="pendingBookings" scope="request" type="java.util.List"/>
        <c:forEach items="${pendingBookings}" var="booking">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.userId}</td>
                <td>${booking.eventId}</td>
                <td>${booking.photographerId}</td>
                <td>${booking.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- All Bookings Section -->
    <h3>All Bookings</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Event ID</th>
            <th>Photographer ID</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="allBookings" scope="request" type="java.util.List"/>
        <c:forEach items="${allBookings}" var="booking">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.userId}</td>
                <td>${booking.eventId}</td>
                <td>${booking.photographerId}</td>
                <td>${booking.status}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/bookings/delete/${booking.id}"
                       class="btn btn-danger"
                       onclick="return confirm('Are you sure you want to delete this booking?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Add New Booking Section -->
    <h3>Add Booking</h3>
    <form action="${pageContext.request.contextPath}/bookings/add" method="post">
        <div class="form-group">
            <label for="id">ID</label>
            <input type="number" name="id" id="id" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="userId">User ID</label>
            <input type="number" name="userId" id="userId" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="eventId">Event ID</label>
            <input type="number" name="eventId" id="eventId" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="photographerId">Photographer ID</label>
            <input type="number" name="photographerId" id="photographerId" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="status">Status</label>
            <input type="text" name="status" id="status" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success mt-2">Add</button>
    </form>
</div>

</body>
</html>
