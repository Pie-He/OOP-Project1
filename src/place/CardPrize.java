package place;

import type.*;
import util.Output;

public class CardPrize extends Place{
	public CardPrize(){
		
	}
	public CardPrize(int x, int y, String symbol) {
		super(x,y,symbol,"赠送道具店");
	}
	@Override
	public void event(Player p) {
		super.event(p);
		int random=(int) (Math.random()*Prop.values().length);
		Prop prop=Prop.values()[random];
		Output.printString("恭喜！获得“"+prop+"”1个！");
		p.addProp(prop);
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "类型"+getType()+"\n";
	}
}
