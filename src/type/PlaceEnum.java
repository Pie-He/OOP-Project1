package type;

import place.*;

public enum PlaceEnum {
	HOUSE(House.class, "¡ò"), COUPON(Coupon.class, "È¯"), BANK(Bank.class, "Òø"), CARDPRIZE(
			CardPrize.class, "¿¨"), LOTTERY(Lottery.class, "²Ê"), NEWS(
			News.class, "ÐÂ"), SHOP(Shop.class, "µÀ"), SPACE(Space.class, "¿Õ");

	private Class<? extends Place> c;
	private String symbol;

	PlaceEnum(Class<? extends Place> c, String symbol) {
		this.c = c;
		this.symbol = symbol;
	}

	public Class<? extends Place> getRealClass() {
		return c;
	}

	public String getSymbol() {
		return symbol;
	}

}
