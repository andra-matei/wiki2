<%--
  Created by IntelliJ IDEA.
  User: azburatura
  Date: 8/12/2016
  Time: 11:57 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/style/mystyle.css">
    <title>Upload file</title>
</head>
<body class="bodyClass">

<form method="POST" action="/uploadFileController" enctype="multipart/form-data" class="myDiv">
    File to upload: <input type="file" name="file" id="fileUpload" class="butonUpload"/>
    <br/>
    <br/>
    <input type="submit" value="Upload" class="butonUpload"> Press here to upload the file!
</form>

</body>
</html>
