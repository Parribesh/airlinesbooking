<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class= "sidebar-main d-flex w-100 align-items-center">
	<div class="d-flex w-25 ">
		<div class='w-100 d-flex justify-content-center align-items-center '>
			<img class="logo" src="${pageContext.request.contextPath }/images/flight.png" width="50px" height= "50px" alt="bank-logo" />
			<div class="mt-2 font-weight-light" style= "font-size: 25px" >Everest Fleet</div>
		</div>
	</div>
	
	<nav class="sidebar w-75 pt-2">
		<div class='d-flex align-items-center justify-content-end'>
			
			<a class= "link" href="${pageContext.request.contextPath}/api/flight/flightForm">Flights</a> 
			
			<a class= "link" href="${pageContext.request.contextPath}/api/user/userForm">User</a>  
		</div>
		<hr/>
		<div class='user-nav'>
		
			<sec:authorize access = "isAuthenticated()">
				<span>Username: <sec:authentication property="principal.username"/></span>
				<span>Roles: <sec:authentication property="principal.authorities"/></span>
				<sec:authorize access = "hasAuthority('USER')">
					<a class= "link" href="${pageContext.request.contextPath }/api/reservation/searchReservation">Reservation</a>
				</sec:authorize>
				<sec:authorize access = "hasAuthority('ADMIN')">
					<span class="text-danger h5 font-weight-light">Admin</span>
					<span><a class= "link" href="${pageContext.request.contextPath}/api/reservation/reservationForm">Reservations</a></span>
					<a class= "link" href="${pageContext.request.contextPath}/api/airlines/airlinesForm">Airlines</a> 
					<a class= "link" href="${pageContext.request.contextPath}/api/airport/airportForm">Airport</a>
					<span><a class= "link" href="${pageContext.request.contextPath}/api/role/roleForm">Role</a></span>
				</sec:authorize>
				<a class= "d-inline-block link bg-dark px-2 py-1 text-white" href="${pageContext.request.contextPath }/logout">Logout</a>
			</sec:authorize>
			
		</div> 
	</nav>
</div>

