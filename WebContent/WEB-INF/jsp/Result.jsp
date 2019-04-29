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
<h2>登録・更新処理の結果</h2>
	<c:forEach var="msg" items="${msg}">
		${msg}<br>
	</c:forEach>
	<input type="button" onclick="location.href='./EmpMgr'" value="トップページ">
	<input type="button" onclick="location.href='./EmpMgr?action=action.EmpListLogic'" value="社員一覧">
	<input type="button" onclick="location.href='./EmpMgr?action=action.DeptListLogic'" value="部署一覧">
</body>
</html>