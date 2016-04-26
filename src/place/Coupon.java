package place;

import type.Player;
import util.IO;

public class Coupon extends Place{
	public Coupon(){
		
	}
	public Coupon(int x, int y, String symbol) {
		super(x,y,symbol,"赠送点券点");
	}
	@Override
	public boolean event(Player p) {
		super.event(p);
		int coupon=((int)(Math.random()*20)*5+5);
		IO.printString("恭喜！获得 "+coupon+"点券！");
		p.addCoupon(coupon);
		return true;
	}
}
