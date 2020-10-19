<%-- 
    Document   : index
    Created on : 19/10/2020, 12:53:59 PM
    Author     : flor
--%>

<%@page import="personas.PersonaDAO"%>
<%@page import="personas.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agregar persona</h1>
            <form action="index.jsp" method="POST">

                <div class="form-group">
                    <label>ID:</label>
                    <input type="text" name="id" id="id">
                </div>
                
                <div class="form-group">
                    <label>Nombre:</label>
                    <input type="text" name="nombre" id="nombre">
                </div>
                
                
                <div class="form-group">
                    <label>Apellidos:</label>
                    <input type="text" name="apellidos" id="apellidos">
                </div>

                <div class="form-group">
                    <label>Dirección: </label0>
                    <input type="text" name="direccion" id="direccion">
                </div>

                <div class="form-group">
                    <label>Telefono: </label>
                    <input type="text" name="telefono" id="telefono">
                </div>

                <div class="text-center">
                    <input type="submit" value="Guardar">
                </div>
            </form>    
    </body>
</html>

<%
    Persona persona = new Persona();
    persona.setId(request.getParameter("id"));
    persona.setNombre(request.getParameter("nombre")); 
    persona.setDireccion(request.getParameter("direccion"));
    persona.setTelefono(request.getParameter("telefono"));
    PersonaDAO personaDao = new PersonaDAO();
    if (persona.getNombre() != null  && persona.getDireccion() != null && persona.getTelefono() != null) {
        personaDao.insert(persona);
    }
%>
