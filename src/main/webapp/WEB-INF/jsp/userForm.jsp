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
<title>User Form</title>
</head>
<body>

<div class="main-header header w-100">
	 <jsp:include page = "./common/header/header.jsp"></jsp:include>
</div>


<div class="page-main w-100 d-flex justify-content-center">
	
	<div class="form-container w-25 ml-4">
		<h3>Registration Form!</h3>
		<frm:form action= "${pageContext.request.contextPath }/api/user/createUser" method = "post" modelAttribute="user" >
			<table class="w-100 p-4">
				<tbody class="w-75 p-4">
					<tr>
						<td >UserId: </td>
						<td><frm:input class='form-control' value = "${u != null ? u.getUserId(): users.size()>0 ? users.get(users.size()-1).getUserId()+1: 1}" type = "text" path="userId"/></td>
						<td><frm:errors path="userId"/></td>
					</tr>
					<tr>
						<td >Username: </td>
						<td><frm:input class='form-control' value = "${u.getUsername() }" type = "text" path="username"/></td>
						<td><frm:errors path="username"/></td>
					</tr>
					<tr>
						<td>Password: </td>
						<td><frm:input class='form-control' type = "password" path="userPassword"/></td>
						<td><frm:errors path="userPassword"/></td>
					</tr>
					
					<tr>
						<td>Email </td>
						<td><frm:input class='form-control' value = "${u.getUserEmail() }" type = "email" path="userEmail"/></td>
						<td><frm:errors path="userEmail"/></td>
					</tr>
					
					<tr>
						<td>Mobile </td>
						<td><frm:input class='form-control' value = "${u.getUserMobile() }" type = "text" path="userMobile"/></td>
						<td><frm:errors path="userMobile"/></td>
					</tr>		
					
					<tr>
						<td>Roles </td>
						<td><frm:select class = "form-control" value = "${u.getUserRoles() }" path="userRoles">
						<sec:authorize access='hasAuthority("ADMIN")'>
							<c:forEach items ="${roles }" var="rl">
								<frm:option value = "${rl.getRoleId() }" label="${rl.getRoleName() }"/>	
							</c:forEach>
						</sec:authorize>
						<sec:authorize access='hasAuthority("USER")'>
								<frm:option value ="1" label="USER"></frm:option>
						</sec:authorize>
						</frm:select></td>
						<td><frm:errors path="userRoles"/></td>
					</tr>	
					
						
				</tbody>
			</table>
				<br></br>
				<input class='btn btn-primary' type="submit"/>
		</frm:form>
		<br></br>
	</div>
<sec:authorize access='hasAuthority("ADMIN")'>	
	<div class =  'table-container d-flex flex-column flex-grow-1 '>
	
	<h3>User TABLE!</h3>
	<div class='table-table'>
	<c:if test="${users.size() > 0}">
		<table class= "table table-striped w-100 m-2" border="1" >
			<thead>
				<tr>
					<th>UserId</th>
					<th>Username</th>
					<th>Password</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Roles</th>
					
					<th colSpan="2">Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="${users }" var="us">
					<tr>
						<td>${us.getUserId() }</td>
						<td>${us.getUsername() }</td>
						<td>${us.getUserPassword() }</td>
						<td>${us.getUserEmail() }</td>
						<td>${us.getUserMobile() }</td>
						<c:forEach items="${us.getUserRoles()}" var="role">
							<td>${role.getRoleName()}</td>
						</c:forEach>
						
						<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/user/updateUser/${us.getUserId() }">Update</a></td>
						<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/user/deleteUser/${us.getUserId() }">Delete</a></td>
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