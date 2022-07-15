window.onload = function(){
	
const $beforeImg = document.getElementById("beforeImg");
			
	$beforeImg.onclick = function() {
				document.getElementById("thumbnailImg").click();
			}
	
	function loadImg(value, num) {
				
		if (value.files && value.files[0]) {			
			const reader = new FileReader();
					
			reader.readAsDataURL(value.files[0]);		
						
			reader.onload = function(e) {				
				document.getElementById("beforeImg").src = e.target.result;
								
			}
		}
	}
}