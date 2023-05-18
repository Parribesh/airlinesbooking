<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<div class= "searchbar-main d-flex">

	<div  class="search-text-container d-flex justify-content-center align-items-center  w-50 ">
		<span class="searchbar-header">Pick a date to fly With us!</span>
	</div>
	
	<div class="search-form-container w-50 d-flex justify-content-start align-items-center">
		<frm:form class="search-form d-flex align-items-center" action="${pageContext.request.contextPath }/api/flight/searchFlight" method="post" modelAttribute="searchModel">
			<div  style="width: 150px" class="form-group mr-3">
				<div class= 'd-flex'>
					<img class="mr-1" height = "25" width="25" src="${pageContext.request.contextPath }/images/airplane.png"></img>
					<label>Departing City: </label>
				</div>
				
				<frm:select class="form-control" type="text" path="from">
					<frm:options items = "${cities }"/>
					<frm:errors path="from"/>
				</frm:select>
			</div>
			<div  style="width: 150px" class="form-group mr-3 ">
				<div class= 'd-flex'>
					<img class="mr-1" height = "25" width="25" src="${pageContext.request.contextPath }/images/airplane.png"></img>
					<label>Arriving City: </label>
				</div>
				
				<frm:select class="form-control" type="text" path="to">
					<frm:options items = "${cities }"/>
					<frm:errors path="to"/>
				</frm:select>
			</div>
			<div style="width: 150px" class="form-group mr-3 ">
				<div class= 'd-flex'>
					<img class="mr-1" height = "25" width="25" src="${pageContext.request.contextPath }/images/calendar.png"></img>
					<label>Date: </label>
				</div>
				
				<frm:input class="form-control" type="date" min="${today }" path= "date"/>
			</div>
		
			<div style="width: 150px" class="form-group mr-3">
				<div class= 'd-flex'>
					<img class="mr-1 rounded-circle" height = "25" width="25" src="${pageContext.request.contextPath }/images/passengers.png"></img>
					<label>Passengers: </label>
				</div>
				<frm:input class="form-control text-center" type="number" value='1' path= "passengers"/>
			</div>
			
			
			<div class="d-flex align-items-center mt-3">
				<button class="btn btn-primary px-4"  type="submit">Submit</button>
			</div>
		</frm:form>
	</div>
</div>

