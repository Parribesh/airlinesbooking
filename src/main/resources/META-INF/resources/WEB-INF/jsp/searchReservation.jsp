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

<div class="search-barr w-100">
	 <jsp:include page = "./common/searchBar/reservationSearch.jsp"></jsp:include>
</div>

<div class="page-main w-100 d-flex justify-content-center">
	<c:if test = "${confirmedReservations != null }">
		<div> 
			<h2>You have ${confirmedReservations.size() } reservations</h2>
		 	<h2>CheckedIn:  ${confirmedReservations.get(0).getCheckedIn() }</h2>
		</div>
	</c:if>
	
</div>


<div class="footer w-100">
	 <jsp:include page = "./common/footer/footer.jsp"></jsp:include>
</div>

</body>
</html>