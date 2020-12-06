package authority_control.plugin.tool.json;

public class User {

	private String uuid; // uuID
	private String name; // ユーザ名
	private int level;//権限レベル

	public User() {
	}

	public User(String uuid, String name, int level) {
		this.uuid = uuid;
		this.name = name;
		this.level = level;

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

	public void setUuid(String uuid) {//
		this.uuid = uuid;
	}

	public void setName(String name) {//
		this.name = name;
	}

	public void setLevel(int level) {//
		this.level = level;
	}

	public String toString() {
		return "ID=" + uuid + ", NAME=" + name + ", LEVEL=" + level;
	}
}