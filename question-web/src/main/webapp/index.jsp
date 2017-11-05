<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}/app/question" var="questionMvcUrl" />
<c:set value="${pageContext.request.contextPath}/resources" var="resourcesUrl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body>
<br/><br/>
Please find below tools:
<ul>
<li><a href="${pageContext.request.contextPath}/app/question/welcome">Memorize</href>
<li><a href="${pageContext.request.contextPath}/app/quiz/welcome">Practice Question</href>
</ul>


</body>
</html>
