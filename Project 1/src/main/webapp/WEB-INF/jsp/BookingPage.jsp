<%@ page import="me.groupFour.data.planeSeatingArrangementsEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SixLiner
  Date: 3/06/2020
  Time: 11:39 pm
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Flight</title>
    <jsp:include page="./layout/header.jsp" />
</head>
<body>
    <div class="container">
        <h3>Flight Details</h3>
        <br>


        <h3>Booking information</h3>

        <table class="table">
            <tr>
                <td>Plane type:</td>
                <td id="planeCode">${currentFlight.planeCode.planeCode}</td>
            </tr>
            <tr>
                <td>Seat location</td>

                <td id="seatLocation"></td>
            </tr>
            <tr>
                <td>Route</td>
                <td>
                    <table class="table">
                        <tr>
                            <th>Departure</th>
                            <th>Arrival</th>
                        </tr>
                        <tr>
                            <td>
                                ${currentFlight.departureCode.airport} @ ${currentFlight.departureTime}
                            </td>
                            <td>
                                ${currentFlight.destinationCode.airport} @ ${currentFlight.arrivalTime}
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>Pricing:</td>
                <td>
                    <table>
                        <tr>
                            <th>Adult</th>
                            <th>Child</th>
                            <th>Class Type</th>
                        </tr>
                        <tr>
                            <td id="adultPrice">$200</td>
                            <td id="childPrice">$150</td>
                            <td>Economy</td>
                        </tr>
                        <tr>
                            <td><button class="btn btn-info" onclick="javascript:window.location.href='/Journey/addHotel'">Add Hotel</button> </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <form action="/Journey/bookinghandling" id="formId" method="GET">
                <input type="hidden" id="seatSelection" name="seatId"/>
                <input type="hidden" id="hiddenValue" name="hasReserved"/>
            <tr>
                <td>Seat type:</td>
                <td id="test">
                    <select id="seatSelection" class="form-control">
                        <option value="adult">Adult</option>
                        <option value="child">Child</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>In total:</td>
                <td id="total"></td>
                <td>
                    <input type="submit" class="btn btn-info" value="Book">
                </td>
            </tr>
            </form>
        </table>
        <br>
        <h3>Seats selected</h3>
        <table class="table">
            <tr id="seatTableHeader">
                <th>Type</th>
                <th>Location</th>
                <th>Option</th>
            </tr>
        </table>
        <br>
        <br>

        <h3>Please select a seat for this flight in your journey:</h3>

        <table class="table table-bordered" style="width: 100%;height:60%; padding-left: 4em">
            <%
                //arrangements comes from database Must be integer array
                planeSeatingArrangementsEntity temp = (planeSeatingArrangementsEntity)request.getAttribute("PlaneConfig");
                Integer[] arrangements=(Integer[])request.getAttribute("Arrangement");

                String[] alphabet = {"A","B","C","D","E","F","G","H","I","J"};
            %>

            <tr>
                <th></th>
                <% for(int a=0;a<arrangements.length;a++){
                    for(int group=0;group<arrangements[a];group++){
                        int seatNumber = arrangements.length * a + group;
                %>
                <th><%= alphabet[seatNumber] %></th>
                <% } %>
                <td></td>
                <% } %>
            </tr>
            <%
                int rows = (Integer)request.getAttribute("Rows");//Set it to how many rows required
                int offset = (Integer)request.getAttribute("offset");
                for(int row=0;row<rows;row++){
            %>
            <tr>
                <td><%= offset+row %></td>
                <%
                    for(int a=0;a<arrangements.length;a++){
                        for(int group=0;group<arrangements[a];group++){
                            int seatNumber = arrangements.length * a + group;
                            String seatCode = String.valueOf(offset+row)+alphabet[seatNumber];
                %>
                <c:choose>
                <c:when test="${true}">
                <td class="avaliable" id="<%= seatCode %>">
                    </c:when>
                    <c:otherwise>
                <td class="blocked" id="<%= seatCode %>">
                    </c:otherwise>
                    </c:choose>
                </td>
                <% } %>
                <td></td>
                <% } %>
            </tr>
            <% } %>
        </table>

    </div>
    <p style="padding-bottom:2em"></p>
</body>

<style>
    .avaliable{
        background-color: green;
    }

    .avaliable:hover{
        background-color: yellow;
    }

    .excluded{
        background-color: white;
    }

    .blocked{
        background-color: red;
    }

    .selected{
        background-color: orange;
    }
</style>

<script>

    //Get flightid, ticket type, search
    $(document).ready(function(){
        $(document).on('change', '.reserveOption', function() {
            $("#hiddenValue").val("t");
        });

        //To exclude seats insert into array below
        let excludedSeats = <%=request.getAttribute("Excluded")%>;

        $.each(excludedSeats , function(index, val) {
            var upperCaseSeatCode = val.toUpperCase();

                $("#"+upperCaseSeatCode).removeClass();
                $("#"+upperCaseSeatCode).addClass("excluded");

        });

        //Triggers when clicking on all avaliable seats
        $(document).on("click",".avaliable",function(){

            //Sets seatId in the form when user clicks avaliable seat
            $("#seatSelection").val($(this).attr("id"));

            $(this).removeClass("avaliable");
            $(this).addClass("selected");

            $("#seatLocation").text($(this).attr("id"));

            let selectionHtml = $("#test").html();


            let tableRowString = "<tr>";
                tableRowString += "<td>"+ $(this).attr("id") +"</td>";
                tableRowString += "<td><select id='seatSelection' class='form-control'>";
                tableRowString += "<option value='adult'>Adult</option><option value='child'>Child</option></select>";
                tableRowString += "<td><select id='seatSelection' class='form-control reserveOption'>";
                tableRowString += "<option value='book'>I'll pay this seat</option>";
                tableRowString += "<option value='reserve'>I'll would like to reserved this seat</option>";
                tableRowString += "</select></td>";
                tableRowString += "<td><button class='btn btn-danger remove' seatId='"+ $(this).attr("id") +"'>Remove</button></td>";
                tableRowString += "</tr>";

            $("#seatTableHeader").after(tableRowString);
        })

        $(document).on("click",".remove",function(){
            let seatID = $(this).attr("seatId");

            //Updates seats table
            $("#"+seatID).removeClass("selected");
            $("#"+seatID).addClass("avaliable");

            //Remove out of selected seats
            $(this).parent().parent().empty().remove();

        })
    })
</script>

<jsp:include page="./layout/footer.jsp" />

</html>
