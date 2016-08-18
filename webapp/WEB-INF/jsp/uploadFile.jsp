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
<body>
<div id="allText" class="myDiv">

    <form method="POST" action="/upload" enctype="multipart/form-data" class="center">

        <input type="file" name="file" id="file-1" class="inputfile inputfile-1"
               onchange="setValue()" required accept="text/plain"/>

        <label for="file-1">
            <%--Choose file to upload icon--%>
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17">
                <path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/>
            </svg>

            <span>Choose a file…</span>
        </label>

        <%--File Path Display--%>
        <input type="text" readonly id="filePath" style="width: auto" placeholder="No path selected">
        <br/><br/>

        <%--SUBMIT Button--%>
        <input type="submit" value="Upload" class="butonUpload">
    </form>
</div>

<script>
    function setValue() {
        var value = document.getElementById("file-1").value;
        document.getElementById("filePath").value = value;
    }
</script>

<div style="position: relative; margin-left: 600px">
    <p class="paragraf">
        @Zburatura Adrian - Junior Developer
        @Matei Andra - Junior AM Engineer
    </p>
</div>

</body>
</html>

