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
	<c:choose>
		<c:when test="${deptId == null}">
			<h2>部署の新規追加</h2>
			<form action="./EmpMgr" method="post">
				<p>
					<label>部署名：<input type="text" name="deptName"></label>
				</p>
				<input type="hidden" name="action" value="action.DeptAddLogic">
				<input type="submit" value="新規追加">
			</form>
		</c:when>
		<c:otherwise>
			<h2>部署の編集</h2>
			<form action="./EmpMgr" method="post">
				<input type="hidden" name="deptId" value="${deptId}">
				<p>部署ID：${deptId}</p>
				<p>
					<label>部署名：<input type="text" name="deptName"
						value="${deptName}"></label>
				</p>
				<input type="hidden" name="action" value="action.DeptUpdateLogic">
				<input type="submit" value="更新">
			</form>
		</c:otherwise>
	</c:choose>

	<input type="button" onclick="location.href='./EmpMgr?action=action.DeptListLogic'" value="部署一覧ページに戻る">
	<input type="button" onclick="location.href='./EmpMgr'" value="トップページ"><br>

	<c:forEach var="m" items="${msg}">
		<p>${m}</p>
	</c:forEach>
</body>
</html>