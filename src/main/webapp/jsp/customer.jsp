<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>View customer</title>
    <meta charset="UTF-8">
    <%@ include file="headers.jsp" %>

</head>
<body>
<%@ include file="navigation.jsp" %>
<% com.goit.model.Customer customer = (com.goit.model.Customer) request.getAttribute("customer"); %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/customers" type="button"  class="btn beak_btn">
                     <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
                       <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                     </svg> customers
                </a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="${customer.id}"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Customer</label>
            <input type="text" class="form-control"
                   value="${customer.name}"
                   id="name" placeholder="Customer">
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
    let id = $('#id').val();
    let name = document.getElementById('name');
    let url;
    let method;

    function save() {
     let body = {
     <% if(customer.getId() != null) {%>
         id: id,
      <% } %>
        name: name.value,
      }
      <% if(customer.getId() == null) {%>
         url = '/customers';
         method = 'POST';
      <% } else { %>
         url = '/customers/<%= customer.getId() %>';
         method = 'PUT';
      <% } %>

        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/customers';
        }
        );
    }
</script>
</body>
</html>