package type;

import util.Output;

public enum Prop {
	roadBlock("·��", 15), remoteBoson("ң������", 30), reverseCard("ת��", 15), stopCard(
			"������", 20), taxInspectionCard("��˰��", 20), averageRichCard("������", 50), plunderCard(
			"�ӶῨ", 30);
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
		case stopCard:
		case taxInspectionCard:
		case averageRichCard:
		case plunderCard:
		default:
			return false;
		}
	}

	private boolean useRemoteBoson(Player p) {
		int dice = Output.getDice();
		if (dice < 1)
			return false;
		Manager.DiceFlag = dice;
		return true;
	}

	private boolean useRoadBlock(Player p) {
		int dis = Output.getDistanceChoice("������ǰ��8���ڵ�����(������ʾ��,x-ȡ��)", -8, 8);
		if (dis > 8)
			return false;
		int poi = p.getPrePoi(dis);
		if (!Manager.map.addBlock(new RoadBlock(poi))) {
			Output.printString("��λ���ѷ���·��");
			return false;
		}
		return true;
	}

	public String toText() {
		return name;
	}

}
