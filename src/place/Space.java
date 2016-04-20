package place;

import type.Player;

public class Space extends Place{
	public Space(){
		
	}
	public Space(int x, int y, String symbol) {
		super(x,y,symbol,"ø’µÿ");
	}
	@Override
	public void event(Player p) {
		super.event(p);		
	}
	@Override
	public String getDescription() {
		return "¿‡–Õ:"+getType()+"\n";
	}
}
