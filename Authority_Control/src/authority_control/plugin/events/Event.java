package authority_control.plugin.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Event implements Listener {

	public Event(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	static int BREAK_PLACE_Lv;
	static int FLY_Lv;
	static int WorldEdit_Lv;
	static int CHANGE_GAMEMODE_Lv;
	static int TP_Lv;
	static int setFIRST_SPAWNPOINTMODE_Lv;

	@EventHandler
	public void onBlockBurnEvent(BlockBurnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockIgniteEvent(BlockIgniteEvent e) {
		e.setCancelled(true);
		if (e.getBlock().getType().equals(Material.FIRE)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent p) {

		Player player = p.getPlayer();
		player.setAllowFlight(false);
		authority_control.plugin.tool.json.Json.Join(player);

		player.sendMessage("あなたの権限はLv" + authority_control.plugin.tool.json.ViewLv.getLv(player) + "です。");
		authority_control.plugin.tool.json.Json.Update_check(player);

	}

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e) {
		if (!(e.getPlayer().hasPermission("waterpunch.break_place"))) {
			e.setCancelled(true);
			authority_control.plugin.main.Error_System.sendPlayer(e.getPlayer(), 1);

		}
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		if (!(e.getPlayer().hasPermission("waterpunch.break_place"))) {
			e.setCancelled(true);
			authority_control.plugin.main.Error_System.sendPlayer(e.getPlayer(), 1);

		}
	}

	@EventHandler
	public void onBAKETU_SETTI(PlayerBucketEmptyEvent e) {
		if (!(e.getPlayer().hasPermission("waterpunch.break_place"))) {
			e.setCancelled(true);
			authority_control.plugin.main.Error_System.sendPlayer(e.getPlayer(), 1);

		}
	}

	@EventHandler
	public void onBAKETU_SETTI(PlayerBucketFillEvent e) {
		if (!(e.getPlayer().hasPermission("waterpunch.break_place"))) {
			e.setCancelled(true);
			authority_control.plugin.main.Error_System.sendPlayer(e.getPlayer(), 1);

		}
	}

	@EventHandler
	public void onLaunch(ProjectileLaunchEvent e) {

		if (e.getEntity().getShooter() instanceof Player) {
			Player player = (Player) e.getEntity().getShooter();
			if (!(player.hasPermission("waterpunch.break_place"))) {
				e.setCancelled(true);
				authority_control.plugin.main.Error_System.sendPlayer(player, 1);
			}
		}
	}

	@EventHandler
	public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent e) {

	}

	@EventHandler
	public void onEntityExplodeEvent(EntityExplodeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {

		Player player = e.getPlayer();
		String com = e.getMessage();

		if (com.contains("//")) {

			if (authority_control.plugin.tool.json.ViewLv.getLv(player) <= WorldEdit_Lv) {
				e.setCancelled(true);
				authority_control.plugin.main.Error_System.sendPlayer(e.getPlayer(), 3);
				;
			}

		} else if (com.contains("/gamemode")) {

			if (!(e.getPlayer().hasPermission("waterpunch.change_gamemode"))) {
				e.setCancelled(true);
				authority_control.plugin.main.Error_System.sendPlayer(e.getPlayer(), 2);
			}

		} else if (com.contains("/tp")) {
			e.setCancelled(false);
		}

	}

	static public void setBlockEvent_Lv(int i) {
		BREAK_PLACE_Lv = i;
		System.out.println("ブロック編集権限=" + BREAK_PLACE_Lv);
	}

	static public void setFLY_Lv(int i) {
		FLY_Lv = i;
		System.out.println("飛行権限=" + FLY_Lv);
	}

	static public void setWorldEdit_Lv(int i) {
		WorldEdit_Lv = i;
		System.out.println("WE使用権限=" + WorldEdit_Lv);
	}

	static public void setCHANGE_GAMEMODE_Lv(int i) {
		CHANGE_GAMEMODE_Lv = i;
		System.out.println("ゲームモード使用権限=" + CHANGE_GAMEMODE_Lv);
	}

	static public void setTP_Lv(int i) {
		TP_Lv = i;
		System.out.println("TP使用権限=" + TP_Lv);
	}

	static public int setFIRST_SPAWNPOINTMODE_Lv(int i) {
		String txt = "無効";
		setFIRST_SPAWNPOINTMODE_Lv = i;
		if (setFIRST_SPAWNPOINTMODE_Lv == 1)
			txt = "有効";

		System.out.println("ログイン位置固定(0以外で有効)= " + txt);
		return setFIRST_SPAWNPOINTMODE_Lv;
	}

	static public int getBlockEvent_Lv() {
		return BREAK_PLACE_Lv;
	}

	static public int getWorldEdit_Lv() {
		return WorldEdit_Lv;
	}

	static public int getFLY_Lv() {
		return FLY_Lv;
	}

	static public int getCHANGE_GAMEMODE_Lv() {
		return CHANGE_GAMEMODE_Lv;
	}

	static public int getTP_Lv() {
		return TP_Lv;
	}

	static public int getFIRST_SPAWNPOINTMODE_Lv() {
		return setFIRST_SPAWNPOINTMODE_Lv;
	}

		@SuppressWarnings("deprecation")
		@EventHandler
		public void onPlayerInteractEvent(PlayerInteractEvent e) {
//			if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem().getItemMeta().get) {
//
//				e.getPlayer().sendMessage("a");
//
//			}

		}

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (player.hasPermission("waterpunch.break_place")) {
			player.sendMessage("[ERROR ID E-01]権限がありません。");
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onOpen(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();

		int CID = event.getRawSlot();
		//		player.sendMessage(event.getAction().toString());

		if (!(event.getRawSlot() == -999)) {

			if (player.getOpenInventory().getTitle().equals("Menu")) {
				event.setCancelled(true);
				if (CID <= 8)
					authority_control.plugin.tool.inventory.Menus.Menu(player, event.getRawSlot(),
							event.getInventory().getItem(event.getRawSlot()).getItemMeta());
			}
			if (player.getOpenInventory().getTitle().equals("Option")) {
				event.setCancelled(true);
				authority_control.plugin.tool.inventory.Menus.Option(player, CID,
						event.getInventory().getItem(event.getRawSlot()).getItemMeta());
			}
			if (player.getOpenInventory().getTitle().equals("YES_OR_ON")) {
				event.setCancelled(true);
				if (authority_control.plugin.tool.json.ViewLv.getMemo(player).equals("FIRSTSPAWN")) {

				}

			}
		}
	}
}
