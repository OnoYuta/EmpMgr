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
		<c:when test="${emp == null}">
			<h2>社員情報の新規追加</h2>
			<p>社員ID：自動的に生成されます</p>
		</c:when>
		<c:otherwise>
			<h2>社員情報の編集</h2>
			<p>社員ID：${emp.empId}</p>
		</c:otherwise>
	</c:choose>

	<form action="./EmpMgr" enctype="multipart/form-data" method="post">
	<input type="hidden" name="empId" value="${emp.empId}">
		<p>
			<label>名前：<input type="text" name="name" value="${emp.name}"></label>
		</p>
		<p>
			<label>年齢： <input type="text" name="age" value="${emp.age}"></label>
		</p>
		<p>
			性別：
			<c:forEach var="sex" items="${sexList}">
				<label> <input type="radio" name="sex" value="${sex}"
					<c:if test="${emp.sex == sex}">checked="checked"</c:if>>${sex.str}
				</label>
			</c:forEach>
		</p>
		<p>
			<c:choose>
				<c:when test="${emp != null}">
					写真:<br>
					<img src='./Img?id=${emp.imgId}'>
					<br>
				</c:when>
			</c:choose>
			<label>ファイル選択： <input type="file" name="file"
				accept="image/png,image/jpeg">
			</label>
		</p>
		<p>
			<label>郵便番号：<input type="text" name="postcode"
				placeholder="123-4567" value="${emp.postcode}"></label>
		</p>
		<p>
			都道府県: <select name="pref">
				<c:forEach var="pref" items="${prefList}">
					<option value="${pref}"
						<c:if test="${emp.pref == pref}">selected</c:if>>${pref}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<label>住所（市町村）：<input type="text" name="city"
				value="${emp.city}"></label>
		</p>

		<p>
			所属：
			<c:choose>
				<c:when test="${deptMap.size() != 0}">
					<select name="deptId">
						<c:forEach var="dept" items="${deptMap}">
							<option value="${dept.key}"
								<c:if test="${emp.deptId == dept.key}">selected</c:if>>${dept.value}</option>
						</c:forEach>
					</select>
				</c:when>
				<c:otherwise>
				現在、登録されている部署はありません。
			</c:otherwise>
			</c:choose>
		</p>
		<p>
			<label>入社日：<input type="date" name="join"
				value=<c:choose>
				<c:when test="${emp == null}">
					"2000-01-01"
				</c:when>
				<c:otherwise>
					"${emp.join}"
				</c:otherwise>
				</c:choose>>
			</label>
		</p>
		<p>
			<label>退社日：<input type="date" name="leave"
				value=<c:choose>
				<c:when test="${emp == null}">
					"2000-01-01"
				</c:when>
				<c:otherwise>
				"${emp.leave}"
				</c:otherwise>
				</c:choose>>
			</label>
		</p>

		<c:choose>
			<c:when test="${emp == null}">
				<input type="hidden" name="action" value="action.EmpAddLogic">
				<input type="submit" value="新規追加">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="action" value="action.EmpUpdateLogic">
				<input type="hidden" name="empId" value="${emp.empId}">
				<input type="submit" value="更新">
			</c:otherwise>
		</c:choose>
	</form>
	<input type="button"
		onclick="location.href='./EmpMgr?action=action.EmpListLogic'"
		value="社員一覧ページに戻る">
	<input type="button" onclick="location.href='./EmpMgr'" value="トップページ">
	<br>

	<c:forEach var="m" items="${msg}">
		<p>${m}</p>
	</c:forEach>
</body>
</html>