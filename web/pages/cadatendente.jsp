<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login">
    <h1>Cadastrar atendente</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="action" value="cadastraratt" />
        Login: <input type="text" name="loginAtt" /><br><br>
        Senha: <input type="password" name="senhaAtt" /><br><br>
        <button type="submit" value="atendentecadastrar" name="cpBtn">Cadastrar Atendente</button><br>
    </form>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>
</div>