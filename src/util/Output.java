package util;

import java.io.IOException;
import java.util.*;

import type.Player;
import type.Prop;
import type.Stock;

public class Output {
	private final static String[] MAINMENU = { "0-查看地图", "1-查看原始地图", "2-使用道具",
			"3-查看10步十步以内示警", "4-查看前后指定步数的具体信息", "5-查看玩家的资产信息",
			"6-想看的都看了，心满意足的扔骰子", "7-不玩了，认输", "8-股票" };
	private final static String NUMBERREGULAR = "-?\\d+$";
	private static Scanner input = new Scanner(System.in);

	public static int getPlayerNumber() {
		return getAndCheck("请输入玩家人数(2-4):", 2, 4);
	}

	public static Deque<String> getPlayerName(int number) {
		Deque<String> players = new LinkedList<String>();
		for (int i = 0; i < number; i++) {
			String name = getAndCheck("请输入玩家" + (i + 1) + "名字(数字、字母、下划线):",
					"^\\w+$");
			players.add(name);
			// players.add(new Player(name, PLSYMBOL[i], HSSYMBOL[i]));
			// System.out.println(player.getDescription());
		}
		return players;
	}

	public static void getReady() throws IOException {
		System.out.println("准备好后，请按回车键开始游戏");
		input.nextLine();
		System.out.println("==============游  戏  开  始==============");
		System.out.println("以下为玩家初始基本信息：");
	}

	public static int getMenuChoice() {
		for (int i = 0; i < MAINMENU.length; i++) {
			System.out.println(MAINMENU[i]);
		}
		int choice = getAndCheck("请输入数字表示选项", 0, MAINMENU.length - 1);
		return choice;
	}

	public static int getProp(ArrayList<String> strs) {
		if (strs.size() < 1) {
			System.out.println("无道具");
			return -1;
		}
		for (int i = 0; i < strs.size(); i++) {
			System.out.printf("%d-%s  ", i, strs.get(i));
			if (i % 5 == 4)
				System.out.println();
		}
		System.out.println();
		// String reg = "^[0-" + (strs.size() - 1) + "xXhH]$";
		String rs = getAndCheck("请输入您想要的卡片编号(输入h获得帮助，输入x返回上一层)", 0,
				strs.size() - 1, "x", "h");
		if (rs.equals("x")) {
			return -1;
		} else if (rs.equals("h")) {
			return -2;
		} else {
			return Integer.parseInt(rs);
		}
	}

	public static int getBuyProp() {
		for (int i = 0; i < Prop.values().length; i++) {
			System.out.printf("%d-%s  ", i, Prop.values()[i].toText());
			if (i % 5 == 4)
				System.out.println();
		}
		System.out.println();
		// String reg = "^[0-" + (Prop.values().length - 1) + "xX]$";
		String rs = getAndCheck("请输入您想要的卡片编号(x-退出)", 0,
				Prop.values().length - 1, "x", "h");
		if (rs.equals("x")) {
			return -1;
		} else if (rs.equals("h")) {
			return -2;
		} else {
			return Integer.parseInt(rs);
		}
	}

	public static int getSaveOrDrawMoney() {
		int choice = getAndCheck("0-退出	1-取钱	2-存钱\n请选择:", 0, 2);
		if (choice == 0)
			return 0;
		int saveMoney = getAndCheck("请输入需要存/取的数目:", 0, Integer.MAX_VALUE);
		if (choice == 1)
			saveMoney = -saveMoney;
		return saveMoney;
	}

	public static int getChoosePlayer(LinkedList<String> l) {
		int[] index = { 0 };
		l.stream().forEach(i -> {
			System.out.printf("%d-%s\n", (index[0]++), i);
		});
		return getDistanceChoice("请输入玩家编号(x-取消)", 0, l.size() - 1);
	}

	public static boolean getYesOrNo(String str) {
		return getAndCheck(str + "(0-取消	1-确定)", 0, 1) == 1;
		// return Integer.parseInt(getAndCheck(str + "(0-取消	1-确定)", "^[0-1]$"))
		// == 1;
	}

	public static int getDistanceChoice(String str, int lb, int ub) {
		// "(\\-1[0-2]|\\-[1-9])|[0-9]|1[0-2]"
		// String reg = "\\-[1-8]|[0-8]";
		// String rs = getAndCheck("请输入前后方8格内的数字(负数表示逆时针方向)", reg);
		String rs = getAndCheck(str, lb, ub, "x");
		if (rs.equals("x"))
			return ub + 1;
		return Integer.parseInt(rs);
	}

	public static int getDice() {
		String dice = getAndCheck("请输入投掷的点数(x-取消):", 1, 6, "x");
		if (dice.equals("x"))
			return 7;
		return Integer.parseInt(dice);
	}

	public static void printString(String str) {
		System.out.println(str);
	}

	public static void printStringArray2(String[][] str) {
		for (int y = 0; y < str[y].length; y++) {
			for (int x = 0; x < str.length; x++) {
				System.out.print(str[x][y]);
			}
			System.out.println();
		}
	}

	public static int[] getStock(int[] amount) {
		System.out.println("序号\t股票名\t\t单价\t\t涨跌幅\t\t持有数");
		for (int i = 0; i < Stock.values().length; i++) {
			System.out.println(i + "\t" + Stock.values()[i].getDescription()
					+ "\t\t" + amount[i]);
		}
		System.out.println("请按以下格式输入：");
		System.out
				.println("输入b x n表示买入序号为x的股票n股,s x n表示卖出序号为x的股票的股票 n股,直接输入x-退出");
		while (true) {
			String inputStr = input.nextLine();
			String[] strs = inputStr.split(" +");
			int[] data = { -1, 0 };
			if (strs[0].equals("x"))
				return data;
			if (strs.length != 3) {
				System.out.println("输入错误");
				continue;
			}
			if (!(InputCheck.check(strs[1], NUMBERREGULAR) && InputCheck.check(
					strs[2], NUMBERREGULAR))) {
				System.out.println("输入错误");
				continue;
			}
			data[0] = Integer.parseInt(strs[1]);
			if (data[0] >= 0 && data[0] < amount.length) {
				System.out.println("输入错误");
				continue;
			}
			if (strs[0].equals("s")) {
				data[1] = -Integer.parseInt(strs[1]);
			} else if (strs[0].equals("b")) {
				data[1] = Integer.parseInt(strs[1]);
			} else {
				System.out.println("输入错误");
				continue;
			}
			return data;
		}
	}

	public static void inputClose() {
		input.close();
	}

	private static String getAndCheck(String regular) {
		while (true) {
			String inputStr = input.nextLine();
			if (InputCheck.check(inputStr, regular)) {
				return inputStr;
			}
			System.out.println("输入错误");
		}
	}

	private static int getAndCheck(String message, int lb, int ub) {
		while (true) {
			System.out.println(message);
			String inputStr = input.nextLine();
			if (InputCheck.check(inputStr, NUMBERREGULAR)) {
				int rs = Integer.parseInt(inputStr);
				if (rs >= lb && rs <= ub)
					return rs;
			}
			System.out.println("输入错误");
		}
	}

	private static String getAndCheck(String message, int lb, int up,
			String... strs) {
		while (true) {
			System.out.println(message);
			String inputStr = input.nextLine().toLowerCase();
			for (String str : strs) {
				if (inputStr.equals(str))
					return inputStr;
			}
			if (InputCheck.check(inputStr, NUMBERREGULAR)) {
				int rs = Integer.parseInt(inputStr);
				if (rs >= lb && rs <= up)
					return String.valueOf(rs);
			}
			System.out.println("输入错误");
		}
	}

	private static String getAndCheck(String message, String regular) {
		while (true) {
			System.out.println(message);
			String inputStr = input.nextLine();
			if (InputCheck.check(inputStr, regular)) {
				return inputStr;
			}
			System.out.println("输入错误," + message);
		}
	}

	/*
	 * private static String getAndCheck(String message, String regular, String
	 * returnMessage) { String rs = getAndCheck(message, regular);
	 * System.out.println(returnMessage); return rs; }
	 */

}
