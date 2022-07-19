var jq = jQuery.noConflict();
var stompClient = null;

function setConnected(connected) {
    jq("#connect").prop("disabled", connected);
    jq("#disconnect").prop("disabled", !connected);
    if (connected) {
        jq("#conversation").show();
    }
    else {
        jq("#conversation").hide();
    }
    jq("#ReceiveMessage").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'message': jq("#sendingMessage").val(), 'name': jq("#sendingName").val()}));
}

function showGreeting(message) {
    jq("#ReceiveMessage").append("<tr><td>" + message + "</td></tr>");
}

jq(function () {
    jq("form").on('submit', function (e) {
        e.preventDefault();
    });

    jq( "#connect" ).click(function() { connect(); });
    jq( "#disconnect" ).click(function() { disconnect(); });
    jq( "#send" ).click(function() { sendName(); });
});

$(function(){
	connect();

});	
