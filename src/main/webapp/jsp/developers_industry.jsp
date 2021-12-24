<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Developers of industry</title>
    <%@ include file="headers.jsp" %>
</head>
<body>
<%@ include file="navigation.jsp" %>
<% java.util.List<com.goit.model.Skill> listSkill = (java.util.List<com.goit.model.Skill>) request.getAttribute("listSkill"); %>
<% java.util.List<java.lang.String> developersIndustry = (java.util.List<java.lang.String>) request.getAttribute("developersIndustry"); %>
<div class="container">
    <div class="row">
        <h2>Developers of industry</h2>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="skill">Skill industry</label>
            <select class="form-select" id="skill" name="skill"
                    value=''
                    aria-label="Floating label select example">
                <option>Select industry</option>
                <c:forEach var="element" items="${listSkill}">
                  <option value='${element}'>${element.industry}</option>
                </c:forEach>
            </select>
        </div>
    </div>
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button onclick="apply()" type="button" class="btn beak_btn">
                  <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-file-check" viewBox="0 0 16 16">
                    <path d="M10.854 6.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 8.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
                    <path d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1z"/>
                  </svg> Apply
                </button>
            </div>
        </div>
    <div class="row">
        <table class="table">
          <thead>
          <tr>
              <th scope="col">Developers of industry</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="element" items="${developersIndustry}">
                <tr>
                    <td><c:out value = "${element}"/></td>
                </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
</div>
<script>
    let project = document.getElementById('skill');
    console.log('skill = ', skill);

    let url;
    let method;

    function apply() {
     let body = JSON.parse(project.value)
     url = '/developers_industry';
     method = 'POST';
    fetch(url, {
        method: method,
        body: JSON.stringify(body)
    })
    .then( _ => {
        window.location.href = '/developers_industry';
    });
      }
</script>
</body>
</html>