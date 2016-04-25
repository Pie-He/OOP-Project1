package place;

import type.Player;

public class Space extends Place{
	public Space(){
		
	}
	public Space(int x, int y, String symbol) {
		super(x,y,symbol,"¿ÕµØ");
	}
	@Override
	public void event(Player p) {
		super.event(p);		
	}
}
