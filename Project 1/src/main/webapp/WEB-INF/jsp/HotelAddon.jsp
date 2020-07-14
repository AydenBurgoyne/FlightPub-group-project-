<%--
  Created by IntelliJ IDEA.
  User: Lachlan
  Date: 5/6/20
  Time: 5:44 pm
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="en">
<head>
    <title>FlightPub Homepage</title>

    <%@include file="./layout/header.jsp"%>

</head>
<body>
<div class="container">
    <h3>Journey Information:</h3>
    <div><p>Because you're one of our valuable
        <c:choose>
            <c:when test="${ActiveAccount.type == true}">
                Business
            </c:when>
            <c:otherwise>
                Recreational
            </c:otherwise>
        </c:choose>
        customers; we've compiled this list of locations to get the ost out of your journey with FlightPub!</p></div>

        <c:choose>
            <c:when test="${ActiveAccount.type == true}">
                <p>With the destination that you've selected and your user type we've
                    selected a hotel that would be the most convenient for your business trip:</p>

                <img src="<c:url value="/resources/businessHotelList.JPG" />" alt="business hotel list">

                <button class="btn btn-info" style="position:absolute; top: 20em; right: 20em;" onclick="confirmFunction()">Click here to book now!</button>
                <button class="btn btn-info" style="position:absolute; top: 20em; right: 20em;" onclick="confirmFunction()">Click here to book now!</button>
                <button class="btn btn-info" style="position:absolute; top: 20em; right: 20em;" onclick="confirmFunction()">Click here to book now!</button>

            </c:when>
            <c:otherwise>
                <p>With the destination selected and compiled against user reviews,
                    we believe that the best location for a beautiful relaxing holiday would be from the list below:</p>

                <img src="<c:url value="/resources/recreationalHotelList.JPG" />" alt="recreational hotel list"/>
                <button class="btn btn-info"  style="position:absolute; top: 20em; right: 20em;" onclick="confirmFunction()">Click here to book now!</button>
                <button class="btn btn-info" style="position:absolute; top: 35em; right: 20em;" onclick="confirmFunction()">Click here to book now!</button>
                <button class="btn btn-info" style="position:absolute; top: 50em; right: 20em;" onclick="confirmFunction()">Click here to book now!</button>

            </c:otherwise>
        </c:choose>

</div>

<script>
function confirmFunction(){
    alert("You have successfully booked this hotel!")
    history.back()
}

</script>
</body>
<%@include file="./layout/footer.jsp"%>
</html>
