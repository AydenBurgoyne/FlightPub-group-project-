<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>FlightPub Homepage</title>

    <%@include file="./layout/header.jsp"%>
</head>
<body>
    <div class="container">
        <h3>Search Journey</h3>
        <%@include file="./layout/search.jsp"%>
    </div>

</body>
<%@include file="./layout/footer.jsp"%>
</html>
