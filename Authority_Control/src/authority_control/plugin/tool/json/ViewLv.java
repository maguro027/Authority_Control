package authority_control.plugin.tool.json;

import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class ViewLv {

	public static int getLv(Player player) {

		int Lv = 0;

		for (MetadataValue M : player.getMetadata("M.")) {

			if (M.asString() == "MSYS.Player") {

				for (MetadataValue mname : player.getMetadata("AUTHORITY_LEVEL.")) {
					Lv = mname.asInt();

				}
			}
		}
		return Lv;
	}

	public static String getMemo(Player player) {

		String Lv = null;

		for (MetadataValue M : player.getMetadata("M.")) {

			if (M.asString() == "MSYS.Player") {

				for (MetadataValue mname : player.getMetadata("AUTHORITY_MEMO.")) {
					Lv = mname.asString();

				}
			}
		}
		return Lv;
	}

}
