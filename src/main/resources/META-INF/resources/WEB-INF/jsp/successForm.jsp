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
<title>Airlines Form</title>
</head>
<body>

<div class="main-header header w-100">
	 <jsp:include page = "./common/header/header.jsp"></jsp:include>
</div>


<div class="page-main w-100 d-flex flex-column align-items-center">
	<div>
	<h3>Your Payment is Successful! Booking Confirmed!</h3>
		<h3>There were ${resv_list.size()} passengers in the booking!</h3>
		
	</div>
	<div>
		<c:forEach items = "${resv_list }" var= "reservation">
			<table class='table'>
				<thead class='thead thead-dark'>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email </th>
						<th>Phone</th>
						<th>Flight</th>
						<th>Ticket No </th>
						<th>Checked Baggage </th>'
						<th>Confirmation Number </th>
						<!-- <th>Checked In </th> -->
					</tr>
				
				</thead>
				<tbody>
						<tr>
							<td>${reservation.getPassenger().getFirstname() }</td>
							<td>${reservation.getPassenger().getLastname() }</td>
							<td>${reservation.getPassenger().getEmail() }</td>
							<td>${reservation.getPassenger().getPhone() }</td>
							<td>${reservation.getPassenger().getFlight().getFlightNumber() }</td>
							<td>${reservation.getTicketNumber() }</td>
							<td>${reservation.getCheckedBags() }</td>
							<td>${reservation.getPaymentConfirmation().getConfirmationNumber() }</td>
							<%-- <td>${reservation.getCheckedIn() }</td> --%>
						</tr>
				</tbody>
			
			</table>
		</c:forEach>
	</div>
</div>

<div class="footer w-100">
	 <jsp:include page = "./common/footer/footer.jsp"></jsp:include>
</div>

</body>
</html>