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


<div class="page-main w-100 d-flex">
	<div class="form-container w-25">
		<h3>Airlines Form!</h3>
		<frm:form action= "${pageContext.request.contextPath }/api/airlines/createAirlines" method = "post" modelAttribute="airline" >
			<table class="w-100 p-4">
				<tbody class="w-75 p-4">
					<tr>
						<td >AirlinesId: </td>
						<td><frm:input class='form-control' value = "${a != null ? a.getAirlinesId(): airlines.size()>0 ? airlines.get(airlines.size()-1).getAirlinesId()+1: 1}" type = "text" path="airlinesId"/></td>
						<td><frm:errors path="airlinesId"/></td>
					</tr>
					<tr>
						<td >Airlines Name: </td>
						<td><frm:input class='form-control' value = "${a.getAirlinesName() }" type = "text" path="airlinesName"/></td>
						<td><frm:errors path="airlinesName"/></td>
					</tr>
					<tr>
						<td>Airlines Code: </td>
						<td><frm:input class='form-control' value = "${a.getAirlinesCode() }" type = "text" path="airlinesCode"/></td>
						<td><frm:errors path="airlinesCode"/></td>
					</tr>
					<%-- <tr>
						<td>Flights: </td>
						<td><frm:select class="w-100" path = "flight">
							<c:forEach items = "${ flights}" var="fl">
								<frm:option value="${fl.getFlightId()}" label="${fl.getFlightNumber() }"></frm:option>
							</c:forEach>
						</frm:select></td>
						<td><frm:errors path="flight"/></td>
					</tr> --%>	
				</tbody>
			</table>
				<br></br>
				<input class='btn btn-primary' type="submit"/>
		</frm:form>
		<br></br>
	</div>
	
	<div class ='table-container d-flex flex-column flex-grow-1 '>
	
	<h3>Airlines TABLE!</h3>
	<div class='table-table'>
	<c:if test="${airlines.size() > 0}">
		<table class= "table table-striped w-100 m-2" border="1" >
			<thead>
				<tr>
					<th>AirlinesId</th>
					<th>AirlinesName</th>
					<th>AirlinesCode</th>
					<th>Flights</th>
					<th colSpan="2">Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="${airlines }" var="ar">
					<tr>
						<td>${ar.getAirlinesId() }</td>
						<td>${ar.getAirlinesName() }</td>
						<td>${ar.getAirlinesCode() }</td>
						<td>
							<c:forEach items="${ar.getFlight() }" var="fl">
								<p>${fl.getFlightNumber()}</p>
							</c:forEach>
						</td>
					
						<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/airlines/updateAirlines/${ar.getAirlinesId() }">Update</a></td>
						<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/airlines/deleteAirlines/${ar.getAirlinesId() }">Delete</a></td>
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