$( document ).ready(function(){
	
});


// catch click event
$("#lookfor").click(function(){		
	
	isValidUser( $("#uid").val() );
	
});

// catch enter event
$("#uid").keyup(function (e) {
    if (e.keyCode == 13) {

    	isValidUser( $("#uid").val() );
    }
});



function  showStatus(status){	
	
	
	
	//status = 3;
	//$("span#label").text("status > ");
	
	if(status == 0){
		
		$("span#uidstatus").removeAttr('class');
		$("span#uidstatus").text("Active");
		$("span#uidstatus").addClass("label label-success");
	
		
	}
	else if (status == 1){
		
		$("span#uidstatus").removeAttr('class');
		$("span#uidstatus").text("Not Exist");
		$("span#uidstatus").addClass("label label-danger");
		
	}
	else if (status == 2){
		
		$("span#uidstatus").removeAttr('class');
		$("span#uidstatus").text("Inactive");
		$("span#uidstatus").addClass("label label-danger");
		
	}
	else if (status == 3){
		
		$("span#uidstatus").removeAttr('class');
		$("span#uidstatus").text("Lock");
		$("span#uidstatus").addClass("label label-danger");
		
	}
	else{
		
		$("span#uidstatus").removeAttr('class');
		$("span#uidstatus").text("Unknown");
		$("span#uidstatus").addClass("label label-default");
		
	}
	
	
	
		
	
};


function isValidUser(data){

	$.ajax({	
				type:"POST",
				url: "isValidUser.jsp",
	       		data: "uid="+data,
	       		success: function(msg)    {
	       			showStatus(msg);
	       			/*
	       			alert(msg.trim());
	       			$("span#msg").empty();
	       			$("span#msg").append(msg);
	       			*/
	       		},
	       		error:   function(xml,msg){ 
	       			alert("Error:ajaxSend function problems"); 
	       		}
	 });
}

