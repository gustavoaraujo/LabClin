<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login">
    <h1>Alterar plano de saúde</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="action" value="alterarplano" />
        <input type="hidden" name="cliId" value="${att.idplano}" />
        Nome: <input type="text" name="nomeCli" value="${att.nome}"/><br><br>
        <button type="submit" value="clientealterar" name="cpBtn">Alterar</button><br>
    </form>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>    
</div>