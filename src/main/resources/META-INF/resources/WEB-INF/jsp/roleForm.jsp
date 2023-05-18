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
<title>Role Form</title>
</head>
<body>

<div class="main-header header w-100">
	 <jsp:include page = "./common/header/header.jsp"></jsp:include>
</div>


<div class="page-main w-100 d-flex">
	
	<div class="form-container w-25 ml-4">
		<h3>Role Form!</h3>
		<frm:form action= "${pageContext.request.contextPath }/api/role/createRole" method = "post" modelAttribute="role" >
			<table class="w-100 p-4">
				<tbody class="w-75 p-4">
					<tr>
						<td >RoleId: </td>
						<td><frm:input class='form-control' value = "${r != null ? u.getRoleId(): roles.size()>0 ? roles.get(roles.size()-1).getRoleId()+1: 1}" type = "text" path="roleId"/></td>
						<td><frm:errors path="roleId"/></td>
					</tr>
					<tr>
						<td >Role Name: </td>
						<td><frm:checkboxes class="m-2" items="${roleTypes }"  path="roleName"/>
						</td>
						<td><frm:errors path="roleName"/></td>
					</tr>
									
				</tbody>
			</table>
				<br></br>
				<input class='btn btn-primary' type="submit"/>
		</frm:form>
		<br></br>
	</div>
	
	<div class =  'table-container d-flex flex-column flex-grow-1 '>
	
	<h3>Role TABLE!</h3>
	<div class='table-table'>
	<c:if test="${roles.size() > 0}">
		<table class= "table table-striped w-100 m-2" border="1" >
			<thead>
				<tr>
					<th>RoleId</th>
					<th>Role Name</th>

					<th colSpan="2">Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach items="${roles }" var="rl">
					<tr>
						<td>${rl.getRoleId() }</td>
						<td>${rl.getRoleName() }</td>
						
						<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/role/updateRole/${rl.getRoleId() }">Update</a></td>
						<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/role/deleteRole/${rl.getRoleId() }">Delete</a></td>
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