<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${msg != null}">
    <h1><%= request.getAttribute("msg") %></h1>    
</c:if>
<c:if test="${msg == null}">
    <h1>Erro!</h1>    
</c:if>






