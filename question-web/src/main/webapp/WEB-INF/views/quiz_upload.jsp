<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}/app/quiz" var="quizMvcUrl" />
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
<input type="hidden" id="quizMvcUrl" value="${quizMvcUrl}" />

  <script>
  $(document).ready(function() {

	  var quizMvcUrl = $('input#quizMvcUrl').val();		  
	  var sessionId = $('input#sessionId').val();
	  var memberNumber = $('input#memberNumber').val();
  
	  $('#inline_content select').change(function() { 		    
		    var sel = $(this).attr('name');
		    var value = $(this).val();
		    var selSpl = sel.split("_");
		    console.log("--------");
		    console.log('attr_name='+sel);
		    console.log('question number='+selSpl[0]);
		    console.log('question left box='+selSpl[1]);
		    console.log('question right box='+value);

		    var selectionArray = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
		    var arrayLength = selectionArray.length;
		    var selectedAnswers = [];
		    for (var i = 0; i < arrayLength; i++) {
		    	var idToCheck = '#' + selSpl[0] + '_' + selectionArray[i];
		    	var idToCheckValue = $(idToCheck).find(":selected").val();
		        if (typeof(idToCheckValue) != 'undefined' && idToCheckValue != null) {
					if(idToCheckValue != '') {
			        	selectedAnswers.push(selectionArray[i]+"="+idToCheckValue);	
					}
				}
		    }
		    console.log(selectedAnswers);
		    var finalAns = encodeURIComponent(selectedAnswers.join(","));
		    console.log(finalAns);

		    var ans = selSpl[0]+'/'+finalAns;	 			
			var url = quizMvcUrl+"/save/"+ans+"/"+memberNumber+"/"+sessionId;   
		    if(finalAns.length > 0) {
		    $.ajax({ // ajax call starts
		          url: url, // JQuery loads serverside.php
		          dataType: 'json', // Choosing a JSON datatype
		          success: function(data) // Variable data contains the data we get from serverside
		          {
		        	  //alert(data);
		          }
		      });
	        }		    
		  
	  }); 
	  
	  $('#inline_content input:radio').click(function() { 
		    var ansVal = $(this).val();
		    var ans = $(this).attr('name')+'/'+ansVal;	 			
			var url = quizMvcUrl+"/save/"+ans+"/"+memberNumber+"/"+sessionId;   
		    if(ansVal.length > 0) {
		    $.ajax({ // ajax call starts
		          url: url, // JQuery loads serverside.php
		          dataType: 'json', // Choosing a JSON datatype
		          success: function(data) // Variable data contains the data we get from serverside
		          {
		        	  //alert(data);
		          }
		      });
	        }		  
	  }); 
	  
	  $('#inline_content input:checkbox').click(function() { 
		    var chk = $(this).attr('name');
		    var chkVal = $("input:checkbox[name="+chk+"]:checked").map(function() { return this.value; }).get().join(",");
		    var ans = $(this).attr('name')+'/'+chkVal;
			var url = quizMvcUrl+"/save/"+ans+"/"+memberNumber+"/"+sessionId;   
			if (chkVal.length > 0) {
		    $.ajax({ // ajax call starts
		          url: url, // JQuery loads serverside.php
		          dataType: 'json', // Choosing a JSON datatype
		          success: function(data) // Variable data contains the data we get from serverside
		          {
		        	  //alert(data);
		          }
		      });	
		      
		      }
		      	  
	  });	  
	  
	  
	  $( ".result_link" ).click(function() {
	  			
		var url = quizMvcUrl+"/results/"+memberNumber+"/"+sessionId; 	
  		$(location).attr('href',url);
  			
		});
	  
  });
  </script>
<h3>Questions</h3>
<div id="inline_content">
<table>
<c:forEach items="${questionBucketList}" var="element" varStatus="status"> 
  <tr>
    <td><b>${status.count}. ${element.question}</b>
	<br/>
	<input type="hidden" id="sessionId" name="sessionId" value="${element.sessionId}">
	<input type="hidden" id="memberNumber" name="memberNumber" value="${element.memberNumber}">	
	
	<c:if test="${ element.questionType == 'single' }">
		<c:forEach var="entry" items="${element.selection}">
	  		<br/><input type="radio" name="${element.questionNumber}" value="<c:out value="${entry.key}" />"><c:out value="${entry.value}" />  		
		</c:forEach>
	</c:if>
	
	<c:if test="${ element.questionType == 'multi' }">
		<c:forEach var="entry" items="${element.selection}">
	  		<br/><input type="checkbox" name="${element.questionNumber}" value="<c:out value="${entry.key}" />"><c:out value="${entry.value}" />  		
		</c:forEach>
	</c:if>
	
	<c:if test="${ element.questionType == 'match-term' }">
		
		<table>		
		<c:forEach var="entry" items="${element.questionsMap}">
		<tr>
		<td>
			<c:out value="${entry.value}" />
		</td><td> 
  		<select name="<c:out value="${element.questionNumber}_${entry.key}"/>" id="<c:out value="${element.questionNumber}_${entry.key}"/>" >
  				<option value=""> - Select - </option>
				<c:forEach var="answersEntry" items="${element.answersMap}">
					<option value="${answersEntry.key}">${answersEntry.value}</option>
				</c:forEach>
			</select>
		</td>
		</tr>
		</c:forEach>
		</table>
	</c:if>
			
		
	<br/>
  </tr>
  <tr><td>&nbsp;</td></tr>
</c:forEach>
</table>
<br/>	
</div>
<button class="result_link" >Results</button>
</body>
</html>