<%-- 
    Document   : mensaje
    Created on : 1/11/2018, 10:47:33 PM
    Author     : doria
--%>
<%HttpSession ses = request.getSession(true);%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='websocket.js'></script>
        <!--Luego le pones los estilos va?si-->
    </head>
    <body>
        <input type="hidden" id="session" value="<%out.println(ses.getId());%>">
        <input type="hidden" id="from" value="<%out.println(ses.getAttribute("id"));%>">
        <p><label >Mensaje</label></p>
        <input name="mensaje" type="text" id="mensaje" placeholder="Ingresa tu mensaje" autofocus="" >
        <p id="bot"><input type="button" id="enviar" name="enviar" value="Enviar" class="boton"></p>
    </body>
</html>
