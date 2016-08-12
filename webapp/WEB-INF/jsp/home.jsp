<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: azburatura
  Date: 8/2/2016
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Home page</title>
</head>
<body>
<div class="container-fluid">
    <div class="container-fluid jumbotron">

    <h2>Hello, aici inserati titlu</h2>

    <form action="/" method="post">
        <input type="text" name="title">
        <input type="submit" value="apasa aici">
    </form>
        </div>

    <form action="/readFile" method="post">
        <input type="submit" value="Read from file">
    </form>
</div>

<br/>
<button type="button" onclick="window.location='/uploadFile'">Upload 1 file page</button>

</body>
</html>
