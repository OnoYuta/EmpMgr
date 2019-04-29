package action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeptDAO;
import DAO.EmpDAO;

public class EmpSearchLogic implements CommonLogic{
	private DeptDAO deptDAO = new DeptDAO();
	private HashMap<Integer, String> deptMap = deptDAO.getDeptMap();
	private EmpDAO empDAO = new EmpDAO();
	private HashMap<Integer, String> empMap = empDAO.getEmpMap();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("deptMap", deptMap);
		ArrayList<Integer> keyList = new ArrayList<>();
		for(Entry<Integer,String> entry:empMap.entrySet()) {
			keyList.add(entry.getKey());
		}
		Collections.sort(keyList);
		request.setAttribute("keyList", keyList);
		return "EmpSearch.jsp";
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		// TODO 自動生成されたメソッド・スタブ
	}

}
