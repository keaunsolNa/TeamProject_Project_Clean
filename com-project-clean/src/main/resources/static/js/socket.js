var stompClient = null;
var senderName = null;
var clientName = document.getElementById("client").value;
console.log(clientName)
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
    $("#ReceiveMessage").append("<tr><td>" + message + "</td></tr>");
}
function sendjs(){
	
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
	connect()
});	

