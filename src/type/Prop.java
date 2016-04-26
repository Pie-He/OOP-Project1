package type;

import java.util.LinkedList;

import util.Output;

public enum Prop {
	roadBlock("·��", 15), remoteBoson("ң������", 30), reverseCard("ת��", 15), stopCard(
			"������", 20), taxInspectionCard("��˰��", 20), averageRichCard("������", 80), plunderCard(
			"�ӶῨ", 30);
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
			Output.printString(aim.getName() + "�޵��ߣ�����");
			return false;
		}
		int random = (int) (Math.random() * aim.getpropNum());
		Prop prop = aim.removeProp(random);
		p.addProp(prop);
		Output.printString(aim.getName() + "ʧȥһ��" + prop.name);
		Output.printString(p.getName() + "���һ��" + prop.name);
		return true;
	}

	private boolean useAverageRichCard(Player p) {
		Output.printString("�������ֽ�ƽ��ʹ��");
		int all = Manager.players.stream().mapToInt(i -> i.getCash()).sum();
		Manager.players.stream().forEach(
				i -> i.setCash(all / Manager.players.size()));
		return false;
	}

	private boolean useTaxInspectionCard(Player p) {
		Player aim;
		if ((aim = getChoosePlayer(p, 5, false)) == null)
			return false;
		Output.printString(aim.getName() + "�ɽ�30%���");
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
		Output.printString("��" + aim.getName() + "ʹ����ת��");
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
		int dis = Output.getDistanceChoice("������ǰ��8���ڵ�����(������ʾ��,x-ȡ��)", -8, 8);
		if (dis > 8)
			return false;
		int poi = p.getPrePoi(dis);
		if (!Map.getInstance().addBlock(new RoadBlock(poi))) {
			Output.printString("��λ���ѷ���·��");
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
