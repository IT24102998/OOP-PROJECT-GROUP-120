<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Event</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2>Edit Event</h2>
  <form action="${pageContext.request.contextPath}/events/update" method="post">
    <input type="hidden" name="id" value="${event.id}">

    <%-- Name change as Description--%>
    <%-- <div class="mb-3">
      <label for="name" class="form-label">Event Name</label>
      <input type="text" class="form-control" id="name" name="name" value="${event.name}" required>
    </div> --%>

    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea class="form-control" id="description" name="description" required>${event.description}</textarea>
    </div>

    <div class="mb-3">
      <label for="type" class="form-label">Type</label>
      <input type="text" class="form-control" id="type" name="type" value="${event.type}" required>
    </div>

    <div class="mb-3">
      <label for="date" class="form-label">Date</label>
      <input type="date" class="form-control" id="date" name="date" value="${event.date}" required>
    </div>

    <div class="mb-3">
      <label for="location" class="form-label">Location</label>
      <input type="text" class="form-control" id="location" name="location" value="${event.location}" required>
    </div>




    <button type="submit" class="btn btn-primary">Update Event</button>
    <a href="${pageContext.request.contextPath}/events" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>
