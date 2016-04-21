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
		super(x, y, symbol, "新闻");
	}

	@Override
	public void event(Player p) {
		// TODO Auto-generated method stub
		super.event(p);
		int random = (int) (Math.random() * 5);// 获得0~4随机数
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
			Output.printString("银行加发储金红利每个人得到存款10%");
			break;
		}
		case 3: {
			Manager.players.stream().map(
					i -> i.addDeposit(-i.getDeposit() / 10));
			Output.printString("所有人缴纳财产税10%");
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
		Output.printString("每个人得到一张卡片");
		Manager.players.stream().forEach(
				i -> {
					int random = (int) (Math.random() * Prop.values().length);
					Prop prop = Prop.values()[random];
					i.addProp(prop);
					Output.printString("恭喜！" + i.getName() + "获得“"
							+ prop.toText() + "”1个！");
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
		String str = choice == 0 ? "公开表扬第一地主 " : "公开补助土地最少者 ";

		Manager.players.stream().filter(i -> i.getHouseAmount() == tmp)
				.forEach(pls::add);
		Output.printString(str
				+ pls.stream().map(i -> i.getName())
						.reduce((x, y) -> x += (" " + y + " ")) + "奖励"
				+ rewards + "元");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "类型" + getType() + "\n";
	}
}
