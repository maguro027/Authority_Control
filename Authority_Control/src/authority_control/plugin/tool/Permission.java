package authority_control.plugin.tool;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Permission {
	static Plugin plugin;

	public static void Permission_plugin(Plugin plugindata) {
		plugin = plugindata;
	}

	public static void ifFly(Player player) {
		if (authority_control.plugin.tool.json.ViewLv.getLv(player) >= authority_control.plugin.events.Event
				.getFLY_Lv()) {
			player.setAllowFlight(true);
			player.sendMessage("フライ可能！(スペースキーを２回押してください。");
		}

	}

	public static void ifWorldEdit(Player player) {
		if (authority_control.plugin.tool.json.ViewLv.getLv(player) >= authority_control.plugin.events.Event
				.getWorldEdit_Lv()) {

			player.addAttachment(plugin, "worldedit.*", true);


		} else {

			player.addAttachment(plugin, "worldedit.*", false);

		}

	}

}
