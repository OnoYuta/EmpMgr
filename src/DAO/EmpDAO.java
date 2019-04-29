package DAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import bean.Emp;
import bean.IMG;

public class EmpDAO extends DAO {
	private Statement st;

	//社員一覧を取得
	public HashMap<Integer, String> getEmpMap() {
		getConn();
		String sql = "SELECT E_ID,NAME FROM EMPLOYEE";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			HashMap<Integer, String> empMap = new HashMap<>();
			while (rs.next()) {
				empMap.put(rs.getInt("E_ID"), rs.getString("NAME"));
			}
			ps.close();
			closeConn();
			return empMap;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConn();
			return null;
		}
	}

	//社員情報検索
	public HashMap<Integer, String> getEmpMap(HashMap<String, String> keys) {
		getConn();
		String sql = "SELECT EMPLOYEE.E_ID,NAME FROM EMPLOYEE "
				+ "JOIN ID_TABLE ON EMPLOYEE.E_ID = ID_TABLE.E_ID "
				+ "WHERE NAME LIKE ? AND EMPLOYEE.E_ID LIKE ? AND D_ID LIKE ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, keys.get("name"));
			ps.setString(2, keys.get("empId"));
			ps.setString(3, keys.get("deptId"));
			ResultSet rs = ps.executeQuery();
			HashMap<Integer, String> empMap = new HashMap<>();
			while (rs.next()) {
				empMap.put(rs.getInt("E_ID"), rs.getString("NAME"));
			}
			closeConn();
			return empMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//社員情報検索(社員Idから全情報を取得)
	public ArrayList<Emp> getAllEmpList(HashMap<String, String> keys) {
		getConn();
		String sql = "SELECT * FROM EMPLOYEE "
				+ "JOIN ID_TABLE ON EMPLOYEE.E_ID = ID_TABLE.E_ID "
				+ "JOIN ADDRESS ON ID_TABLE.A_ID = ADDRESS.A_ID "
				+ "JOIN DEPARTMENT ON ID_TABLE.D_ID = DEPARTMENT.D_ID "
				+ "JOIN IMAGE ON ID_TABLE.I_ID = IMAGE.I_ID "
				+ "WHERE NAME LIKE ? AND EMPLOYEE.E_ID LIKE ? AND ID_TABLE.D_ID LIKE ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, keys.get("name"));
			ps.setString(2, keys.get("empId"));
			ps.setString(3, keys.get("deptId"));
			ResultSet rs = ps.executeQuery();
			ArrayList<Emp> empList = new ArrayList<>();
			while (rs.next()) {
				Emp emp = new Emp();
				emp.setEmpId(rs.getString("E_ID"));
				emp.setName(rs.getString("NAME"));
				emp.setAge(rs.getString("AGE"));
				emp.setSex(rs.getString("SEX"));
				emp.setImgId(rs.getString("I_ID"));
				emp.setJoin(rs.getString("J_DATE"));
				emp.setLeave(rs.getString("J_DATE"));
				emp.setDept(rs.getString("D_NAME"));
				emp.setPostcode(rs.getString("POSTCODE"));
				emp.setPref(rs.getString("PREF"));
				emp.setCity(rs.getString("CITY"));
				empList.add(emp);
			}
			ps.close();
			return empList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConn();
		}
	}

	//社員情報検索(社員Idから全情報を取得)
	public HashMap<String, String> getEmpById(String empId) {
		getConn();
		String sql = "SELECT * FROM EMPLOYEE "
				+ "JOIN ID_TABLE ON EMPLOYEE.E_ID = ID_TABLE.E_ID "
				+ "JOIN ADDRESS ON ID_TABLE.A_ID = ADDRESS.A_ID "
				+ "JOIN DEPARTMENT ON ID_TABLE.D_ID = DEPARTMENT.D_ID "
				+ "JOIN IMAGE ON ID_TABLE.I_ID = IMAGE.I_ID "
				+ "WHERE EMPLOYEE.E_ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ResultSet rs = ps.executeQuery();
			HashMap<String, String> emp = new HashMap<>();
			if (rs != null && rs.next()) {
				emp.put("empId", rs.getString("E_ID"));
				emp.put("name", rs.getString("NAME"));
				emp.put("age", rs.getString("AGE"));
				emp.put("sex", rs.getString("SEX"));
				emp.put("join", rs.getString("J_DATE"));
				emp.put("leave", rs.getString("J_DATE"));
				emp.put("empId", rs.getString("E_ID"));
				emp.put("deptId", rs.getString("D_ID"));
				emp.put("postcode", rs.getString("POSTCODE"));
				emp.put("pref", rs.getString("PREF"));
				emp.put("city", rs.getString("CITY"));
				emp.put("deptName", rs.getString("D_NAME"));
				emp.put("imgId", rs.getString("I_ID"));
				emp.put("addId", rs.getString("A_ID"));
			}
			ps.close();
			closeConn();
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//社員の削除
	public boolean deleteEmp(int empId) {
		getConn();
		String sql = "DELETE FROM EMPLOYEE WHERE E_ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}
			ps.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConn();
			return false;
		}
	}

	//インサートを実行した直後にAIで生成されたIdを取得するメソッド
	public int executeAndGetId(PreparedStatement ps) throws SQLException {
		//インサートを実行
		int result = ps.executeUpdate();
		if (result != 1) {
			return -1;
		}
		//直前に生成されたIDを取得
		ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
		int Id = 0;
		if (rs != null && rs.next()) {
			Id = rs.getInt("LAST");
		}
		return Id;
	}

	public boolean addEmp(HashMap<String, String> map, byte[] img) {
		try {
			getConn();
			// 自動コミットモードを解除
			conn.setAutoCommit(false);
			try {
				st = conn.createStatement();
				//insEmp
				String insEmp = "INSERT INTO EMPLOYEE(NAME,AGE,SEX,J_DATE,L_DATE) VALUES(?,?,?,?,?)";
				PreparedStatement psInsEmp = conn.prepareStatement(insEmp);
				psInsEmp.setString(1, map.get("name"));
				psInsEmp.setString(2, map.get("age"));
				psInsEmp.setString(3, map.get("sex"));
				psInsEmp.setString(4, map.get("join"));
				psInsEmp.setString(5, map.get("leave"));
				int empId = executeAndGetId(psInsEmp);
				psInsEmp.close();

				//insAdd
				String insAdd = "INSERT INTO ADDRESS(POSTCODE,PREF,CITY) VALUES(?,?,?)";
				PreparedStatement psInsAdd = conn.prepareStatement(insAdd);
				psInsAdd.setString(1, map.get("postcode"));
				psInsAdd.setString(2, map.get("pref"));
				psInsAdd.setString(3, map.get("city"));
				int addId = executeAndGetId(psInsAdd);
				psInsAdd.close();

				//insImg
				String insImg = "INSERT INTO IMAGE(IMG,CONTENT_TYPE) VALUES(?,?)";
				PreparedStatement psInsImg = conn.prepareStatement(insImg);
				psInsImg.setBinaryStream(1, new ByteArrayInputStream(img));
				psInsImg.setString(2, map.get("content_type"));
				int imgId = executeAndGetId(psInsImg);
				psInsImg.close();

				//Idを正しく取得できているかチェック
				int deptId = Integer.parseInt(map.get("deptId"));
				if (empId == 0 || deptId == 0 || imgId == 0 || addId == 0) {
					return false;
				}

				//insId
				String insId = "INSERT INTO ID_TABLE(E_ID,D_ID,I_ID,A_ID) VALUES(?,?,?,?)";
				PreparedStatement psInsId = conn.prepareStatement(insId);
				psInsId.setInt(1, empId);
				psInsId.setInt(2, deptId);
				psInsId.setInt(3, imgId);
				psInsId.setInt(4, addId);
				int result = psInsId.executeUpdate();
				if (result != 1) {
					return false;
				}

				// コミットを行い処理を確定する
				conn.commit();
				st.close();
				psInsId.close();
				return true;

			} catch (SQLException e) {
				// 途中でエラーが発生した場合は、ロールバックを行なう
				conn.rollback();
				throw e;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConn();
		}
	}

	public IMG getImg(HashMap<String, String> map) {
		getConn();
		String sql = "SELECT * FROM IMAGE "
				+ "WHERE I_ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, map.get("imgId"));
			ResultSet rs = ps.executeQuery();
			IMG img = null;
			if (rs != null && rs.next()) {
				img = new IMG();
				img.setContent_type(rs.getString("CONTENT_TYPE"));
				InputStream is = rs.getBinaryStream("IMG");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[128];
				int len;
				//インプットストリームから最大128byte読み込む
				while ((len = is.read(buf)) > 0) {
					baos.write(buf, 0, len);//buf先頭からlenまでbaosに書き込む
				}
				baos.close();//クローズしても中身を取り出せる
				img.setImg(baos.toByteArray());
			}
			ps.close();

			return img;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} finally {
			closeConn();
		}
	}

	public boolean updateEmp(HashMap<String, String> map, byte[] img) {
		try {
			getConn();
			// 自動コミットモードを解除
			conn.setAutoCommit(false);
			int result;
			try {
				st = conn.createStatement();
				//upEmp
				String upEmp = "UPDATE EMPLOYEE SET "
						+ "NAME = ?,"
						+ "AGE = ?,"
						+ "SEX = ?,"
						+ "J_DATE = ?,"
						+ "L_DATE = ? "
						+ "WHERE E_ID = ?";
				PreparedStatement psUpEmp = conn.prepareStatement(upEmp);
				psUpEmp.setString(1, map.get("name"));
				psUpEmp.setString(2, map.get("age"));
				psUpEmp.setString(3, map.get("sex"));
				psUpEmp.setString(4, map.get("join"));
				psUpEmp.setString(5, map.get("leave"));
				psUpEmp.setString(6, map.get("empId"));
				result = psUpEmp.executeUpdate();
				if (result != 1) {
					return false;
				}
				psUpEmp.close();

				//upAdd
				String upAdd = "UPDATE ADDRESS SET "
						+ "POSTCODE = ?,"
						+ "PREF = ?,"
						+ "CITY = ? "
						+ "WHERE A_ID = ?";
				PreparedStatement psUpAdd = conn.prepareStatement(upAdd);
				psUpAdd.setString(1, map.get("postcode"));
				psUpAdd.setString(2, map.get("pref"));
				psUpAdd.setString(3, map.get("city"));
				psUpAdd.setString(4, map.get("addId"));
				result = psUpAdd.executeUpdate();
				if (result != 1) {
					return false;
				}
				psUpAdd.close();

				//upImg
				if (!map.get("fileName").equals("")) {
					String upImg = "UPDATE IMAGE SET "
							+ "IMG = ?,"
							+ "CONTENT_TYPE = ? "
							+ "WHERE I_ID = ?";
					PreparedStatement psUpImg = conn.prepareStatement(upImg);
					psUpImg.setBinaryStream(1, new ByteArrayInputStream(img));
					psUpImg.setString(2, map.get("content_type"));
					psUpImg.setString(3, map.get("imgId"));
					result = psUpImg.executeUpdate();
					if (result != 1) {
						return false;
					}
					psUpImg.close();
				}
				// コミットを行い処理を確定する
				conn.commit();
				st.close();
				return true;

			} catch (SQLException e) {
				// 途中でエラーが発生した場合は、ロールバックを行なう
				conn.rollback();
				throw e;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConn();
		}
	}

	//各種Id取得(社員Idから検索)
	public HashMap<String,String> getId(String empId) {
		getConn();
		String sql = "SELECT * FROM ID_TABLE WHERE E_ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ResultSet rs = ps.executeQuery();
			HashMap<String,String> id = new HashMap<>();
			while (rs.next()) {
				id.put("empId", rs.getString("E_ID"));
				id.put("deptId", rs.getString("D_ID"));
				id.put("addId", rs.getString("A_ID"));
				id.put("imgId", rs.getString("I_ID"));
			}
			ps.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConn();
		}
	}

}
