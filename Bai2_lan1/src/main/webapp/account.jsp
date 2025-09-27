<%--
  Created by IntelliJ IDEA.
  User: viet6
  Date: 9/27/2025
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<table border="1">
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    <th>Date of Birth</th>
  </tr>
  <c:forEach var="acc" items="${accounts}">
    <tr>
      <td>${acc.firstname}</td>
      <td>${acc.lastname}</td>
      <td>${acc.email}</td>
      <td>${acc.dateOfBirth}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
