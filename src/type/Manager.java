package type;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import util.IO;
import util.Tools;

public class Manager {
	private static final Manager MANAGER = new Manager();

	private Manager() {
	}

	public static Manager getInstance() {
		return MANAGER;
	}

	public static LinkedList<Player> players;
	final private static String[] PLSYMBOL = { "��", "��", "��", "��" };
	final private static String[] HSSYMBOL = { "��", "��", "��", "��" };
	/*
	 * static{
	 * player.stream().filter(item->(item.getCash()==10000)).findFirst(); }
	 */
	final private static SimpleDateFormat SDF = new SimpleDateFormat(
			"������yyyy��MM��dd�� EE");
	private Map map = Map.getInstance();
	static int diceFlag = -1;
	private Calendar calendar = Calendar.getInstance();

	public void start() throws IOException {
		int playerNum = IO.getPlayerNumber();
		int index = 0;
		players = new LinkedList<Player>();
		for (String name : IO.getPlayerName(playerNum)) {
			players.add(new Player(name, PLSYMBOL[index], HSSYMBOL[index++]));
		}
		calendar.set(2016, 0, 1);
		// News.news4();
		IO.getReady();
		/*
		 * // Player player = Manager.players.pop(); //Player player = new
		 * Player(); players.add(player); player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.remoteBoson);
		 */
		map.init(players);
		for (int i = 0; i < 20; i++)
			players.getFirst().addProp(Prop.remoteBoson);
		for (int i = 0; i < 20; i++) {
			players.get(1).addProp(Prop.remoteBoson);
			players.get(1).addProp(Prop.stopCard);
		}
		EVENT: {
			while (calendar.get(Calendar.YEAR) < 2017) {
				IO.printString(SDF.format(calendar.getTime()));
				if (!isWeekend()) {
					for (Stock s : Stock.values()) {
						s.change();
					}
				}
				for (int i = 0; i < players.size(); i++) {
					if (!this.event(players.get(i))) {
						players.remove(i);
						i--;
					}
					if (players.size() == 1) {
						IO.printString("��Ϸ������" + players.peek().getName()
								+ "��ʤ������");
						break EVENT;
					}
				}
				if (this.isMonthLast()) {
					IO.printString("�µ׷��Ŵ�����Ϣ������");
					players.stream().forEach(i -> {
						int m = i.getDeposit() / 10;
						IO.printString(i.getName() + "�����Ϣ" + m);
						i.addDeposit(m);
					});
				}
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		IO.inputClose();
	}

	public void fail(Player p) {
		IO.printString(p.getName() + "ʧ�ܣ�");
		p.fail();
	}

	private boolean event(Player player) {
		IO.printString("���������\"" + player.getName() + "\"����ʱ�䣬����ǰ��������"
				+ ((player.getDirection() > 0) ? "˳ʱ��" : "��ʱ��"));
		while (true) {
			int choice = IO.getMenuChoice();
			switch (choice) {
			case 0:
				IO.printStringArray2(map.toText());
				break;
			case 1:
				IO.printStringArray2(map.getInitalMap());
				break;
			case 2:
				int propChoice;
				while ((propChoice = IO.getProp(player.propToText())) >= 0) {
					player.useProp(propChoice);
					if (diceFlag >= 0) {
						boolean is = map.event(player, diceFlag);
						IO.printStringArray2(map.toText());
						diceFlag = -1;
						return is;
					}
				}
				break;
			case 3:
				warning(player);
				break;
			case 4:
				int dis = IO.getDistanceChoice(
						"�����������ѯ�ĵ��������Ĳ���(���ø�����ʾ��x-�˳�)", -map.mapLength,
						map.mapLength);
				IO.printString(map.getDescription(player.getPrePoi(dis)));
				break;
			case 5:
				IO.printString(Tools.stringCover(16, "Name", "Coupon", "Cash",
						"Deposit", "House Property", "Total Property"));
				for (Player p : players)
					IO.printString(p.getMessage());
				break;
			case 6:
				int dice = this.randomDice();
				IO.printString("Ͷ������:" + dice);
				boolean is = map.event(player, dice);
				IO.printStringArray2(map.toText());
				return is;
			case 7:
				player.fail();
				return false;
			case 8:
				if(isWeekend()){
					IO.printString("��ĩ���У�����");
				}else{
					stockMarket(player);
				}
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
		l.stream().forEach(i -> IO.printString("ǰ��" + i + "����·�ϣ�����"));
		if (l.size() == 0)
			IO.printString("ǰ����·��");
	}

	private int randomDice() {
		return (int) (Math.random() * 6) + 1;
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

	private boolean isWeekend() {
		return calendar.get(Calendar.DAY_OF_WEEK) == 1
				|| calendar.get(Calendar.DAY_OF_WEEK) == 7;
	}

	private void stockMarket(Player player) {
		while (true) {
			int len = Stock.values().length;
			int[] amount = new int[len];
			for (int i = 0; i < len; i++) {
				amount[i] = player.getStockAmount(Stock.values()[i]);
			}
			int[] data = IO.getStock(amount);// 0-b or s 1-which stock
												// 2-amount
			// ��Ʊ�ж�
			if (data[0] < 0)
				return;
			if (data[0] == 0)
				Stock.values()[data[1]].buyStock(player, data[2]);
			else
				Stock.values()[data[1]].sellStock(player, data[2]);
		}
	}
}
