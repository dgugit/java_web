<%@ page import="com.comunal.rest.dto.UserDTO" %>
<%@ page import="com.comunal.rest.dto.RequestDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <c:url value="/css/main.css" var="jstlCss"/>
    <link href="${jstlCss}" rel="stylesheet"/>

    <link rel="stylesheet" type="text/css" href="/css/welcome.css"/>

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="cont" id="state">
    <% UserDTO user = (UserDTO) session.getAttribute("currUser");
        if (user != null) {%>
    <div class="form sign-in">
        <% if (((List<RequestDTO>) request.getAttribute("requests")).size() > 0) { %>
        <h2>Ваші заявки:</h2>
        <table class="table table-hover" style="width: 85%; font-size: 10px">
            <thead>
            <tr>
                <th scope="col">Тип</th>
                <th scope="col">Кількість</th>
                <th scope="col">Дата</th>
                <th scope="col">Опис</th>
                <th scope="col">Статус</th>
                <% if (user.getAuthority().toString().equals("ADMIN")) {%>
                <th scope="col">Користувач</th>
                <th scope="col">Бригада</th>
                <th scope="col"></th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requests}" var="request">
                <tr>
                    <th scope="row">${request.type}</th>
                    <th scope="row">${request.amount}</th>
                    <th scope="row"><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                    value="${request.date}"/></th>
                    <th scope="row">${request.description}</th>
                    <th scope="row">${request.status}</th>
                    <% if (user.getAuthority().toString().equals("ADMIN")) {%>
                    <th scope="row">${request.user}</th>
                    <th scope="row">${request.team}</th>
                    <th scope="row">
                        <a href=""
                           class="action-button modalUpd"
                           data-toggle="modal"
                           data-target="#modalForm"
                           data-request-id="${request.id}"
                           data-request-type="${request.type}"
                           data-request-type-id="${request.typeId}"
                           data-request-description="${request.description}"
                           data-request-team="${request.team}"
                        >Редагувати</a>
                    </th>
                    <%}%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <% } else { %>
        <h2>Заявок поки немає</h2>
        <% }%>
    </div>
    <div class="sub-cont">
        <div class="img">
            <div class="img__text m--up">
                <h2>${currUser.firstName} ${currUser.lastName}</h2>
                <p>Логін: ${currUser.login}</p>
            </div>
            <div class="img__text m--in">
            </div>
            <% if (user.getAuthority().toString().equals("CLIENT")) {%>
            <div class="img__btn img__btn_click">
                <span class="m--up">Оновити дані</span>
                <span class="m--in">Скасувати</span>
            </div>
            <div class="img__btn img__btn_modal_click" style="margin-top: 20px">
                <span class="m--up">Створити заявку</span>
                <span class="m--in">Оновити пароль</span>
            </div>
            <%}%>
            <% if (user.getAuthority().toString().equals("ADMIN")) {%>
            <div class="img__btn img__btn_modal_click_admin">
                <span class="m--up">Створити бригаду</span>
            </div>
            <%}%>
        </div>
        <div class="form sign-up">
            <h2>Введіть актуальні дані,</h2>
            <form id="updateUser" name="updateUser">
                <label>
                    <span>Імя</span>
                    <input type="text" name="firstName" pattern="^[A-Z][a-z]{2,14}$" value="${currUser.firstName}"
                           required/>
                </label>
                <label>
                    <span>Прізвище</span>
                    <input type="text" name="lastName" pattern="^[A-Z][a-z]{2,14}$" value="${currUser.lastName}"
                           required/>
                </label>
                <label>
                    <span>Логін</span>
                    <input type="text" name="login" value="${currUser.login}" required/>
                </label>
                <button type="submit" class="submit">Оновити</button>
            </form>
        </div>
    </div>

    <!-- Modal update password -->
    <div class="modal fade" id="updatePasswordModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Оновити пароль</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="updatePassword" name="updatePassword">
                        <label>
                            <span>Поточний пароль</span>
                            <input type="password" name="password" required/>
                        </label>
                        <label>
                            <span>Новий пароль</span>
                            <input type="password" name="currentPassword" required/>
                        </label>
                        <label>
                            <span>Повторіть пароль</span>
                            <input type="password" name="confPassword" required/>
                        </label>
                        <button type="submit" class="submit">Зареєструватись</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal add request -->
    <div class="modal fade" id="createRequestModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Створити заявку</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="createRequest" name="createRequest">
                        <label>
                            <span>Тип роботи</span>
                            <select name="typeId">
                                <option value="1">Електрика</option>
                                <option value="2">Сантехніка</option>
                                <option value="3">Газопостачання</option>
                                <option value="4">Слесарські роботи</option>
                                <option value="5">Дрібна робота</option>
                                <option value="6">Отоплення</option>
                            </select>
                        </label>
                        <label>
                            <span>Бажаний час</span>
                            <input type="datetime-local" name="date" required/>
                        </label>
                        <label>
                            <span>Кількість</span>
                            <input type="number" name="amount" min="1" required/>
                        </label>
                        <label>
                            <span>Деталі</span>
                            <input type="text" name="description"/>
                        </label>
                        <button type="submit" class="submit">Створити</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal add team -->
    <div class="modal fade" id="createTeamModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Створити бригаду</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="createTeam" name="createTeam">
                        <label>
                            <span>Тип роботи</span>
                            <select name="typeId">
                                <option value="1">Електрика</option>
                                <option value="2">Сантехніка</option>
                                <option value="3">Газопостачання</option>
                                <option value="4">Слесарські роботи</option>
                                <option value="5">Дрібна робота</option>
                                <option value="6">Отоплення</option>
                            </select>
                        </label>
                        <label>
                            <span>Назва</span>
                            <input type="text" name="name" required/>
                        </label>
                        <button type="submit" class="submit">Створити</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal update request -->
    <div class="modal fade" id="modalForm" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Редагувати заявку</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="updateRequestTeam" name="updateRequestTeam" method="post" action="${pageContext.request.contextPath}/admin/request">
                        <input type="text" name="id" id="id" required hidden>
                        <input type="text" name="typeId" id="typeIdUpdate" required hidden>

                        <label>
                            <span>Тип роботи</span>
                            <input type="text" id="type" readonly/>
                        </label>
                        <label>
                            <span>Опис</span>
                            <input type="text" id="description" readonly/>
                        </label>
                        <label>
                            <span>Поточна команда</span>
                            <input type="text" id="currTeam" readonly/>
                        </label>
                        <label>
                            <span>Нова команда</span>
                            <select name="teamId" id="newTeamId" required>

                            </select>
                        </label>
                        <button type="submit" class="submit">Оновити</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%}%>

</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script>
    //triggered when modal is about to be shown
    $('#modalForm').on('show.bs.modal', function (e) {
        //get data-id attribute of the clicked element
        var id = $(e.relatedTarget).data('request-id');
        var type = $(e.relatedTarget).data('request-type');
        var typeId = $(e.relatedTarget).data('request-type-id');
        var description = $(e.relatedTarget).data('request-description');
        var team = $(e.relatedTarget).data('request-team');

        //populate the textbox
        $(e.currentTarget).find('input[id="type"]').val(type);
        $(e.currentTarget).find('input[id="typeIdUpdate"]').val(typeId);
        $(e.currentTarget).find('input[id="id"]').val(id);
        $(e.currentTarget).find('input[id="description"]').val(description);
        $(e.currentTarget).find('input[id="currTeam"]').val(team);

        $.ajax({
            url: 'http://localhost:8080/admin/teams/'+Number(typeId),
            type: 'get',
            success:function(response){

                var len = response.length;

                $("#newTeamId").empty();
                for( var i = 0; i<len; i++){
                    var id = response[i]['id'];
                    var name = response[i]['name'];

                    $("#newTeamId").append("<option value='"+id+"'>"+name+"</option>");

                }
            }
        });
    });
</script>

<script>
    document.querySelector('.img__btn_click').addEventListener('click', function () {
        document.querySelector('.cont').classList.toggle('s--signup');
    });

    document.querySelector('.img__btn_modal_click').addEventListener('click', function () {
        var state = document.getElementById('state').className;
        if (state === 'cont') {
            $("#createRequestModal").modal();
        } else {
            $("#updatePasswordModal").modal();
        }
    });
</script>
<script>
    document.querySelector('.img__btn_modal_click_admin').addEventListener('click', function () {
        $("#createTeamModal").modal();
    });
</script>
<script>
    (function ($) {
        function processForm(e) {
            var data = getFormData($(this));
            $.ajax({
                url: 'http://localhost:8080/user',
                dataType: 'text',
                type: 'put',
                data: JSON.stringify(data),
                datatype: 'json',
                contentType: "application/json",
                success: function (data, textStatus, jQxhr) {
                    window.location.href = '/';
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    debugger;
                    alert(errorThrown);
                }
            });

            e.preventDefault();
        }

        $('#updateUser').submit(processForm);
    })(jQuery);

    (function ($) {
        function processForm1(e) {
            var data = getFormData($(this));
            console.info(data);
            $.ajax({
                url: 'http://localhost:8080/user/password',
                dataType: 'text',
                type: 'put',
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

        $('#updatePassword').submit(processForm1);
    })(jQuery);

    (function ($) {
        function processForm2(e) {
            var data = getFormData($(this));
            console.info(data);
            $.ajax({
                url: 'http://localhost:8080/user/request',
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

        $('#createRequest').submit(processForm2);
    })(jQuery);

    (function ($) {
        function processForm3(e) {
            var data = getFormData($(this));
            console.info(data);
            $.ajax({
                url: 'http://localhost:8080/admin/team',
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

        $('#createTeam').submit(processForm3);
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
