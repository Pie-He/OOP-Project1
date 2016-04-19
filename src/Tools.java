import java.io.*;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import type.Manager;
import type.Player;
import type.Prop;
import util.Output;
import place.*;

import com.alibaba.fastjson.*;

public class Tools {

	public static void main(String[] args) throws IOException {
		// Prop p=Prop.remoteBoson;
		// p.use();
		/*
		 * LinkedList<Player> l = new LinkedList(); l.addFirst(new Player());
		 * l.addFirst(new Player()); l.addFirst(new Player()); l.addFirst(new
		 * Player()); l.stream().forEach(i -> i.setCash(2)); for (Player p : l)
		 * { p.setName("a"); } l.stream().map(i ->
		 * i.getName()).forEach(System.out::println);
		 * System.out.println(l.stream().map(i -> i.getCash()).findAny());
		 * System.out.println(l.stream() .filter(item -> item !=
		 * null).findAny()); System.out.println(l.remove(null));
		 */// String a = "aadsafagadsf";
		/*
		 * char[] b = a.toCharArray(); File file = new File("test.txt");
		 * System.out.println(file.exists()); if (file.exists()) {
		 * BufferedReader br = new BufferedReader(new FileReader(file)); String
		 * playerStr = br.readLine(); System.out.println(playerStr); Player
		 * player = JSON.parseObject(playerStr, Player.class);
		 * System.out.println(player.getCash());
		 * System.out.println(player.getCoupon());
		 * System.out.println(player.getDeposit());
		 * //System.out.println(player.getDescription());
		 * System.out.println(player.getName());
		 * System.out.println(player.getSymbol());
		 * 
		 * br.close(); }
		 */
		// writeMap();
		LinkedList<Player> l = new LinkedList();
		l.add(new Player());
		l.add(new Player());
		l.add(new Player());
		IntSummaryStatistics stats = l.stream()
				.mapToInt((x) -> x.getCash()).summaryStatistics();
		System.out.println(stats.getMax());
		Output.getBuyProp();
		/*
		 * Player t = new Player("hpj", "人", "地"); String str =
		 * JSON.toJSON(t).toString(); JSONObject jo = JSON.parseObject(str);
		 * Player a = JSON.toJavaObject(jo, Player.class);
		 * System.out.print((int) jo.get("cash"));
		 */

	}

	public static void writeMap() throws IOException {
		Place[] places = new Place[68];
		// 初始化土地
		{
			for (int i = 0; i <= 4; i++) {
				places[i] = new House(2 * i, 0, "◎", 500, "莲花路" + (i + 1));
			}

			for (int i = 6; i <= 7; i++) {
				places[i] = new House(2 * i, 0, "◎", 800, "中山路" + (i - 5));
			}
			for (int i = 8; i <= 10; i++) {
				places[i] = new House(2 * i - 2, 2, "◎", 800, "中山路" + (i - 5));
			}

			for (int i = 12; i <= 14; i++) {
				places[i] = new House(20, 2 * i - 20, "◎", 1500, "云顶路"
						+ (i - 11));
			}

			for (int i = 15; i <= 16; i++) {
				places[i] = new House(2 * i - 8, 8, "◎", 1500, "云顶路" + (i - 11));
			}

			for (int i = 18; i <= 22; i++) {
				places[i] = new House(28, -2 * i + 44, "◎", 2000, "邯郸路"
						+ (i - 17));
			}

			for (int i = 36; i <= 38; i++) {
				places[i] = new House(32, 2 * i - 60, "◎", 1500, "国定路"
						+ (i - 35));
			}

			for (int i = 39; i <= 40; i++) {
				places[i] = new House(-2 * i + 108, 16, "◎", 1500, "国定路"
						+ (i - 35));
			}

			for (int i = 42; i <= 44; i++) {
				places[i] = new House(-2 * i + 112, 12, "◎", 1200, "国权路"
						+ (i - 41));
			}
			places[45] = new House(24, 14, "◎", 1200, "国权路" + 4);
			places[46] = new House(24, 16, "◎", 1200, "国权路" + 5);

			for (int i = 48; i <= 50; i++) {
				places[i] = new House(-2 * i + 118, 18, "◎", 2500, "政通路"
						+ (i - 47));
			}
			places[51] = new House(18, 16, "◎", 2500, "政通路" + 4);
			places[52] = new House(18, 14, "◎", 2500, "政通路" + 5);

			for (int i = 54; i <= 56; i++) {
				places[i] = new House(-2 * i + 124, 12, "◎", 2000, "南京路"
						+ (i - 53));
			}
			places[57] = new House(12, 10, "◎", 2000, "南京路" + 4);
			places[58] = new House(10, 10, "◎", 2000, "南京路" + 5);

			places[60] = new House(6, 10, "◎", 800, "腾飞路" + 1);
			for (int i = 61; i <= 64; i++) {
				places[i] = new House(4, -2 * i + 132, "◎", 800, "腾飞路"
						+ (i - 59));
			}

			for (int i = 24; i <= 27; i++) {
				places[i] = new Coupon(2 * i - 16, 0, "券");
			}
			places[28] = new Coupon(38, 2, "券");
			for (int i = 30; i <= 33; i++) {
				places[i] = new Coupon(38, 2 * i - 54, "券");
			}
			places[34] = new Coupon(36, 12, "券");
			places[66] = new Coupon(0, 4, "券");
			places[67] = new Coupon(0, 2, "券");

			places[5] = new Shop(10, 0, "道");
			places[11] = new News(20, 2, "新");
			places[17] = new Bank(26, 8, "银");
			places[23] = new Lottery(30, 0, "彩");
			places[29] = new Space(38, 4, "空");
			places[35] = new Space(34, 12, "空");
			places[41] = new Shop(28, 14, "道");
			places[47] = new CardPrize(24, 18, "卡");
			places[53] = new News(18, 12, "新");
			places[59] = new Bank(8, 10, "银");
			places[65] = new Lottery(2, 4, "彩");
		}
		File file = new File("places.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		if (file.exists()) {
			for (int i = 0; i < places.length; i++) {
				bw.write(JSON.toJSONString(places[i]));
				bw.write("\r\n");
			}
		}
		bw.close();
	}

}
