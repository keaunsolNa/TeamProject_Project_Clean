var stompClient = null;
var senderName = null;
var clientName = document.getElementById("client").value;
var movePath = null;
var desPath = null;
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
    stompClient.send("/app/hello"+clientName, {}, JSON.stringify({'message': $("#sendingMessage").val(), 'name': $("#sendingName").val(), 'path': $("#path").val()}));
}

function showGreeting(message) {
    $("#ReceiveMessage").show();
    $("#ReceiveMessage").append("<tr><td>" + message + "</td></tr>");
//  $("#ReceiveMessage").append("<button id='move' type='button'>" + "관련 페이지로 이동" + "</button>");
    $("#ReceiveMessage").append("<script>" + alert(message) + "</script>");
//    $("#ReceiveMessage").append("<script>" + 'if(confirm("내용을 확인하시겠습니까?")){location.href="message"}' + "</script>");

}
	
$(function () {
	$("form").on('submit', function (e) {
		if(document.getElementById("formSender")){
			senderName = document.getElementById("employeeName").value;
			desPath = document.getElementById("path").value;
        e.preventDefault();
		    $( "#connect" ).click(function() { connect(); });
			$( "#disconnect" ).click(function() { disconnect(); });
	    	sendName();  
	    	
		}	
    });
});

$(document).ready(function(){
	connect();
	Notification.requestPermission();
});	

 
function calculate(movePath) {
    setTimeout(function () {
        notify(movePath);
    }, 1000);
}
 
function notify(movePath) {
    if (Notification.permission !== 'granted') {
        alert('알람 신청이 거부되었습니다. 승인이 필요합니다.');
    }
    else {
        var notification = new Notification('Notification title', {
            icon: 'http://cdn.sstatic.net/stackexchange/img/logos/so/so-icon.png',
            body: '신규 알림을 확인하세요~',
        });
 
        notification.onclick = function () {
	
			movePath = document.getElementById("path").value;
			console.log(movePath)
            	window.open(movePath);
        };
    }
}

