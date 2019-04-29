package action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.DeptDAO;
import DAO.EmpDAO;

public class EmpAddLogic implements CommonLogic {
	private DeptDAO deptDao = new DeptDAO();
	private EmpDAO empDao = new EmpDAO();
	private HashMap<Integer, String> deptMap = deptDao.getDeptMap();
	private HashMap<String, String> argMap = new HashMap<>();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if (msg.size() == 0) {
			try {
				Part part = request.getPart("file");
				InputStream is = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[128];
				int len;
				//インプットストリームから最大128byte読み込む
				while ((len = is.read(buf)) > 0) {
					baos.write(buf, 0, len);//buf先頭からlenまでbaosに書き込む
				}
				baos.close();//クローズしても中身を取り出せる
				if (empDao.addEmp(argMap, baos.toByteArray())) {
					msg.add("社員情報を新規追加しました。");
					request.setAttribute("msg", msg);
					return "Result.jsp";
				} else {
					msg.add("社員情報の追加に失敗しました。");
					request.setAttribute("msg", msg);
					return "Result.jsp";
				}
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				throw new RuntimeException(e);
			} catch (ServletException e) {
				// TODO 自動生成された catch ブロック
				throw new RuntimeException(e);
			}
		} else {
			request.setAttribute("msg", msg);
			return "Result.jsp";
		}
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		msg.clear();
		//name
		String name = request.getParameter("name");
		if (isEmpty(name)) {
			msg.add("名前を入力してください。");
		} else {
			argMap.put("name", name);
		}

		//age
		String age = request.getParameter("age");
		if (isNum(age)) {
			argMap.put("age", age);
		} else {
			msg.add("年齢を半角数字で入力してください。");
		}

		//sex
		String sex = request.getParameter("sex");
		if (isSex(sex)) {
			argMap.put("sex", sex);
		} else {
			msg.add("性別をラジオボタンから選択してください。");
		}

		//file
		try {
			Part part = request.getPart("file");
			if(isEmpty(part.getSubmittedFileName())) {
				msg.add("画像ファイルを選択してください。");
			}else {
				argMap.put("content_type", part.getContentType());
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		//postcode
		String postcode = request.getParameter("postcode");
		if (isPostcode(postcode)) {
			argMap.put("postcode", postcode);
		} else {
			msg.add("郵便番号は半角数字(xxx-xxxx)で入力してください。");
		}

		//pref
		String pref = request.getParameter("pref");
		if (isPref(pref)) {
			argMap.put("pref", pref);
		} else {
			msg.add("都道府県をプルダウンメニューから選択してください。");
		}

		//city
		String city = request.getParameter("city");
		if (isEmpty(city)) {
			msg.add("住所(市町村)を入力してください。");
		} else {
			argMap.put("city", city);
		}

		//deptId
		String deptId = request.getParameter("deptId");
		if (isNum(deptId)) {
			int i = Integer.parseInt(deptId);
			if (deptMap.containsKey(i)) {
				argMap.put("deptId", deptId);
			} else {
				msg.add("存在しない部署IDが選択されました。");
			}
		} else {
			msg.add("部署IDの値が不適切です。");
		}

		//join
		String join = request.getParameter("join");
		if (isDate(join)) {
			argMap.put("join", join);
		} else {
			msg.add("入社日の値が不適切です。");
		}

		//leave
		String leave = request.getParameter("leave");
		if (isDate(leave)) {
			argMap.put("leave", leave);
		} else {
			msg.add("退社日の値が不適切です。");
		}
	}

}
