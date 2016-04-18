package place;

import java.util.Deque;
import java.util.LinkedList;

import type.Manager;
import type.Player;
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
		int random = (int) (Math.random() * 5);// 获得0~4随机数
		switch (random) {
		case 0: {
			news0(p);
			break;
		}
		case 1: {
			news1(p);
			break;
		}
		case 2: {
			news2(p);
			break;
		}
		case 3: {
			news3(p);
			break;
		}
		case 4: {
			news4(p);
			break;
		}
		}
	}

	private void news4(Player p) {
		// TODO Auto-generated method stub

	}

	private void news3(Player p) {
		// TODO Auto-generated method stub

	}

	private void news2(Player p) {
		// TODO Auto-generated method stub

	}

	private void news1(Player p) {
		// TODO Auto-generated method stub

	}

	private void news0(Player p) {
		// TODO Auto-generated method stub
		int rewards = ((int) (Math.random() * 10 + 1)) * 1000;
		Deque<Player> pls = new LinkedList<Player>();
		int max = 0;
		for (Player player : Manager.players) {
			if (player.getHouseAmount() < max)
				continue;
			if (player.getHouseAmount() > max) {
				pls.clear();
			}
			pls.add(player);
		}
		pls.stream().forEach(i -> i.addCash(rewards));
		Output.printString("公开表扬第一地主"
				+ pls.stream().map(i -> i.getName() + " ") + "奖励" + rewards
				+ "元");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
