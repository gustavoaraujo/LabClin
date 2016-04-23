<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .login {
        padding: 7em !important;
    }
    
    .login form {
        padding: 0 23em !important;
    }
</style>
<div class="login">
    <h1>Alterar cliente</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="action" value="alterarcli" />
        <input type="hidden" name="cliId" value="${att.idCliente}" />
        Nome: <input type="text" name="nomeCli" value="${att.nome}" style="width: 80%"/><br><br>
        Cpf: <input type="text" name="cpfCli" value="${att.cpf}" style="width: 85%"/><br><br>
        Telefone: <input type="text" name="telCli" value="${att.telefone}" style="width: 75%"/><br><br>
        Sexo: <select name="sexoCli" style="width: 83.3%">
            <c:if test="${att.sexo == 'Masculino'}">
                <option value="Masculino" selected>Masculino</option>
            </c:if>
            <c:if test="${att.sexo != 'Masculino'}">
                <option value="Masculino">Masculino</option>
            </c:if>
            <c:if test="${att.sexo == 'Feminino'}">
                <option value="Feminino" selected>Feminino</option>
            </c:if>
            <c:if test="${att.sexo != 'Feminino'}">
                <option value="Feminino">Masculino</option>
            </c:if>
        </select><br><br>
        Endereço: <input type="text" name="endCli" value="${att.endereco}" style="width: 73.3%"/><br><br>
        Senha: <input type="password" name="senhaCli" value="${att.senha}" style="width: 80%"/><br><br>
        <button type="submit" value="clientealterar" name="cpBtn">Alterar</button><br>
    </form>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>
</div>