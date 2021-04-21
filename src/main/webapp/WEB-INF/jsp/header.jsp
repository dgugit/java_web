<%@ page import="com.comunal.rest.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">ЖЕК №502</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <% UserDTO user = (UserDTO) session.getAttribute("currUser");
                if (user != null) {%>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    ${currUser.firstName} ${currUser.lastName}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="<c:url value = "/user/profile"/>">Profile</a>
                    <a class="dropdown-item" href="<c:url value="/user/logout"/>">Log out</a>
                </div>
            </li>
            <% if (user.getAuthority().toString().equals("ADMIN")) {%>
            <%}%>
            <%}%>
        </ul>
    </div>
</nav>