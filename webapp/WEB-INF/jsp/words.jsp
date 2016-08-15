<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andmatei
  Date: 8/11/2016
  Time: 3:33 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top 10 words</title>
</head>
<body>

<table border="1" class="table table-striped" bgcolor="#5bc0de">
    <th>Name</th>
    <th>Occurences</th>
    <c:forEach items="${wordList}" var="word">
        <tr>
            <td><c:out value="${word.name}"/></td>
            <td><c:out value="${word.occurences}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
