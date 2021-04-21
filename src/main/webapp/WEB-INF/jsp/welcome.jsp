<%@ page import="com.comunal.rest.dto.UserDTO" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <c:url value="/css/main.css" var="jstlCss"/>
    <link href="${jstlCss}" rel="stylesheet"/>

    <link rel="stylesheet" type="text/css" href="/css/welcome.css"/>

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="cont">
<% UserDTO user = (UserDTO) session.getAttribute("currUser");
    if (user == null) {%>
    <div class="form sign-in">
        <h2>Вітаємо,</h2>
        <form action="${pageContext.request.contextPath}/user/sigIn" method="POST">
            <label>
                <span>Логін</span>
                <input type="text" name="login" required/>
            </label>
            <label>
                <span>Пароль</span>
                <input type="password" name="password" required/>
            </label>
            <button type="submit" class="submit">Ввійти</button>
        </form>
    </div>
    <div class="sub-cont">
        <div class="img">
            <div class="img__text m--up">
                <h2>Вперше тут?</h2>
                <p>Зареєструйся і користуйся всіма можливостями додатку!</p>
            </div>
            <div class="img__text m--in">
                <h2>Вже з нами?</h2>
                <p>Якщо вже маєте акаут, просто ввійдіть)</p>
            </div>
            <div class="img__btn">
                <span class="m--up">Зареєструватись</span>
                <span class="m--in">Ввійти</span>
            </div>
        </div>
        <div class="form sign-up">
            <h2>Ми зробимо твоє життя комфортнішим,</h2>
            <form id="newUser" name="newUser">
                <label>
                    <span>Імя</span>
                    <input type="text" name="firstName" pattern="^[A-Z][a-z]{2,14}$" required/>
                </label>
                <label>
                    <span>Прізвище</span>
                    <input type="text" name="lastName" pattern="^[A-Z][a-z]{2,14}$" required/>
                </label>
                <label>
                    <span>Логін</span>
                    <input type="text" name="login" required/>
                </label>
                <label>
                    <span>Пароль</span>
                    <input type="password" name="password" required/>
                </label>
                <label>
                    <span>Повторіть пароль</span>
                    <input type="password" name="confPassword" required/>
                </label>
                <button type="submit" class="submit">Зареєструватись</button>
            </form>
        </div>
    </div>
    <%} if (user != null) {%>
    <div class="img_welcome">

    </div>
    <%}%>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script>
    document.querySelector('.img__btn').addEventListener('click', function () {
        document.querySelector('.cont').classList.toggle('s--signup');
    });
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
    (function ($) {
        function processForm(e) {
            var data = getFormData($(this));
            console.info(data);
            $.ajax({
                url: window.location.origin + '/user',
                dataType: 'text',
                type: 'post',
                data: JSON.stringify(data),
                datatype: 'json',
                contentType: "application/json",
                success: function (data, textStatus, jQxhr) {
                    window.location.href = '/';
                    alert(data);
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });

            e.preventDefault();
        }

        $('#newUser').submit(processForm);
    })(jQuery);

    function getFormData($form) {
        var unindexed_array = $form.serializeArray();
        var indexed_array = {};

        $.map(unindexed_array, function (n, i) {
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }
</script>
</body>

</html>
