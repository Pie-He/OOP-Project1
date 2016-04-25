package place;

import type.Player;
import util.Output;

public class Lottery extends Place {
	public Lottery() {

	}

	public Lottery(int x, int y, String symbol) {
		super(x, y, symbol, "²ÊÆ±");
	}

	@Override
	public void event(Player p) {
		super.event(p);
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
		Output.printString("¹§Ï²£¡²ÊÆ±ÖÐ½± " + lottery + "Ôª£¡");
		p.addCash(lottery);
	}

}
