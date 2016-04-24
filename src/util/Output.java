package util;

import java.io.IOException;
import java.util.*;

import type.Player;
import type.Prop;
import type.Stock;

public class Output {
	private final static String[] MAINMENU = { "0-�鿴��ͼ", "1-�鿴ԭʼ��ͼ", "2-ʹ�õ���",
			"3-�鿴10��ʮ������ʾ��", "4-�鿴ǰ��ָ�������ľ�����Ϣ", "5-�鿴��ҵ��ʲ���Ϣ",
			"6-�뿴�Ķ����ˣ����������������", "7-�����ˣ�����", "8-��Ʊ" };
	private final static String NUMBERREGULAR = "-?\\d+$";
	private static Scanner input = new Scanner(System.in);

	public static int getPlayerNumber() {
		return getAndCheck("�������������(2-4):", 2, 4);
	}

	public static Deque<String> getPlayerName(int number) {
		Deque<String> players = new LinkedList<String>();
		for (int i = 0; i < number; i++) {
			String name = getAndCheck("���������" + (i + 1) + "����(���֡���ĸ���»���):",
					"^\\w+$");
			players.add(name);
			// players.add(new Player(name, PLSYMBOL[i], HSSYMBOL[i]));
			// System.out.println(player.getDescription());
		}
		return players;
	}

	public static void getReady() throws IOException {
		System.out.println("׼���ú��밴�س�����ʼ��Ϸ");
		input.nextLine();
		System.out.println("==============��  Ϸ  ��  ʼ==============");
		System.out.println("����Ϊ��ҳ�ʼ������Ϣ��");
	}

	public static int getMenuChoice() {
		for (int i = 0; i < MAINMENU.length; i++) {
			System.out.println(MAINMENU[i]);
		}
		int choice = getAndCheck("���������ֱ�ʾѡ��", 0, MAINMENU.length - 1);
		return choice;
	}

	public static int getProp(ArrayList<String> strs) {
		if (strs.size() < 1) {
			System.out.println("�޵���");
			return -1;
		}
		for (int i = 0; i < strs.size(); i++) {
			System.out.printf("%d-%s  ", i, strs.get(i));
			if (i % 5 == 4)
				System.out.println();
		}
		System.out.println();
		// String reg = "^[0-" + (strs.size() - 1) + "xXhH]$";
		String rs = getAndCheck("����������Ҫ�Ŀ�Ƭ���(����h��ð���������x������һ��)", 0,
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
		String rs = getAndCheck("����������Ҫ�Ŀ�Ƭ���(x-�˳�)", 0,
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
		int choice = getAndCheck("0-�˳�	1-ȡǮ	2-��Ǯ\n��ѡ��:", 0, 2);
		if (choice == 0)
			return 0;
		int saveMoney = getAndCheck("��������Ҫ��/ȡ����Ŀ:", 0, Integer.MAX_VALUE);
		if (choice == 1)
			saveMoney = -saveMoney;
		return saveMoney;
	}

	public static int getChoosePlayer(LinkedList<String> l) {
		int[] index = { 0 };
		l.stream().forEach(i -> {
			System.out.printf("%d-%s\n", (index[0]++), i);
		});
		return getDistanceChoice("��������ұ��(x-ȡ��)", 0, l.size() - 1);
	}

	public static boolean getYesOrNo(String str) {
		return getAndCheck(str + "(0-ȡ��	1-ȷ��)", 0, 1) == 1;
		// return Integer.parseInt(getAndCheck(str + "(0-ȡ��	1-ȷ��)", "^[0-1]$"))
		// == 1;
	}

	public static int getDistanceChoice(String str, int lb, int ub) {
		// "(\\-1[0-2]|\\-[1-9])|[0-9]|1[0-2]"
		// String reg = "\\-[1-8]|[0-8]";
		// String rs = getAndCheck("������ǰ��8���ڵ�����(������ʾ��ʱ�뷽��)", reg);
		String rs = getAndCheck(str, lb, ub, "x");
		if (rs.equals("x"))
			return ub + 1;
		return Integer.parseInt(rs);
	}

	public static int getDice() {
		String dice = getAndCheck("������Ͷ���ĵ���(x-ȡ��):", 1, 6, "x");
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
		System.out.println("���\t��Ʊ��\t\t����\t\t�ǵ���\t\t������");
		for (int i = 0; i < Stock.values().length; i++) {
			System.out.println(i + "\t" + Stock.values()[i].getDescription()
					+ "\t\t" + amount[i]);
		}
		System.out.println("�밴���¸�ʽ���룺");
		System.out
				.println("����b x n��ʾ�������Ϊx�Ĺ�Ʊn��,s x n��ʾ�������Ϊx�Ĺ�Ʊ�Ĺ�Ʊ n��,ֱ������x-�˳�");
		while (true) {
			String inputStr = input.nextLine();
			String[] strs = inputStr.split(" +");
			int[] data = { -1, 0 };
			if (strs[0].equals("x"))
				return data;
			if (strs.length != 3) {
				System.out.println("�������");
				continue;
			}
			if (!(InputCheck.check(strs[1], NUMBERREGULAR) && InputCheck.check(
					strs[2], NUMBERREGULAR))) {
				System.out.println("�������");
				continue;
			}
			data[0] = Integer.parseInt(strs[1]);
			if (data[0] >= 0 && data[0] < amount.length) {
				System.out.println("�������");
				continue;
			}
			if (strs[0].equals("s")) {
				data[1] = -Integer.parseInt(strs[1]);
			} else if (strs[0].equals("b")) {
				data[1] = Integer.parseInt(strs[1]);
			} else {
				System.out.println("�������");
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
			System.out.println("�������");
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
			System.out.println("�������");
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
			System.out.println("�������");
		}
	}

	private static String getAndCheck(String message, String regular) {
		while (true) {
			System.out.println(message);
			String inputStr = input.nextLine();
			if (InputCheck.check(inputStr, regular)) {
				return inputStr;
			}
			System.out.println("�������," + message);
		}
	}

	/*
	 * private static String getAndCheck(String message, String regular, String
	 * returnMessage) { String rs = getAndCheck(message, regular);
	 * System.out.println(returnMessage); return rs; }
	 */

}
