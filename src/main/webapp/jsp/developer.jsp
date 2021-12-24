<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>View developer</title>
    <meta charset="UTF-8">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="headers.jsp" %>
</head>
<body>
<%@ include file="navigation.jsp" %>
<% com.goit.model.Developer developer = (com.goit.model.Developer) request.getAttribute("developer"); %>
<% java.util.List<com.goit.model.Company> listCompany = (java.util.List<com.goit.model.Company>) request.getAttribute("listCompany"); %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/developers" type="button" class="btn beak_btn">
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                </svg>
                developers</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="${developer.id}"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Last name</label>
            <input type="text" class="form-control"
                   value="${developer.lastName}"
                   id="lastName" placeholder="Last name">
        </div>
        <div class="mb-3">
            <label for="firstName" class="form-label">First name</label>
            <input type="text" class="form-control"
                   value="${developer.firstName}"
                   id="firstName" placeholder="First name">
        </div>
        <div class="mb-3">
            <label for="surname" class="form-label">Surname</label>
            <input type="text" class="form-control"
                   value="${developer.surname}"
                   id="surname" placeholder="Developer surname">
        </div>
        <div class="mb-3">
            <label for="developerAge" class="form-label">Age</label>
            <input type="text" class="form-control"
                   value="${developer.developerAge}"
                   id="developerAge" placeholder="Age">
        </div>
        <div class="mb-3">
            <label for="dateOfBirth" class="form-label">Date of birth</label>
            <input type="date" class="form-control"
                   value="${developer.dateOfBirth}"
                   id="dateOfBirth" placeholder="dateOfBirth (dd-mm-yyyy)">
        </div>
        <div class="mb-3">
            <label for="gender" class="form-label">Gender</label>
            <input type="text" class="form-control"
                   value="${developer.gender}"
                   id="gender" placeholder="gender">
        </div>
        <div class="mb-3">
            <label for="company">Company</label>
            <select class="form-select" id="company" name="company"
                    value='${developer.company}'
                    aria-label="Floating label select example">
                <option>Select company</option>
                <c:forEach var="element" items="${listCompany}">
                      <c:choose>
                         <c:when test="${element.id == developer.company.id}">
                            <option selected value='${element}'>${element.name}</option>
                         </c:when>
                         <c:otherwise>
                            <option value='${element}'>${element.name}</option>
                         </c:otherwise>
                      </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="salary" class="form-label">Salary</label>
            <input type="text" class="form-control"
                   value="${developer.salary}"
                   id="salary" placeholder="salary">
        </div>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button onclick="save()" type="button" class="btn beak_btn">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-check" viewBox="0 0 16 16">
                    <path d="M10.854 6.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 8.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
                    <path d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1z"/>
                  </svg> Save
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    let id = document.getElementById('id');
    let lastName = document.getElementById('lastName');
    let firstName = document.getElementById('firstName');
    let surname = document.getElementById('surname');
    let developerAge = document.getElementById('developerAge');
    let dateOfBirth = document.getElementById('dateOfBirth');
    let gender = document.getElementById('gender');
    let company = document.getElementById('company');
    let salary = document.getElementById('salary');
    console.log('company = ', company);

    let url;
    let method;

    function save() {
     let body = {
     <% if(developer.getId() != null) {%>
         id: id.value,
      <% } %>
        lastName: lastName.value,
        firstName: firstName.value,
        surname: surname.value,
        developerAge: developerAge.value,
        dateOfBirth: dateOfBirth.value,
        company: JSON.parse(company.value),
        gender: gender.value,
        salary: salary.value,
       }
        <% if(developer.getId() == null) {%>
           url = '/developers';
           method = 'POST';
        <% } else { %>
           url = '/developers/<%= developer.getId() %>';
           method = 'PUT';
        <% } %>
          fetch(url, {
              method: method,
              body: JSON.stringify(body)
          })
          .then( _ => {
              window.location.href = '/developers';
          }
          );
      }
</script>
</body>
</html>