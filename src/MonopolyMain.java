import java.io.IOException;
import java.util.*;

import type.Player;
import util.*;

;

public class MonopolyMain {

	public static void main(String[] args) throws IOException {
		int playerNum = Output.getPlayerNumber();
		Output.getPlayerName(playerNum);
		Output.getReady();
		MainMenu.mainMenu();
		Output.inputClose();
	}
}
