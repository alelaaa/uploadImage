<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseUri" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<title>首页</title>
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
<h1>测试文件上传</h1>
	<table border="1" style="width: 60%">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>图片名称</th>
			<th>图片</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${requestScope.userList}" var="userList">
			<tr>
				<td>${userList.id}</td>
				<td>${userList.userName}</td>
				<td>${userList.userImg}</td>
				<td><img src="../${userList.userImg}" width="100px" height="100px"/></td>
				<td>
					<a href="${baseUri}/deleteUser?id=${userList.id}" onclick="return confirm('确认删除？')">删除</a>&nbsp;&nbsp;
					<a href="${baseUri}/toUpdateUser?id=${userList.id}">修改</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<h1>添加用户</h1>
	<form action="addUser" method="post" enctype="multipart/form-data">
		用户名称：
			<input type="text" name="userName"/><br/><br/>
		图片：
		<input type="file" name="file" onchange="showPreview(this)" /><br/><br/>
    	<img id="portrait" src="" style="display:none;width: 100px;height:100px;" /><br/><br/>
		<input type="submit" value="提交">
	</form>
	
	
</body>
</html>