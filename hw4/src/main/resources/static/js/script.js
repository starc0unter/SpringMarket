let stompClient = null;

window.onload = connect();

function connect() {
    let socket = new SockJS('/addProduct');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/productAdded', function (order) {
            let parsed = JSON.parse(order.body)
            for (let [id, quantity] of Object.entries(parsed.productId2Quantity)) {
                showResult(id, quantity)
            }
        });
    });
}

function addProduct(name) {
    stompClient.send("/app/addProduct", {}, JSON.stringify({'name': name}));
}

function showResult(productId, quantity) {
    document.getElementById("product" + productId).innerHTML = quantity;
}