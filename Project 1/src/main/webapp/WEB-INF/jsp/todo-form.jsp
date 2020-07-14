<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: luker
  Date: 30-Mar-20
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>New ToDo Item</h1>
<form:form action="/todo/createItem" method="post" modelAttribute="item">
    Item Name: <form:input path="name"/> <br/>
    <form:errors path="name" cssClass="error" /> <br/>

    Description: <form:textarea path="desc" /> <br/>
    <form:errors path="desc" cssClass="error"/> <br/>

    Completed: <form:checkbox path="done"/> <br/>
    <form:errors path="done" cssClass="error"/> <br/>

    <input type="submit"/>
</form:form>

</body>
</html>
