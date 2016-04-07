package util;

import java.io.*;

import place.*;
import type.Player;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Map {

	private Cell[][] cells;
	private Place[] places;
	private String[][] staticMap;
	public Map(int weight, int height) {
		this.cells = new Cell[weight][height];
		this.staticMap=new String[weight][height];
		places=new Place[68];
		getMapData();
		this.initial();
	}

	private void getMapData() {
		File file = new File("places.txt");
		String str;
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				int i = 0;
				while ((str = br.readLine()) != null) {
					JSONObject jo = JSON.parseObject(str);
					places[i] = getRealInstance(jo);
					i++;
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void initial(){
		for (int i = 0; i < places.length; i++) {
			int x = places[i].getX();
			int y = places[i].getY();
			cells[x][y] = new Cell(x, y, places[i]);
		}
		for (int x = 0; x < staticMap.length; x++) {
			for (int y = 0; y < staticMap[x].length; y++) {
				staticMap[x][y] = (cells[x][y]==null?"¡¡":cells[x][y].toText());

			}
		}
	}
	public String[][] toText() {
		String[][] map = new String[cells.length][cells[0].length];
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				map[x][y] = (cells[x][y]==null?"¡¡":cells[x][y].toText());
				//=(cells[x][y]==null?"¡¡":cells[x][y].toText());
			}
		}
		return map;
	}

	public String[][] getInitalMap(){
		return this.staticMap;
	}
	
	private Place getRealInstance(JSONObject jo) {
		String symbol = jo.get("symbol").toString();

		switch (symbol) {
		case "¡ò":
			return JSON.toJavaObject(jo, House.class);
		case "È¯":
			return JSON.toJavaObject(jo, Coupon.class);
		case "µÀ":
			return JSON.toJavaObject(jo, Shop.class);
		case "ÐÂ":
			return JSON.toJavaObject(jo, News.class);
		case "Òø":
			return JSON.toJavaObject(jo, Bank.class);
		case "²Ê":
			return JSON.toJavaObject(jo, Lottery.class);
		case "¿Õ":
			return JSON.toJavaObject(jo, Space.class);
		case "¿¨":
			return JSON.toJavaObject(jo, Card.class);
		default:
			return null;
		}
	}

	private class Cell {

		private int x;
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
