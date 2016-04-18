package place;

import type.*;
import util.Output;

public class CardPrize extends Place{
	public CardPrize(){
		
	}
	public CardPrize(int x, int y, String symbol) {
		super(x,y,symbol);
	}
	@Override
	public void event(Player p) {
		// TODO Auto-generated method stub
		int random=(int) (Math.random()*Prop.values().length);
		Prop prop=Prop.values()[random];
		Output.printString("¹§Ï²£¡»ñµÃ¡°"+prop+"¡±1¸ö£¡");
		p.addProp(prop);
	}
}
