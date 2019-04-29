package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeptDAO;
import DAO.EmpDAO;
import enumeration.Pref;
import enumeration.Sex;

public class EmpEditLogic implements CommonLogic{
	private DeptDAO deptDAO = new DeptDAO();
	private HashMap<Integer, String> deptMap = deptDAO.getDeptMap();
	private EmpDAO empDAO = new EmpDAO();
	private HashMap<Integer, String> empMap = empDAO.getEmpMap();
	String empId;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sexList", Sex.values());
		request.setAttribute("deptMap", deptMap);
		request.setAttribute("prefList", Pref.values());
		if(empId != null) {
			HashMap<String,String> emp = empDAO.getEmpById(empId);
			request.setAttribute("emp", emp);
		}
		return "EmpEdit.jsp";
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("empId");
		if(param != null) {
			int i = Integer.parseInt(param);
			if(empMap.containsKey(i)) {
				empId = param;
			}
		}
	}
}
