var sjq  = jQuery.noConflict();
var stompClient = null;

function setConnected(connected) {
    if (connected) {
        sjq("#conversation").show();
    }
    else {
        sjq("#conversation").hide();
    }
    
sjq("#ReceiveMessage").html("").hide();
}

function connect() {
    var socket = new SockJS('/checkListSocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({"token" : "발급받은 토큰"}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/queue/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'message': sjq("#sendingMessage").val(), 'name': sjq("#sendingName").val()}));
}

function showGreeting(message) {
    sjq("#ReceiveMessage").show();
    sjq("#ReceiveMessage").append("<tr><td>" + message + "</td></tr>");
}

sjq(function () {
    sjq("form").on('submit', function (e) {
        e.preventDefault();
    });

    sjq( "#connect" ).click(function() { connect(); });
    sjq( "#disconnect" ).click(function() { disconnect(); });
    sjq( "#send" ).click(function() { sendName(); });
});

sjq(function(){
	connect();
});	


