<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<div class= "searchbar-main d-flex">

	<div  class="search-text-container d-flex justify-content-center align-items-center  w-50 ">
		<span class="searchbar-header">Search Your Reservation By: </span>
	</div>
	
	<div class="w-50 d-flex justify-content-center align-items-center">
		
		<frm:form class="d-flex flex-wrap align-items-center h-50  w-100" action="${pageContext.request.contextPath }/api/reservation/searchReservation" method="post" modelAttribute="resSearchModel">
			
			<div class="form-group m-auto w-25">
				
				<label class='font-weight-bold'> Ticket No: </label>
				<frm:input class="form-control" type="text" path="ticketNo"/>
				<frm:errors path="ticketNo"></frm:errors>
			</div>
			
			<div class='my-auto font-weight-bold'>OR</div>
			
			<div class="form-group m-auto w-25">
				
				<label class='font-weight-bold'> Confirmation No:  </label>
				
				<frm:input class="form-control" type="text" path="confirmationNo"/>
				<frm:errors path="confirmationNo"></frm:errors>
			</div>
				
			<div class="d-flex h-50 align-items-end w-25">
				
				<button class="btn btn-primary px-4"  type="submit">Submit</button>
			
			</div>
			
		</frm:form>
	</div>
</div>

