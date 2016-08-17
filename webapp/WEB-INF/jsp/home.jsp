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
    <link rel="stylesheet" type="text/css" href="/resources/style/mystyle.css">
    <title>Wiki Indexer</title>
</head>
<body>
<div id="allText" class="myDiv">
    <div class="bg-warning">

        <h2 class="center">Welcome to Wiki Indexer!</h2>

        <form action="/" method="post" class="center">
            <input type="text" name="title" placeholder="Insert here the article title">
            <button type="submit" value="submit" class="buton" style="width: 140px">Press Here</button>
        </form>


        <form action="/readFile" method="post" class="center">
            <button type="submit" class="buton" value="submit" style="width: 140px">Read From File</button>
            <br/>
            <br/>
            <button type="button" class="buton" onclick="window.location='/uploadFile'" style="width: 140px">
                Upload File Page
            </button>
        </form>

    </div>

</div>
<div style="position: relative; margin-left: 600px">
    <p class="paragraf">
        @Zburatura Adrian - Junior Developer
        @Matei Andra - Junior AM Engineer
    </p>
</div>
</body>
</html>
