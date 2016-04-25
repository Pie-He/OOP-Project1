package place;

import type.Player;
import util.*;

public class Bank extends Place {
	public Bank() {

	}

	public Bank(int x, int y, String symbol) {
		super(x, y, symbol, "����");
	}

	@Override
	public void event(Player p) {
		super.event(p);
		Output.printString("�����ֽ�:" + p.getCash() + " �������д��:" + p.getDeposit());
		int money = Output.getSaveOrDrawMoney();// ������Ǯ ����ȡǮ
		if (money == 0)
			return;
		if (!p.addCash(-money)) {
			Output.printString("�ֽ���");
			return;
		}
		if (!p.addDeposit(money)) {
			Output.printString("�������");
			return;
		}
		Output.printString("��/ȡ��ɹ�");
	}

}
