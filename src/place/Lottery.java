package place;

import type.Player;
import util.IO;

public class Lottery extends Place {
	public Lottery() {

	}

	public Lottery(int x, int y, String symbol) {
		super(x, y, symbol, "彩票");
	}

	@Override
	public boolean event(Player p) {
		super.event(p);
		if (!IO.getYesOrNo("是否要支付2000元买彩票")) {
			return true;
		}
		if (p.addCash(-2000)) {
			IO.printString("现金不足");
			return true;
		}
		int random = (int) (Math.random() * 10 + 1);
		int lottery = 0;
		if (random == 1)
			lottery = 10000;
		else if (1 < random && random <= 3)
			lottery = 5000;
		else if (3 < random && random <= 6)
			lottery = 2000;
		else
			lottery = 1000;
		IO.printString("恭喜！彩票中奖 " + lottery + "元！");
		p.addCash(lottery);
		return true;
	}

}
