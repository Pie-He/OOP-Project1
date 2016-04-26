package place;

import type.Player;
import util.*;

public class Bank extends Place {
	public Bank() {

	}

	public Bank(int x, int y, String symbol) {
		super(x, y, symbol, "银行");
	}

	@Override
	public boolean event(Player p) {
		super.event(p);
		IO.printString("您的现金:" + p.getCash() + " 您的银行存款:" + p.getDeposit());
		int money = IO.getSaveOrDrawMoney();// 正：存钱 负：取钱
		if (money == 0)
			return true;
		if (!p.addCash(-money)) {
			IO.printString("现金不足");
			return true;
		}
		if (!p.addDeposit(money)) {
			IO.printString("存款余额不足");
			return true;
		}
		IO.printString("存/取款成功");
		return true;
	}

}
