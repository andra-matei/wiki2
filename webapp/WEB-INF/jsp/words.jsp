<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andmatei
  Date: 8/11/2016
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top 10 words</title>
    <script type="text/javascript" src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>
<body>

<c:if test="${not empty wordList}">

    <script type="text/javascript">
        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer",
                    {
                        title: {
                            text: "${articleTitle}"
                        },
                        animationEnabled: true,
                        axisY: {
                            title: "Occurrences"
                        },
                        legend: {
                            verticalAlign: "bottom",
                            horizontalAlign: "center"
                        },
                        theme: "theme2",
                        data: [

                            {
                                type: "column",
                                showInLegend: true,
                                legendMarkerColor: "grey",
                                legendText: "Top 10 words",
                                dataPoints: [
                                    <c:forEach items="${wordList}" var="word">
                                    {y:   <c:out value="${word.occurences}"/>, label: "<c:out value="${word.name}"/>"},
                                    </c:forEach>
                                ]
                            }
                        ]
                    });

            chart.render();
        }
    </script>
</c:if>

<c:choose>
    <c:when test="${empty wordList}">
        <h2 id="chartContainer" style="color: crimson">"${eroare}"</h2>
    </c:when>
    <c:otherwise>
        <div id="chartContainer" style="height: 300px; width: 100%;">
        </div>
    </c:otherwise>
</c:choose>


</body>
</html>