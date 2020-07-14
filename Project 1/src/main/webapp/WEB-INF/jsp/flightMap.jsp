<%--
  Created by IntelliJ IDEA.
  User: Lachlan
  Date: 30/5/20
  Time: 11:40 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>flightMap</title>

    test, test <br>

    <%--${country}<br>--%>
    <%--${country}--%>

    <c:forEach items="${flights}" var="d">
        Departure: ${d.departureCode.destinationCode}, <br>
        Destination: ${d.destinationCode.destinationCode}, <br>
        Duration: ${d.duration}, <br>
        <br><br>
       <%-- ${d.stopOverCode},
        ${d.destinationCode},<br>--%>
    </c:forEach>

    <c:forEach items="${llist}" var="list">
        ${list}, <br>
        <br>
        <%-- ${d.stopOverCode},
         ${d.destinationCode},<br>--%>
    </c:forEach>


    <%--<c:forEach items="${flightMap}" var="d">
        ${},<br>
    </c:forEach>--%>

    <%--<br>
    <br>
    <br>

    <c:forEach items="${countries}" var="c">
        ${c.countryCode2},
        ${c.countryCode2},
        ${c.countryName} <br>
    </c:forEach>

    <br>
    <br>
    <br>

    <c:forEach items="${sortedCountries}" var="c">
        ${c.countryCode3.countryCode3},
        ${c.countryCode3.countryName},
        ${c.destinationCode} <br>
    </c:forEach>--%>


</head>
<body>

</body>
</html>
