package type;

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
