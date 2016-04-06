package place;

public abstract class Place {
	private int x;
	private int y;
	private String symbol;

	Place() {

	}

	Place(int x, int y, String symbol) {
		this.x = x;
		this.y = y;
		this.setSymbol(symbol);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
