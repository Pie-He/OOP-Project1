import java.io.*;

import type.Player;
import place.*;

import com.alibaba.fastjson.*;

public class Tools {

	public static void main(String[] args) throws IOException {
		/*String a = "aadsafagadsf";
		char[] b = a.toCharArray();
		Player t = new Player("hpj", "人", "地");
		String str = JSON.toJSON(t).toString();
		JSONObject j=JSON.parseObject(str);
		System.out.print(j.get("name"));
		System.out.println(JSON.toJSON(b));
		System.out.println(str);
		File file = new File("test.txt");
		System.out.println(file.exists());
		if (file.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String playerStr = br.readLine();
			System.out.println(playerStr);
			Player player = JSON.parseObject(playerStr, Player.class);
			System.out.println(player.getCash());
			System.out.println(player.getCoupon());
			System.out.println(player.getDeposit());
			System.out.println(player.getDescription());
			System.out.println(player.getName());
			System.out.println(player.getSymbol());

			br.close();
		}
		//writeMap();*/
		
	}

	public static void writeMap() throws IOException {
		Place[] places = new Place[68];
		// 初始化土地
		{
			for (int i = 0; i <= 4; i++) {
				places[i] = new House(2 * i, 0, "◎");
			}

			for (int i = 6; i <= 7; i++) {
				places[i] = new House(2 * i, 0, "◎");
			}
			for (int i = 8; i <= 10; i++) {
				places[i] = new House(2 * i - 2, 2, "◎");
			}

			for (int i = 12; i <= 14; i++) {
				places[i] = new House(20, 2 * i - 20, "◎");
			}

			for (int i = 15; i <= 16; i++) {
				places[i] = new House(2 * i - 8, 8, "◎");
			}

			for (int i = 18; i <= 22; i++) {
				places[i] = new House(28, -2 * i + 44, "◎");
			}

			for (int i = 36; i <= 38; i++) {
				places[i] = new House(32, 2 * i - 60, "◎");
			}

			for (int i = 39; i <= 40; i++) {
				places[i] = new House(-2 * i + 108, 16, "◎");
			}

			for (int i = 42; i <= 44; i++) {
				places[i] = new House(-2 * i + 112, 12, "◎");
			}
			places[45] = new House(24, 14, "◎");
			places[46] = new House(24, 16, "◎");

			for (int i = 48; i <= 50; i++) {
				places[i] = new House(-2 * i + 118, 18, "◎");
			}
			places[51] = new House(18, 16, "◎");
			places[52] = new House(18, 14, "◎");

			for (int i = 54; i <= 56; i++) {
				places[i] = new House(-2 * i + 124, 12, "◎");
			}
			places[57] = new House(12, 10, "◎");
			places[58] = new House(10, 10, "◎");

			places[60] = new House(6, 10, "◎");
			for (int i = 61; i <= 64; i++) {
				places[i] = new House(4, 2 * i - 112, "◎");
			}

			for (int i = 24; i <= 27; i++) {
				places[i] = new Coupon(2 * i - 16, 0, "券");
			}
			places[28] = new Coupon(38, 2, "券");
			for (int i = 30; i <= 33; i++) {
				places[i] = new Coupon(2 * i - 54, 38, "券");
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
			places[47] = new Card(24, 18, "卡");
			places[53] = new News(18, 12, "新");
			places[59] = new Bank(8, 10, "银");
			places[65] = new Lottery(2, 4, "彩");
		}
		File file = new File("places.txt");
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		if(file.exists()){
			for(int i=0;i<places.length;i++){
				bw.write(JSON.toJSONString(places[i]));
				bw.write("\r\n");
			}
		}
		bw.close();
	}

}
