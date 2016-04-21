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
			this.useRoadBlock(p);
			break;
		case remoteBoson:
		case reverseCard:
		case stopCard:
		case taxInspectionCard:
		case averageRichCard:
		case plunderCard:
		default:
			break;
		}
		return true;
	}

	private void useRoadBlock(Player p) {
		int dis=Output.getPoiChoice();
		int poi=p.getPoi()+dis;
		if(!Manager.map.addBlock(new RoadBlock(poi))){
			Output.printString("��λ���ѷ���·��");
		}
	}

	public String toText() {
		return name;
	}
}
