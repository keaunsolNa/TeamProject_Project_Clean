$("input[type='checkbox']").each(function(){
	if($(this).is(":checked") == true){
		$(this).val("YES");
	} else if($(this).is(":checked") == false){
		$(this).val("NOT")
	}
});

$("input[type='checkbox']").change(function(){
	if($(this).is(":checked") == true){
		$(this).val("YES");
	} else{
		$(this).val("NOT");
	}
});
