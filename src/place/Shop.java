package place;

import type.Player;
import type.Prop;
import util.Output;

public class Shop extends Place{
	public Shop(){
		
	}
	public Shop(int x, int y, String symbol) {
		super(x,y,symbol,"商店");
	}
	@Override
	public void event(Player p) {
		// TODO Auto-generated method stub
		while(true){
			int choice=Output.getBuyProp();
			if(choice<0)
				break;
			Prop prop=Prop.values()[choice];
			if(p.addCoupon(-prop.getPrice()))
				p.addProp(prop);
			else
				Output.printString("点券不足");
		}		
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "类型"+getType()+"\n";
	}
}
