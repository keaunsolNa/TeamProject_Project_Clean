var stompClient = null;
var senderName = null;
var clientName = document.getElementById("client").value;
function setConnected(connected) {
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    
$("#ReceiveMessage").html("").hide();
}

function connect() {
    var socket = new SockJS('/checkListSocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({"token" : "발급받은 토큰"}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/queue/greetings'+clientName, function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function sendName() {
    stompClient.send("/app/hello"+clientName, {}, JSON.stringify({'message': $("#sendingMessage").val(), 'name': $("#sendingName").val()}));
}

function showGreeting(message) {
    $("#ReceiveMessage").show();
    $("#ReceiveMessage").append("<script>" + alert(message) + "</script>");
	console.log(clientName)
	if(clientName.indexOf("cleanup")){
		if(confirm("해당 내역을 확인하시겠습니까?")){
			
			if(message === clientName + "님의 체크리스트가 재반려되었습니다. 블랙리스트 경고 횟수가 증가합니다."){
	       		location.href='/employee/checkList/black/select'
	  		} else if(message === clientName + "님의 체크리스트가 승인되었습니다. 급여 확인 부탁드립니다.") {
	     		location.href='/employee/checkList/accept/select'
	 		} else if(message === clientName + "님의 체크리스트가 반려되었습니다."){
				location.href='/employee/checkList/denial/select'
			} 
			
		} else {
			
	  		$("#ReceiveMessage").append("<tr><td>" + message + "</td></tr>");
	  		
		}
	}		
}
	
$(function () {
	$("form").on('submit', function (e) {
		if(document.getElementById("formSender")){
			senderName = document.getElementById("employeeName").value;
        e.preventDefault();
		    $( "#connect" ).click(function() { connect(); });
			$( "#disconnect" ).click(function() { disconnect(); });
	    	sendName();  
	    	
		}	
    });
});

$(document).ready(function(){
	connect();
});	
