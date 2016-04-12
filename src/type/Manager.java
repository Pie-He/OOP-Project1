package type;

import java.util.*;

import place.Place;
import type.*;
import util.Output;

public class Manager {
	public static Deque<Player> players = new LinkedList<Player>();
	/*
	 * static{
	 * player.stream().filter(item->(item.getCash()==10000)).findFirst(); }
	 */
	public static Map map = new Map(39, 19);

	public void start() {
		// int playerNum = Output.getPlayerNumber();
		// Output.getPlayerName(playerNum);
		// Output.getReady();
		// Player player = Manager.players.pop();
		Player player = new Player();
		players.add(player);
		player.addProp(Prop.averageRichCard);
		player.addProp(Prop.averageRichCard);
		player.addProp(Prop.averageRichCard);
		player.addProp(Prop.remoteBoson);
		this.event(player);
		Output.inputClose();
	}

	private void event(Player player) {
		while (true) {
			int choice = Output.getMenuChoice();
			switch (choice) {
			case 0:
				Output.printStringArray2(map.toText());
				break;
			case 1:
				Output.printStringArray2(map.getInitalMap());
				break;
			case 2:
				int propChoice;
				if ((propChoice = Output.getProp(player.propToText())) >= 0) {
					player.useProp(propChoice);
				}
				break;
			case 3:

			case 4:

			case 5:
				Output.printString("玩家名\t\t点券\t\t现金\t\t存款\t\t房产\t\t资产总额");
				for (Player p : players)
					Output.printString(p.getMessage());
				break;
			case 6:
				
			case 7:
				player.fail();
				break;
			}
		}
	}

}
