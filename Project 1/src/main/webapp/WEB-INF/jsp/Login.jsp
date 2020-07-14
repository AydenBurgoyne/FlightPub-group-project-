<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Login</title>
    <%@include file="./layout/header.jsp"%>
</head>

<body class="text-center">
    <div class="container" style="height: auto; width: 20%;">
        <h2> Please enter your credentials </h2>
        <form:form class="form-signin" action="/Account/ValidateLogin" method="post" modelAttribute="LoginQueryEntity">
            Email Address: <form:input class="form-control" path="emailAddress"/> <br/>
            <form:errors path="emailAddress" cssClass="error" /> <br/>
            Password: <form:password class="form-control" path="password" /> <br/>
            <form:errors path="password" cssClass="error"/> <br/>
            <input class="btn btn-info" type="submit" value="Submit"/>
        </form:form>
    </div>
</body>

<%@include file="./layout/footer.jsp"%>
</html>
