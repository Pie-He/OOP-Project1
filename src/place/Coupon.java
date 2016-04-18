package place;

import type.Player;
import util.Output;

public class Coupon extends Place{
	public Coupon(){
		
	}
	public Coupon(int x, int y, String symbol) {
		super(x,y,symbol,"���͵�ȯ��");
	}
	@Override
	public void event(Player p) {
		// TODO Auto-generated method stub
		int coupon=((int)(Math.random()*6)*5+5);
		Output.printString("��ϲ����� "+coupon+"��ȯ��");
		p.addCoupon(coupon);
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
