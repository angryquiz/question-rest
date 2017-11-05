<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}/app/quiz" var="questionMvcUrl" />
<c:set value="${pageContext.request.contextPath}/resources" var="resourcesUrl" />
<html>
<head>
<script src="${resourcesUrl}/jquery-1.11.1.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

</head>
<body>
<input type="hidden" id="questionMvcUrl" value="${questionMvcUrl}" />
<h3>Message : ${message}</h3>	
<div id="inline_content">
</div>    
<br/><br/>
<div id="inline_content">
	<form method="POST" enctype="multipart/form-data"
		action="${questionMvcUrl}/upload">
		File to upload: <input type="file" name="file"><br /> Member Number: <input
			type="text" name="memberNumber" value="1234"><br /> <br /> <input type="submit"
			value="Upload">
	</form>
</div>  
<br/><br/>
Templates: 
<br/>
<a href="/question-web/resources/memorize-this.xlsx">memorize-this.xlsx</a>	
<br/>
<a href="/question-web/resources/sample.xlsx">sample.xlsx</a>	
<br/>
<a href="/question-web/resources/sample-multi.xlsx">sample-multi.xlsx</a>	
<br/>
<a href="/question-web/resources/sample-match-term-3.xlsx">sample-match-term-3.xlsx</a>	
</body>
</html>