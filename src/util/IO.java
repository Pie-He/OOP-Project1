package util;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import type.*;

public class IO {
	private final static String[] MAINMENU = { "0-查看地图", "1-查看原始地图", "2-使用道具",
			"3-查看10步十步以内示警", "4-查看前后指定步数的具体信息", "5-查看玩家的资产信息",
			"6-想看的都看了，心满意足的扔骰子", "7-不玩了，认输", "8-股票" };
	private final static String[] propHelp = {
			"路障:可以在前后8步之内安放一个路障，任意玩家经过路障时会停在路障所在位置不能前行",
			"遥控骰子:使用时可以任意控制骰子的结果，结果只能是1-6", "转向卡:使自己或距离自己五步以内的对手标掉转方向",
			"滞留卡:该回合停留在原地，并再次触发原地事件",
			"查税卡:强行将距离自己五步以内的对手30%的存款缴税(所缴税款并不给查税卡的发动者)", "均富卡:将所有人的现金平均分配",
			"掠夺卡:距离自己五步以内的对手 随机将对手的一张卡牌据为己有" };
	private static Scanner input = new Scanner(System.in);

	public static int getPlayerNumber() {
		return getAndCheck("请输入玩家人数(2-4):", 2, 4);
	}

	public static Collection<String> getPlayerName(int number) {
		Deque<String> players = new LinkedList<String>();
		for (int i = 0; i < number; i++) {
			String name = getAndCheck("请输入玩家" + (i + 1) + "名字(数字、字母、下划线):",
					"^\\w+$");
			players.add(name);
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
		String rs;
		while ((rs = getAndCheck("请输入您想要的卡片编号(输入h获得帮助，输入x返回上一层)", 0,
				strs.size() - 1, "x", "h")).equals("h")) {
			for (String str : propHelp)
				System.out.println(str);
			System.out.println();
		}
		if (rs.equals("x")) {
			return -1;
		} else {
			return Integer.parseInt(rs);
		}
	}

	public static int getBuyProp() {
		for (int i = 0; i < Prop.values().length; i++) {
			System.out.printf("%d-%s  %s\n", i, Prop.values()[i].toText(),
					+Prop.values()[i].getPrice() + "点券");
		}
		System.out.println();
		// String reg = "^[0-" + (Prop.values().length - 1) + "xX]$";
		String rs;
		while ((rs = getAndCheck("请输入您想要的卡片编号(输入h获得帮助，输入x返回上一层)", 0,
				Prop.values().length - 1, "x", "h")).equals("h")) {
			for (String str : propHelp)
				System.out.println(str);
			System.out.println();
		}
		if (rs.equals("x")) {
			return -1;
		} else if (rs.equals("h")) {
			return -2;
		} else {
			return Integer.parseInt(rs);
		}
	}

	public static int getSaveOrDrawMoney() {
		String choiceStr = getAndCheck("x-退出	1-取钱	2-存钱\n请选择:", 1, 2, "x");
		if (choiceStr.equals("x"))
			return 0;
		int choice = Integer.parseInt(choiceStr);
		int saveMoney = getAndCheck("请输入需要存/取的数目:", 0, Integer.MAX_VALUE);
		if (choice == 1)
			saveMoney = -saveMoney;
		return saveMoney;
	}

	public static int getChoosePlayer(LinkedList<String> l) {
		if (l.size() == 0) {
			System.out.println("无目标");
			return 0;
		}
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

	public static int[] getStock(int[] amount) {
		System.out.print(Tools.stringCover(16, "Order Number"));
		System.out.println(Tools.stringCover(16, "Name", "Price",
				"Rise and Fall", "Holds"));
		// System.out.println("序号\t股票名\t\t单价\t\t涨跌幅\t\t持有数");
		for (int i = 0; i < Stock.values().length; i++) {
			System.out.println(Tools.stringCover(16, String.valueOf(i),
					Stock.values()[i].getDescription(),
					String.valueOf(amount[i])));
		}
		System.out.println("请按以下格式输入：");
		System.out
				.println("输入b x n表示买入序号为x的股票n股,s x n表示卖出序号为x的股票的股票 n股,直接输入x-退出");
		while (true) {
			String inputStr = input.nextLine();
			String[] strs = inputStr.split(" +");
			int[] data = { -1, 0, 0 };
			if (strs[0].equals("x"))
				return data;
			if (strs.length != 3) {
				System.out.println("输入错误");
				continue;
			}
			// System.out.println(strs.length + strs[0]);
			if (strs[0].equals("s")) {
				data[0] = 1;
			} else if (strs[0].equals("b")) {
				data[0] = 0;
			} else {
				System.out.println("输入错误");
				continue;
			}
			if (!(checkInteger(strs[1]) && checkInteger(strs[2]))) {
				System.out.println("输入错误");
				continue;
			}
			data[2] = Integer.parseInt(strs[2]);
			data[1] = Integer.parseInt(strs[1]);
			if (data[1] < 0 || data[1] > amount.length) {
				System.out.println("输入错误");
				continue;
			}
			return data;
		}
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

	public static void inputClose() {
		input.close();
	}

	private static int getAndCheck(String message, int lb, int ub) {
		while (true) {
			System.out.println(message);
			String inputStr = input.nextLine();
			if (checkInteger(inputStr)) {
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
			if (checkInteger(inputStr)) {
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
			if (check(inputStr, regular)) {
				return inputStr;
			}
			System.out.println("输入错误," + message);
		}
	}

	private static boolean checkInteger(String input) {
		Pattern pattern = Pattern.compile("^[+-]?[0-9]+$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	private static boolean check(String inputStr, String regular) {
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();
	}
}
