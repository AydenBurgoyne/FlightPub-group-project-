    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%--
          Created by IntelliJ IDEA.
          User: SixLiner
          Date: 31/05/2020
          Time: 10:01 pm
          To change this template use File | Settings | File Templates.
        --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous" />
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <style>
        .dropbtn {
        background-color: #b2ccd1;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
        margin-right: 1em;
        }

        .dropdown {
        position: relative;
        display: inline-block;
        }

        .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f1f1f1;
        min-width: 25em;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
        }

        .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
        }

        .dropdown-content a:hover {background-color: #ddd;}

        .dropdown:hover .dropdown-content {display: block;}

        .dropdown:hover .dropbtn {background-color: #8ea8ad;}
    </style>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">FlightPub</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact Us</a>
                </li>

            </ul>

            <div class="form-inline my-2 my-lg-0">
                <c:if test="${ActiveUser!=null}">
                    <div class="dropdown">
                        <c:if test="${ActiveUser.type}">
                        <button class="dropbtn" data-toggle="tooltip" title="Because you are a business user, we have assigned your recommended flights based on your most frequently visited locations, leaving from your most frequent departure location in the next month">Recommended Flights</button>
                        <div class="dropdown-content">
                            <a href="#">Melbourne > Sydney, 0 day(s) from now</a>
                            <a href="#">Melbourne > Sydney, 1 day(s) from now</a>
                            <a href="#">Melbourne > Sydney, 2 day(s) from now</a>
                            <a href="#">Melbourne > Sydney, 4 day(s) from now</a>
                        </div>
                        </c:if>
                        <c:if test="${!ActiveUser.type}">
                            <button class="dropbtn" data-toggle="tooltip" title="Because you are a recreational user, we have generated recommended flights that leave from your most frequent departure location in the next month and arrive at locations in your destination wishlist!">Recommended Flights</button>
                            <div class="dropdown-content">
                            <a href="#">Sydney > Honolulu, 2 day(s) from now</a>
                            <a href="#">Sydney > Rio De Janeiro, 2 day(s) from now</a>
                            <a href="#">Sydney > Honolulu, 8 day(s) from now</a>
                            <a href="#">Sydney > Rio De Janeiro, 13 day(s) from now</a>
                            </div>
                        </c:if>
                    </div>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/Account/ViewAccount">Active User: ${ActiveUser.firstName} ${ActiveUser.lastName}</a>
<%--                    <h5 class="navbar-brand">Active User: ${ActiveUser.firstName} ${ActiveUser.lastName}</h5>--%>
                    <a class="nav-link" href="${pageContext.request.contextPath}/Account/ViewAccount">View Account</a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/Account/LogOut">Log Out</a>
                </c:if>
                <c:if test="${ActiveUser==null}">
                    <p style="margin-top: 17px; color: #17a2b8"> Please log in to view a personalised journey recommendation list </p>
                    <a class="nav-link" href="${pageContext.request.contextPath}/Account/Login">Login</a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/Account/CreateAccount">Create Account</a>
                </c:if>

            </div>
        </div>
    </nav>