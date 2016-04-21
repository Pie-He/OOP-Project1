package place;

import java.util.Deque;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;

import type.Manager;
import type.Player;
import type.Prop;
import util.Output;

public class News extends Place {
	public News() {

	}

	public News(int x, int y, String symbol) {
		super(x, y, symbol, "����");
	}

	@Override
	public void event(Player p) {
		// TODO Auto-generated method stub
		super.event(p);
		int random = (int) (Math.random() * 5);// ���0~4�����
		switch (random) {
		case 0: {
			news(0);
			break;
		}
		case 1: {
			news(1);
			break;
		}
		case 2: {
			Manager.players.stream()
					.map(i -> i.addDeposit(i.getDeposit() / 10));
			Output.printString("���мӷ��������ÿ���˵õ����10%");
			break;
		}
		case 3: {
			Manager.players.stream().map(
					i -> i.addDeposit(-i.getDeposit() / 10));
			Output.printString("�����˽��ɲƲ�˰10%");
			break;
		}
		case 4: {
			news4();
			break;
		}
		}
	}

	private void news4() {
		// TODO Auto-generated method stub
		Output.printString("ÿ���˵õ�һ�ſ�Ƭ");
		Manager.players.stream().forEach(
				i -> {
					int random = (int) (Math.random() * Prop.values().length);
					Prop prop = Prop.values()[random];
					i.addProp(prop);
					Output.printString("��ϲ��" + i.getName() + "��á�"
							+ prop.toText() + "��1����");
				});
	}

	private void news(int choice) {
		int rewards = ((int) (Math.random() * 10 + 1)) * 1000;
		Deque<Player> pls = new LinkedList<Player>();
		/*
		 * int max = 0; for (Player player : Manager.players) { if
		 * (player.getHouseAmount() < max) continue; if (player.getHouseAmount()
		 * > max) { pls.clear(); } pls.add(player); }
		 * 
		 * pls.stream().forEach(i -> i.addCash(rewards));
		 */

		IntSummaryStatistics stats = Manager.players.stream()
				.mapToInt((x) -> x.getHouseAmount()).summaryStatistics();

		int tmp = choice == 0 ? stats.getMax() : stats.getMin();
		String str = choice == 0 ? "���������һ���� " : "������������������ ";

		Manager.players.stream().filter(i -> i.getHouseAmount() == tmp)
				.forEach(pls::add);
		Output.printString(str
				+ pls.stream().map(i -> i.getName())
						.reduce((x, y) -> x += (" " + y + " ")) + "����"
				+ rewards + "Ԫ");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "����" + getType() + "\n";
	}
}
