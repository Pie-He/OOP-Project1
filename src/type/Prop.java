package type;

import util.Output;

public enum Prop {
	roadBlock("路障", 15), remoteBoson("遥控骰子", 30), reverseCard("转向卡", 15), stopCard(
			"滞留卡", 20), taxInspectionCard("查税卡", 20), averageRichCard("均富卡", 50), plunderCard(
			"掠夺卡", 30);
	private String name;
	private int price;

	Prop(String name, int price) {
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
		return false;
	}

	private boolean useAverageRichCard(Player p) {
		return false;
	}

	private boolean useTaxInspectionCard(Player p) {
		return false;
	}

	private boolean useStopCard(Player p) {
		Manager.DiceFlag=0;
		return true;
	}

	private boolean useReverseCard(Player p) {
		p.reverse();
		return true;
	}

	private boolean useRemoteBoson(Player p) {
		int dice = Output.getDice();
		if (dice < 1)
			return false;
		Manager.DiceFlag = dice;
		return true;
	}

	private boolean useRoadBlock(Player p) {
		int dis = Output.getDistanceChoice("请输入前后方8格内的数字(负数表示后方,x-取消)", -8, 8);
		if (dis > 8)
			return false;
		int poi = p.getPrePoi(dis);
		if (!Manager.map.addBlock(new RoadBlock(poi))) {
			Output.printString("该位置已放置路障");
			return false;
		}
		return true;
	}

	public String toText() {
		return name;
	}

}
