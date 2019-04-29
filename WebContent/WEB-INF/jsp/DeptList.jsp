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
	<h2>部署一覧</h2>
	<table>
		<c:choose>
			<c:when test="${deptMap.size() != 0}">
				<tr>
					<th>部署ID</th>
					<th>部署名</th>
				</tr>
				<c:forEach var="key" items="${keyList}">
					<tr>
						<th>${key}</th>
						<th>${deptMap[key]}</th>
						<th>
							<form action="./EmpMgr" method="post">
								<input type="hidden" name="deptId" value="${key}"> <input
									type="hidden" name="action" value="action.DeptEditLogic">
								<input type="submit" value="編集">
							</form>
						</th>
						<th>
							<form action="./EmpMgr" method="post">
								<input type="hidden" name="deptId" value="${key}"> <input
									type="hidden" name="action" value="action.DeptDeleteLogic">
								<input type="submit" value="削除">
							</form>
						</th>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>現在、登録されている部署はありません。</p>
			</c:otherwise>
		</c:choose>

	</table>
	<input type="button"
		onclick="location.href='./EmpMgr?action=action.DeptEditLogic'"
		value="新規追加">
	<input type="button" onclick="location.href='./EmpMgr'" value="トップページ"><br>
	<c:forEach var="msg" items="${msg}">
		${msg}<br>
	</c:forEach>
</body>
</html>