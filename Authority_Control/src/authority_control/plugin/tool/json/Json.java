package authority_control.plugin.tool.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

import authority_control.plugin.tool.meta.Meta;

public class Json {
	static Plugin plugin;

	public static void Json_plugin(Plugin plugindata) {
		plugin = plugindata;
	}

	@SuppressWarnings("unchecked")
	public static void Join(Player p) {

		int level = 0;
		UUID uuid = null;

		Gson gson = new Gson();
		User user = new User(p.getUniqueId().toString(), p.getName(), level, null);

		//		String jstr = gson.toJson(user, user.getClass());
		//		System.out.println(jstr);

		String relativePath = "";

		String URL_ = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/";
		File file_ = new File(URL_);
		if (!(file_.exists()))
			file_.mkdir();

		String URL__ = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/Player/";
		File file__ = new File(URL__);
		if (!(file__.exists()))
			file__.mkdir();

		String URL = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/Player/"
				+ p.getName() + ".json";

		File file = new File(URL);
		if (file.exists()) {

			try {

				Gson ggson = new Gson();

				JsonElement json = ggson.fromJson(new FileReader(URL), JsonElement.class);

				Map<String, String> map = new HashMap<String, String>();
				map = (Map<String, String>) ggson.fromJson(json, map.getClass());

				Map<String, Double> mapp = new HashMap<String, Double>();
				mapp = (Map<String, Double>) ggson.fromJson(json, mapp.getClass());

				uuid = UUID.fromString(map.get("uuid"));

				double i = 0;

				i = mapp.get("level");
				level = (int) i;

				if (!(uuid.equals(p.getUniqueId()))) {
					System.out.println("警告");
					System.out.println("UUIDが一致しません");
					System.out.println("ユーザーをキックします");
					System.out.println("元のUUiD   :" + p.getUniqueId().toString());
					System.out.println("今回のUUID :" + uuid.toString());

					p.kickPlayer("UUIDが一致しません。");
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				//			} catch (IOException e) {
				e.printStackTrace();
				p.kickPlayer("未知のエラーが発生しました。"
						+ "プレーヤーデータを削除します。[Authority_Control]");
				System.out.println(p.getName() + "のデータにエラーがあります。"
						+ URL + ".jsonを削除しました。");

			}
		} else {
			System.out.println(p.getName() + "は初回ログインです");

			if (authority_control.plugin.main.Option.getFIRSTSPAWNPOINT() == 1) {

				////////////////////////////////////////////////////////////////////////////////////////////////////

			}

			try (Writer writer = new FileWriter(URL)) {

				gson = new GsonBuilder().create();
				gson.toJson(user, writer);

			} catch (IOException e) {

				e.printStackTrace();

			}
		}

		if (authority_control.plugin.tool.json.ViewLv.getLv(p) >= authority_control.plugin.events.Event
				.getWorldEdit_Lv())
			p.addAttachment(plugin, "worldedit.*", true);

		Meta pmeta = new Meta(p);

		pmeta.setLevel(level);
		pmeta.setPlayerMeta();
	}

	@SuppressWarnings("unchecked")
	public static void view(CommandSender sender, String name) {

		String relativePath = "";
		String URL = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/Player/"
				+ name + ".json";
		File file = new File(URL);
		if (file.exists()) {

			try {

				Gson ggson = new Gson();

				JsonElement json = ggson.fromJson(new FileReader(URL), JsonElement.class);

				Map<String, String> map = new HashMap<String, String>();
				map = (Map<String, String>) ggson.fromJson(json, map.getClass());

				Map<String, Double> mapp = new HashMap<String, Double>();
				mapp = (Map<String, Double>) ggson.fromJson(json, mapp.getClass());

				int level = 0;
				double i = 0;

				i = mapp.get("level");
				level = (int) i;

				BasicFileAttributes attrs;
				//
				attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
				FileTime time = attrs.creationTime();

				String pattern = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

				String formatted = simpleDateFormat.format(new Date(time.toMillis()));

				sender.sendMessage(ChatColor.YELLOW + "---------------------------------------");
				sender.sendMessage("[" + ChatColor.BLUE + "Player" + ChatColor.WHITE + "]");
				sender.sendMessage(name);
				sender.sendMessage("[" + ChatColor.BLUE + "FirstLogin" + ChatColor.WHITE + "]");
				sender.sendMessage(formatted);
				sender.sendMessage("[" + ChatColor.BLUE + "LEVEL" + ChatColor.WHITE + "]");
				sender.sendMessage(level + "");
				sender.sendMessage("[" + ChatColor.BLUE + "UUID" + ChatColor.WHITE + "]");
				sender.sendMessage(map.get("uuid"));

				sender.sendMessage(ChatColor.YELLOW + "---------------------------------------");
			} catch (IOException e) {

				e.printStackTrace();

			}

		} else {

			sender.sendMessage(name + "というプレーヤーのデータはありません。");

		}
		return;
	}

	public static void Update_check(Player player) {

		authority_control.plugin.tool.Permission.ifFly(player);
		authority_control.plugin.tool.Permission.ifWorldEdit(player);
		authority_control.plugin.tool.Permission.ifBREAK_PLACE(player);
		authority_control.plugin.tool.Permission.ifCHANGE_GAMEMODE(player);

	}

	public static void Update(CommandSender sender, Player player, int level) {

		if (sender.isOp()) {

			int Plevel = authority_control.plugin.tool.json.ViewLv.getLv(player);

			String relativePath = "";
			String URL = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/Player/"
					+ player.getName() + ".json";

			try (Writer writer = new FileWriter(URL)) {

				Gson gson = new Gson();
				User user = new User(player.getUniqueId().toString(), player.getName(), level, null);

				gson = new GsonBuilder().create();
				gson.toJson(user, writer);

				Meta pmeta = new Meta(player);
				pmeta.setLevel(level);
				pmeta.updatePlayerlavel();

				if (sender instanceof Player)
					sender.sendMessage(player.getName() + "の権限を変更しました[Level:" + Plevel + "→Level:" +
							authority_control.plugin.tool.json.ViewLv.getLv(player) + "]");
				System.out.println(player.getName() + "の権限を変更しました[Level:" + Plevel + "→Level:"
						+ authority_control.plugin.tool.json.ViewLv.getLv(player) + "]");

				/////////////////
				Update_check(player);
				/////////////////

			} catch (IOException e) {

				e.printStackTrace();

			}
		} else {
			sender.sendMessage("どうやって呼び出した????");
		}
	}

	public static void Update(CommandSender sender, String player_name, int level) {
		if (sender.isOp()) {
			String uid = "";
			int lowlevel = 0;
			String relativePath = "";

			String URL = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/Player/"
					+ player_name + ".json";

			File file = new File(URL);
			if (file.exists()) {
				try {
					JsonReader reader = new JsonReader(new FileReader(URL));

					reader.beginObject();

					while (reader.hasNext()) {

						String name = reader.nextName();

						if (name.equals("uuid")) {

							uid = reader.nextString();

						} else if (name.equals("name")) {

							reader.nextString();

						} else if (name.equals("level")) {

							lowlevel = reader.nextInt();

						} else {
							reader.skipValue();//avoid some unhandle events
						}
					}

					try (Writer writer = new FileWriter(URL)) {

						Gson gson = new Gson();
						User user = new User(uid, player_name, level, null);

						gson = new GsonBuilder().create();
						gson.toJson(user, writer);

						//			System.out.println(user.toString());
						//			System.out.println("/////////////////////////");

						if (sender instanceof Player)
							sender.sendMessage(player_name + "の権限を変更しました[Level:" + lowlevel + "→Level:" +
									level + "]");
						System.out.println(player_name + "の権限を変更しました[Level:" + lowlevel + "→Level:" +
								level + "]");

					} catch (IOException e) {

						e.printStackTrace();

					}
				} catch (IOException e) {

					e.printStackTrace();

				}
			} else

			{

				sender.sendMessage(player_name + "というプレーヤーのデータはありません。");

			}
		} else {
			sender.sendMessage("どうやって呼び出した????");
		}
	}

	public static void View(CommandSender sender, Player player) {

	}

}
