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
	public void event(Player p) {
		super.event(p);
		Output.printString("您的现金:" + p.getCash() + " 您的银行存款:"
				+ p.getDeposit());
		int money = Output.getSaveOrDrawMoney();// 正：存钱 负：取钱
		if (!p.addCash(-money)) {
			Output.printString("现金不足");
			return;
		}
		if (!p.addDeposit(money)) {
			Output.printString("存款余额不足");
			return;
		}
		Output.printString("存/取款成功");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "类型" + getType() + "\n";
	}
}
