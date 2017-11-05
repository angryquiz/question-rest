<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}/app/question" var="questionMvcUrl" />
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
  <script>
  $(document).ready(function() {
	  
	  <!-- $("input[type=submit]").attr("disabled", "disabled"); -->
	  
	  $("input[type=submit]").removeAttr("disabled");  
	  
	  var questionMvcUrl = $('input#questionMvcUrl').val();	
	  
	  $("#reviewAnswers").click(function() {
		  var sessionId = $('input#sessionId').val();
		  var memberNumber = $('input#memberNumber').val();
		  var url = questionMvcUrl+"/review/"+memberNumber+"/"+sessionId;
		  window.location = url;
		});

	  return false; // keeps the page from not refreshing 
  
  });
  </script>

	<form method="get" action="${questionMvcUrl}/next/${memberNumber}/${question.sessionId}">
	<input type="hidden" id="sessionId" name="sessionId" value="${question.sessionId}">
	<input type="hidden" id="number" name="number" value="${question.questionNumber}">	
	<input type="hidden" id="memberNumber" name="memberNumber" value="${memberNumber}">
	
	<!-- 
	<h4>${message}</h4>
	<h4>QuestionCode# : ${question.questionNumber} 
	totalQuestionRunningValue# : ${question.questionBucketDetails.totalQuestionRunningValue} 
	totalQuestion# : ${question.questionBucketDetails.totalQuestion}
	numberOfSetsDone# : ${question.questionBucketDetails.numberOfSetsDone} 
	questionSetRunningValue# : ${question.questionBucketDetails.questionSetRunningValue}
	questionSetTotalValue# : ${question.questionBucketDetails.questionSetTotalValue} </h4>
	 -->
	 
	<c:if test="${empty question.question}">
		
		<c:if test = "${question.status == 'STATUS_NULL_QUESTIONS_NOT_ANSWERED'}">      
   			<h4>No more question to review.  </h4> 				
	    	<br/>
		</c:if> 	
		<input type="submit" value="Next Question" id="nextQuestion">
	</c:if>
	
	<c:if test="${not empty question.question}">
		<h4>${question.question}</h4>
		
		<c:if test="${ question.questionType == 'multi' }">		
			<c:forEach var="entry" items="${question.selection}">
		  		<br/><c:out value="${entry.key}" />=<c:out value="${entry.value}" />  		
			</c:forEach>
		</c:if>
		
		<c:if test="${ question.questionType == 'single' }">		
			<c:forEach var="entry" items="${question.selection}">
		  		<br/><c:out value="${entry.key}" />=<c:out value="${entry.value}" />  		
			</c:forEach>
		</c:if>
				
		<c:if test="${ question.questionType == 'match-term' }">
			
			<table border="0">			
			<tr><td><b>Questions:</b></td></tr>
			<c:forEach var="entry" items="${question.questionsMap}">
			<tr>
			<td>
				<c:out value="${entry.key}"/>. <c:out value="${entry.value}" />
			</td><td> 
			</td>
			</tr>
			</c:forEach>			
			<tr><td></td></tr>
			<tr><td><b>Answers:</b></td></tr>
			<c:forEach var="entry" items="${question.answersMap}">
			<tr>
			<td>
				<c:out value="${entry.key}"/>. <c:out value="${entry.value}" />
			</td><td> 
			</td>
			</tr>
			</c:forEach>
									
			</table>
		</c:if>
				
		<br/>
		<h4>Correct Answer : ${question.answer}</h4>
		<h4>Explanation : </h4>
		 ${question.explanation}
		<br/>
		<h3 id="showStatus"></h3>
		<input type="button" value="Review Answers" id="reviewAnswers">	
	</c:if>
	</form>

</body>
</html>