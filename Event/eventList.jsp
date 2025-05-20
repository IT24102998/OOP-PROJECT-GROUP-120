<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Event Management</title>
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

<div class="sidebar">
  <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
  <a href="${pageContext.request.contextPath}/users">Users</a>
  <a href="${pageContext.request.contextPath}/photographers">Photographers</a>
  <a href="${pageContext.request.contextPath}/events" class="active">Events</a>
  <a href="${pageContext.request.contextPath}/bookings">Bookings</a>
  <a href="${pageContext.request.contextPath}/reviews">Reviews</a>
  <a href="${pageContext.request.contextPath}/Payment">Payment</a>
  <a href="${pageContext.request.contextPath}/logout" class="text-danger">Logout</a>
</div>

<div class="content">
  <h2>Event Management</h2>

  <div class="table-responsive">
    <table class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Description</th> <th>Type</th>
        <th>Date</th>
        <th>Location</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="event" items="${events}">
        <tr>
          <td>${event.id}</td>
          <td>${event.description}</td> <td>${event.type}</td>
          <td>${event.date}</td>
          <td>${event.location}</td>
          <td>
            <a href="${pageContext.request.contextPath}/events/edit/${event.id}" class="btn btn-warning">Edit</a>
            <a href="${pageContext.request.contextPath}/events/delete/${event.id}" class="btn btn-danger"
               onclick="return confirm('Are you sure you want to delete this event?');">Delete</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <h3>Add Event</h3>
  <form action="${pageContext.request.contextPath}/events/add" method="post">
    <div class="row mb-3">
      <div class="col-md-6">
        <label for="id" class="form-label">ID</label>
        <input type="number" name="id" id="id" class="form-control" placeholder="Enter Event ID" required>
      </div>
      <div class="col-md-6">
        <label for="description" class="form-label">Description</label> <input type="text" name="description" id="description" class="form-control" placeholder="Enter Description" required> </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-6">
        <label for="type" class="form-label">Type</label>
        <input type="text" name="type" id="type" class="form-control" placeholder="Enter Event Type" required>
      </div>
      <div class="col-md-6">
        <label for="date" class="form-label">Date</label>
        <input type="date" name="date" id="date" class="form-control" required>
      </div>
    </div>
    <div class="mb-3">
      <label for="location" class="form-label">Location</label>
      <input type="text" name="location" id="location" class="form-control" placeholder="Enter Event Location" required>
    </div>
    <button type="submit" class="btn btn-success">Add</button>
  </form>
</div>

</body>
</html>