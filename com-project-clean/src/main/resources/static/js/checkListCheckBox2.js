const checkboxs = document.querySelectorAll('form .checklist .checkbox');
	for(let i =0; i < checkboxs.length; i++){
		
		const checkbox = checkboxs[i]
		
			if(checkbox.value == "NOT"){
				
				$(checkbox).attr("disabled", true)
				
			} else if(checkbox.value == "YES") {
				
				$(checkbox).attr("checked",true)
				$(checkbox).attr("disabled", true)
				
			};
};