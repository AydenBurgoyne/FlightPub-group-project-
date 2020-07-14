<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ayden
  Date: 4/06/2020
  Time: 11:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Confirmation Page</title>
    <jsp:include page="layout/header.jsp"/>
</head>
<body>

<div class="container">
    <h3>Someone has reserved a seat for you!</h3>

    <p class="lead" style="text-align: center">
        The journey is from [Melbourne] to [Sydney] on [05/08/2020] <br>
        For flight 1 of 1 in this journey they have reserved seat 13b for you in [business] class <br>
        You have two weeks from the time you received this email to confirm and pay for this booking via the button below.
    </p>

    <a href="#" class="btn btn-info" value="Confirm Booking" style="width: 7%;margin-left: 500px;"> Confirm Booking</a>

</div>
</body>
<jsp:include page="layout/footer.jsp"/>
</html>