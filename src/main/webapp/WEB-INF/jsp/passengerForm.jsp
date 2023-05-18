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
<title>Passenger Form</title>
</head>
<body>

<div class="main-header header w-100">
	 <jsp:include page = "./common/header/header.jsp"></jsp:include>
</div>



<div class="page-main w-100 d-flex justify-content-center align-item-center mt-5">
	
	<div class="m-4 flex-grow d-flex justify-content-center w-50">
		
		<div class='w-100 d-flex justify-content-center'>
		<frm:form class='w-100' action= "${pageContext.request.contextPath }/api/passenger/createPassenger" method = "post" modelAttribute="passenger" >
			<table class=" table p-4">
				<thead class='thead-dark text-white'>
					<tr>
						<th><span>Passenger: ${passengerNo}</span></th>
						<th class="text-right"><h3 class='m-0'>Passenger Form </h3></th>
					</tr>
				</thead>
				<tbody class="p-4">
					<tr>
						<td class='font-weight-bold' >PassengerId: </td>
						<td><frm:input class='form-control' readonly='true' value = "${p != null ? p.getPassengerId(): passengers.size()>0 ? passengers.get(passengers.size()-1).getPassengerId()+1: 1}" type = "text" path="passengerId"/></td>
						<td><frm:errors class='error-message' path="passengerId"/></td>
					</tr>
					<tr>
						<td class='font-weight-bold' ><div>First Name:</div> <div class='text-danger'><frm:errors class='error-message' path="firstname"/></div> </td>
						
						<td class='font-weight-bold'><div>Last Name:</div> <div class='text-danger'><frm:errors class='error-message' path="lastname"/></div> </td>
						
					</tr>
					<tr>
						<td class=''><frm:input class='form-control ' value = "${p.getFirstname() }" type = "text" path="firstname"/></td>
						
						<td class='px-3'><frm:input class='form-control ' value = "${p.getLastname() }" type = "text" path="lastname"/></td>
						
					</tr>
					
					<tr class= ''>
						<td class='font-weight-bold'><div>Email:</div> <div class='text-danger'><frm:errors class='error-message' path="email"/></div> </td>
						<td class='font-weight-bold'><div>Gender:</div> <div class='text-danger'><frm:errors class='error-message' path="gender"/></div> </td>
	
					</tr>	
					
					<tr>
						<td class='px-3'><frm:input class='form-control' value = "${p.getEmail() }" type = "text" path="email"/></td>
						
						<td class='px-3'><frm:select class = "form-control"  value = "${p.getGender() }" path="gender">
							<frm:options items="${GenderType}"></frm:options>
						</frm:select>
						</td>
						
					</tr>	
					
					<tr>
						<td class='font-weight-bold'><div>Phone:</div><div class='text-danger'><frm:errors class='error-message' path="phone"/></div> </td>
						<td><frm:input class='form-control ' value = "${p.getPhone() }" type = "text" path="phone"/></td>
						
					</tr>
					
					<tr>
						<td class='font-weight-bold'><div>DoB: </div><div class='text-danger'><frm:errors class='error-message' path="dob"/></div></td>
						<td><frm:input class='form-control' value = "${p.getDob() }" type = "date" path="dob"/></td>
						
					</tr>	
					
					<tr>
						<td class='font-weight-bold'><div>Identification:</div><div class='text-danger'><frm:errors class='error-message' path="identificationType"/></div> </td>
						<td>
							<frm:select class = "form-control"  value = "${p.getIdentificationType() }" path="identificationType">
								<frm:options items="${IdentificationType}"></frm:options>
							</frm:select>
						</td>
						
					</tr>

					<tr>
						<td class='font-weight-bold'><div>Flight Number:</div><div class='text-danger'> <frm:errors class='error-message' path="flight"/></div></td>
						<td>
							<frm:input readonly ='true' class = "form-control"  value = "${flight.getFlightId()}" path="flight"/>
						</td>
						
					</tr>		
					<tr>
						<td></td>
						<td class='text-right'><input class='btn btn-primary px-5 ' type="submit" value='Next'/> </td>
					</tr>
						
				</tbody>
			</table>
				<br></br>
				
		</frm:form>
		</div>
		<br></br>
	</div>
	
	<sec:authorize access="hasRole('Admin')">
	
	<div class =  'table-container d-flex flex-column '>
	
	<h3>Passengers TABLE!</h3>
	<div class='table-table'>
	<c:if test="${passengers.size() > 0}">
		<table class= "table table-striped w-100 m-2" border="1" >
			<thead>
				<tr>
					<th>PassengerId</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Gender</th>
					<th>DoB</th>
					<th>Identification</th>
					<th colSpan="2">Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="${passengers }" var="pr">
					<tr>
						<td>${pr.getPassengerId() }</td>
						<td>${pr.getFirstname() }</td>
						<td>${pr.getLastname() }</td>
						<td>${pr.getEmail() }</td>
						<td>${pr.getPhone() }</td>
						<td>${pr.getGender() }</td>
						<td>${pr.getDob() }</td>
						<td>${pr.getIdentificationType() }</td>
						
					
						<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/passenger/updatePassenger/${pr.getPassengerId() }">Update</a></td>
						<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/passenger/deletePassenger/${pr.getPassengerId() }">Delete</a></td>
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