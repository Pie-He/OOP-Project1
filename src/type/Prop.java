package type;

public enum Prop {
	roadBlock("路障"), remoteBoson("遥控骰子"), reverseCard("转向卡"), stopCard("滞留卡"), taxInspectionCard(
			"查税卡"), averageRichCard("均富卡"), plunderCard("掠夺卡");
	private String name;

	Prop(String name) {
		this.name = name;
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
