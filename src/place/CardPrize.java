package place;

import type.*;
import util.IO;

public class CardPrize extends Place {
	public CardPrize() {

	}

	public CardPrize(int x, int y, String symbol) {
		super(x, y, symbol, "赠送道具店");
	}

	@Override
	public boolean event(Player p) {
		super.event(p);
		int random = (int) (Math.random() * Prop.values().length);
		Prop prop = Prop.values()[random];
		IO.printString("恭喜！获得“" + prop.toText() + "”1个！");
		p.addProp(prop);
		return true;
	}
}
