package authority_control.plugin.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import authority_control.plugin.events.Event;

public class Core extends JavaPlugin {

	@Override
	public void onEnable() {
		new Event(this);
		authority_control.plugin.tool.meta.Meta.Player_metaEXP(this);
		authority_control.plugin.tool.Permission.Permission_plugin(this);
		authority_control.plugin.tool.json.Json.Json_plugin(this);

		System.out.println("Authority_Control Start Power by WP ");//Enadle

		authority_control.plugin.main.Option.roadOption();
	}

	@Override
	public void onDisable() {
		System.out.println("Authority_Control Stop");//Disable
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("acs")) {
			//		sender.sendMessage("長さ:" + args.length);

			if (args.length == 0) {
				sender.sendMessage("USE /acs help");
			} else {

				//subcmd

				if (args[0].equalsIgnoreCase("set")) {

					if (sender.isOp()) {

						if (args.length == 3 && Pattern.compile("^-?[0-9]+$").matcher(args[2]).find()) {

							Player target = getServer().getPlayerExact(args[1]);

							int level = 0;

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
					}
				}

				if (args[0].equalsIgnoreCase("view")) {

					if (args.length == 2) {

						authority_control.plugin.tool.json.Json.view(sender, args[1]);

					} else {
						if (sender instanceof Player) {
							authority_control.plugin.tool.json.Json.view(sender, sender.getName());
						} else {
							sender.sendMessage("/acs view [ターゲット]");
						}
					}

				}

				if (args[0].equalsIgnoreCase("help")) {

					sender.sendMessage(ChatColor.YELLOW + "--------------------------------------");

					sender.sendMessage("/acs set [Player] [new Level]");
					sender.sendMessage("/acs view [Player]");
					//				sender.sendMessage("/acs ");

					sender.sendMessage(ChatColor.YELLOW + "--------------------------------------");

				}
				if (args[0].equalsIgnoreCase("optionset")) {

					if (sender.isOp()) {

						if (args.length == 3 && Pattern.compile("^-?[0-9]+$").matcher(args[2]).find()) {

							int level = 0;
							try {
								level = Integer.parseInt(args[2]);
								authority_control.plugin.tool.json.Json.Update(sender, args[1], level);
							} catch (NumberFormatException e) {
								sender.sendMessage("権限値が巨大すぎます。");
							}
						} else {
							sender.sendMessage("/acs set [ターゲット] [新しい権限値]");
						}
					}
				}

				if (args[0].equalsIgnoreCase("menu")) {
					if (sender instanceof Player) {//PlayerOnly
						authority_control.plugin.tool.inventory.Menus.setMenu((Player) sender);
					}
				}

			}
		}
		return false;

	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (args.length == 1)
			if (cmd.getName().equalsIgnoreCase("acs")) {

				ArrayList<String> subcmd = new ArrayList<String>();
				subcmd.add("view");
				subcmd.add("set");
				subcmd.add("help");
				subcmd.add("optionset");
				final List<String> completions = new ArrayList<>();

				StringUtil.copyPartialMatches(args[0], subcmd, completions);
				Collections.sort(completions);
				return completions;

			}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("optionset")) {
				Player target = getServer().getPlayerExact(sender.getName());
				int onetimekey = (int) (Math.random() * 999999);

				ArrayList<String> subcmd = new ArrayList<String>();
				subcmd.add("BREAK_PLACE");
				subcmd.add("FLY");
				subcmd.add("WORLDEDIT");
				subcmd.add("CHANGE_GAMEMODE");
				subcmd.add("FIRST_SPAWNPOINTMODE");
				if (!(target == null))
					subcmd.add("one time key:" + onetimekey);

				final List<String> completions = new ArrayList<>();

				StringUtil.copyPartialMatches(args[1], subcmd, completions);
				Collections.sort(completions);

				if (!(target == null))
					authority_control.plugin.tool.Random.setRandom(target);
				return completions;

			}
		}

		return null;
	}

	void message(int errID) {

	}

}
