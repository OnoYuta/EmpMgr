package action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmpDAO;
import bean.Emp;

public class FileDownloadLogic implements CommonLogic{

	private EmpDAO dao = new EmpDAO();
	private HashMap<String, String> keywords = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
            put("name","%");
            put("deptId","%");
            put("empId","%");
        }
    };

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		//検索条件に一致する社員情報を取得
		ArrayList<Emp> empList = dao.getAllEmpList(keywords);
		//csvファイル形式に変換
		StringBuilder csv = new StringBuilder();
		csv.append(Emp.getHeader() + "\n");
		for(Emp emp:empList) {
			csv.append(emp.toString() + "\n");
		}
		request.setAttribute("csv", csv.toString());

		return "FileDownload.jsp";
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		String paramName = request.getParameter("name");
		if (paramName != null) {
			if (!isEmpty(paramName)) {
				keywords.put("name", "%" + paramName + "%");
			}
		}

		String paramDeptId = request.getParameter("deptId");
		if (paramDeptId != null) {
			if (isNum(paramDeptId)) {
				keywords.put("deptId", paramDeptId);
			}
		}

		String paramEmpId = request.getParameter("empId");
		if (paramEmpId != null) {
			if (isNum(paramEmpId)) {
				keywords.put("empId", paramEmpId);
			}
		}
	}

}
