package type.place;

import type.Prop;
import type.item.Player;
import util.Const;
import util.IO;

public class Shop extends Place{
	public Shop(){
		
	}
	public Shop(int x, int y, String symbol) {
		super(x,y,symbol,"…ÃµÍ");
	}
	@Override
	public boolean event(Player p) {
		super.event(p);
		while(true){
			IO.printString("ƒ˙”–"+p.getCoupon()+"µ„»Ø");
			int choice=IO.getBuyProp();
			if(choice<0)
				break;
			Prop prop=Prop.values()[choice];
			if(p.addCoupon(-prop.getPrice()))
				p.addProp(prop);
			else
				IO.printString(Const.COUPON_NOT_ENOUGH);
		}		
		return true;
	}
}
