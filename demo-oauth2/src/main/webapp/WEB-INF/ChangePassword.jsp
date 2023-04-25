<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 4/24/2023
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
</head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<body>
<div class="form-container sign-in-container">
    <form action="" method="post" onsubmit="" >
        <h1>Change Password</h1>
        <label for="password">New Password</label>
        <input type="password" placeholder="Password" name="password" autocomplete="current-password" required="" id="password" />
        <label for="re_password">Cofim Password</label>
        <input type="password" placeholder="Cofim Password" name="re_password" id="re_password" required="">
        <button type="submit">Change Password</button>
    </form>
</div>
</body>
</html>
