package type;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import type.item.Player;
import type.item.RoadBlock;
import type.place.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Map {
	private static final Map MAP = new Map();

	private Map() {
		places = new ArrayList<Place>();
		getMapData();
		mapLength = places.size();
		this.cells = new Cell[width][height];
		this.staticMap = new String[width][height];
	}
	public static Map getInstance() {
		return MAP;
	}

	private Cell[][] cells;
	private ArrayList<Place> places;
	private String[][] staticMap;
	private int width;
	private int height;
	public final int mapLength;

	public String[][] toText() {
		String[][] map = new String[cells.length][cells[0].length];
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				map[x][y] = (cells[x][y] == null ? "　" : cells[x][y].toText());
				// =(cells[x][y]==null?"　":cells[x][y].toText());
			}
		}
		return map;
	}

	public String[][] getInitalMap() {
		return this.staticMap;
	}

	public boolean event(Player player, int dice) {
		for (int i = 0; i < dice; i++) {
			if (!movePlayer(player))
				return true;
		}
		if (!(places.get(player.getPoi()) instanceof Bank))
			return places.get(player.getPoi()).event(player);
		return true;
	}

	public boolean addBlock(RoadBlock r) {
		if (this.places.get(r.getPoi()).isBlock())
			return false;
		this.places.get(r.getPoi()).put(r);
		return true;

	}

	public boolean isBlock(int poi) {
		return this.places.get(poi).isBlock();
	}

	public String getDescription(int poi) {
		return places.get(poi).getDescription();
	}

	public void removePlayer(Player player){
		places.get(player.getPoi()).remove(player);
	}
	public void init(Collection<Player> players) {
		places.stream().forEach(p -> {
			int x = p.getX();
			int y = p.getY();
			cells[x][y] = new Cell(x, y, p);
		});
		for (int x = 0; x < staticMap.length; x++) {
			for (int y = 0; y < staticMap[x].length; y++) {
				staticMap[x][y] = (cells[x][y] == null ? "　" : cells[x][y]
						.toText());

			}
		}
		players.stream().forEach(item -> places.get(item.getPoi()).put(item));
		// places[0].put(item);
	}

	// 如果能继续移动返回true，否则返回false,当超越其他玩家时，图标覆盖问题
	private boolean movePlayer(Player p) {
		int poi0 = p.getPoi();
		places.get(poi0).remove(p);
		int poi = p.walk();
		places.get(poi).put(p);
		if (places.get(poi).removeBlock()) {
			places.get(poi).event(p);
			return false;
		}
		if (places.get(poi) instanceof Bank)
			places.get(poi).event(p);
		return true;
	}

	private void getMapData() {
		File file = new File("places.txt");
		String str;
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((str = br.readLine()) != null) {
					JSONObject jo = JSON.parseObject(str);
					Place place = getRealInstance(jo);
					places.add(place);
					width = width > place.getX() + 1 ? width : place.getX() + 1;
					height = height > place.getY() + 1 ? height
							: place.getY() + 1;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Place getRealInstance(JSONObject jo) {
		String symbol = jo.get("symbol").toString();
		for (PlaceEnum p : PlaceEnum.values()) {
			if (p.getSymbol().equals(symbol)) {
				return JSON.toJavaObject(jo, p.getRealClass());
			}
		}
		return null;
	}

	private class Cell {

		@SuppressWarnings("unused")
		private int x;
		@SuppressWarnings("unused")
		private int y;
		private Place place;

		Cell(int x, int y, Place place) {
			this.x = x;
			this.y = y;
			this.place = place;
		}

		String toText() {
			return place.toText();
		}
	}
}
