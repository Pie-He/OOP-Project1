import java.io.IOException;

import type.Manager;

public class MonopolyMain {

	public static void main(String[] args) throws IOException {
		Manager.getInstance().start();
	}
}
