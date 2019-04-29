<%@ page import="java.io.OutputStream,java.util.ArrayList"%>
<%
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename=\"downloadFile.txt\"");

	String csv = (String) request.getAttribute("csv");
	//出力内容確認用
	System.out.println(csv);

	OutputStream os = response.getOutputStream();
	byte[] downloadData = csv.getBytes();
	os.write(downloadData);
	os.close();
%>