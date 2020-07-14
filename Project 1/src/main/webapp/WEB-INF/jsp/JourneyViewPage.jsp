<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ayden
  Date: 5/06/2020
  Time: 5:07 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Journey View</title>
    <jsp:include page="layout/header.jsp"/>
</head>
<body>

<div class="container">
    <h2>Journey View</h2>
    <table class="table table-striped">
        <tr><h3>Flight:${Dep.departureCode.airport} To ${Des.destinationCode.airport}</h3></tr>
        <c:forEach items="${Flights}" var="Leg">
            <h4>Leg:${Leg.departureCode.airport} to ${Leg.destinationCode.airport}</h4>
        </c:forEach>
        <form action="/Journey/newBooking" method="get">
            <input type="hidden" value="${FlightID}" name="flightIds"/>
            <input type="hidden" value="${ticket.ticketCode}" name="TicketType"/>
            <input type="hidden" value="${ClassType.classCode}" name="ClassCode"/>
<%--            <input type="submit" class="btn btn-info" value="Book" />--%>
<%--        </form>--%>
    </table>
    <H2>Smart traveller Advice:</H2>
    <p>The average smart traveller suggested threat level for this journey is 3/10 </p>
    <p>For more information click the <a href="https://www.smartraveller.gov.au/">SmartTraveller</a> Link </p>
    <H2>Reviews:</H2>
    <div class="accordion" id="accordionExample">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h2 class="mb-0">
                    <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                        <h3>Average Review Score: 5</h3>
                    </button>
                </h2>
            </div>

            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="card-body">
                    <H4>Qantas: 5 stars</H4>
                    <H4>Virgin: 5 stars</H4>
                    <H4>Hotel1: 5 stars</H4>
                </div>
            </div>
        </div>
</div>
    <input type="submit" class="btn btn-info" value="Book" />
    </form>
</body>
<jsp:include page="layout/footer.jsp"/>
</html>