<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css">
<meta charset="UTF-8">
<title>Airport Form</title>
</head>
<body>

<div class="main-header header w-100">
 <jsp:include page = "./common/header/header.jsp"></jsp:include> 
</div>


<div class="page-main w-100 d-flex">
	
	<div class="form-container w-25 ml-4">
		<h3>Airport Form!</h3>
		<frm:form action= "${pageContext.request.contextPath }/api/airport/createAirport" method = "post" modelAttribute="airport" >
			<table class="w-100 p-4">
				<tbody class="w-75 p-4">
					<tr>
						<td >AirportId: </td>
						<td><frm:input class='form-control' value = "${ap != null ? ap.getAirportId(): airports.size()>0 ? airports.get(airports.size()-1).getAirportId()+1: 1}" type = "text" path="airportId"/></td>
						<td><frm:errors path="airportId"/></td>
					</tr>
					<tr>
						<td >Airport Name: </td>
						<td><frm:input class='form-control' value = "${ap.getAirportName() }" type = "text" path="airportName"/></td>
						<td><frm:errors path="airportName"/></td>
					</tr>
					<tr>
						<td>Airport Code: </td>
						<td><frm:input class='form-control' value = "${ap.getAirportCode() }" type = "text" path="airportCode"/></td>
						<td><frm:errors path="airportCode"/></td>
					</tr>	
					<tr>
						<td>Airport City: </td>
						<td><frm:input class='form-control' value = "${ap.getAirportCity() }" type = "text" path="airportCity"/></td>
						<td><frm:errors path="airportCity"/></td>
					</tr>
			<%-- 		<tr>
						<td>Departing Flight </td>
						<td><frm:select class='form-control' type = "text" path="departureFlights">
								<c:forEach items = "${flights }" var = "fl">
									<frm:option value = "${fl.getFlightId() }" label="${fl.getFlightNumber() }"/>
								</c:forEach>
							</frm:select>
						</td>
						<td><frm:errors path="departureFlights"/></td>
					</tr>
					
					<tr>
						<td>Arriving Flight: </td>
						<td><frm:select class='form-control' type = "text" path="arrivalFlights">
								<c:forEach items = "${flights }" var = "fl">
									<frm:option value = "${fl.getFlightId() }" label="${fl.getFlightNumber() }"/>
								</c:forEach>
							</frm:select>
						</td>
						<td><frm:errors path="arrivalFlights"/></td>
					</tr> --%>
				</tbody>
			</table>
				<br></br>
				<input class='btn btn-primary' type="submit"/>
		</frm:form>
		<br></br>
	</div>
	
	<div class =  'table-container d-flex flex-column flex-grow-1 '>
	
	<h3>Airport TABLE!</h3>
	<div class='table-table'>
	<c:if test="${airports.size() > 0}">
		<table class= "table table-striped w-100 m-2" border="1" >
			<thead>
				<tr>
					<th>AirportId</th>
					<th>Airport Name</th>
					<th>Airport Code</th>
					<th>Airport City</th>
					<th>Departing Flights</th>
					<th>Arriving Flights</th>
					<th colSpan="2">Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="${airports }" var="ar">
					<tr>
						<td>${ar.getAirportId() }</td>
						<td>${ar.getAirportName() }</td>
						<td>${ar.getAirportCode() }</td>
						<td>${ar.getAirportCity() }</td>
						<td><c:forEach items = "${ar.getDepartureFlights() }" var="df">${df.getFlightNumber()} to ${df.getArrivalAirport().getAirportCity()} at ${df.getDepartureTime()} <br></br></c:forEach></td>
						<td><c:forEach items = "${ar.getArrivalFlights() }" var="af">${af.getFlightNumber()} to ${af.getDepartureAirport().getAirportCity() } at ${af.getDepartureTime()}<br></br></c:forEach></td>
					
						<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/airport/updateAirport/${ar.getAirportId() }">Update</a></td>
						<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/airport/deleteAirport/${ar.getAirportId() }">Delete</a></td>
					</tr>
				</c:forEach>
		 </tbody>
		</table>
	</c:if>
	
	</div>
	</div>
	
</div>

<div class="footer w-100">
	 <jsp:include page = "./common/footer/footer.jsp"></jsp:include>
</div>

</body>
</html>