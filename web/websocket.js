$(document).ready(function(){
    var socket = new WebSocket("ws://localhost:8080/chat/actions");
    //console.log(socket);
    socket.onmessage = onMessage;
    
    function onMessage(message){
        console.log(message);
    }
    
    $("#enviar").click(function(){
        console.log($("#mensaje").val());
        socket.send(JSON.stringify({identification : false, mensaje: $("#mensaje").val(), from: parseInt($("#from").val()), to: "Alguien"}))
    });
    
    socket.onopen = function() {
        socket.send(JSON.stringify({identification : true, id_session: $('#session').val()}));
    }
    
});