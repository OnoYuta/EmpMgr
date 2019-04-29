package enumeration;

public enum Sex {
	MAN("男性"),
	WOMAN("女性");
	private String str;
	private Sex(String str) {
		this.str = str;
	}
	public String getStr() {
		return str;
	}
}
