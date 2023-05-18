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
<title>Payment Form</title>
</head>
<body>

<div class="main-header header w-100">
	 <jsp:include page = "./common/header/header.jsp"></jsp:include>
</div>


<div class="page-main w-100 d-flex flex-column align-items-center">
	<div>
	<h3>Please Provide Your Card Information!</h3>
	<c:if test='${pass_list.size() > 0 }'>
		<h3>There were ${pass_list.size()} passengers in the booking!</h3>
	</c:if>
	<c:if test='${message != null }'>
		<h3 class='error-message'>Message: ${message } Please Fix Issues </h3>
	</c:if>
		
		
	</div>
	<div class= 'w-50 mb-5'>
	<frm:form action = "${pageContext.request.contextPath }/api/payment/createPayment" method = "post" modelAttribute="paymentInfo">
		<table class='table font-weight-bold'>
			<thead class='thead thead-dark'>
				<tr>
					<th></th>
					<th class='text-right text-white'>Payment Info!</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>FirstName<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="firstname"/></div></td>
					<td>LastName<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="lastname"/></div></td>
				</tr>
				<tr>
					<td><frm:input class='form-control' type = 'text' path = 'firstname'/></td>
					<td><frm:input class='form-control' type = 'text' path = 'lastname'/></td>
				</tr>
				<tr>
					<td> AddressLine1<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="billingAddress.addressLine1"/></div></td>
					<td>AddressLine2 (optional)</td>	
				</tr>
				
				<tr>
					<td> <frm:input class='form-control' type='text' path='billingAddress.addressLine1'/></td>
					<td> <frm:input class='form-control' type='text' path='billingAddress.addressLine2'/></td>
				</tr>
				
				<tr>
					<td>City<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="billingAddress.city"/></div></td>
					<td>Country<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="billingAddress.country"/></div></td>
				</tr>
				
				<tr>
					<td> <frm:input class='form-control' type='text' path='billingAddress.city'/></td>
					<td> <frm:input class='form-control' type='text' path='billingAddress.country'/></td>
				</tr>
				
				<tr>
					<td>State<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="billingAddress.state"/></div></td>
					<td>ZipCode<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="billingAddress.zipCode"/></div></td>
				</tr>
				
				<tr>
					<td> <frm:input class='form-control' type='text' path='billingAddress.state'/></td>
					<td> <frm:input class='form-control' type='text' path='billingAddress.zipCode'/></td>
					
				</tr>
				
				<tr>
					<td> PaymentType<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="paymentType"/></div> </td>
					<td>
					 <frm:select class='form-control' path='paymentType'>
					 	<frm:options items='${CardType }' ></frm:options>
					 </frm:select>
					</td>
					
				</tr>
				
				<tr>
					<td> Card No<span class='error-message'>*</span><div class='text-danger'><frm:errors class='error-message' path="cardNumber"/></div> </td>
				</tr>
				
				<tr>
					<td> <frm:input class='form-control' type='text' path='cardNumber'/></td>
				</tr>
				
				
				<tr>
					<td></td>
					<td class='float-right'><input class='btn btn-success' type='submit'/></td>
				</tr>
			</tbody>
		</table>
		</frm:form>
	</div>
</div>

<div class="footer w-100">
	 <jsp:include page = "./common/footer/footer.jsp"></jsp:include>
</div>

</body>
</html>