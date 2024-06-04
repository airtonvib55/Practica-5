<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : estudiante.jsp
    Created on : 4 jun. de 2024, 14:27:05
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
        <h1>Practica 5 - JPA</h1>
        <h2>Javier Apaza Mamani</h2>
        <a href="MainController?action=add">Nuevo</a>
        <table border="2px">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Email</th>
                <th>Fecha de Nacimiento</th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${estudiantes}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nombre}</td>
                    <td>${item.apellidos}</td>
                    <td>${item.email}</td>
                    <td>${item.fechaNacimiento}</td>
                    <td><a href="MainController?action=edit&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro ???'))">Eliminar</a></td>
                </tr>
            </c:forEach>


        </table>
    </center>


</body>
</html>
