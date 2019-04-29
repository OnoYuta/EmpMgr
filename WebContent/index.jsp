<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員情報管理ツール</title>
</head>
<body>
	<h2>トップメニュー</h2>
	<div class='line'></div>
	<ul>
		<li><a href="./EmpMgr?action=action.EmpListLogic">社員情報一覧</a></li>
		<li><a href="./EmpMgr?action=action.EmpSearchLogic">社員情報検索</a></li>
		<li><a href="./EmpMgr?action=action.EmpEditLogic">社員情報新規追加</a></li>
		<li><a href="./EmpMgr?action=action.DeptListLogic">部署情報一覧</a></li>
		<li><a href="./EmpMgr?action=action.DeptEditLogic">部署情報新規追加</a></li>
	</ul>

</body>
</html>