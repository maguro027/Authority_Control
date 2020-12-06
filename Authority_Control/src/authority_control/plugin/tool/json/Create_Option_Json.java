package authority_control.plugin.tool.json;

public class Create_Option_Json {

	private int BREAK_PLACE;
	private int FLY;
	private int WORLDEDIT;
	private int CHANGE_GAMEMODE;
	private int FIRST_SPAWNPOINTMODE;

	public Create_Option_Json(int i) {

		i = 0;
		this.BREAK_PLACE = 1;
		this.FLY = 1;
		this.WORLDEDIT = 1;
		this.CHANGE_GAMEMODE = 1;
		this.FIRST_SPAWNPOINTMODE = 0;

	}

	public void setBREAK_PLACE(int i) {
		this.BREAK_PLACE = i;
	}

	public void setFLY(int i) {
		this.FLY = i;
	}

	public void setWORLDEDIT(int i) {
		this.WORLDEDIT = i;
	}

	public void setCHANGE_GAMEMODE(int i) {
		this.CHANGE_GAMEMODE = i;
	}

	public void setFIRSTSPAWNPOINTMODE(int i) {
		this.FIRST_SPAWNPOINTMODE = i;
	}

	public String OPString() {
		return "BREAK_PLACE=" + BREAK_PLACE + ", FLY=" + FLY + ", WORLDEDIT=" + WORLDEDIT + ", CHANGE_GAMEMODE="
				+ CHANGE_GAMEMODE + ", FIRST_SPAWNPOINTMODE=" + FIRST_SPAWNPOINTMODE;

	}
}
