package place;

import type.Player;

public class Lottery extends Place{
	public Lottery(){
		
	}
	public Lottery(int x, int y, String symbol) {
		super(x,y,symbol,"��Ʊ");
	}
	@Override
	public void event(Player p) {
		super.event(p);
	}
	@Override
	public String getDescription() {
		return "����"+getType()+"\n";
	}
}
