<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Invoice Management</title>
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
    <a href="${pageContext.request.contextPath}/photographers">Photographers</a>
    <a href="${pageContext.request.contextPath}/events">Events</a>
    <a href="${pageContext.request.contextPath}/bookings">Bookings</a>
    <a href="${pageContext.request.contextPath}/reviews">Reviews</a>
    <a href="${pageContext.request.contextPath}/Payment" class="active">Payment</a>
    <a href="${pageContext.request.contextPath}/logout" class="text-danger">Logout</a>
</div>

<!-- ✅ Content Area -->
<div class="content">
    <h2>Payment Management</h2>

    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Booking ID</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="invoice" items="${invoices}">
            <tr>
                <td>${invoice.id}</td>
                <td>${invoice.bookingId}</td>
                <td>${invoice.amount}</td>
                <td>${invoice.date}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/invoices/delete/${invoice.id}"
                       class="btn btn-danger"
                       onclick="return confirm('Are you sure you want to delete this invoice?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h3>Add Payment</h3>
    <form action="${pageContext.request.contextPath}/invoices/add" method="post">
        <div class="row mb-3">
            <div class="col-md-6">
                <label>ID</label>
                <input type="number" name="id" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label>Booking ID</label>
                <input type="number" name="bookingId" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <label>Amount</label>
                <input type="number" step="0.01" name="amount" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label>Date</label>
                <input type="date" name="date" class="form-control" required>
            </div>
        </div>
        <button type="submit" class="btn btn-success">Add</button>
    </form>
</div>

</body>
</html>
