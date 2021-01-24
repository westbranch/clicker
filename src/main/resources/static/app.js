var stompClient = null;

function connect() {
    var socket = new SockJS('/clicker-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/click', function (response) {
            document.querySelector("#counter").textContent = JSON.parse(response.body);
        });
        stompClient.send("/app/init", {}, {});
    });
}

window.onload = function () {
    document.querySelector("#click").addEventListener("click", event => stompClient.send("/app/count", {}, {}));
    connect();
};

