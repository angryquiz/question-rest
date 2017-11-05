<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="${pageContext.request.contextPath}/app/quiz" var="quizMvcUrl" />
<c:set value="${pageContext.request.contextPath}/resources" var="resourcesUrl" />
<html>
<head>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

<script src="${resourcesUrl}/jquery-1.11.1.min.js"></script>
</head>
<body>
<input type="hidden" id="quizMvcUrl" value="${quizMvcUrl}" />
<input type="hidden" id="uniqueid" value="${uniqueid}" /> 
  <script>
  $(document).ready(function() {

	  
  });
  </script>
<h3>Results</h3>
<br/><br/><br/>
<div id="inline_content">
<table>
<c:set var="counter" value="0" scope="page" />
<c:set var="counterCorrect" value="0" scope="page" />
<c:forEach items="${questionBucketList}" var="element" varStatus="status"> 
  <c:set var="counter" value="${counter + 1}" scope="page"/>
  <tr>
	<c:choose>
	    <c:when test="${element.correct == 'true'}">
	    
	    	<c:set var="counterCorrect" value="${counterCorrect + 1}" scope="page"/>
	       <td>
	    </c:when>
	    <c:otherwise>
	    	<td style="padding-left: 5px;color:red">	        
	    </c:otherwise>
	</c:choose>	
    <b>${status.count}. ${element.question}</b>
	<br/>
	${element.selections}
	
	<c:if test="${ element.questionType == 'single' }">
		<c:forEach var="entry" items="${element.selection}">
			<br/>${entry.key}. <c:out value="${entry.value}" />
		</c:forEach>
	</c:if>
	
	<c:if test="${ element.questionType == 'multi' }">
		<c:forEach var="entry" items="${element.selection}">
			<br/>${entry.key}. <c:out value="${entry.value}" />
		</c:forEach>
	</c:if>	
	
	<c:if test="${ element.questionType == 'match-term' }">

		<table>		
		<tr><td><b><br/>Questions:</b></td></tr>
		<c:forEach var="entry" items="${element.questionsMap}">
		<tr>
		<td>
			<c:out value="${entry.key}. ${entry.value}" />
		</td>
		</tr>
		</c:forEach>
		<tr><td><b><br/>Anwers:</b></td></tr>
		<c:forEach var="entry" items="${element.answersMap}">
		<tr>		
		<td>
			<c:out value="${entry.key}. ${entry.value}" />
		</td>		
		
		</tr>
		</c:forEach>
		
		</table>
			
	</c:if>	
	
	<br/><br/>
	Answer: ${element.answer} 
	<br/>
	Your Answer: ${element.memberAnswer}
	<br/>
	Correct: ${element.correct}
	<br/>
	Explanation: 
	<br/><br/>
	${element.explanation}
	<br/>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</c:forEach>
</table>
<br/><br/>
<b>
Summary
</b>
<br/>
<br/>
Total Correct Answer: ${counterCorrect}
<br/>
Total Question: ${counter}
<fmt:formatNumber var="average"
  value="${ ( counterCorrect / counter ) * 100 }"
  maxFractionDigits="0" />

<br/>
average: ${average} %
  <br/>
<br/>	
</div>
</body>
</html>