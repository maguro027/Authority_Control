package authority_control.plugin.main;

import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import authority_control.plugin.events.Event;

public class Core extends JavaPlugin {

	@Override
	public void onEnable() {
		new Event(this);
		authority_control.plugin.tool.meta.Meta.Player_metaEXP(this);
		authority_control.plugin.tool.Permission.Permission_plugin(this);

		System.out.println("Authority_Control Start Power WP ");//Enadle
		authority_control.plugin.main.Option.roadOption();
	}

	@Override
	public void onDisable() {
		System.out.println("Authority_Control Stop");//Disable
	}
	


	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		//		sender.sendMessage("長さ:" + args.length);

		if (args.length == 0) {
			sender.sendMessage("USE /acs help");
		} else {

			//subcom

			if (args[0].equalsIgnoreCase("set")) {

				if (sender.isOp()) {

					if (!(args.length > 3)) {

						Player target = getServer().getPlayerExact(args[1]);

						int level = 0;

						if (Pattern.compile("^-?[0-9]+$").matcher(args[2]).find()) {
							try {
								level = Integer.parseInt(args[2]);

								if (!(target == null)) {

									authority_control.plugin.tool.json.Json.Update(sender, target, level);

								} else {

									authority_control.plugin.tool.json.Json.Update(sender, args[1], level);

								}
							} catch (NumberFormatException e) {

								sender.sendMessage("権限値が巨大すぎます。");

							}
						} else {
							sender.sendMessage("/acs set [ターゲット] [新しい権限値]");
						}
					} else {
						sender.sendMessage("/acs set [ターゲット] [新しい権限値]");
					}
				}
			}

			if (args[0].equalsIgnoreCase("view")) {

				if (args.length == 2) {

					authority_control.plugin.tool.json.Json.view(sender, args[1]);

				} else {

					sender.sendMessage("/acs view [ターゲット]");
					//
				}

			}
			if (args[0].equalsIgnoreCase("help")) {

				sender.sendMessage(ChatColor.YELLOW + "---------------------------------------");

				sender.sendMessage("/acs set [Player] [new Level]");
				sender.sendMessage("/acs view [Player]");
				//				sender.sendMessage("/acs ");

				sender.sendMessage(ChatColor.YELLOW + "---------------------------------------");

			}

		}
		return false;

	}

	void message(int errID) {

	}
	
	

}
