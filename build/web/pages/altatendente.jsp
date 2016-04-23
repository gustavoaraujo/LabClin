<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login">
    <h1>Alterar atendente</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="attId" value="${att.idatendente}" />
        Login:<input type="text" name="attLogin" value="${att.login}"/><br><br>
        Senha:<input type="password" name="attSenha"/><br><br>
        <button type="submit" name="action" value="alteraratt">Alterar</button>
    </form><br>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>    
</div>
