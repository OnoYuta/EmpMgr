package bean;

import java.lang.reflect.Field;

public class Emp {

	private String empId;
	private String name;
	private String age;
	private String sex;
	private String imgId;
	private String postcode;
	private String pref;
	private String city;
	private String dept;
	private String join;
	private String leave;

	public Emp() {}

	public Emp(String empId, String name, String age, String sex, String imgId, String postcode, String pref, String city,
			String dept, String join, String leave) {
		super();
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.imgId = imgId;
		this.postcode = postcode;
		this.pref = pref;
		this.city = city;
		this.dept = dept;
		this.join = join;
		this.leave = leave;
	}

	static public String getHeader() {
		Class<?> clazz = null;
		StringBuilder sb = new StringBuilder();
		try {
			//getClass()の使い方わからなかった
			clazz = Class.forName("bean.Emp");
			for (Field f : clazz.getDeclaredFields()) {
				sb.append(f.getName() + ",");
			}
		} catch (ClassNotFoundException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//最後の","を削除する
		sb.delete(sb.lastIndexOf(","), sb.length());
		return sb.toString();
	}

	@Override
	public String toString() {
		return empId + "," + name + "," + age + "," + sex + "," + imgId + "," + postcode + "," + pref + "," + city + "," + dept + ","
				+ join + "," + leave;
	}

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPref() {
		return pref;
	}
	public void setPref(String pref) {
		this.pref = pref;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJoin() {
		return join;
	}
	public void setJoin(String join) {
		this.join = join;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}

}
