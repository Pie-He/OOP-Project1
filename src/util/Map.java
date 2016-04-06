package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import place.*;
import type.Player;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Map {
	
	private Place[][] places;
	public Map(int weight,int height){
		this.places=new Place[weight][height];
	}
	public static void getMapData() throws IOException {
		File file = new File("places.txt");
		String str;
		if (file.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(file));
			int i = 0;
			while ((str = br.readLine()) != null) {
				JSONObject jo = JSON.parseObject(str);
				Manager.place[i] = getRealInstance(jo);
				i++;
			}
			br.close();
		}
	}

	private static Place getRealInstance(JSONObject jo) {
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
}
