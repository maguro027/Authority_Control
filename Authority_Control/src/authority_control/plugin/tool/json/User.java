package authority_control.plugin.tool.json;

public class User {

	private String uuid; // uuID
	private String name; // ユーザ名
	private int level;//権限レベル
	private String memo;//memo

	public User() {
	}

	public User(String uuid, String name, int level, String memo) {
		this.uuid = uuid;
		this.name = name;
		this.level = level;
		this.memo = memo;
	}

	public String getId() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public String getMemo() {
		return memo;
	}

	public void setUuid(String uuid) {//
		this.uuid = uuid;
	}

	public void setName(String name) {//
		this.name = name;
	}

	public void setLevel(int level) {//
		this.level = level;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String toString() {
		return "ID=" + uuid + ", NAME=" + name + ", LEVEL=" + level + ", MEMO=" + memo;
	}
}