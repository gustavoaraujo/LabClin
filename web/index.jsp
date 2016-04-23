<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Labclin</title>
  </head>
  <style>
  * {
    font-family: "Lucida Sans", sans-serif;
  }
  
  body {
      margin: 0;
      background: url(/LabClin/images/bg.png);
  }
  
  .areacliente {
    font-size: 16px;
    padding: 0;
    color: red;
  }    
  
  .listamenu, .menuinterno {
    list-style: none;
    margin: 0;
    padding: 0em 1em;
    background: red;
  }

  .listamenu li, .menuinterno li {
    display: inline-block;
    color: white;
  }

  .listamenu li input:hover, .menuinterno li button:hover, .logout:hover, .listamenu li button:hover {
    color: red;
    background: white;
    transition: all 0.4s ease-in-out;
  }
  
  .titulo {
      font-family: Arial Black;
  }
  
  .btnmenu {
    background: none;
    border: none;
    cursor: pointer;
    color: white;
  }
  
  .listamenu li input, .listamenu li button {
    text-transform: capitalize;
    height: 3em;
    padding: 0 2em;
    transition: all 0.4s ease-in-out;
  }
  
  .logout {
      color: black;
      text-decoration: none;
      font-size: 13px;
  }
  
  .textohome, footer p, .login h1 {
      text-align: center;
  }
  
  footer {
      background: red;
      color: white;
      padding: 0.3em;
      box-shadow: 0px -4px 11px #CCC;
  }
  
  .central {
      padding: 15em;
  }
  
  footer p {
      margin: 0;
      font-size: 13.6px;
  }
  
  #formInicial {
      box-shadow: 0px 1px 8px #CCC;
  }
  
  .login form {
      padding: 0 24em;
  }
  
  .login {
      padding: 12em;
  }
  
  .login p {
      position: absolute;
  }
  
  .area {
      padding: 2em;
  }
  
  .area table, .icad table {
      width: 100%;
  }
  
  .area table tr td, .icad table tr td {
      margin: 1em;
      padding: 1em;
      background: white;
      text-align: center;
  }
  
  th {
      background: red !important;
      color: white;
      padding: 1em;
  }
  
  .txtLogout {
      position: absolute;
      padding: 1em;
  }
  
  .icad {
      padding: 10em;
  }
  
  .formleft {
      float: left;
  }
  
  .formright, .formright button {
      float: right;
  }
  
  </style>
  <body>
    <form action="ControllerCentral" id="formInicial">
        <ul class="listamenu">
            <li class="titulo">LABCLIN</li>
            <c:if test="${user == null && atendenteLog == null && clienteLog == null}">
                <li><input type="submit" value="administrador" class="btnmenu" name="action"></li>
                <li><input type="submit" value="atendente" class="btnmenu" name="action"></li>
                <li><input type="submit" value="cliente" class="btnmenu" name="action"></li>    
            </c:if>
            <c:if test="${user != null}">
                <li><button type="submit" value="areaadmin" class="btnmenu" name="action">Faturamento</button></li>
                <li><button type="submit" value="icadatendente" class="btnmenu" name="action">Atendente</button></li>
            </c:if>    
            <c:if test="${user != null || atendenteLog != null}">
                <li><button type="submit" value="icadcliente" class="btnmenu" name="action">Cliente</button></li>
                <li><button type="submit" value="icadplanosaude" class="btnmenu" name="action">Plano de Saúde</button></li>
                <li><button type="submit" value="icadexame" class="btnmenu" name="action">Exame</button></li>
            </c:if>
            <c:if test="${user != null || atendenteLog != null || clienteLog != null}">
                <li><button type="submit" value="logout" class="btnmenu" name="action">Logout</button></li>  
            </c:if>
        </ul>
    </form>
      <% 
      String pagina = request.getParameter("page");
      
      if(pagina == null) {
          pagina = (String)request.getAttribute("page");
      }
      
      if(pagina != null)
      {
          pagina = "pages/"+pagina+".jsp";
      }
      else
      {
          pagina = "pages/home.jsp";
      }
      %>
    <div style="height: 36.9em;">
        <jsp:include page="<%= pagina %>"/>
    </div>
    <footer>
        <p>2016 - Desenvolvido por Gustavo da Silva Araujo</p>
    </footer>
  </body>
</html>
