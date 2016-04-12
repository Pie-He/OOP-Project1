package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import type.Player;

public class Output {
	final private static String[] PLSYMBOL = { "��", "��", "��", "��" };
	final private static String[] HSSYMBOL = { "��", "��", "��", "��" };
	private final static String[] MainMenu = { "0-�鿴��ͼ", "1-�鿴ԭʼ��ͼ", "2-ʹ�õ���",
			"3-�鿴10��ʮ������ʾ��", "4-�鿴ǰ��ָ�������ľ�����Ϣ", "5-�鿴��ҵ��ʲ���Ϣ",
			"6-�뿴�Ķ����ˣ����������������", "7-�����ˣ�����" };
	private static Scanner input = new Scanner(System.in);

	public static int getPlayerNumber() {
		return Integer.parseInt(getAndCheck("�������������(2-4):", "^[2-4]$"));
	}

	public static void getPlayerName(int number) {
		for (int i = 0; i < number; i++) {
			String name = getAndCheck("���������" + (i + 1) + "����(���֡���ĸ���»���):",
					"^\\w+$");
			Player player = new Player(name, PLSYMBOL[i], HSSYMBOL[i]);
			//System.out.println(player.getDescription());
		}
	}

	public static void getReady() throws IOException {
		System.out.println("׼���ú��밴�س�����ʼ��Ϸ");
		System.in.read();
		System.out.println("==============��  Ϸ  ��  ʼ==============");
		System.out.println("����Ϊ��ҳ�ʼ������Ϣ��");
	}

	public static int getMenuChoice() {
		for (int i = 0; i < MainMenu.length; i++) {
			System.out.println(MainMenu[i]);
		}
		int choice = Integer.parseInt(getAndCheck("���������ֱ�ʾѡ��", "^[0-7]$"));
		return choice;

	}

	public static int getProp(ArrayList<String> strs) {
		for (int i = 0; i < strs.size(); i++) {
			System.out.printf("%d-%s  ", i, strs.get(i));
			if (i == 5)
				System.out.println();
		}
		System.out.println();
		String reg = "^[0-" + (strs.size() - 1) + "xXhH]$";
		String rs = getAndCheck("����������Ҫ�Ŀ�Ƭ���(����h��ð���������x������һ��)", reg);
		if (rs.toLowerCase().equals("x")) {
			return -1;
		} else if (rs.toLowerCase().equals("h")) {
			return -2;
		} else {
			return Integer.parseInt(rs);
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

	private static String getAndCheck(String regular) {
		while (true) {
			String inputStr = input.nextLine();
			if (InputCheck.check(inputStr, regular)) {
				return inputStr;
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
