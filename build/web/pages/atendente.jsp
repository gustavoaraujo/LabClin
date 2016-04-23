<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login">
    <h1>Login do Atendente</h1>
    <form action="ControllerCentral" method="POST">
        <input type="hidden" name="action" value="areaatt" />
        Login:<input type="text" name="login"><br><br>
        Senha:<input type="password" name="senha"><br><br>
        <input type="submit" value="Login">
        <c:if test="${msg != null}">
            <p>${msg}</p>
        </c:if>
    </form><br>
</div>