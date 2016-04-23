<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login">
    <h1>Cadastrar plano de saúde</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="action" value="cadastrarplanosaude" />
        Nome: <input type="text" name="nomePlano" /><br><br>
        <button type="submit" value="planocadastrar" name="cpBtn">Cadastrar</button><br>
    </form>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>
</div>