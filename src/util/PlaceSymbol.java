package util;

public enum PlaceSymbol {
	House("��");
	private String symbol;
	private PlaceSymbol(String symbol){
		this.symbol=symbol;
	}
	public String getSymbol() {
		return symbol;
	}
}
