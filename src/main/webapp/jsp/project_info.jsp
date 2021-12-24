<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Project info</title>
    <%@ include file="headers.jsp" %>
</head>
<body>
<%@ include file="navigation.jsp" %>
<% java.util.List<java.lang.String> listProjectInfo = (java.util.List<java.lang.String>) request.getAttribute("listProjectInfo"); %>
<div class="container">
    <div class="row">
        <h2>Project info</h2>
    </div>
    <div class="row">
        <table class="table">
          <thead>
          <tr>
              <th scope="col">Project info</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="element" items="${listProjectInfo}">
                <tr>
                    <td><c:out value = "${element}"/></td>
                </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
</div>
</body>
</html>