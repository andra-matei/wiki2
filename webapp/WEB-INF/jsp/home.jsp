<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: azburatura
  Date: 8/2/2016
  Time: 2:02 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Home page</title>
</head>
<body>
<div class="bg-warning">

    <h2>Hello, aici inserati titlu</h2>

    <form action="/" method="post">
        <input type="text" name="title">
        <button type="submit" value="submit" class="btn btn-info">Apasa Aici</button>
    </form>


    <form action="/readFile" method="post">
        <button type="submit" value="submit" class="btn btn-info" >Read From File</button>
    </form>


    <br/>
    <button type="button" class="btn btn-info" onclick="window.location='/uploadFile'">Upload 1 file page</button>
</div>
</body>
</html>
