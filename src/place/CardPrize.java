package place;

import type.*;
import util.Output;

public class CardPrize extends Place{
	public CardPrize(){
		
	}
	public CardPrize(int x, int y, String symbol) {
		super(x,y,symbol,"���͵��ߵ�");
	}
	@Override
	public void event(Player p) {
		super.event(p);
		int random=(int) (Math.random()*Prop.values().length);
		Prop prop=Prop.values()[random];
		Output.printString("��ϲ����á�"+prop+"��1����");
		p.addProp(prop);
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "����"+getType()+"\n";
	}
}
