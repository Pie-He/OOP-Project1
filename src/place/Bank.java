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
			IO.printString(Const.CASH_NOT_ENOUGH);
			return true;
		}
		if (!p.addDeposit(money)) {
			IO.printString(Const.DEPO_NOT_ENOUGH);
			return true;
		}
		IO.printString(Const.SUCCESS);
		return true;
	}

}
