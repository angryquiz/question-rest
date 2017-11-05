<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}/app/question" var="questionMvcUrl" />
<c:set value="${pageContext.request.contextPath}/resources" var="resourcesUrl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.5/angular.min.js"></script>
</head>
<body ng-app="myapp">

<div>
<label>Name:</label>
<input type="text" ng-model="yourName" placeholder="Enter a name here">
<hr>
<h1>Hello {{yourName}}!...</h1>
</div>

  <div ng-controller="MyController" >
    <button ng-click="myData.doClick(item, $event)">Send AJAX Request</button>
    <br/>
    Data from server: {{myData.fromServer}}
  </div>

  <script>
    angular.module("myapp", [])
        .controller("MyController", function($scope, $http) {
            $scope.myData = {};
            $scope.myData.doClick = function(item, event) {

                var responsePromise = $http.get("/question-web/app/kfc/brands/kfc-kampar");

                responsePromise.success(function(data, status, headers, config) {
                    $scope.myData.fromServer = data.name;
                });
                responsePromise.error(function(data, status, headers, config) {
                    alert("AJAX failed!");
                });
            }

        } );
  </script>
  
  <br/><br/>
  	<form method="POST" enctype="multipart/form-data"
		action="/question-web/app/questions/getFirstQuestion/1111/inputFile">
		File to upload: <input type="file" name="file"><br /> 
		<br /> <input type="submit"
			value="Upload"> Press here to upload the file!
	</form>
  
<br/><br/><br/>
<ul>
<li><a href="${pageContext.request.contextPath}/app/question/welcome">Memorize</href>
<li><a href="${pageContext.request.contextPath}/app/question/welcome">Practice Question</href>
</ul>
  
</body>
</html>
