package util;

import java.util.*;

import place.Place;
import type.*;

public class Manager {
	public static Collection<Player> player = new LinkedList<Player>();
	/*
	 * static{
	 * player.stream().filter(item->(item.getCash()==10000)).findFirst(); }
	 */
	public static Map map = new Map(39, 19);

	public void start() {
		// int playerNum = Output.getPlayerNumber();
		// Output.getPlayerName(playerNum);
		// Output.getReady();
		int choice = Output.getMenuChoice();
		this.event(choice);
		Output.inputClose();
	}

	private void event(int choice) {
		switch (choice) {
		case 0:
			Output.printString(map.toText());
			break;
		case 1:
			Output.printString(map.getInitalMap());
			break;
		case 2:

		case 3:

		case 4:

		case 5:

		case 6:
			
		case 7:

		}
	}

}
