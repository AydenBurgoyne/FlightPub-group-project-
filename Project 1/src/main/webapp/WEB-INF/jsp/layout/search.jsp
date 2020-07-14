    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%--
          Created by IntelliJ IDEA.
          User: SixLiner
          Date: 31/05/2020
          Time: 9:46 pm
          To change this template use File | Settings | File Templates.
        --%>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src ="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript">
        $(function(){
            $("#toDest").autocomplete({
                source: "/search/AirportAutoComplete"
            });
        });
        $(function(){
            $("#fromDest").autocomplete({
                source: "/search/AirportAutoComplete"
            });
        });
        $(function(){
        $("#viaDest").autocomplete({
        source: "/search/AirportAutoComplete"
        });
        });
        </script>
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
                        <form:input class="form-control" path="viaDest" /> <br/>
                        <form:errors path="viaDest" cssClass="error"/>
                    </td>
                    <td>
                    Ticket Type:
                    <form:select class="form-control" path="ticketCode" >
                        <form:option value="A" label="Standby"/>
                        <form:option value="B" label="Premium Discounted"/>
                        <form:option value="C" label="Discounted"/>
                        <form:option value="D" label="Standard"/>
                        <form:option value="E" label="Premium"/>
                        <form:option value="F" label="ld"/>
                        <form:option value="G" label="Platinum"/>
                    </form:select><br/>
                    <form:errors path="ticketCode" cssClass="error"/>

                    </td>

                    <td  rowspan="2" ><input style="margin-top:70px;" class="form-control btn btn-info" type="submit" value="Search"></td>
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
                        <form:input class="form-control" type="number" min="0" max="1000" path="maxLayoverTime"/> <br/>
                        <form:errors path="toTime" cssClass="error"/>
                    </td>
                    <td>
                    Flight Class:
                    <form:select class="form-control" path="classCode" >
                        <form:option value="BUS" label="Business"/>
                        <form:option value="ECO" label="Economy"/>
                        <form:option value="FIR" label="First Class"/>
                        <form:option value="PME" label="Premium Economy"/>
                    </form:select> </br>
                    <form:errors path="classCode" cssClass="error"/>
                    </td>
                </tr>

        </form:form>

        </table>

