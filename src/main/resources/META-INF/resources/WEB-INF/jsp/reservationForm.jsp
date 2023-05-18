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
<title>Reservation Form</title>
</head>
<body>

<div class="main-header header w-100">
	 <jsp:include page = "./common/header/header.jsp"></jsp:include>
</div>

<c:if test = "${passenger != null }">
<div class="page-main w-100 d-flex justify-content-center mt-5">
	<sec:authorize access="hasAuthority('USER')">
	<div class="form-container w-50">
		
		<frm:form action= "${pageContext.request.contextPath }/api/reservation/createReservation" method = "post" modelAttribute="reservation" >
			<table class="table w-100">
				<thead class='thead-dark'>
					<tr>
						<th></th>
						<th class="text-right"><h3>Reservation Form!</h3></th>
					</tr>
				</thead>
				<tbody class="reservation-body">
					<tr>
						<td >Ticket Number: </td>
						<td><frm:input class='form-control' value = "${totalReservations + 1}" type = "text" path="ticketNumber"/>
							<div><frm:errors path="ticketNumber"/></div>
						</td>
						
					</tr>
					<tr>
						<td >Passenger: </td>
						<td><frm:input readonly='true' class='form-control' value =  "${passenger.getPassengerId() }"  placeholder = "${passenger.getFirstname() }" type = "text" path="passenger"/>
							<div><frm:errors path="passenger"/></div>
						</td>
						
					</tr>
					
					<tr>
						<td>Flight:</td>
						<td><frm:input readonly='true' class='form-control' value="${flight.getFlightId() }" placeholder="${flight.getFlightNumber() }" type="text" path="flight"/>
						<div><frm:errors path="flight"/></div>
						</td>
						
					</tr>
					
					<tr>
						<td>CheckedBags:</td>
						<td><frm:input class= 'form-control' type='number' value='${r.getCheckedBags()}' path='checkedBags'/>
							<div><frm:errors path='checkedBags'/></div>
						</td>
						
					</tr>
					<tr >
					<td>CheckedIn</td>
						<td>
							<frm:radiobutton class = 'w-25' value="false" path='checkedIn' label='NO'/>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td class='text-right'><input class='btn btn-primary' type="submit"/></td>
					</tr>
						
				</tbody>
			</table>
				<br></br>
				
		</frm:form>
		<br></br>
	</div>
	</sec:authorize>
	</div>
	
	</c:if>
		<sec:authorize access="hasAnyAuthority('ADMIN', 'USER')">
		
	<div class =  'table-container d-flex flex-column flex-grow-1 '>
	
	<h3>Reservations TABLE!</h3>
	<div class='table-table'>
	<c:if test="${reservations.size() > 0}">
		<table class= "table table-striped w-100 m-2" border="1" >
			<thead>
				<tr>
					<th>Ticket Number</th>
					<th>Passenger</th>
					<th>Flight</th>
					<th>Checked Bags</th>
					<th>Checked In</th>
					<th colSpan="2">Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="${reservations }" var="rv">
					<tr>
						<td>${rv.getTicketNumber() }</td>
						<td>${rv.getPassenger().getFirstname() }</td>
						<td>${rv.getFlight().getFlightNumber() }</td>
						<td>${rv.getCheckedBags() }</td>
						<%-- <td>${rv.getCheckedIn() }</td> --%>

						<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/reservation/updateReservation/${pr.getReservationId() }">Update</a></td>
						<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/reservation/deleteReservation/${pr.getReservationId() }">Delete</a></td>
					</tr>
				</c:forEach>
		 </tbody>
		</table>
	</c:if>
	
	</div>

	</div>
	</sec:authorize>
</div>

<div class="footer w-100">
	 <jsp:include page = "./common/footer/footer.jsp"></jsp:include>
</div>

</body>
</html>