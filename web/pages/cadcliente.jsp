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
    <h1>Cadastrar cliente</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="action" value="cadastrarcli" />
        Nome: <input type="text" name="nomeCli" style="width: 80%" /><br><br>
        Cpf: <input type="text" name="cpfCli" style="width: 85%" /><br><br>
        Telefone: <input type="text" name="telCli" style="width: 75%" /><br><br>
        Sexo: <select name="sexoCli" style="width: 83.3%" >
            <option value="Masculino">Masculino</option>
            <option value="Feminino">Feminino</option>
        </select><br><br>
        Endereço: <input type="text" name="endCli" style="width: 73.3%" /><br><br>
        Senha: <input type="password" name="senhaCli" style="width: 80%" /><br><br>
        <button type="submit" value="clientecadastrar" name="cpBtn">Cadastrar Cliente</button><br>
    </form>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>    
</div>