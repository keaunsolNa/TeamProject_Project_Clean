
	$("#employeeOpnion").prop("readonly", true);
	$("#employeeOpnion2").prop("readonly", true);

	$('textarea').keyup(function(){
		var letter = $(this).val()
		$('#adminOpnion').html(letter)
	})