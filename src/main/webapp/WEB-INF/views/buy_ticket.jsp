<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet"
		      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		      integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		      crossorigin="anonymous">
		<title>Buy ticket</title>
	</head>
	<body>

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/" style="color: red">Railway</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
			        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
						<a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
					</li>
					<security:authorize access="hasAuthority('ADMIN')">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/trains">Trains</a>
						</li>
					</security:authorize>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/stations">Stations</a>
					</li>
					<security:authorize access="isAnonymous()">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
						</li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="nav-item">
							<form:form action="${pageContext.request.contextPath}/logout" method="post">
								<input type="submit" value="Logout"/>
							</form:form>
						</li>
					</security:authorize>
				</ul>
			</div>
		</nav>

		<div class="container-fluid mt-3">

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<label class="input-group-text" for="inputGroupSelect01">From</label>
				</div>
				<select class="custom-select" id="inputGroupSelect01">
					<c:forEach var="tempStation" items="${stations}">
						<option selected>${tempStation.name}</option>
					</c:forEach>
				</select>
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<label class="input-group-text" for="inputGroupSelect02">To</label>
				</div>
				<select class="custom-select" id="inputGroupSelect02">
					<c:forEach var="tempStation" items="${stations}">
						<option selected>${tempStation.name}</option>
					</c:forEach>
				</select>
			</div>

			<button type="button" class="btn btn-primary">
				<a href="#" class="text-white-50">Find trains</a>
			</button>

		</div>

		<footer class="navbar fixed-bottom">
			<div class="footer-copyright text-center py-3">© <%= LocalDate.now().getYear() %> Copyright: Gluegun
				Ltd.
			</div>
		</footer>

		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		        crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		        crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		        crossorigin="anonymous"></script>

	</body>
</html>
