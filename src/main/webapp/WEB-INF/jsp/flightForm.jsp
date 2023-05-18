<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css">
<meta charset="UTF-8">
<title>Flight Form</title>
</head>
<body>

<div class="main-header header w-100">
	 <jsp:include page = "./common/header/header.jsp"></jsp:include>
</div>

<div class="search-barr w-100">
	 <jsp:include page = "./common/searchBar/searchBar.jsp"></jsp:include>
</div>


<div class="page-main w-100 d-flex ">

	<div class="form-container d-flex  float-left">
	
		<div class='filter-main flex-grow-1 d-flex flex-column align-items-center'>
			<sec:authorize access="hasAuthority('USER')">
				<div class=' w-100 d-flex justify-content-start'>
					<h5 >Filter your Results</h5>
				</div>
				
				<div class='price-filter d-flex  justify-content-center mt-1 p-3'>
					<table class=''>
						<tbody>
							<tr class= ''>
								<td class='text-right'><h6>Min Price.</h6></td>
								<td class ='text-right'>
									<h6>Max Price.</h6>
								</td>
							</tr>
							
							<tr class='text-right'>
								<td class=''><input class = 'form-control mx-2 float-right' id='from' type= 'number'/></td>
								<td class=''><input class='form-control float-right' id='from' type='number'/></td>
							<tr>
								
							<tr>
								<td></td>
								<td class='text-right'>
										<button class='btn btn-info mt-3'>Apply</button>
								</td>
								<tr>
							</tr>
						
						</tbody>
					</table>
				</div>
			</sec:authorize>
		</div>
	<div>
		<sec:authorize access='hasAuthority("ADMIN")'>
			<h3>Flight Form: </h3>
			<div class='search-result'>
				<frm:form action= "${pageContext.request.contextPath }/api/flight/createFlight" method = "post" modelAttribute="flight" >
					<table class="w-100 p-4">
						<tbody class="w-75 p-4">
							<tr>
								<td >FlightId: </td>
								<td><frm:input class='form-control' value = "${f != null ? f.getFlightId(): flights.size()>0 ? flights.get(flights.size()-1).getFlightId()+1: 1}" type = "text" path="flightId"/></td>
								<td><frm:errors path="flightId"/></td>
							</tr>
							<tr>
								<td >Flight Number: </td>
								<td><frm:input class='form-control' value = "${f.getFlightNumber() }" type = "text" path="flightNumber"/></td>
								<td><frm:errors class='error-message' path="flightNumber"/></td>
							</tr>
							<tr>
								<td>Departing Airport: </td>
								<td><frm:select class='form-control' type = "text" path="departureAirport">
										<c:forEach items = "${airports }" var = "ar">
											<frm:option value = "${ar.getAirportId() }" label="${ar.getAirportCode() }"/>
										</c:forEach>
									</frm:select>
								</td>
								<td><frm:errors class='error-message' path="departureAirport"/></td>
							</tr>
							
							<tr>
								<td>Arriving Airport: </td>
								<td><frm:select class='form-control' type = "text" path="arrivalAirport">
										<c:forEach items = "${airports }" var = "ar">
											<frm:option value = "${ar.getAirportId() }" label="${ar.getAirportCode() }"/>
										</c:forEach>
									</frm:select>
								</td>
								<td><frm:errors class='error-message' path="arrivalAirport"/></td>
							</tr>	
							
							<tr>
								<td>Operating Airlines: </td>
								<td><frm:select class = "form-control" value = "${f.getOperatingAirlines() }" path="operatingAirlines">
									<c:forEach items="${operatingAirlines }" var="ar">
											<frm:option value = "${ ar.getAirlinesId()}" label ="${ar.getAirlinesName() }"/>
									</c:forEach>
									
								</frm:select></td>
								<td><frm:errors class='error-message' path="operatingAirlines"/></td>
							</tr>	
							
							<tr>
								<td>Flight Departure Date: </td>
								<td><frm:input class='form-control' value = "${f.getDepartureDate() }" type = "date" path="departureDate"/></td>
								<td><frm:errors class='error-message' path="departureDate"/></td>
							</tr>
							
							<tr>
								<td>Flight Departure Time: </td>
								<td><frm:input class='form-control' value = "${f.getDepartureTime() }" type = "time" path="departureTime"/></td>
								<td><frm:errors class='error-message' path="departureTime"/></td>
							</tr>	
							
							<tr>
								<td>Flight Capacity: </td>
								<td><frm:input class='form-control' value = "${f.getCapacity() }" type = "number" path="capacity"/></td>
								<td><frm:errors class='error-message' path="capacity"/></td>
							</tr>	
							
							<tr>
								<td>Booked: </td>
								<td><frm:input class='form-control' value = "${f.getBooked() }" type = "number" path="booked"/></td>
								<td><frm:errors class='error-message' path="booked"/></td>
							</tr>	
							
							<tr>
								<td>Price: </td>
								<td><frm:input class='form-control' value = "${f.getTicketPrice() }" type = "number" path="ticketPrice"/></td>
								<td><frm:errors class='error-message' path="ticketPrice"/></td>
							</tr>
							
							<tr>
								<td></td>
								<td class="text-right"><input class='btn btn-danger' type="submit"/></td>
							</tr>
								
						</tbody>
					</table>
					
					</frm:form>
					</div>
				<br></br>
			</sec:authorize>
		</div>
	</div>
	
	<div class = 'table-container'>
		<div class='d-flex align-items-start text-muted p-0'>
			<span class='m-0'>${message}</span>:
			<span class='m-0'>SearchResults</span>
			<span>&nbsp;</span>
			<span class='m-0'>${flights.size()}</span>	
		</div>
		<div class=''>
			<c:if test="${flights.size() > 0}">
				<table class= "table table-striped" border="1" >
					<thead>
						<tr>
							<th>FlightId</th>
							<th>Flight Number</th>
							<th>Departure Airport</th>
							<th>Arrival Airport</th>
							<th>Operating Airlines</th>
							<th>Departure Date</th>
							<th>Departure Time</th>
							<th>Capacity</th>
							<th>Booked</th>
							<th>Ticket Price</th>
							<th colSpan="2">Action</th>
						</tr>
					</thead>
					
					<tbody>
					
						<c:forEach items="${flights }" var="fl">
							<tr>
								<td>${fl.getFlightId() }</td>
								<td>${fl.getFlightNumber() }</td>
								<td>${fl.getDepartureAirport().getAirportName() }</td>
								<td>${fl.getArrivalAirport().getAirportName() }</td>
								<td>${fl.getOperatingAirlines().getAirlinesName() }</td>
								<td>${fl.getDepartureDate() }</td>
								<td>${fl.getDepartureTime() }</td>
								<td>${fl.getCapacity() }</td>
								<td>${fl.getBooked() }</td>
								<td>${fl.getTicketPrice() }</td>
								
								<sec:authorize access ="hasAuthority('ADMIN')">
								<td><a  class="btn btn-info" href="${pageContext.request.contextPath }/api/flight/updateFlight/${fl.getFlightId() }">Edit</a></td>
								</sec:authorize>
								<td><a  class="btn btn-info" href="${pageContext.request.contextPath }/api/flight/flightDetials/${fl.getFlightId() }">Details</a></td>
								
								<sec:authorize access ="hasAuthority('USER')">
								<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/passenger/reserveFlight/${fl.getFlightId() }">Book</a></td>
								</sec:authorize>
								
								<sec:authorize access ="hasAuthority('ADMIN')">
								<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/flight/deleteFlight/${fl.getFlightId() }">Delete</a></td>
								</sec:authorize>
	
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