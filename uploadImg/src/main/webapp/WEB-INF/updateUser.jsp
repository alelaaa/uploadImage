<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseUri" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<title>修改用户的信息</title>
<script type="text/javascript">
    function showPreview(source) {
      var file = source.files[0];
      if(window.FileReader) {
          var fr = new FileReader();
          console.log(fr);
          var portrait = document.getElementById('portrait');
          fr.onloadend = function(e) {
            portrait.src = e.target.result;
          };
          fr.readAsDataURL(file);
          portrait.style.display = 'block';
      }
    }
</script>   
</head>
<body>
<h1>修改用户信息</h1>
<form action="updateUser" method="post" enctype="multipart/form-data">
		用户名称：
			<input type="text" name="userName" value="${user.userName }"/><br/><br/>
		原始图片：
		<img src="${baseUri}/upload/${user.userImg}" width="100px" height="100px"/><br/><br/>
		<input type="file" name="file" onchange="showPreview(this)" /><br/><br/>
    	<img id="portrait" src="" style="display:none;width: 100px;height:100px;" /><br/><br/>
    	<input type="hidden" name="id" value="${user.id}"/><!-- 隐藏 -->
		<input type="submit" value="提交">
	</form>

</body>
</html>