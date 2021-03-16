<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<h2 >Українець Олег</h2>
<h3 >Варіант 12</h3>
<p >Здійснити впорядкування книг за назвами. Інформація про книги знаходиться в БД.
    Із БД вибираються тільки ті книги, рік видання яких більший за вказаний користувачем.
</p>
Рік видачі: <br />
<form action="${pageContext.request.contextPath}/getbooks" method="get"
      enctype="multipart/form-data">
    <input type="number" name="year_of_book"  />
    <br />
    <input type="submit" value="Пошук" />
</form>

</body>
</html>