<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SixLiner
  Date: 2/06/2020
  Time: 11:33 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../layout/header.jsp" />
</head>
<body>
    <div class="container">
        <table class="table">
            <tr>
                <td>Price:</td>
                <td>$${journey.price}</td>
            </tr>
            <tr>
                <td>Stops:</td>
                <table class="table">
                    <tr>
                        <th>Depart at</th>
                        <th>Duration</th>
                        <th>Arrive at</th>
                    </tr>
                    //Loop through legs
                    <c:forEach items="${flights}" var="trip">
                        <tr>
                            <td>
                                ${trip.departureTime} at ${trip.departureCode.airport}
                            </td>
                            <td>
                                ${trip.duration}
                            </td>
                            <td>
                                ${trip.arrivalTime} at ${trip.destinationCode.airport}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </tr>
        </table>
    </div>
</body>

<jsp:include page="../layout/footer.jsp"/>
</html>
