package type;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import util.Output;

public class Manager {
	public static LinkedList<Player> players;
	final private static String[] PLSYMBOL = { "��", "��", "��", "��" };
	final private static String[] HSSYMBOL = { "��", "��", "��", "��" };
	/*
	 * static{
	 * player.stream().filter(item->(item.getCash()==10000)).findFirst(); }
	 */
	final private static SimpleDateFormat SDF = new SimpleDateFormat(
			"������yyyy��MM��dd��");
	static Map map = new Map(39, 19);
	static int diceFlag = -1;
	private Calendar calendar = Calendar.getInstance();

	public void start() throws IOException {
		int playerNum = Output.getPlayerNumber();
		int index = 0;
		players = new LinkedList<Player>();
		for (String name : Output.getPlayerName(playerNum)) {
			players.add(new Player(name, PLSYMBOL[index], HSSYMBOL[index++]));
		}
		calendar.set(2016, 0, 1);
		// News.news4();
		Output.getReady();
		/*
		 * // Player player = Manager.players.pop(); //Player player = new
		 * Player(); players.add(player); player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.remoteBoson);
		 */
		map.init(players);
		for (int i = 0; i < 20; i++)
			players.getFirst().addProp(Prop.plunderCard);
		for (int i = 0; i < 20; i++)
			players.get(1).addProp(Prop.taxInspectionCard);
		while (calendar.get(Calendar.YEAR) < 2017) {
			Output.printString(SDF.format(calendar.getTime()));
			for (int i = 0; i < players.size(); i++)
				this.event(players.get(i));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		Output.inputClose();
	}

	private boolean event(Player player) {
		Output.printString("��������ҡ�" + player.getName() + "������ʱ�䣬����ǰ��������"
				+ ((player.getDirection() > 0) ? "˳ʱ��" : "��ʱ��"));
		while (true) {
			int choice = Output.getMenuChoice();
			switch (choice) {
			case 0:
				Output.printStringArray2(map.toText());
				break;
			case 1:
				Output.printStringArray2(map.getInitalMap());
				break;
			case 2:
				int propChoice;
				while ((propChoice = Output.getProp(player.propToText())) >= 0) {
					player.useProp(propChoice);
					if (diceFlag >= 0) {
						map.event(player, diceFlag);
						diceFlag = -1;
						return true;
					}
				}
				break;
			case 3:
				warning(player);
				break;
			case 4:
				int dis = Output.getDistanceChoice(
						"�����������ѯ�ĵ��������Ĳ���(���ø�����ʾ��x-�˳�)", -map.mapLength,
						map.mapLength);
				Output.printString(map.getDescription(player.getPrePoi(dis)));
				break;
			case 5:
				Output.printString("�����\t\t��ȯ\t\t�ֽ�\t\t���\t\t����\t\t�ʲ��ܶ�");
				for (Player p : players)
					Output.printString(p.getMessage());
				break;
			case 6:
				int dice = this.randomDice();
				Output.printString("Ͷ������:" + dice);
				map.event(player, dice);
				Output.printStringArray2(map.toText());
				return true;
			case 7:
				player.fail();
				return false;
			case 8:
				break;
			}
		}
	}

	private void warning(Player player) {
		List<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < 11; i++) {
			if (map.isBlock(player.getPrePoi(i)))
				l.add(i);
		}
		l.stream().forEach(i -> Output.printString("ǰ��" + i + "����·�ϣ�����"));
		if (l.size() == 0)
			Output.printString("ǰ����·��");
	}

	private int randomDice() {
		return (int) (Math.random() * 6) + 1;
	}

	public static void fail(Player p) {

	}

	private boolean isMonthLast() {
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		boolean is = false;
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			is = true;
		}
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return is;
	}

	private void stockMarket(Player player) {
		int len = Stock.values().length;
		int[] amount = new int[len];
		for (int i = 0; i < len; i++) {
			amount[i] = player.getStockAmount(Stock.values()[i]);
		}
		int[] data=Output.getStock(amount);
		//��Ʊ�ж�
	}
}
