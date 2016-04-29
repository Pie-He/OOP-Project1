package type.place;

import type.item.Player;

public class Space extends Place {
	public Space() {

	}

	public Space(int x, int y, String symbol) {
		super(x, y, symbol, "¿ÕµØ");
	}

	@Override
	public boolean event(Player p) {
		return super.event(p);
	}
}
