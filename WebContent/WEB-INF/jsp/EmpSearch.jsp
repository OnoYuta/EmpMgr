<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員情報管理ツール</title>
</head>
<body>
<h2>社員情報検索</h2>
	<form action="./EmpMgr" method="post">
	<input type="hidden" name="action" value="action.EmpListLogic">
		<p>
			<label>所属部署： <select name="deptId">
			<option value="">指定しない</option>
					<c:forEach var="dept" items="${deptMap}">
						<option value="${dept.key}">${dept.value}</option>
					</c:forEach>
			</select></label>
		</p>
		<p>
			<label>社員番号：<select name="empId">
			<option value="">指定しない</option>
					<c:forEach var="key" items="${keyList}">
						<option value="${key}">${key}</option>
					</c:forEach>
			</select></label>
		</p>
		<p>
			<label>名前に含む文字：<input type="text" name="name"></label>
		</p>
		<input type="submit" value="検索">
	</form>
	<input type="button" onclick="location.href='./EmpMgr'" value="トップページ"><br>
</body>
</html>