<%--
  Created by IntelliJ IDEA.
  User: azburatura
  Date: 8/12/2016
  Time: 11:57 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload file</title>
</head>
<body>

<form method="POST" action="/uploadFileController" enctype="multipart/form-data">
    File to upload: <input type="file" name="file" id="fileUpload"/>
    <br/>

    <input type="submit" value="Upload"> Press here to upload the file!
</form>

</body>
</html>
