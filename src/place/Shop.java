package place;

import type.Player;
import type.Prop;
import util.IO;

public class Shop extends Place{
	public Shop(){
		
	}
	public Shop(int x, int y, String symbol) {
		super(x,y,symbol,"商店");
	}
	@Override
	public boolean event(Player p) {
		super.event(p);
		while(true){
			IO.printString("您有"+p.getCoupon()+"点券");
			int choice=IO.getBuyProp();
			if(choice<0)
				break;
			Prop prop=Prop.values()[choice];
			if(p.addCoupon(-prop.getPrice()))
				p.addProp(prop);
			else
				IO.printString("点券不足");
		}		
		return true;
	}
}
