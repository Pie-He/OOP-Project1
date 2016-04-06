package util;

import java.io.IOException;
import java.util.Scanner;

import type.Player;

public class Output {
	final private static String[] PLSYMBOL = { "□", "■", "△", "▲" };
	final private static String[] HSSYMBOL = { "○", "●", "☆", "★" };
	private final static String[] MainMenu = { "0-查看地图", "1-查看原始地图", "2-使用道具",
			"3-查看10步十步以内示警", "4-查看前后指定步数的具体信息", "5-查看玩家的资产信息",
			"6-想看的都看了，心满意足的扔骰子", "7-不玩了，认输" };
	private static Scanner input = new Scanner(System.in);

	public static int getPlayerNumber() {
		return Integer.parseInt(getAndCheck("请输入玩家人数(2-4):", "^[2-4]$"));
	}

	public static void getPlayerName(int number) {
		for (int i = 0; i < number; i++) {
			String name = getAndCheck("请输入玩家" + (i + 1) + "名字(数字、字母、下划线):",
					"^\\w+$");
			Player player = new Player(name, PLSYMBOL[i], HSSYMBOL[i]);
			System.out.println(player.getDescription());
		}
	}

	public static void getReady() throws IOException {
		System.out.println("准备好后，请按回车键开始游戏");
		System.in.read();
		System.out.println("==============游  戏  开  始==============");
		System.out.println("以下为玩家初始基本信息：");
	}

	public static int getMenuChoice() {
		for (int i = 0; i < MainMenu.length; i++) {
			System.out.println(MainMenu[i]);
		}
		int choice = Integer.parseInt(getAndCheck("请输入数字表示选项", "^[0-7]$"));
		return choice;

	}

	public static String getAndCheck(String regular) {
		while (true) {
			String inputStr = input.nextLine();
			if (InputCheck.check(inputStr, regular)) {
				return inputStr;
			}
			System.out.println("输入错误");
		}
	}

	public static void getMap(String[][] map) {
		for (int y = 0; y < map[y].length; y++) {
			for (int x = 0; x < map.length; x++) {
				System.out.print(map[x][y]);
			}
			System.out.println();
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

	private static String getAndCheck(String message, String regular,
			String returnMessage) {
		String rs = getAndCheck(message, regular);
		System.out.println(returnMessage);
		return rs;
	}

	public static void inputClose() {
		input.close();
	}
}
