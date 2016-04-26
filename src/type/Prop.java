package type;

import java.util.LinkedList;

import util.Output;

public enum Prop {
	roadBlock("路障", 15), remoteBoson("遥控骰子", 30), reverseCard("转向卡", 15), stopCard(
			"滞留卡", 20), taxInspectionCard("查税卡", 20), averageRichCard("均富卡", 80), plunderCard(
			"掠夺卡", 30);
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
			Output.printString(aim.getName() + "无道具！！！");
			return false;
		}
		int random = (int) (Math.random() * aim.getpropNum());
		Prop prop = aim.removeProp(random);
		p.addProp(prop);
		Output.printString(aim.getName() + "失去一张" + prop.name);
		Output.printString(p.getName() + "获得一张" + prop.name);
		return true;
	}

	private boolean useAverageRichCard(Player p) {
		Output.printString("所有人现金平分使用");
		int all = Manager.players.stream().mapToInt(i -> i.getCash()).sum();
		Manager.players.stream().forEach(
				i -> i.setCash(all / Manager.players.size()));
		return false;
	}

	private boolean useTaxInspectionCard(Player p) {
		Player aim;
		if ((aim = getChoosePlayer(p, 5, false)) == null)
			return false;
		Output.printString(aim.getName() + "缴交30%存款");
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
		Output.printString("对" + aim.getName() + "使用了转向卡");
		aim.reverse();
		return true;
	}

	private boolean useRemoteBoson(Player p) {
		int dice = Output.getDice();
		if (dice > 6)
			return false;
		Manager.diceFlag = dice;
		return true;
	}

	private boolean useRoadBlock(Player p) {
		int dis = Output.getDistanceChoice("请输入前后方8格内的数字(负数表示后方,x-取消)", -8, 8);
		if (dis > 8)
			return false;
		int poi = p.getPrePoi(dis);
		if (!Map.getInstance().addBlock(new RoadBlock(poi))) {
			Output.printString("该位置已放置路障");
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
		int choice = Output.getChoosePlayer(strs);
		if (choice >= l.size()) {
			return null;
		}
		return l.get(choice);
	}

	public String toText() {
		return name;
	}

}
