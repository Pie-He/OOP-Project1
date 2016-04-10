package type;

public enum Prop {
	roadBlock("·��"), remoteBoson("ң������"), reverseCard("ת��"), stopCard("������"), taxInspectionCard(
			"��˰��"), averageRichCard("������"), plunderCard("�ӶῨ");
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
