package type;

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

	public boolean use() {
		switch (this) {
		case roadBlock:
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

	public String toText() {
		return name;
	}
}
