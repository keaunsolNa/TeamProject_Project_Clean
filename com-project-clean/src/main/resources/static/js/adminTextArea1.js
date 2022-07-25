if(document.getElementById("employeeOpnion2")){
	} else{
		const div = document.getElementById("submitarea");
		div.remove();
	}
	
	$("#adminOpnion").prop("readonly", true);
	$("#adminOpnion2").prop("readonly", true);
	$("#employeeOpnion2").prop("readonly", true);
	$('textarea').keyup(function(){
		var letter = $(this).val()
		$('#adminOpnion').html(letter)
	})