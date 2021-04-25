package authority_control.plugin.tool.meta;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class Meta {
	static Plugin plugin;

	public static void Player_metaEXP(Plugin plugindata) {
		plugin = plugindata;
	}

	private Player player;
	private int level = 0;
	private String memo = "";

	private final static String DATA_KEY = "M.";
	private final static String LEVEL = "AUTHORITY_LEVEL.";
	private final static String MEMO = "AUTHORITY_MEMO.";
	private final static String ONETIMEKEY = "AUTHORITY_ONETIMEKEY.";

	public Meta(Player player) {
		this.player = player;
	}

	public void setLevel(int i) {
		this.level = i;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setPlayerMeta() {

		if (!(player == null)) {

			player.setMetadata(
					DATA_KEY, // key
					new FixedMetadataValue(
							plugin, // プラグイン
							"MSYS.Player" // 設定したい値
					));

			player.setMetadata(
					LEVEL, // key
					new FixedMetadataValue(
							plugin, // プラグイン
							level // 設定したい値
					));
			player.setMetadata(
					MEMO, // key
					new FixedMetadataValue(
							plugin, // プラグイン
							memo // 設定したい値
					));
		}
	}

	public void updatePlayermemo(String str) {
		player.removeMetadata(
				MEMO, // key
				plugin // プラグイン
		);
		player.setMetadata(
				MEMO, // key
				new FixedMetadataValue(
						plugin, // プラグイン
						str // 設定したい値
				));
	}


	public void updatePlayerlavel() {
		player.removeMetadata(
				LEVEL, // key
				plugin // プラグイン
		);
		player.setMetadata(
				LEVEL, // key
				new FixedMetadataValue(
						plugin, // プラグイン
						level // 設定したい値
				));
	}

	public void setOnetime(int i) {
		player.removeMetadata(
				ONETIMEKEY, // key
				plugin // プラグイン
		);
		player.setMetadata(
				ONETIMEKEY, // key
				new FixedMetadataValue(
						plugin, // プラグイン
						i // 設定したい値
				));
	}
}
