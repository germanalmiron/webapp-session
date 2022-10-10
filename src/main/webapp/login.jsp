<%@page contentType="text/html" pageEncoding= "UTF-8"  %>
<%@page import ="java.util.List"  %>
<% List <String> errores = (List<String>)request.getAttribute("errores"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <!-- CSS only -->
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
            integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
            crossorigin="anonymous"
    />
</head>

<body>
<div class="container well col-3">
    <br />
    <h1 class="text-center text-primary">Inicio de sesión</h1>
    <form action="/webapp-session/login" method="post">
        <!-- usuario input-->
        <div class="form-outline mb-4">
            <input
                    type="text"
                    name="username"
                    id="username"
                    class="form-control"
                    aria-describedby="passwordHelpInline"
                    placeholder="Ingrese su usuario"
            />
        </div>
        <!-- Password input -->
        <div>
            <input
                    type="password"
                    name="password"
                    id="password"
                    class="form-control"
                    aria-describedby="passwordHelpInline"
                    placeholder="Ingrese su contraseña"
            />
        </div>
        <br />
        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block">
            Ingresar
        </button>
    </form>
    <br>
     <% if(errores!= null && errores.size()>0){
                                %>
                                <ul class="alert alert-danger">
                                <% for(String error:errores){
                                %>
                                <ul><%out.print(error);  %>
                                </ul>
                                <%}%>
                                </ul>
                                <%}%>

</div>
</body>
</html>
