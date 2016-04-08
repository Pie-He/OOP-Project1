package util;

import java.util.*;

import place.Place;
import type.*;

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
		//Player player = Manager.players.pop();
		Player player=new Player();
		player.addProp(Prop.averageRichCard);
		player.addProp(Prop.averageRichCard);
		player.addProp(Prop.averageRichCard);
		player.addProp(Prop.remoteBoson);
		int choice = Output.getMenuChoice();
		this.event(choice, player);
		Output.inputClose();
	}

	private void event(int choice, Player player) {
		switch (choice) {
		case 0:
			Output.printStringArray2(map.toText());
			break;
		case 1:
			Output.printStringArray2(map.getInitalMap());
			break;
		case 2:
			Output.getProp(player.propToText());
			break;
		case 3:

		case 4:

		case 5:

		case 6:

		case 7:

		}
	}

}
