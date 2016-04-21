package type;

import java.io.IOException;
import java.util.*;
import util.Output;

public class Manager {
	public static Deque<Player> players;
	/*
	 * static{
	 * player.stream().filter(item->(item.getCash()==10000)).findFirst(); }
	 */
	public static Map map = new Map(39, 19);

	public void start() throws IOException {
		int playerNum = Output.getPlayerNumber();
		players = Output.getPlayerName(playerNum);
		Output.getReady();
		/*
		 * // Player player = Manager.players.pop(); //Player player = new
		 * Player(); players.add(player); player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.averageRichCard);
		 * player.addProp(Prop.remoteBoson);
		 */
		map.init(players);
		for(int i=0;i<20;i++)
			players.getFirst().addProp(Prop.roadBlock);
		for (int i = 0; i < 100; i++) {
			players.stream().forEach(p -> {
				
				this.event(p);
			});
		}
		Output.inputClose();
	}

	private boolean event(Player player) {
		Output.printString(player.getName()+"行动回合。");
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
				while ((propChoice = Output.getProp(player.propToText())) >= 0) {
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
				int dice = this.randomDice();
				Output.printString("投掷点数:" + dice);
				map.event(player, dice);
				Output.printStringArray2(map.toText());
				return true;
			case 7:
				player.fail();
				return false;
			case 8:
				break;
			}
		}
	}

	private int randomDice() {
		return (int) (Math.random() * 6) + 1;
	}

	public static void fail(Player p) {
		// TODO Auto-generated method stub

	}
}
