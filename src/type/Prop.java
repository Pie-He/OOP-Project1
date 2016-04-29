package type;

import java.util.LinkedList;

import util.Const;
import util.IO;

public enum Prop {
	roadBlock("路障", 15), remoteBoson("遥控骰子", 30), reverseCard("转向卡", 15), stopCard(
			"滞留卡", 20), taxInspectionCard("查税卡", 20), averageRichCard("均富卡", 80), plunderCard(
			"掠夺卡", 20);
	private String name;
	private int price;

	private Prop(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public boolean use(Player p) {
		switch (this) {
		case roadBlock:
			return this.useRoadBlock(p);
		case remoteBoson:
			return this.useRemoteBoson(p);
		case reverseCard:
			return this.useReverseCard(p);
		case stopCard:
			return this.useStopCard(p);
		case taxInspectionCard:
			return this.useTaxInspectionCard(p);
		case averageRichCard:
			return this.useAverageRichCard(p);
		case plunderCard:
			return this.usePlunderCard(p);
		default:
			return false;
		}
	}

	private boolean usePlunderCard(Player p) {
		Player aim;
		if ((aim = getChoosePlayer(p, 5, false)) == null)
			return false;
		if (aim.getpropNum() <= 0) {
			IO.printString(aim.getName() + Const.PROP_NO_PROP);
			return false;
		}
		int random = (int) (Math.random() * aim.getpropNum());
		Prop prop = aim.removeProp(random);
		p.addProp(prop);
		IO.printString(aim.getName() + "失去一张" + prop.name);
		IO.printString(p.getName() + "获得一张" + prop.name);
		return true;
	}

	private boolean useAverageRichCard(Player p) {
		IO.printString(Const.PROP_AVERAGE);
		int all = Manager.players.stream().mapToInt(i -> i.getCash()).sum();
		Manager.players.stream().forEach(
				i -> i.setCash(all / Manager.players.size()));
		return true;
	}

	private boolean useTaxInspectionCard(Player p) {
		Player aim;
		if ((aim = getChoosePlayer(p, 5, false)) == null)
			return false;
		IO.printString(aim.getName() + Const.PROP_DEPO_FINE);
		aim.addDeposit(-aim.getDeposit() * 3 / 10);
		return true;
	}

	private boolean useStopCard(Player p) {
		Manager.diceFlag = 0;
		return true;
	}

	private boolean useReverseCard(Player p) {
		Player aim;
		if ((aim = getChoosePlayer(p, 5, true)) == null)
			return false;
		IO.printString("对" + aim.getName() + "使用了转向卡");
		aim.reverse();
		return true;
	}

	private boolean useRemoteBoson(Player p) {
		int dice = IO.getDice();
		if (dice > 6)
			return false;
		Manager.diceFlag = dice;
		return true;
	}

	private boolean useRoadBlock(Player p) {
		int dis = IO.getDistanceChoice(Const.PROP_CHOOSE.toString(), -8, 8);
		if (dis > 8)
			return false;
		int poi = p.getPrePoi(dis);
		if (!Map.getInstance().addBlock(new RoadBlock(poi))) {
			IO.printString(Const.BLOCK_EXSITED);
			return false;
		}
		return true;
	}

	private Player getChoosePlayer(Player p, int range, boolean includeSelf) {
		LinkedList<Player> l = new LinkedList<Player>();
		LinkedList<String> strs = new LinkedList<String>();
		Manager.players.stream().filter(i -> p.isInView(i, range))
				.forEach(i -> {
					// if(!includeSelf&&i==p)
					// continue;
						if (includeSelf || i != p) {
							l.add(i);
							strs.add(i.getName());
						}
					});
		int choice = IO.getChoosePlayer(strs);
		if (choice >= l.size()) {
			return null;
		}
		return l.get(choice);
	}

	public String toText() {
		return name;
	}

}
