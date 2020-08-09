<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Edit train</title>
	</head>
	<body>

		<c:url var="action" value="/trains/update"/>

		<form:form method="post" action="${action}" modelAttribute="trainDto">
			<table>
				<tr>
					<td>
						<form:hidden path="id"/>
						<form:label path="trainNumber">Train number</form:label></td>
					<td><form:input path="trainNumber"/></td>
				</tr>
				<tr>
					<td><form:label path="seatsAmount">Seats amount</form:label></td>
					<td><form:input path="seatsAmount"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Update train"></td>
				</tr>
			</table>
		</form:form>
	</body>
</html>
