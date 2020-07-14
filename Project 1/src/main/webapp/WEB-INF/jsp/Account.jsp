<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Account Details</title>
    <jsp:include page="layout/header.jsp"/>

    <style>

        .myList
        {
            width: 40%;
            height: 5%;
            overflow: scroll;
            margin: 25px auto 80px;

            padding: 0;

            border: 2px solid #ccc;

            font-size: 16px;
            font-family: Arial, sans-serif;

            -webkit-overflow-scrolling: touch;
        }
        * {box-sizing: border-box;}

        .myListItem {
            padding: 10px 20px;

            border-bottom: 1px solid #ccc;

        }
        .myListItem:last-child {
            border-bottom: none;
        }
        .myListItem:nth-child(even) {
            background: #f8f8f8;
        }



    </style>
</head>
<body>
    <div class="container">
        <h3>FlightPub</h3>
        <br>
        <table class="table">
            <tr>
                <td>Name:</td>
                <td>
                    <input type="text" class="form-control" value="${account.firstName} ${account.lastName}" required>
                </td>
            </tr>
            <tr>
                <td>Address:</td>
                <td>
                    <input type="text" class="form-control" value="${account.address}" required>
                </td>
            </tr>
            <tr>
                <td>Date of Birth:</td>
                <td>
                    <input type="text" class="form-control" value="${account.dateOfBirth}" required>
                </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>
                    <input type="email" class="form-control" value="${account.email}" required>
                </td>
            </tr>
            <tr>
                <td>Account type:</td>
                <td>
                    <input type="text" class="form-control" value="${account.type}">
                </td>
            </tr>
            <tr>
                <td>Destination Wishlist:</td>
                <td>
                    <input type="text" class="form-control" value="${account.dummyWishList}">
                </td>
            </tr>
            <tr>
                <td><input onclick="myFunction()" type="submit" id="submit" class="btn btn-info" value="Save Changes"></td>
            </tr>
        </table>

<%--        <div class="toast" id="myToast" role="alert" aria-live="assertive" aria-atomic="true">--%>
<%--            <div class="toast-header">--%>
<%--                <strong class="mr-auto">Account changes saved</strong>--%>
<%--                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">--%>
<%--                    <span aria-hidden="true">&times;</span>--%>
<%--                </button>--%>
<%--            </div>--%>
<%--            <div class="toast-body">--%>
<%--               Message--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

    <h4 style="margin-left: 500px">
      Journey History
    </h4>
        <ul class="myList">
            <li class="myListItem">
                Melbourne > Sydney, 04-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 09-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 14-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 19-08-19
            </li>
            <li class="myListItem">
                Amsterdam > Rome-Fiumicino, 03-09-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 04-10-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 09-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 14-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 20-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 23-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 25-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 27-08-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 29-09-19
            </li>
            <li class="myListItem">
                Melbourne > Sydney, 20-10-19
            </li>
        </ul>
        </div>
    </div>



</body>
<script>
    // $(document).ready(function(){
    //     $("#submit").on("click", function(){
    //         $("#myToast").toast({ delay: 3000 });
    //         $("#myToast").toast('show');
    //     });
    // });

    function myFunction() {
        alert("Account changes saved");
    }
</script>
<jsp:include page="layout/footer.jsp"/>
</html>
