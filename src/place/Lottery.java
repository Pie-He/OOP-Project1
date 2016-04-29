package place;

import type.Player;
import util.Const;
import util.IO;

public class Lottery extends Place {
	public Lottery() {

	}

	public Lottery(int x, int y, String symbol) {
		super(x, y, symbol, "²ÊÆ±");
	}

	@Override
	public boolean event(Player p) {
		super.event(p);
		if (!IO.getYesOrNo(Const.LOTTERY_BUT_OR_NOT)) {
			return true;
		}
		if (!p.addCash(-2000)) {
			IO.printString(Const.CASH_NOT_ENOUGH);
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
		IO.printString(Const.LOTTERY_SUCCESS.toString() + lottery);
		p.addCash(lottery);
		return true;
	}

}
