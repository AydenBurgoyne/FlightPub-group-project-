<%--
  Created by IntelliJ IDEA.
  User: luker
  Date: 02-Jun-20
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <%@ page %>
    <%@include file="./layout/header.jsp"%>

    <title>Search Results:</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>

        .tableFixHead          { overflow-y: auto; height: 80%;}
        .tableFixHead thead th { position: sticky; top: 0; }

        /* Just common table stuff. Really. */
        table  { border-collapse: collapse; width: 100%; }

        th, td { padding: 8px 16px; }
        th     { background:#eee; }

        .filterTable{
            table-layout: fixed;
        }
        .filterData
        {
            width: 12.5% ;
        }
        .slider {
            -webkit-appearance: none;
            width: 100%;
            height: 10px;
            background: #d3d3d3;
            outline: none;
            opacity: 0.7;
            -webkit-transition: .2s;
            transition: opacity .2s;
        }

        .slider:hover {
            opacity: 1;
        }

        .slider::-webkit-slider-thumb {
            -webkit-appearance: none;
            appearance: none;
            width: 10px;
            height: 10px;
            background: #4CAF50;
            cursor: pointer;
        }

        .slider::-moz-range-thumb {
            width: 25px;
            height: 25px;
            background: #4CAF50;
            cursor: pointer;
        }

    </style>
</head>
<body>

<div class="container">
    <h3>Search Journey</h3>
    <%@include file="./layout/search.jsp"%>
</div>

<div style="text-align: center;color: #4e555b" >
    <h2>Search Results</h2>
</div>
<table class="filterTable">
    <tr>
        <td style=" width: 7%">
            <b>Filter By:</b>
        </td>
        <td class="filterData">
            Maximum Price:
            <input type="range" min="1" max="10000" value="50" class="slider" id="myRange">
            <p>Value: <span id="demo"></span></p>
        </td>
        <td class="filterData">
            Maximum Number of stops:
            <input type="range" min="1" max="8" value="50" class="slider" id="myRangetwo">
            <p>Value: <span id="demotwo"></span></p>
        </td>
        <td class="filterData">
            Minimum review rating:
            <input type="range" min="1" max="5" value="50" class="slider" id="myRangethree">
            <p>Value: <span id="demothree"></span></p>
        </td>
        <td style=" width: 20%">
            Essential Stopover Location:
            <select name="places">
                <option>Kuala Lumpur</option>
                <option>Perth</option>
                <option>Hong Kong</option>
                <option>Helsinki</option>
            </select>
        </td>

        <td style=" width: 4%">
            <b>Sort By:</b>
        </td>
        <td class="filterData">
            <select class="form-control" name="filterBy">
                <option value="price">Price</option>
                <option value="duration">Duration</option>
                <option value="jetlag">Jet Lag Severity</option>
                <option value="stopCount">Number of stops</option>
            </select>
        </td>
        <td class="filterData">
            <select class="form-control" name="orderBy">
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
        </td>
    </tr>
</table>
<div class="tableFixHead" style="margin: 0 auto;height:60%; overflow-x:hidden">
    <table>
        <thead>
            <tr>
                <th>Journey Legs</th>
                <th>Price</th>
                <th>Departure Time</th>
                <th>Arrival Time</th>
                <th>Total Journey Duration</th>
                <th>Jet lag indicator</th>
            </tr>
        </thead>

        <c:forEach items="${journeyList}" var ="journey" >
            <c:set var="jetlag" value="${journey.calculateJetLag()}"/>
            <tr class="results" id="${journey.flightIDs}">
                <td style="border: 1px solid #b8daff;" >
                    <table>
                        <tr>

                            <c:forEach items="${journey.legsOfJourney}" var ="legs" varStatus="status">
                                <td flightId="${legs.flightID}" searchTime="${journey.searchTime}" ticketType="${legs.tickettype}" ticketClass="${legs.ticketclass}">${legs.flightID.departureCode.airport}</td>
                                <td>${legs.flightID.stopOverCode.airport}</td>
                            <c:if test="${status.last}">
                                <td>${legs.flightID.destinationCode.airport}</td>
                            </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                </td>
                <td style="border: 1px solid #b8daff;" >$${journey.price}</td>
                <td style="border: 1px solid #b8daff;" >${journey.departureTime}</td>
                <td style="border: 1px solid #b8daff;" >${journey.arrivalTime}</td>
                <td style="border: 1px solid #b8daff;" >${journey.totalDuration}hrs</td>
                <td class="jetlag jetlag${jetlag}">${jetlag}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
    $(document).ready(function(){
        $(document).on("click",".results",function(){
            window.location.href = "/Journey/viewJourney?flightIds="+ $(this).attr('id')+"&f"})
    })

    var slider = document.getElementById("myRange");
    var output = document.getElementById("demo");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        output.innerHTML = this.value;
    }

    var sliderr = document.getElementById("myRangetwo");
    var outputt = document.getElementById("demotwo");
    outputt.innerHTML = sliderr.value;

    sliderr.oninput = function() {
        outputt.innerHTML = this.value;
    }

    var sliderrr = document.getElementById("myRangethree");
    var outputtt = document.getElementById("demothree");
    outputtt.innerHTML = sliderrr.value;

    sliderrr.oninput = function() {
        outputtt.innerHTML = this.value;
    }

</script>
<style>
    .jetlag{
        color: white;
        border: 1px solid #b8daff;
    }

    .jetlag1{
        background-color: #b1dfbb;
    }

    .jetlag2{
        background-color: #ffdf7e;
    }

    .jetlag3{
        background-color: #e4606d;
    }
</style>

</body>
</html>
