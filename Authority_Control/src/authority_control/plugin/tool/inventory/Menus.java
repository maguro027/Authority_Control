package authority_control.plugin.tool.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import authority_control.plugin.tool.meta.Meta;

public class Menus {

	public static void YorN(Player player, String txt) {

		Inventory inbv = Bukkit.createInventory(null, 9, "YES_OR_ON");

		ItemStack YES = new ItemStack(Material.DIAMOND_BLOCK, 1);
		ItemMeta YES_META = YES.getItemMeta();
		String YES_NAME = "YES";
		YES_META.setDisplayName(YES_NAME);
		YES.setItemMeta(YES_META);

		ItemStack NO = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta NO_META = NO.getItemMeta();
		String NO_NAME = "NO";
		NO_META.setDisplayName(NO_NAME);
		NO.setItemMeta(NO_META);

		ItemStack Beacon = new ItemStack(Material.BEACON, 1);
		ItemMeta im = Beacon.getItemMeta(); //ItemStackから、ItemMetaを取得します。
		String name = txt;
		im.setDisplayName(name);
		Beacon.setItemMeta(im);

		ItemStack Glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta Glassact = Glass.getItemMeta();
		String Glassname = " ";
		Glassact.setDisplayName(Glassname);
		Glass.setItemMeta(Glassact);

		inbv.setItem(0, new ItemStack(Glass)); //0使用
		inbv.setItem(1, new ItemStack(Glass)); //1使用
		inbv.setItem(2, new ItemStack(YES)); //2使用
		inbv.setItem(3, new ItemStack(Glass)); //3使用
		inbv.setItem(4, new ItemStack(Beacon)); //4使用
		inbv.setItem(5, new ItemStack(Glass)); //5使用
		inbv.setItem(6, new ItemStack(NO)); //6使用
		inbv.setItem(7, new ItemStack(Glass)); //7使用
		inbv.setItem(8, new ItemStack(Glass)); //8使用

		player.closeInventory();
		player.openInventory(inbv);

	}

	public static void setMenu(Player player) {

		Inventory inbv = Bukkit.createInventory(null, 9, "Menu");

		List<String> lores = new ArrayList<String>();
		lores.add("あなたの権限はLv " + authority_control.plugin.tool.json.ViewLv.getLv(player) + " です。");
		lores.add(" ");
		lores.add("ブロック編集");
		lores.add("Lv " + authority_control.plugin.events.Event.getBlockEvent_Lv() + " 以上から可能です。");
		lores.add(" ");
		lores.add("飛行");
		lores.add("Lv " + authority_control.plugin.events.Event.getFLY_Lv() + " 以上から可能です。");
		lores.add(" ");
		lores.add("WE");
		lores.add("Lv " + authority_control.plugin.events.Event.getWorldEdit_Lv() + " 以上から可能です。");
		lores.add(" ");
		lores.add("ゲームモード変更");
		lores.add("Lv " + authority_control.plugin.events.Event.getCHANGE_GAMEMODE_Lv() + " 以上から可能です。");

		ItemStack Beacon = new ItemStack(Material.BEACON, 1);
		ItemMeta im = Beacon.getItemMeta(); //ItemStackから、ItemMetaを取得します。
		String name = "あなたの権限";
		im.setDisplayName(name);
		im.setLore(lores);
		Beacon.setItemMeta(im);

		ItemStack Glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta Glassact = Glass.getItemMeta();
		String Glassname = " ";
		Glassact.setDisplayName(Glassname);
		Glass.setItemMeta(Glassact);

		ItemStack Creative = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta Creativeact = Creative.getItemMeta();
		String Creativename = "Creative";
		Creativeact.setDisplayName(Creativename);
		Creative.setItemMeta(Creativeact);

		ItemStack Survival = new ItemStack(Material.WOOD_AXE, 1);
		ItemMeta Survivalact = Survival.getItemMeta();
		String Survivalname = "Survival";
		Survivalact.setDisplayName(Survivalname);
		Survival.setItemMeta(Survivalact);

		ItemStack Adventure = new ItemStack(Material.GOLD_AXE, 1);
		ItemMeta Adventureact = Adventure.getItemMeta();
		String Adventurename = "Adventure";
		Adventureact.setDisplayName(Adventurename);
		Adventure.setItemMeta(Adventureact);

		ItemStack FLY = new ItemStack(Material.FEATHER, 1);
		ItemMeta FLYact = FLY.getItemMeta();
		String FLYname = "FLY";
		FLYact.setDisplayName(FLYname);
		FLY.setItemMeta(FLYact);

		ItemStack Option = new ItemStack(Material.HOPPER, 1);
		ItemMeta Optionact = Option.getItemMeta();
		String Optionname = "Option";
		Optionact.setDisplayName(Optionname);
		Option.setItemMeta(Optionact);

		inbv.setItem(0, new ItemStack(Beacon)); //0使用
		inbv.setItem(1, new ItemStack(Glass)); //1使用
//		if (player.hasPermission("sample.op"))
//			inbv.setItem(1, new ItemStack(Option));
		inbv.setItem(2, new ItemStack(Glass)); //2使用
		inbv.setItem(3, new ItemStack(Glass)); //3使用
		inbv.setItem(4, new ItemStack(Glass)); //4使用
		if (authority_control.plugin.tool.json.ViewLv.getLv(player) >= authority_control.plugin.events.Event
				.getFLY_Lv()) {
			inbv.setItem(5, new ItemStack(FLY)); //5使用
		} else {
			inbv.setItem(5, new ItemStack(Glass)); //5使用
		}
		if (!(authority_control.plugin.tool.json.ViewLv.getLv(player) <= authority_control.plugin.events.Event
				.getCHANGE_GAMEMODE_Lv())) {

			inbv.setItem(6, new ItemStack(Adventure)); //6使用
			inbv.setItem(7, new ItemStack(Survival)); //7使用
			inbv.setItem(8, new ItemStack(Creative)); //8使用

		} else {
			inbv.setItem(6, new ItemStack(Glass)); //6使用
			inbv.setItem(7, new ItemStack(Glass)); //7使用
			inbv.setItem(8, new ItemStack(Glass)); //8使用

		}
		player.closeInventory();
		player.openInventory(inbv);
	}

	public static void Menu(Player player, int ID, ItemMeta item) {

		int Option = 1;

		int FLY = 5;
		int Adventure = 6;
		int Survival = 7;
		int Creative = 8;

		try {
			if (ID == Option && player.hasPermission("sample.op") && item.getDisplayName().equals("Option")) {
				setOption(player);
			}

			if (ID == Survival || ID == Creative || ID == Adventure) {
				if (authority_control.plugin.tool.json.ViewLv.getLv(player) <= authority_control.plugin.events.Event
						.getCHANGE_GAMEMODE_Lv()) {
					player.sendMessage("[ERROR ID E-03]権限がありません。");
					player.setGameMode(GameMode.SURVIVAL);
				} else if (ID == Adventure && item.getDisplayName().equals("Adventure")) {
					player.setGameMode(GameMode.ADVENTURE);
				} else if (ID == Survival && item.getDisplayName().equals("Survival")) {
					player.setGameMode(GameMode.SURVIVAL);
				} else if (ID == Creative && item.getDisplayName().equals("Creative")) {
					player.setGameMode(GameMode.CREATIVE);
				}
			}
			if (ID == FLY && authority_control.plugin.tool.json.ViewLv
					.getLv(player) >= authority_control.plugin.events.Event.getFLY_Lv()) {

				if (player.getAllowFlight() == false) {
					player.setAllowFlight(true);
					player.sendMessage("フライON");
				} else if (player.getAllowFlight() == true) {
					player.setAllowFlight(false);
					player.sendMessage("フライOFF");
				}

			} else {
			}
		} finally {

		}

	}

	public static void setOption(Player player) {

		Inventory inbv = Bukkit.createInventory(null, 9, "Option");

		List<String> lores = new ArrayList<String>();
		lores.add("各種プレーヤー毎に付与された");
		lores.add("権限レベルを編集できます。");

		ItemStack REDTORCH = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		ItemMeta im = REDTORCH.getItemMeta();
		String name = "プレーヤー権限設定";
		im.setDisplayName(name);
		im.setLore(lores);
		REDTORCH.setItemMeta(im);

		List<String> lores1 = new ArrayList<String>();
		lores1.add("警告");
		lores1.add("サーバー全体の権限を編集します。");

		ItemStack WORKBENCH = new ItemStack(Material.WORKBENCH, 1);
		ItemMeta mim = WORKBENCH.getItemMeta();
		String mname = "サーバー権限設定";
		mim.setDisplayName(mname);
		mim.setLore(lores1);
		WORKBENCH.setItemMeta(mim);

		List<String> lores2 = new ArrayList<String>();
		lores2.add("警告");
		lores2.add("初回ログインのユーザーのスポーン場所を現在地に変更します。");

		ItemStack FIRSTSPAWN = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta FIRSTSPAWN_ = FIRSTSPAWN.getItemMeta();
		String FIRSTSPAWN_NAME = "初回スポーン場所変更";
		FIRSTSPAWN_.setDisplayName(FIRSTSPAWN_NAME);
		FIRSTSPAWN_.setLore(lores2);
		FIRSTSPAWN.setItemMeta(FIRSTSPAWN_);

		ItemStack Glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta Glassact = Glass.getItemMeta();
		String Glassname = " ";
		Glassact.setDisplayName(Glassname);
		Glass.setItemMeta(Glassact);

		inbv.setItem(0, new ItemStack(Glass)); //0使用
		if (player.hasPermission("sample.op"))
			inbv.setItem(0, new ItemStack(REDTORCH));
		inbv.setItem(1, new ItemStack(Glass)); //0使用
		inbv.setItem(2, new ItemStack(Glass)); //2使用
		inbv.setItem(3, new ItemStack(Glass)); //3使用
		inbv.setItem(4, new ItemStack(Glass)); //4使用
		if (player.hasPermission("sample.op"))
			inbv.setItem(4, new ItemStack(WORKBENCH));
		inbv.setItem(5, new ItemStack(Glass)); //5使用
		inbv.setItem(6, new ItemStack(Glass)); //6使用
		inbv.setItem(7, new ItemStack(Glass)); //7使用
		inbv.setItem(8, new ItemStack(Glass)); //8使用
		if (player.hasPermission("sample.op"))
			inbv.setItem(8, new ItemStack(FIRSTSPAWN));

		player.closeInventory();
		player.openInventory(inbv);

	}

	public static void Option(Player player, int ID, ItemMeta item) {

		int USER = 0;
		int SERVER = 4;
		int WARP = 8;

		if (ID == USER && player.hasPermission("sample.op") && item.getDisplayName().equals("プレーヤー権限設定")) {
			player.sendMessage("チャットにて変更したいユーザー名を発言してください");
		}
		if (ID == SERVER && player.hasPermission("sample.op") && item.getDisplayName().equals("サーバー権限設定")) {

		}
		if (ID == WARP && player.hasPermission("sample.op") && item.getDisplayName().equals("初回スポーン場所変更")) {

			Meta pmeta = new Meta(player);

			pmeta.setMemo("FIRSTSPAWN");
			pmeta.setLevel(authority_control.plugin.tool.json.ViewLv.getLv(player));
			pmeta.setPlayerMeta();

			YorN(player, "初期スポーン場所を変更しますか？");
		}

	}

	static void test() {
	}
}
