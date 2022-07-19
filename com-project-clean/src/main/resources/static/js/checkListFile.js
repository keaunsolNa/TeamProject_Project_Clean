		
	const $contentImgArea1 = document.getElementById("contentImgArea1");
	const $contentImgArea2 = document.getElementById("contentImgArea2");
	
	$contentImgArea1.onclick = function() {
				document.getElementById("thumbnailImg1").click();
			}
	$contentImgArea2.onclick = function() {
				document.getElementById("thumbnailImg2").click();
			}
	
	function loadImg(value, num) {
				
		if (value.files && value.files[0]) {			
			const reader = new FileReader();
					
			reader.readAsDataURL(value.files[0]);		
						
			reader.onload = function(e) {	
				switch(num){
					case 1:
						document.getElementById("beforeImg").src = e.target.result;
						break;
					case 2:
						document.getElementById("afterImg").src = e.target.result;
						break;
				}
								
			}
		}
	}

