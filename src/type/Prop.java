package type;

public enum Prop {
	roadBlock("·��"), remoteBoson("ң������"), reverseCard("ת��"), stopCard("������"), taxInspectionCard(
			"��˰��"), averageRichCard("������"), plunderCard("�ӶῨ");
	private String name;
	Prop(String name) {
		this.name=name;
	}

	public void use() {
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
	}

	public String toText() {
		return name;
	}
}
