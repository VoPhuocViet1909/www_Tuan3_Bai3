<%--
  Created by IntelliJ IDEA.
  User: viet6
  Date: 9/27/2025
  Time: 10:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>User Registration Form</title>
  <style>
    body { font-family: Arial, sans-serif; background: #f5f5f5; }
    .container {
      width: 400px; margin: 30px auto; padding: 20px;
      border: 1px solid #ccc; border-radius: 8px; background: #fff;
    }
    h2 { text-align: center; margin-bottom: 20px; }
    input, select { width: 100%; padding: 10px; margin: 5px 0 15px; border: 1px solid #ccc; border-radius: 5px; }
    .row { display: flex; gap: 10px; }
    .gender { margin-bottom: 15px; display: flex; gap: 10px; align-items: center; }
    .btn {
      background: #1877f2; color: white; padding: 10px;
      width: 100%; border: none; border-radius: 5px; cursor: pointer;
    }
    .btn:hover { background: #145dbf; }
  </style>
</head>
<body>
<div class="container">
  <h2>User Registration Form</h2>
  <form action="${pageContext.request.contextPath}/RegisterForm" method="post">

    <div class="row">
      <input type="text" name="firstname" placeholder="First Name" required>
      <input type="text" name="lastname" placeholder="Last Name" required>
    </div>
    <input type="email" name="email" placeholder="Your Email" required>
    <input type="password" name="password" placeholder="Password" required>

    <label>Birthday</label>
    <div class="row">
      <select name="month" required>
        <option value="">Month</option>
        <option value="1">January</option>
        <option value="2">February</option>
        <!-- … thêm các tháng khác -->
        <option value="12">December</option>
      </select>
      <select name="day" required>
        <option value="">Day</option>
        <% for(int d=1; d<=31; d++){ %>
        <option value="<%=d%>"><%=d%></option>
        <% } %>
      </select>
      <select name="year" required>
        <option value="">Year</option>
        <% for(int y=2025; y>=1900; y--){ %>
        <option value="<%=y%>"><%=y%></option>
        <% } %>
      </select>
    </div>

    <div class="gender">
      <label><input type="radio" name="gender" value="Female" required> Female</label>
      <label><input type="radio" name="gender" value="Male"> Male</label>
    </div>

    <button type="submit" class="btn">Sign Up</button>
  </form>
</div>
</body>
</html>
