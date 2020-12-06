package authority_control.plugin.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import authority_control.plugin.tool.json.Create_Option_Json;

public class Option {
	static int FIRSTSPAWNPOINTMODE = 0;

	public static void roadOption() {

		SearchOption_dir();
		SearchOption();

	}

	public static void REroadOption() {

	}

	static void SearchOption_dir() {

		String relativePath = "";
		String URL_ = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/";
		File file_ = new File(URL_);
		if (!(file_.exists()))
			file_.mkdir();
		String URL__ = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/Option/";
		File file__ = new File(URL__);
		if (!(file__.exists()))
			file__.mkdir();

	}

	@SuppressWarnings("unchecked")
	static void SearchOption() {

		int BREAK_PLACE = 0;
		int FLY = 0;
		int WORLDEDIT = 0;
		int CHANGE_GAMEMODE = 0;
		int UPDATE_FIRSTSPAWNPOINT = 0;

		String relativePath = "";

		String URL = new File(relativePath).getAbsolutePath().toString() + "/plugins/Authority/Option/option.json";

		File file = new File(URL);

		if (file.exists()) {

			try {

				Gson gson = new Gson();

				JsonElement json = gson.fromJson(new FileReader(URL), JsonElement.class);

				Map<String, Double> map = new HashMap<String, Double>();
				map = (Map<String, Double>) gson.fromJson(json, map.getClass());

				double i = 0;

				i = map.get("BREAK_PLACE");
				BREAK_PLACE = (int) i;
				authority_control.plugin.events.Event.setBlockEvent_Lv(BREAK_PLACE);

				i = map.get("FLY");
				FLY = (int) i;
				authority_control.plugin.events.Event.setFLY_Lv(FLY);

				i = map.get("WORLDEDIT");
				WORLDEDIT = (int) i;
				authority_control.plugin.events.Event.setWorldEdit_Lv(WORLDEDIT);

				i = map.get("CHANGE_GAMEMODE");
				CHANGE_GAMEMODE = (int) i;
				authority_control.plugin.events.Event.setCHANGE_GAMEMODE_Lv(CHANGE_GAMEMODE);

				if (map.containsKey("FIRST_SPAWNPOINTMODE")) {

					i = map.get("FIRST_SPAWNPOINTMODE");
					UPDATE_FIRSTSPAWNPOINT = (int) i;
					authority_control.plugin.events.Event.setFIRST_SPAWNPOINTMODE_Lv(UPDATE_FIRSTSPAWNPOINT);

				} else {

					authority_control.plugin.events.Event.setFIRST_SPAWNPOINTMODE_Lv(0);
					try (Writer writer = new FileWriter(URL)) {

						Gson ggson = new Gson();
						Create_Option_Json opti = new Create_Option_Json(1);
						opti.setBREAK_PLACE(authority_control.plugin.events.Event.getBlockEvent_Lv());
						opti.setWORLDEDIT(authority_control.plugin.events.Event.getWorldEdit_Lv());
						opti.setCHANGE_GAMEMODE(authority_control.plugin.events.Event.getCHANGE_GAMEMODE_Lv());
						opti.setFLY(authority_control.plugin.events.Event.getFLY_Lv());
						opti.setFIRSTSPAWNPOINTMODE(authority_control.plugin.events.Event.getTP_Lv());

						ggson = new GsonBuilder().create();
						ggson.toJson(opti, writer);

					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			} catch (Exception e) {

				e.printStackTrace();

			}

		} else {

			try (Writer writer = new FileWriter(URL)) {

				Gson gson = new Gson();
				Create_Option_Json opti = new Create_Option_Json(1);

				gson = new GsonBuilder().create();
				gson.toJson(opti, writer);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getFIRSTSPAWNPOINT() {
		return FIRSTSPAWNPOINTMODE;
	}

	static void setFIRSTSPAWNPOINTMODE(int i) {
		FIRSTSPAWNPOINTMODE = i;
	}

}
