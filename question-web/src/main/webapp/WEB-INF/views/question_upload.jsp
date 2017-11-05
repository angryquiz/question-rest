<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}/app/question"
	var="questionMvcUrl" />
<c:set value="${pageContext.request.contextPath}/resources"
	var="resourcesUrl" />
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
	  $("#checkAnswer").click(function(){
		  
		  $("#showStatus").text("");
		  
		  var questionMvcUrl = $('input#questionMvcUrl').val();		  
		  var sessionId = $('input#sessionId').val();
		  var number = $('input#number').val();
		  var questionType = $('input#questionType').val();
		  var memberNumber = $('input#memberNumber').val();
		  
		  var finalAns = '';
		  
		  var answer = $('input:radio[name=answer]:checked').val();
		  
		  var answerbox = $('input:checkbox[name=answerbox]:checked').val();
		  
		  if (typeof(answer) != 'undefined' && answer != null)
			{
			    finalAns = answer;
			}

			if (typeof(answerbox) != 'undefined' && answerbox != null)
			{
			    finalAns = $("input:checkbox[name=answerbox]:checked").map(function() { return this.value; }).get().join(",");
			    
			}
			
			if(questionType == 'match-term') {
				
				
				var selectedAnswers = [];
				
			    var selectionArray = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
			    var arrayLength = selectionArray.length;
			    var selectedAnswers = [];
			    for (var i = 0; i < arrayLength; i++) {
			    	var idToCheck = '#' + selectionArray[i];
			    	var idToCheckValue = $(idToCheck).find(":selected").val();
			        if (typeof(idToCheckValue) != 'undefined' && idToCheckValue != null) {
						if(idToCheckValue != '') {
				        	selectedAnswers.push(selectionArray[i]+"="+idToCheckValue);	
						}
					}
			    }
				
				finalAns = encodeURIComponent(selectedAnswers.join(","));
				
			}
					  
		  
		    
		  var url = questionMvcUrl+"/check/"+number+"/"+finalAns+"/"+memberNumber+"/"+sessionId;	    
	    $.ajax({ // ajax call starts
	          url: url, // JQuery loads serverside.php
	          dataType: 'json', // Choosing a JSON datatype
	          success: function(data) // Variable data contains the data we get from serverside
	          {
	        	  $("#showStatus").text(data.answer);
	          }
	      });
	      return false; // keeps the page from not refreshing 
	    
	  });	  
  });
  </script>

	<form method="get" action="${questionMvcUrl}/next/${memberNumber}/${question.sessionId}">
	<input type="hidden" id="sessionId" name="sessionId" value="${question.sessionId}">
	<input type="hidden" id="number" name="number" value="${question.questionNumber}">	
	<input type="hidden" id="questionType" name="questionType" value="${question.questionType}">
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
	 
	<h4>${question.question}</h4>

	<c:if test="${ question.questionType == 'single' }">
		<c:forEach var="entry" items="${question.selection}">
	  		<br/><input type="radio" name="answer" value="<c:out value="${entry.key}" />"><c:out value="${entry.value}" />  		
		</c:forEach>
	</c:if>
	
	<c:if test="${ question.questionType == 'multi' }">
		<c:forEach var="entry" items="${question.selection}">
	  		<br/><input type="checkbox" name="answerbox" value="<c:out value="${entry.key}" />"><c:out value="${entry.value}" />  		
		</c:forEach>
	</c:if>

		<c:if test="${ question.questionType == 'match-term' }">
			
			<table border="0">
			<c:forEach var="entry" items="${question.questionsMap}">
			<tr>
			<td>
				<c:out value="${entry.value}" />
			</td><td> 
	  		<select name="<c:out value="${entry.key}"/>" id="<c:out value="${entry.key}"/>" >
	  				<option value=""> - Select - </option>
					<c:forEach var="answersEntry" items="${question.answersMap}">
						<option value="${answersEntry.key}">${answersEntry.value}</option>
					</c:forEach>
				</select>
			</td>
			</tr>
			</c:forEach>

			</table>
		</c:if>


		<br /> <br />
		<h3 id="showStatus"></h3>
		<input type="button" value="Check Answer" id="checkAnswer"> <input
			type="submit" value="Next Question" id="nextQuestion">
	</form>


</body>
</html>