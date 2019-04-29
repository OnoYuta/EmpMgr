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
	<h2>社員情報一覧</h2>
	<c:choose>
		<c:when test="${empMap.size() != 0}">
			<table>
				<tr>
					<th>社員ID</th>
					<th>名前</th>
				</tr>
				<c:forEach var="key" items="${keyList}">
					<tr>
						<td>${key}</td>
						<td>${empMap[key]}</td>
						<td>
							<form action="./EmpMgr" method="post">
								<input type="hidden" name="empId" value="${key}">
								<button type="submit" name="action"
									value="action.EmpEditLogic">編集</button>
							</form>
						</td>
						<td>
							<form action="./EmpMgr" method="post">
								<input type="hidden" name="empId" value="${key}">
								<button type="submit" name="action"
									value="action.EmpDeleteLogic">削除</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<p>条件に一致する検索結果はありませんでした。</p>
		</c:otherwise>
	</c:choose>
	<input type="button" onclick="location.href='./EmpMgr?action=action.EmpEditLogic'" value="新規追加">
	<input type="button" onclick="location.href='./EmpMgr?action=action.EmpSearchLogic'" value="社員検索">
	<input type="button" onclick="location.href='./Download?name=${keyword[name]}&deptId=${keyword[deptId]}&empId=${keyword[empId]}'" value="CSVファイルに出力">
	<input type="button" onclick="location.href='./EmpMgr'" value="トップページ"><br>

	<c:forEach var="msg" items="${msg}">
		${msg}<br>
	</c:forEach>
</body>
</html>