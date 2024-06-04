<%-- 
    Document   : frmestudiante
    Created on : 4 jun. de 2024, 15:39:34
    Author     : JAVIER APAZA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <center>
        <h1>Formulario</h1>

        <form action="MainController" method="post">
            <input type="hidden" name="id" value="${estudiante.id}">
            <label for="">Nombre</label>
            <input type="text" name="nombre" value="${estudiante.nombre}">
            <br>
            <br>
            <label for="">Apelldios</label>
            <input type="text" name="apellidos" value="${estudiante.apellidos}" >
            <br>
            <br>
            <label for="">Email</label>
            <input type="text" name="email" value="${estudiante.email}">
            <br>
            <br>
            <label>Fecha Nac</label>
            <input type="date" name="fechaN" value="${estudiante.fechaNacimiento}">
            <br>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </center>


</body>
</html>
