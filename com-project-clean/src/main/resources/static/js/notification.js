Notification.requestPermission();
 
function calculate() {
    setTimeout(function () {
        notify();
    }, 1000);
}
 
function notify() {
    if (Notification.permission !== 'granted') {
        alert('알람 신청이 거부되었습니다. 승인이 필요합니다.');
    }
    else {
        var notification = new Notification('Notification title', {
            icon: 'http://cdn.sstatic.net/stackexchange/img/logos/so/so-icon.png',
            body: 'Notification text',
        });
 
        notification.onclick = function () {
            window.open('http://google.com');
        };
    }
}
