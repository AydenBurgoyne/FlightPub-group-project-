<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SixLiner
  Date: 31/05/2020
  Time: 9:46 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Journey</title>
    <%@include file="../layout/header.jsp"%>
</head>
<body>
    <div class="container">
        <h3>Search Journeys</h3>
        <br>
        <table class="table table-bordered">
            <form:form action="/search" method="get" modelAttribute="SearchQueryEntity">
            <tr>
                <td>
                    From Destination
                    <form:input class="form-control" path="fromDest"/> <br>
                    <form:errors path="fromDest" cssClass="error" />
                </td>
                <td>
                    To Destination
                    <form:input class="form-control" path="toDest" /> <br/>
                    <form:errors path="toDest" cssClass="error"/>
                </td>
                <td>
                    Via:
                    <form:input class="form-control" type="date" path="fromTime" /> <br/>
                    <form:errors path="fromTime" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    Earliest Time:
                    <form:input class="form-control" type="date" path="fromTime" /> <br/>
                    <form:errors path="fromTime" cssClass="error"/>
                </td>
                <td>
                    Latest Departure Date:
                    <form:input class="form-control" type="date" path="toTime"/> <br/>
                    <form:errors path="toTime" cssClass="error"/>
                </td>
                <td>
                    Max Layover Time:
                    <form:input class="form-control" type="number" min="0" max="72" path="maxLayoverTime"/> <br/>
                    <form:errors path="toTime" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-info" style="margin-top: 30px;margin-left: 30px;">Search</button>
                </td>
            </tr>
            <tr>
                <td><input class="form-control btn btn-info" style="margin-top: 30px;margin-left: 30px;" type="submit"></td>
            </tr>
            </form:form>

        </table>
        <br>
        <table class="table table-striped">
            <tr>
                <th>Departure</th>
                <th>Departure Time</th>
                <th>Arrival</th>
                <th>Arrival Time</th>
                <th>Price</th>
                <th>Total Flight Time</th>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>

        <table class="table" style="position: fixed;bottom: 52px;width: 58%;">
            <tr>
                <td><a href="">Previous</a></td>
                <td>Page Number </td>
                <td><a href="">Next</a></td>
            </tr>
        </table>
    </div>
</body>
    <%@include file="../layout/footer.jsp"%>
</html>
