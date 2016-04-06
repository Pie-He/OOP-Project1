package util;

import java.io.*;

import place.*;
import type.Player;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Map {

	private Cell[][] cells;
	private Place[] places;

	public Map(int weight, int height) {
		this.cells = new Cell[weight][height];
		/*for (int x = 0; x < cells.length; x++) {
			cells[x]=new Cell[height];
			for (int y = 0; y <cells[x].length; y++) {
				//System.out.println(x+" "+y);
				cells[x][y]=new Cell(x,y,null);
			}
		}*/
		places=new Place[68];
		getMapData();
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
					//System.out.println(jo.get("x")+" "+jo.get("y"));
					places[i] = getRealInstance(jo);
					//System.out.println(places[i].getX()+" "+places[i].getY());
					i++;
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void printMap() {
		if (cells[0][0] == null) {
			for (int i = 0; i < places.length; i++) {
				int x = places[i].getX();
				int y = places[i].getY();
				cells[x][y] = new Cell(x, y, places[i]);
				System.out.println(x+" "+y);
			}
			System.out.println();
		}
		
		String[][] map = new String[cells.length][cells[0].length];
		for (int x = 0; x < map.length; x++) {
			//map[x]=new String[cells[x].length];
			for (int y = 0; y < map[x].length; y++) {
				//System.out.println(x+" "+y);
				map[x][y] = (cells[x][y]==null?"¡¡":cells[x][y].toTextual());
			}
		}
		Output.getMap(map);
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

		String toTextual() {
			return place==null?"¡¡":place.toTextual();
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}
}
