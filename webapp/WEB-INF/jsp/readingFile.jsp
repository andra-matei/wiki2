<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andmatei
  Date: 8/12/2016
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>From file</title>
</head>
<body>

<table border="1">
    <th>Article</th>
    <th>Name</th>
    <th>Occurences</th>
    <c:forEach items="${wordListFromFile}" var="word">
        <tr>
            <td><c:out value="${word.article}"/></td>
            <td><c:out value="${word.name}"/></td>
            <td><c:out value="${word.occurences}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
