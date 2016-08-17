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
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<c:if test="${not empty wordList}">
    <%--Script for displaying the chart in CanvasJs--%>
    <script type="text/javascript">
        window.onload = function () {
            CanvasJS.addColorSet("blueShades",
                    [//colorSet Array

                        "#66CCCC",
                        "#388E8E",
                        "#008080",
                        "#2F4F4F",
                        "#B4CDCD"
                    ]);
            var chart = new CanvasJS.Chart("chartContainer",
                    {
                        colorSet: "blueShades",
                        title: {
                            text: "${articleTitle}",
                            fontSize: 40,
                            titleFontFamily: "verdana",
                        },
                        animationEnabled: true,
                        animationDuration: 2000,
                        dataPointMaxWidth: 90,
                        axisY: {
                            title: "Occurrences",
                            tickThickness: 1,
                            gridThickness: 1,
                        },
                        legend: {
                            verticalAlign: "bottom",
                            horizontalAlign: "center"
                        },
                        theme: "theme2",
                        backgroundColor: "beige",
                        data: [

                            {
                                type: "column",
                                showInLegend: true,
                                legendMarkerColor: "grey",
                                legendText: "Top 10 words come from " + "${source}" + " Time spent: " + "${timeSpent}" + " milliseconds",
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
    <%--If the wordList is empty then display an error--%>
    <c:when test="${empty wordList}">
        <h1 id="chartContainer" class="alert alert-danger" style="text-align: center">${eroare}</h1>
    </c:when>
    <%--If the wordList is not empty then display the chart with the top 10 words--%>
    <c:otherwise>
        <div id="chartContainer" style="height: 100%; width: 100%;">
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
