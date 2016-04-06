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
	public static void getMapData() throws IOException{
		File file = new File("places.txt");
		String str;
		if(file.exists()){
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((str=br.readLine())!=null){
				JSONObject o=JSON.parseObject(str);
				String symbol=(String)o.get("symbol");
			}
			br.close();
		}
	}
	
	private static void getRealInstance(Place place){
		Class a;
		switch(place.getSymbol()){
		case "¡ò":
			
		}
	}
}
